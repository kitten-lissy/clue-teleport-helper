package com.clueteleporthelper;

import net.runelite.api.coords.WorldPoint;
import net.runelite.client.plugins.cluescrolls.clues.CoordinateClue;
import net.runelite.client.plugins.cluescrolls.clues.CrypticClue;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Detection paths that use RuneLite's own clue classes through public getters only.
 */
public class ClueDetectionTest {

    // ---- H5: Prifddinas coordinate clues are one dig spot, not a multi-step clue ----

    @Test
    public void prifddinasCoordinateClueIsSingleStep() {
        // RuneLite stores Prifddinas dig spots in region 8755 and mirrors them to region 12894
        // (WorldPoint.REGION_MIRRORS), so getLocations returns two points for one spot.
        WorldPoint prif = new WorldPoint(2200, 3290, 0);
        CoordinateClue clue = CoordinateClue.builder()
                .itemId(0)
                .directions("test")
                .location(prif)
                .build();

        // Precondition: RuneLite reports the dig spot and its mirror point.
        assertEquals(2, clue.getLocations(null).length);

        assertFalse(ClueTeleportHelperPlugin.isMultiStep(clue));
        assertEquals(prif, ClueTeleportHelperPlugin.clueLocation(clue));

        // The single spot then gets the hand-picked Prifddinas route.
        LocationOverrides.OverrideResult override = LocationOverrides.getOverride(prif);
        assertNotNull(override);
        assertTrue(override.teleports.contains(TeleportData.PRIFDDINAS));
    }

    @Test
    public void overworldCoordinateClueIsSingleStep() {
        WorldPoint spot = new WorldPoint(3088, 3469, 0);
        CoordinateClue clue = CoordinateClue.builder()
                .itemId(0)
                .directions("test")
                .location(spot)
                .build();
        assertFalse(ClueTeleportHelperPlugin.isMultiStep(clue));
        assertEquals(spot, ClueTeleportHelperPlugin.clueLocation(clue));
    }

    // ---- H2: kill tasks are found from CrypticClue.getText() ----

    private static final String[] KILL_TASK_TEXTS = {
            "In a while...",
            "Ghostly bones.",
            "A demon's best friend holds the next step of this clue.",
            "You'll get licked.",
            "Reflection is the weakness for these eyes of evil.",
            "Let's hope you don't meet a watery death when you encounter this fiend.",
            "You will have to fly high where a sword cannot help you.",
            "Mix yellow with blue and add heat, make sure you bring protection.",
            "You'll have to plug your nose if you use this source of herbs.",
            "You might have to turn over a few stones to progress.",
            "Banish a chilling spirit.",
            "Crush the spirited earth.",
    };

    @Test
    public void everyKillTaskMatchesARuneLiteClue() {
        for (String text : KILL_TASK_TEXTS) {
            CrypticClue clue = CrypticClue.forText(text);
            assertNotNull("RuneLite has no cryptic clue: " + text, clue);
            assertNotNull("No kill task for: " + text, KillTaskData.getKillTask(clue.getText()));
        }
    }

    @Test
    public void ordinaryCrypticIsNotAKillTask() {
        assertNull(KillTaskData.getKillTask("Search the crates in East Ardougne's general store."));
        assertNull(KillTaskData.getKillTask(null));
    }

    // ---- M7: Viggora, whose location RuneLite reads from a varbit ----

    @Test
    public void viggoraIsRecognisedAndMapped() {
        CrypticClue clue = CrypticClue.forText(
                "Come brave adventurer, your sense is on fire. If you talk to me, it's an old god you desire.");
        assertNotNull(clue);
        assertTrue(VariableLocationData.isViggora(clue.getText()));

        assertEquals(new WorldPoint(3295, 3934, 1), VariableLocationData.viggoraLocation(1));
        assertEquals(new WorldPoint(3447, 3547, 1), VariableLocationData.viggoraLocation(2));
        assertEquals(new WorldPoint(3121, 9995, 0), VariableLocationData.viggoraLocation(3));
        assertNull(VariableLocationData.viggoraLocation(0));
        assertNull(VariableLocationData.viggoraNote(0));
    }

    @Test
    public void viggoraLocationCannotBeReadWithoutTheCluePlugin() {
        CrypticClue clue = CrypticClue.forText(
                "Come brave adventurer, your sense is on fire. If you talk to me, it's an old god you desire.");
        WorldPoint loc;
        try {
            loc = ClueTeleportHelperPlugin.clueLocation(clue);
        } catch (RuntimeException e) {
            loc = null;
        }
        // The plugin then falls back to its own varbit table.
        assertNull(loc);
    }

    // ---- bank cache storage ----

    @Test
    public void storedIdsRoundTrip() {
        java.util.Set<Integer> ids = new java.util.HashSet<>(java.util.Arrays.asList(1712, 563, 8007));
        String stored = ClueTeleportHelperPlugin.formatIds(ids);
        assertEquals("563,1712,8007", stored);
        assertEquals(ids, ClueTeleportHelperPlugin.parseIds(stored));
        assertTrue(ClueTeleportHelperPlugin.parseIds(null).isEmpty());
        assertTrue(ClueTeleportHelperPlugin.parseIds("").isEmpty());
        assertEquals(java.util.Collections.singleton(5), ClueTeleportHelperPlugin.parseIds("x,5"));
    }
}
