package com.clueteleporthelper;

import net.runelite.api.coords.WorldPoint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

/**
 * Turns a target tile and an optional hand-picked list into the panel lines.
 * Pure logic with no client access, so it is covered by unit tests.
 */
public class TeleportRanker {

    /** Teleports landing this close to each other are shown on one line. */
    static final int SAME_SPOT_TILES = 5;

    /** Map squares at or above this Y are underground or instanced copies of the surface. */
    static final int UNDERGROUND_Y = 4000;

    private final int count;
    private final boolean combine;
    private final Predicate<TeleportData> allowed;
    private final Predicate<TeleportData> usable;

    /**
     * @param count   how many lines to return
     * @param combine merge teleports that land on the same spot into one line
     * @param allowed teleports that may appear at all (toggles, Wilderness, "only owned")
     * @param usable  teleports the player can use; the rest are greyed out
     */
    public TeleportRanker(int count, boolean combine, Predicate<TeleportData> allowed, Predicate<TeleportData> usable) {
        this.count = Math.max(1, count);
        this.combine = combine;
        this.allowed = allowed;
        this.usable = usable;
    }

    /**
     * Rank teleports for one target. Hand-picked teleports come first in their
     * given order, then the closest of the rest fill the remaining lines.
     *
     * @param target    ground-level clue tile, or null when the step has no tile
     * @param preferred hand-picked teleports (override, key clue, kill task), may be empty
     * @param fill      whether to top up with the closest teleports
     */
    public List<TeleportSuggestion> rank(WorldPoint target, List<TeleportData> preferred, boolean fill) {
        List<TeleportData> candidates = new ArrayList<>();
        for (TeleportData tp : preferred) {
            if (allowed.test(tp) && !candidates.contains(tp)) {
                candidates.add(tp);
            }
        }
        if (fill && target != null) {
            Arrays.stream(TeleportData.values())
                    .filter(tp -> tp.getLocation() != null && allowed.test(tp) && !candidates.contains(tp))
                    .sorted(Comparator.comparingInt(tp -> surface(tp.getLocation()).distanceTo(surface(target))))
                    .forEach(candidates::add);
        }

        List<TeleportSuggestion> result = new ArrayList<>();
        for (TeleportData tp : candidates) {
            TeleportSuggestion near = combine ? findSameSpot(result, tp) : null;
            if (near != null) {
                near.addAlternative(tp, usable.test(tp));
            } else if (result.size() < count) {
                result.add(new TeleportSuggestion(tp, distance(tp, target), null, usable.test(tp)));
            }
        }
        return result;
    }

    /**
     * One line per step (three-step cryptic clues): the hand-picked teleport for
     * that spot if there is one, otherwise the closest teleport.
     */
    public List<TeleportSuggestion> rankSteps(List<WorldPoint> stepTargets, List<String> labels,
                                              java.util.function.Function<WorldPoint, List<TeleportData>> preferredFor) {
        List<TeleportSuggestion> result = new ArrayList<>();
        TeleportRanker single = new TeleportRanker(1, combine, allowed, usable);
        for (int i = 0; i < stepTargets.size(); i++) {
            WorldPoint target = stepTargets.get(i);
            List<TeleportData> preferred = preferredFor.apply(target);
            List<TeleportSuggestion> best = single.rank(target, preferred == null ? Collections.emptyList() : preferred, true);
            if (!best.isEmpty()) {
                TeleportSuggestion b = best.get(0);
                TeleportSuggestion labelled = new TeleportSuggestion(b.getPrimary(), b.getDistance(), labels.get(i), b.isUsable());
                for (TeleportData alt : b.getTeleports()) {
                    labelled.addAlternative(alt, usable.test(alt));
                }
                result.add(labelled);
            }
        }
        return result;
    }

    private static TeleportSuggestion findSameSpot(List<TeleportSuggestion> lines, TeleportData tp) {
        if (tp.getLocation() == null) {
            return null;
        }
        for (TeleportSuggestion line : lines) {
            WorldPoint loc = line.getPrimary().getLocation();
            if (loc != null && loc.distanceTo(tp.getLocation()) <= SAME_SPOT_TILES) {
                return line;
            }
        }
        return null;
    }

    /**
     * Straight-line distance, or null when there is no target or when one end is
     * underground or instanced and the other is not (the number would be meaningless).
     */
    static Integer distance(TeleportData tp, WorldPoint target) {
        if (target == null || tp.getLocation() == null) {
            return null;
        }
        WorldPoint from = surface(tp.getLocation());
        WorldPoint to = surface(target);
        boolean targetBelow = to.getY() >= UNDERGROUND_Y;
        boolean teleportBelow = from.getY() >= UNDERGROUND_Y;
        if (targetBelow != teleportBelow) {
            return null;
        }
        return from.distanceTo(to);
    }

    /** Most dungeons and basements sit exactly 6400 tiles north of the ground above them. */
    static final int DUNGEON_MIN_Y = 9000;
    static final int DUNGEON_MAX_Y = 10600;
    static final int DUNGEON_OFFSET = 6400;

    /**
     * The point on the surface above a dungeon or basement tile, so a clue in the
     * Varrock bank basement ranks Varrock teleports first. Other points are returned
     * unchanged (on plane 0, so upper floors compare by position only).
     */
    static WorldPoint surface(WorldPoint p) {
        int y = p.getY();
        if (y >= DUNGEON_MIN_Y && y < DUNGEON_MAX_Y) {
            y -= DUNGEON_OFFSET;
        }
        return new WorldPoint(p.getX(), y, 0);
    }
}
