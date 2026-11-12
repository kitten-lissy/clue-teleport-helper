package com.clueteleporthelper;

import net.runelite.api.coords.WorldPoint;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.Assert.*;

public class TeleportRankerTest {

    private static final Predicate<TeleportData> ALL = tp -> true;
    private static final Predicate<TeleportData> NO_WILDERNESS = tp -> !tp.isWilderness();

    private static List<TeleportSuggestion> rank(WorldPoint target, int count, boolean combine,
                                                 Predicate<TeleportData> allowed) {
        return new TeleportRanker(count, combine, allowed, ALL).rank(target, Collections.emptyList(), true);
    }

    private static boolean contains(List<TeleportSuggestion> lines, TeleportData tp) {
        return lines.stream().anyMatch(l -> l.getTeleports().contains(tp));
    }

    @Test
    public void returnsRequestedCountClosestFirst() {
        WorldPoint target = new WorldPoint(3213, 3424, 0); // Varrock square
        List<TeleportSuggestion> lines = rank(target, 5, true, ALL);
        assertEquals(5, lines.size());
        assertEquals(TeleportData.VARROCK, lines.get(0).getPrimary());
        for (int i = 1; i < lines.size(); i++) {
            assertTrue(lines.get(i - 1).getDistance() <= lines.get(i).getDistance());
        }
        assertEquals(1, rank(target, 1, true, ALL).size());
        assertEquals(10, rank(target, 10, true, ALL).size());
    }

    @Test
    public void sameSpotTeleportsShareOneLine() {
        WorldPoint target = new WorldPoint(2520, 3571, 0); // Barbarian Assault
        List<TeleportSuggestion> lines = rank(target, 5, true, ALL);
        TeleportSuggestion first = lines.get(0);
        assertTrue(first.getTeleports().contains(TeleportData.BARBARIAN_ASSAULT));
        assertTrue(first.getTeleports().contains(TeleportData.MINIGAME_BARBARIAN_ASSAULT));
        assertEquals("Games Neck / Minigame", first.getMethodLabel());

        // Each destination appears once.
        long lines_with_ba = lines.stream()
                .filter(l -> l.getTeleports().contains(TeleportData.BARBARIAN_ASSAULT)
                        || l.getTeleports().contains(TeleportData.MINIGAME_BARBARIAN_ASSAULT))
                .count();
        assertEquals(1, lines_with_ba);
    }

    @Test
    public void combiningCanBeTurnedOff() {
        WorldPoint target = new WorldPoint(2520, 3571, 0);
        List<TeleportSuggestion> lines = rank(target, 5, false, ALL);
        for (TeleportSuggestion line : lines) {
            assertEquals(1, line.getTeleports().size());
        }
        assertTrue(contains(lines, TeleportData.BARBARIAN_ASSAULT));
        assertTrue(contains(lines, TeleportData.MINIGAME_BARBARIAN_ASSAULT));
    }

    @Test
    public void wildernessTeleportsCanBeAvoided() {
        WorldPoint target = new WorldPoint(3234, 3634, 0); // Chaos Temple, level 15 Wilderness
        assertTrue(contains(rank(target, 5, true, ALL), TeleportData.CHAOS_TEMPLE));

        List<TeleportSuggestion> safe = rank(target, 5, true, NO_WILDERNESS);
        assertEquals(5, safe.size());
        for (TeleportSuggestion line : safe) {
            for (TeleportData tp : line.getTeleports()) {
                assertFalse(tp + " is in the Wilderness", tp.isWilderness());
            }
        }
        // Ferox Enclave is a safe zone and stays available.
        assertTrue(contains(safe, TeleportData.FEROX_ENCLAVE));
    }

    @Test
    public void handPickedTeleportsComeFirstInOrder() {
        WorldPoint target = new WorldPoint(3213, 3424, 0);
        List<TeleportData> preferred = Arrays.asList(TeleportData.DIGSITE, TeleportData.EDGEVILLE);
        List<TeleportSuggestion> lines = new TeleportRanker(5, true, ALL, ALL).rank(target, preferred, true);
        assertEquals(TeleportData.DIGSITE, lines.get(0).getPrimary());
        assertEquals(TeleportData.EDGEVILLE, lines.get(1).getPrimary());
        assertEquals(5, lines.size());
    }

    @Test
    public void handPickedTeleportsRespectFilters() {
        List<TeleportData> preferred = Arrays.asList(TeleportData.LAVA_MAZE, TeleportData.EDGEVILLE);
        List<TeleportSuggestion> lines = new TeleportRanker(5, true, NO_WILDERNESS, ALL)
                .rank(null, preferred, false);
        assertEquals(1, lines.size());
        assertEquals(TeleportData.EDGEVILLE, lines.get(0).getPrimary());
        assertNull("No tile, so no distance", lines.get(0).getDistance());
    }

    @Test
    public void unusableTeleportsAreMarkedAndAUsableTwinLeads() {
        WorldPoint target = new WorldPoint(2520, 3571, 0);
        Predicate<TeleportData> onlyMinigame = tp -> tp != TeleportData.BARBARIAN_ASSAULT;
        List<TeleportSuggestion> lines = new TeleportRanker(5, true, ALL, onlyMinigame)
                .rank(target, Collections.emptyList(), true);
        TeleportSuggestion first = lines.get(0);
        assertTrue(first.isUsable());
        assertEquals(TeleportData.MINIGAME_BARBARIAN_ASSAULT, first.getPrimary());

        Predicate<TeleportData> none = tp -> false;
        List<TeleportSuggestion> grey = new TeleportRanker(3, true, ALL, none)
                .rank(target, Collections.emptyList(), true);
        assertFalse(grey.get(0).isUsable());
    }

    @Test
    public void noItemTeleportsCanBeLeftOut() {
        WorldPoint target = new WorldPoint(3131, 3496, 0); // Edgeville fairy ring
        Predicate<TeleportData> noRings = tp -> tp.getMethod() != TeleportData.Method.FAIRY_RING;
        List<TeleportSuggestion> lines = rank(target, 10, true, noRings);
        for (TeleportSuggestion line : lines) {
            for (TeleportData tp : line.getTeleports()) {
                assertNotEquals(TeleportData.Method.FAIRY_RING, tp.getMethod());
            }
        }
    }

    @Test
    public void distanceUsesGroundAboveDungeonsAndHidesInstances() {
        // A dungeon tile is measured from the ground above it (West Varrock bank basement)
        Integer basement = TeleportRanker.distance(TeleportData.VARROCK, new WorldPoint(3187, 9825, 0));
        assertNotNull(basement);
        assertTrue(basement < 40);
        // Instanced areas have no ground above them, so no distance is shown
        assertNull(TeleportRanker.distance(TeleportData.EDGEVILLE, new WorldPoint(1863, 4639, 0)));
        assertEquals(Integer.valueOf(0), TeleportRanker.distance(TeleportData.VARROCK, new WorldPoint(3213, 3424, 0)));
        assertNull(TeleportRanker.distance(TeleportData.VARROCK, null));
    }

    @Test
    public void stepsGetOneLineEach() {
        List<WorldPoint> steps = Arrays.asList(new WorldPoint(3213, 3424, 0), new WorldPoint(2840, 3350, 0));
        List<TeleportSuggestion> lines = new TeleportRanker(5, true, ALL, ALL)
                .rankSteps(steps, Arrays.asList("Step 1", "Step 3"),
                        t -> {
                            LocationOverrides.OverrideResult o = LocationOverrides.getOverride(t);
                            return o == null ? null : o.teleports;
                        });
        assertEquals(2, lines.size());
        assertEquals("Step 1", lines.get(0).getLabel());
        assertEquals(TeleportData.VARROCK, lines.get(0).getPrimary());
        assertEquals("Step 3", lines.get(1).getLabel());
        // Entrana uses its hand-picked override.
        assertEquals(TeleportData.DRAYNOR_VILLAGE, lines.get(1).getPrimary());
    }
}
