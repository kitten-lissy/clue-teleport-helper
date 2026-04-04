package com.clueteleporthelper;

import java.util.*;

/**
 * Data for Sherlock/Charlie skill challenge clue steps.
 * These clues don't expose a location, so we match by challenge text
 * and provide teleport suggestions to complete the task.
 */
public class SkillChallengeData {

    private static final List<SkillChallenge> CHALLENGES = new ArrayList<>();

    static {
        // Sherlock challenges (elite)
        CHALLENGES.add(new SkillChallenge(
                "Cut a yew log",
                "Yew trees — Woodcutting Guild, south of Falador, Seers' Village",
                Arrays.asList(TeleportData.WOODCUTTING_GUILD, TeleportData.FALADOR, TeleportData.CAMELOT)
        ));

        // TODO: Add remaining Sherlock challenges
        // CHALLENGES.add(new SkillChallenge("Burn a yew log", ...));
        // CHALLENGES.add(new SkillChallenge("Cook a swordfish", ...));
        // etc.
    }

    /**
     * Check if a challenge text matches a known skill challenge.
     */
    public static SkillChallenge getChallenge(String challengeText) {
        if (challengeText == null) {
            return null;
        }
        String lower = challengeText.toLowerCase();
        for (SkillChallenge sc : CHALLENGES) {
            if (lower.contains(sc.textMatch.toLowerCase())) {
                return sc;
            }
        }
        return null;
    }

    public static class SkillChallenge {
        public final String textMatch;
        public final String locationNote;
        public final List<TeleportData> teleports;

        SkillChallenge(String textMatch, String locationNote, List<TeleportData> teleports) {
            this.textMatch = textMatch;
            this.locationNote = locationNote;
            this.teleports = teleports;
        }
    }
}
