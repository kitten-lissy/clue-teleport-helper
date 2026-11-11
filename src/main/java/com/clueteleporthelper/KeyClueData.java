package com.clueteleporthelper;

import net.runelite.api.coords.WorldPoint;
import java.util.*;

/**
 * Data for medium clue steps that require killing a monster for a key
 * before searching a crate/drawer. The plugin checks inventory for the key
 * and shows different teleport suggestions depending on which phase the
 * player is in.
 */
public class KeyClueData {

    private static final List<KeyClue> KEY_CLUES = new ArrayList<>();

    static {
        // "A graceful man of many colours..."
        // Kill any Hill Giant then Search crate on 2nd floor of Osten's shop, Shayzien
        KEY_CLUES.add(new KeyClue(
                new WorldPoint(1506, 3590, 2),
                "Kill Hill Giant near Shayzien for key",
                "Search crate on 2nd floor of Osten's shop, Shayzien",
                Arrays.asList(TeleportData.SHAYZIEN, TeleportData.LOVAKENGJ),
                Arrays.asList(TeleportData.SHAYZIEN, TeleportData.LOVAKENGJ)
        ));

        // "Search the basket of apples in an orchard..."
        // Kill any Hill Giant then Search basket of apples in Hosidius
        KEY_CLUES.add(new KeyClue(
                new WorldPoint(1718, 3626, 0),
                "Kill any Hill Giant for key (Edgeville Dungeon / Varrock Sewers)",
                "Search basket of apples in Hosidius orchard",
                Arrays.asList(TeleportData.EDGEVILLE, TeleportData.VARROCK),
                Arrays.asList(TeleportData.HOSIDIUS, TeleportData.XERICS_GLADE)
        ));

        // "Go to the village being attacked by trolls..."
        // Kill Penda in The Toad and Chicken then Search drawers in Dunstan's house, Burthorpe
        KEY_CLUES.add(new KeyClue(
                new WorldPoint(2921, 3577, 0),
                "Kill Penda in The Toad and Chicken pub, Burthorpe",
                "Search drawers in Dunstan's house, Burthorpe",
                Arrays.asList(TeleportData.BURTHORPE, TeleportData.WARRIORS_GUILD),
                Arrays.asList(TeleportData.BURTHORPE, TeleportData.WARRIORS_GUILD)
        ));

        // "Go to this building to be illuminated..."
        // Kill guard in Rellekka marketplace then Search drawers on 1st floor of Lighthouse
        KEY_CLUES.add(new KeyClue(
                new WorldPoint(2512, 3641, 1),
                "Kill a Guard in Rellekka marketplace for key",
                "Search drawers on 1st floor of Lighthouse",
                Arrays.asList(TeleportData.FAIRY_DKS, TeleportData.HOUSE_RELLEKKA),
                Arrays.asList(TeleportData.FAIRY_ALP)
        ));

        // "In a town where everyone has perfect vision..."
        // Kill any chicken then Search drawers in Seers' Village
        KEY_CLUES.add(new KeyClue(
                new WorldPoint(2709, 3478, 0),
                "Kill any Chicken for key (farm south of Seers')",
                "Search drawers opposite Elemental Workshop, Seers' Village",
                Arrays.asList(TeleportData.CAMELOT),
                Arrays.asList(TeleportData.CAMELOT)
        ));

        // "In a town where the guards are armed with maces..."
        // Kill guard dog at Handelmort Mansion then Search upstairs of pub N of Ardougne Castle
        KEY_CLUES.add(new KeyClue(
                new WorldPoint(2574, 3326, 1),
                "Kill Guard Dog at Handelmort Mansion, Ardougne",
                "Search upstairs of pub north of Ardougne Castle",
                Arrays.asList(TeleportData.ARDOUGNE),
                Arrays.asList(TeleportData.ARDOUGNE)
        ));

        // "In a town where thieves steal from stalls..."
        // Kill any guard in East Ardougne then Search drawers upstairs in Jerico's house
        KEY_CLUES.add(new KeyClue(
                new WorldPoint(2611, 3324, 1),
                "Kill any Guard in East Ardougne for key",
                "Search drawers upstairs in Jerico's house, Ardougne",
                Arrays.asList(TeleportData.ARDOUGNE),
                Arrays.asList(TeleportData.ARDOUGNE)
        ));

        // "In a town where wizards are known to gather..."
        // Kill any man nearby then Search upstairs chest in house NW of Yanille bank
        KEY_CLUES.add(new KeyClue(
                new WorldPoint(2593, 3108, 1),
                "Kill any Man in Yanille for key",
                "Search chest upstairs NW of Yanille bank",
                Arrays.asList(TeleportData.WATCHTOWER, TeleportData.HOUSE_YANILLE),
                Arrays.asList(TeleportData.WATCHTOWER, TeleportData.HOUSE_YANILLE)
        ));

        // "Search the upstairs drawers of a house in a village where pirates..."
        // Kill any pirate in Brimhaven then Search drawers upstairs in SE house
        KEY_CLUES.add(new KeyClue(
                new WorldPoint(2809, 3165, 1),
                "Kill any Pirate in Brimhaven for key",
                "Search drawers upstairs in SE Brimhaven house",
                Arrays.asList(TeleportData.HOUSE_BRIMHAVEN, TeleportData.KARAMJA),
                Arrays.asList(TeleportData.HOUSE_BRIMHAVEN, TeleportData.KARAMJA)
        ));

        // "Probably filled with wizards socks."
        // Kill any wizard at Wizards' Tower then Search drawers in basement
        KEY_CLUES.add(new KeyClue(
                new WorldPoint(3116, 9562, 0),
                "Kill any Wizard at the tower for key",
                "Search drawers in Wizards' Tower basement",
                Arrays.asList(TeleportData.WIZARDS_TOWER, TeleportData.FAIRY_DIS),
                Arrays.asList(TeleportData.WIZARDS_TOWER, TeleportData.FAIRY_DIS)
        ));

        // "The dead, red dragon watches over this chest..."
        // Kill any barbarian then Search chest in Barbarian Village
        KEY_CLUES.add(new KeyClue(
                new WorldPoint(3353, 3332, 0),
                "Kill any Barbarian for key",
                "Search chest by the red dragon",
                Arrays.asList(TeleportData.VARROCK, TeleportData.EDGEVILLE),
                Arrays.asList(TeleportData.VARROCK, TeleportData.DIGSITE)
        ));
    }

    /**
     * Check if a clue location matches a kill-for-key clue.
     * Compares on plane 0 with a tolerance of 5 tiles.
     */
    public static KeyClue getKeyClue(WorldPoint clueLocation) {
        for (KeyClue kc : KEY_CLUES) {
            WorldPoint crateGround = new WorldPoint(
                    kc.crateLocation.getX(), kc.crateLocation.getY(), 0);
            if (crateGround.distanceTo(clueLocation) <= 5) {
                return kc;
            }
        }
        return null;
    }

    public static class KeyClue {
        public final WorldPoint crateLocation;
        public final String killNote;
        public final String searchNote;
        public final List<TeleportData> killTeleports;
        public final List<TeleportData> crateTeleports;

        KeyClue(WorldPoint crateLocation, String killNote, String searchNote,
                List<TeleportData> killTeleports, List<TeleportData> crateTeleports) {
            this.crateLocation = crateLocation;
            this.killNote = killNote;
            this.searchNote = searchNote;
            this.killTeleports = killTeleports;
            this.crateTeleports = crateTeleports;
        }
    }
}
