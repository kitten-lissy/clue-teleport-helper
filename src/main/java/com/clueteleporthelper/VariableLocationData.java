package com.clueteleporthelper;

import net.runelite.api.coords.WorldPoint;

/**
 * Clue steps whose location RuneLite works out from a varbit inside the Clue Scroll
 * plugin. That lookup needs the plugin itself, which other plugins may not access,
 * so the same table is kept here. Values and tiles are copied from RuneLite's
 * CrypticClue (client 1.13.6).
 */
public final class VariableLocationData {

    /** Start of the master cryptic that sends the player to Viggora. */
    static final String VIGGORA_TEXT = "Come brave adventurer, your sense is on fire.";

    /** Varbit RuneLite reads for Viggora's position (1, 2 or 3). */
    public static final int VIGGORA_VARBIT = 815;

    private VariableLocationData() {
    }

    public static boolean isViggora(String clueText) {
        return clueText != null && clueText.startsWith(VIGGORA_TEXT);
    }

    /** Viggora's tile for a varbit value, or null for an unknown value. */
    public static WorldPoint viggoraLocation(int varbitValue) {
        switch (varbitValue) {
            case 1:
                return new WorldPoint(3295, 3934, 1);
            case 2:
                return new WorldPoint(3447, 3547, 1);
            case 3:
                return new WorldPoint(3121, 9995, 0);
            default:
                return null;
        }
    }

    public static String viggoraNote(int varbitValue) {
        switch (varbitValue) {
            case 1:
                return "Viggora is at the Rogues' Castle (deep Wilderness)";
            case 2:
                return "Viggora is in the Slayer Tower";
            case 3:
                return "Viggora is in the Edgeville Dungeon";
            default:
                return null;
        }
    }
}
