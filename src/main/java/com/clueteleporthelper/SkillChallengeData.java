package com.clueteleporthelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Sherlock and Charlie skill challenges. These steps have no fixed location,
 * so they are matched by the challenge text RuneLite stores on the clue.
 *
 * Challenge texts are copied from RuneLite's SkillChallengeClue (client 1.13.6).
 * Where a challenge can be done in many places the note says so and only the
 * return trip is suggested. Locations were checked against the OSRS wiki.
 */
public class SkillChallengeData {

    /** Sherlock wanders east of the Sorcerer's Tower (wiki: Camelot teleport, or fairy ring BLR). */
    public static final List<TeleportData> SHERLOCK_TELEPORTS =
            Collections.unmodifiableList(Arrays.asList(TeleportData.CAMELOT, TeleportData.FAIRY_BLR));

    /** Charlie the Tramp stands by the southern entrance to Varrock (wiki). */
    public static final List<TeleportData> CHARLIE_TELEPORTS =
            Collections.unmodifiableList(Collections.singletonList(TeleportData.VARROCK));

    private static final String ANYWHERE = "Can be done anywhere";
    private static final String ANY_ANVIL = "Any anvil";
    private static final String ANY_FURNACE = "Any furnace";

    private static final List<SkillChallenge> CHALLENGES = new ArrayList<>();

    static {
        // ---- Sherlock (elite) ----
        sherlock("Equip a Dragon Scimitar.", ANYWHERE);
        sherlock("Enchant some Dragonstone Jewellery.", ANYWHERE);
        sherlock("Craft a nature rune.", "Nature Altar, north of Shilo Village (or via the Abyss)",
                TeleportData.FAIRY_CKR);
        sherlock("Catch a mottled eel with aerial fishing in Lake Molch.", "Lake Molch, Kebos Lowlands",
                TeleportData.FAIRY_DJR, TeleportData.SHAYZIEN, TeleportData.FARMING_GUILD);
        sherlock("Score a goal in skullball.", "Werewolf Skullball course near Canifis",
                TeleportData.FAIRY_CKS);
        sherlock("Complete a lap of Ape atoll agility course.", "Ape Atoll agility course",
                TeleportData.APE_ATOLL, TeleportData.FAIRY_CLR);
        sherlock("Create a super defence potion.", ANYWHERE);
        sherlock("Steal from a chest in Ardougne Castle.", "Ardougne Castle, upstairs",
                TeleportData.ARDOUGNE);
        sherlock("Craft a green dragonhide body.", ANYWHERE);
        sherlock("String a yew longbow.", ANYWHERE);
        sherlock("Kill a Dust Devil.", "Smoke Dungeon near Pollnivneach, or the Catacombs of Kourend",
                TeleportData.HOUSE_POLLNIVNEACH, TeleportData.FAIRY_DLQ);
        sherlock("Catch a black warlock.", "Feldip Hunter area, or around the Farming Guild",
                TeleportData.FAIRY_AKS, TeleportData.FARMING_GUILD);
        sherlock("Catch a red chinchompa.", "Feldip Hunter area",
                TeleportData.FAIRY_AKS);
        sherlock("Mine a mithril ore.", "Mining Guild has mithril rocks",
                TeleportData.MINING_GUILD, TeleportData.FALADOR);
        sherlock("Smith a mithril 2h sword.", ANY_ANVIL);
        sherlock("Catch a raw shark.", "Fishing Guild",
                TeleportData.FISHING_GUILD);
        sherlock("Cut a yew log.", "Yew trees: Woodcutting Guild, south of Falador, Seers' Village",
                TeleportData.WOODCUTTING_GUILD, TeleportData.FALADOR, TeleportData.CAMELOT);
        sherlock("Fix a magical lamp in Dorgesh-Kaan.", "Dorgesh-Kaan",
                TeleportData.FAIRY_AJQ);
        sherlock("Burn a yew log.", ANYWHERE);
        sherlock("Cook a swordfish", "Any range or fire");
        sherlock("Craft multiple cosmic runes from a single essence.",
                "Cosmic Altar, southern Zanaris (or via the Abyss)",
                TeleportData.FAIRY_BKS);
        sherlock("Plant a watermelon seed.", "Any allotment patch");
        sherlock("Activate the Chivalry prayer.", ANYWHERE);
        sherlock("Smith a tier 2 or above Shayzien platebody.", null);
        sherlock("Mine some nickel.", "On an island reached by Sailing");

        // ---- Sherlock (master) ----
        sherlock("Equip an abyssal whip in front of the abyssal demons of the Slayer Tower.", "Slayer Tower",
                TeleportData.SLAYER_TOWER);
        sherlock("Smith a runite med helm.", ANY_ANVIL);
        sherlock("Teleport to a spirit tree you planted yourself.", null);
        sherlock("Create a Barrows teleport tablet.", null);
        sherlock("Kill a Nechryael in the Slayer Tower.", "Slayer Tower",
                TeleportData.SLAYER_TOWER);
        sherlock("Kill a Spiritual Mage while wearing something from their god.", "God Wars Dungeon",
                TeleportData.TROLLHEIM, TeleportData.HOUSE_TROLLHEIM);
        sherlock("Create an unstrung dragonstone amulet at a furnace.", ANY_FURNACE);
        sherlock("Burn a magic log.", ANYWHERE);
        sherlock("Burn a redwood log.", ANYWHERE);
        sherlock("Complete a lap of Rellekka's Rooftop Agility Course", "Rellekka rooftop course",
                TeleportData.HOUSE_RELLEKKA, TeleportData.FAIRY_DKS);
        sherlock("Mix an anti-venom potion.", ANYWHERE);
        sherlock("Mine a piece of Runite ore", "Mining Guild has runite rocks",
                TeleportData.MINING_GUILD);
        sherlock("Steal a gem from the Ardougne market.", "Ardougne market",
                TeleportData.ARDOUGNE);
        sherlock("Pickpocket an elf.", null);
        sherlock("Bind a blood rune at the blood altar.",
                "Kourend's Blood Altar or the one under Meiyerditch");
        sherlock("Create a ranging mix potion.", ANYWHERE);
        sherlock("Fletch a rune dart.", ANYWHERE);
        sherlock("Cremate a set of fiyr remains.", "Funeral pyre, Mort'ton",
                TeleportData.MORTTON, TeleportData.MINIGAME_SHADES_OF_MORTTON);
        sherlock("Dissect a sacred eel.", "Sacred eels are caught at Zul-Andra",
                TeleportData.FAIRY_BJS);
        sherlock("Kill a lizardman shaman.", "Lizardman Canyon, between Lovakengj and Shayzien",
                TeleportData.SHAYZIEN, TeleportData.LOVAKENGJ);
        sherlock("Catch an Anglerfish.", "Port Piscarilius",
                TeleportData.PISCARILIUS);
        sherlock("Chop a redwood log.", "Woodcutting Guild",
                TeleportData.WOODCUTTING_GUILD);
        sherlock("Craft a light orb in the Dorgesh-Kaan bank.", "Dorgesh-Kaan bank",
                TeleportData.FAIRY_AJQ);
        sherlock("Kill a reanimated Abyssal Demon.", null);
        sherlock("Kill a Fiyr shade inside Mort'tons shade catacombs.", "Shade Catacombs, Mort'ton",
                TeleportData.MORTTON, TeleportData.MINIGAME_SHADES_OF_MORTTON);
        sherlock("Catch a tecu salamander.", "South-east of Ralos' Rise, Varlamore",
                TeleportData.CIVITAS_ILLA_FORTIS, TeleportData.QUETZAL_TEOMAT);

        // ---- Charlie the Tramp (hard) ----
        charlie("I really need a cooked pike.", "a cooked pike");
        charlie("I really need a cooked trout.", "a cooked trout");
        charlie("I really need a leather body.", "a leather body");
        charlie("I really need some leather chaps.", "some leather chaps");
        charlie("I really need a raw herring.", "a raw herring");
        charlie("I really need a raw trout.", "a raw trout");
        charlie("I really need a piece of iron ore.", "a piece of iron ore");
        charlie("I really need an iron dagger.", "an iron dagger");
    }

    private static void sherlock(String text, String where, TeleportData... teleports) {
        CHALLENGES.add(new SkillChallenge(text, false, where, Arrays.asList(teleports)));
    }

    private static void charlie(String text, String item) {
        CHALLENGES.add(new SkillChallenge(text, true,
                "Bring " + item + " to Charlie the Tramp, south entrance of Varrock", CHARLIE_TELEPORTS));
    }

    /**
     * Find the challenge whose text matches any of the given clue strings
     * (RuneLite keeps the scroll text and the chat text separately).
     */
    public static SkillChallenge getChallenge(String... clueTexts) {
        for (String text : clueTexts) {
            if (text == null || text.isEmpty()) {
                continue;
            }
            String lower = text.trim().toLowerCase();
            for (SkillChallenge sc : CHALLENGES) {
                if (lower.equals(sc.textMatch.toLowerCase())) {
                    return sc;
                }
            }
        }
        return null;
    }

    static List<SkillChallenge> all() {
        return Collections.unmodifiableList(CHALLENGES);
    }

    public static class SkillChallenge {
        public final String textMatch;
        public final boolean charlie;
        /** Where to do it, or null when the wiki gives no single place. */
        public final String locationNote;
        public final List<TeleportData> teleports;

        SkillChallenge(String textMatch, boolean charlie, String locationNote, List<TeleportData> teleports) {
            this.textMatch = textMatch;
            this.charlie = charlie;
            this.locationNote = locationNote;
            this.teleports = Collections.unmodifiableList(teleports);
        }

        /** Teleports to show: the task spot first, then the trip back to the NPC. */
        public List<TeleportData> teleports(boolean completed) {
            if (charlie) {
                return teleports;
            }
            if (completed) {
                return SHERLOCK_TELEPORTS;
            }
            List<TeleportData> all = new ArrayList<>(teleports);
            for (TeleportData tp : SHERLOCK_TELEPORTS) {
                if (!all.contains(tp)) {
                    all.add(tp);
                }
            }
            return all;
        }

        public String note(boolean completed) {
            if (charlie) {
                return locationNote;
            }
            if (completed) {
                return "Challenge done. Return to Sherlock";
            }
            return (locationNote != null ? locationNote + ". " : "") + "Then return to Sherlock";
        }
    }
}
