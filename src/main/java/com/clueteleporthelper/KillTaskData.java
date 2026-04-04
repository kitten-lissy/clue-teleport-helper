package com.clueteleporthelper;

import java.util.*;

/**
 * Data for cryptic clue steps that require killing a specific monster.
 * These clues don't expose a location, so we match by clue text and
 * provide teleport suggestions to reach the monster.
 */
public class KillTaskData {

    private static final List<KillTask> KILL_TASKS = new ArrayList<>();

    static {
        // "In a while..." → Kill a crocodile
        KILL_TASKS.add(new KillTask(
                "In a while",
                "Kill a Crocodile",
                "Kharidian Desert, south of Pollnivneach along River Elid",
                Arrays.asList(TeleportData.HOUSE_POLLNIVNEACH, TeleportData.NARDAH)
        ));

        // "Ghostly bones." → Kill an Ankou
        KILL_TASKS.add(new KillTask(
                "Ghostly bones",
                "Kill an Ankou",
                "Stronghold of Security floor 4, or Catacombs of Kourend",
                Arrays.asList(TeleportData.EDGEVILLE, TeleportData.XERICS_HEART)
        ));

        // "A demon's best friend holds the next step" → Kill a Hellhound
        KILL_TASKS.add(new KillTask(
                "demon's best friend",
                "Kill a Hellhound",
                "Taverley Dungeon, Catacombs of Kourend, or Wilderness",
                Arrays.asList(TeleportData.FALADOR, TeleportData.XERICS_HEART)
        ));

        // "You'll get licked." → Kill a Bloodveld
        KILL_TASKS.add(new KillTask(
                "You'll get licked",
                "Kill a Bloodveld",
                "Slayer Tower or Catacombs of Kourend",
                Arrays.asList(TeleportData.SLAYER_TOWER, TeleportData.XERICS_HEART)
        ));

        // "Reflection is the weakness for these eyes of evil." → Kill a Basilisk
        KILL_TASKS.add(new KillTask(
                "Reflection is the weakness",
                "Kill a Basilisk (bring mirror shield!)",
                "Fremennik Slayer Dungeon or Jormungand's Prison",
                Arrays.asList(TeleportData.FREMENNIK_SLAYER_DUNGEON, TeleportData.FAIRY_DKS)
        ));

        // "Let's hope you don't meet a watery death" → Kill a Waterfiend
        KILL_TASKS.add(new KillTask(
                "watery death",
                "Kill a Waterfiend",
                "Ancient Cavern (whirlpool near Barbarian Assault)",
                Arrays.asList(TeleportData.BARBARIAN_ASSAULT, TeleportData.MINIGAME_BARBARIAN_ASSAULT)
        ));

        // "You will have to fly high where a sword cannot help you." → Kill an Aviansie
        KILL_TASKS.add(new KillTask(
                "fly high where a sword cannot",
                "Kill an Aviansie (ranged only!)",
                "God Wars Dungeon (Armadyl area)",
                Arrays.asList(TeleportData.TROLLHEIM, TeleportData.HOUSE_TROLLHEIM)
        ));

        // "Mix yellow with blue and add heat, make sure you bring protection." → Kill a Green Dragon
        KILL_TASKS.add(new KillTask(
                "Mix yellow with blue",
                "Kill a Green Dragon (antifire!)",
                "Wilderness or Corsair Cove Dungeon",
                Arrays.asList(TeleportData.FEROX_ENCLAVE, TeleportData.MINIGAME_CLAN_WARS)
        ));

        // "You'll have to plug your nose if you use this source of herbs." → Kill an Aberrant Spectre
        KILL_TASKS.add(new KillTask(
                "plug your nose",
                "Kill an Aberrant Spectre (nosepeg/slayer helm!)",
                "Slayer Tower or Catacombs of Kourend",
                Arrays.asList(TeleportData.SLAYER_TOWER, TeleportData.XERICS_HEART)
        ));

        // "You might have to turn over a few stones to progress." → Kill a Rock Crab
        KILL_TASKS.add(new KillTask(
                "turn over a few stones",
                "Kill a Rock Crab",
                "Rellekka beach or Waterbirth Island",
                Arrays.asList(TeleportData.FAIRY_DKS, TeleportData.HOUSE_RELLEKKA)
        ));

        // "Banish a chilling spirit." → Kill a Frost Nagua
        KILL_TASKS.add(new KillTask(
                "chilling spirit",
                "Kill a Frost Nagua",
                "Fremennik Slayer Dungeon",
                Arrays.asList(TeleportData.FREMENNIK_SLAYER_DUNGEON, TeleportData.FAIRY_DKS)
        ));

        // "Crush the spirited earth." → Kill an Earthen Nagua
        KILL_TASKS.add(new KillTask(
                "spirited earth",
                "Kill an Earthen Nagua",
                "Fremennik Slayer Dungeon",
                Arrays.asList(TeleportData.FREMENNIK_SLAYER_DUNGEON, TeleportData.FAIRY_DKS)
        ));
    }

    /**
     * Check if a clue's text matches a kill task.
     */
    public static KillTask getKillTask(String clueText) {
        if (clueText == null) {
            return null;
        }
        String lower = clueText.toLowerCase();
        for (KillTask kt : KILL_TASKS) {
            if (lower.contains(kt.textMatch.toLowerCase())) {
                return kt;
            }
        }
        return null;
    }

    public static class KillTask {
        public final String textMatch;
        public final String monsterNote;
        public final String locationNote;
        public final List<TeleportData> teleports;

        KillTask(String textMatch, String monsterNote, String locationNote,
                 List<TeleportData> teleports) {
            this.textMatch = textMatch;
            this.monsterNote = monsterNote;
            this.locationNote = locationNote;
            this.teleports = teleports;
        }
    }
}
