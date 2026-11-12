package com.clueteleporthelper;

import net.runelite.api.coords.WorldPoint;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LocationOverrides {

    private static final List<OverrideRegion> OVERRIDES = new ArrayList<>();

    private static final String ABYSS_WARNING = "Via Abyss (Wilderness - be careful!)";

    static {
        // Teleports near Mage of Zamorak (~3102, 3557) for Abyss access
        List<TeleportData> abyssTeleports = Arrays.asList(
                TeleportData.EDGEVILLE,
                TeleportData.FAIRY_DKR,
                TeleportData.FEROX_ENCLAVE
        );

        // Rellekka-departure islands (Neitiznot, Jatizso, Waterbirth, Lunar Isle)
        List<TeleportData> rellekkaTeleports = Arrays.asList(
                TeleportData.FAIRY_AJR,
                TeleportData.FAIRY_DKS
        );

        // --- Island Overrides ---

        // Entrana: boat from Port Sarim
        OVERRIDES.add(new OverrideRegion(
                2800, 2880, 3300, 3400, 0,
                Arrays.asList(
                        TeleportData.DRAYNOR_VILLAGE,
                        TeleportData.FALADOR,
                        TeleportData.EXPLORERS_RING
                ),
                "Boat from Port Sarim"
        ));

        // Crandor: via Karamja volcano or Corsair Cove
        OVERRIDES.add(new OverrideRegion(
                2810, 2870, 3230, 3310, 0,
                Arrays.asList(
                        TeleportData.KARAMJA,
                        TeleportData.FAIRY_AIQ
                ),
                "Via Karamja volcano"
        ));

        // Fossil Island: boat from Digsite
        OVERRIDES.add(new OverrideRegion(
                3620, 3850, 3740, 3910, 0,
                Arrays.asList(
                        TeleportData.DIGSITE
                ),
                "Boat from Digsite"
        ));

        // Mos Le'Harmless: charter from Port Phasmatys
        OVERRIDES.add(new OverrideRegion(
                3640, 3860, 2920, 3100, 0,
                Arrays.asList(
                        TeleportData.ECTOPHIAL,
                        TeleportData.MOS_LEHARMLESS
                ),
                "Charter from Port Phasmatys"
        ));

        // Ape Atoll
        OVERRIDES.add(new OverrideRegion(
                2680, 2820, 2680, 2820, 0,
                Arrays.asList(
                        TeleportData.SPIRIT_TREE_GNOME_STRONGHOLD
                ),
                "Spirit Tree to Gnome Stronghold, then Glider"
        ));

        // Crash Island (near Ape Atoll, Monkey Madness route)
        OVERRIDES.add(new OverrideRegion(
                2890, 2950, 2700, 2750, 0,
                Arrays.asList(
                        TeleportData.SPIRIT_TREE_GNOME_STRONGHOLD
                ),
                "Spirit Tree to Gnome Stronghold, then Glider"
        ));

        // Neitiznot
        OVERRIDES.add(new OverrideRegion(
                2290, 2370, 3760, 3830, 0,
                rellekkaTeleports,
                "Boat from Rellekka"
        ));

        // Jatizso
        OVERRIDES.add(new OverrideRegion(
                2370, 2440, 3760, 3830, 0,
                rellekkaTeleports,
                "Boat from Rellekka"
        ));

        // Waterbirth Island
        OVERRIDES.add(new OverrideRegion(
                2490, 2570, 3710, 3780, 0,
                rellekkaTeleports,
                "Boat from Rellekka"
        ));

        // Lunar Isle
        OVERRIDES.add(new OverrideRegion(
                2060, 2180, 3870, 3960, 0,
                rellekkaTeleports,
                "Boat from Rellekka"
        ));

        // Zul-Andra area
        OVERRIDES.add(new OverrideRegion(
                2120, 2210, 3050, 3110, 0,
                Arrays.asList(
                        TeleportData.FAIRY_BJS
                ),
                "Fairy Ring BJS"
        ));

        // Kharazi Jungle (mossy rock, legends quest area)
        OVERRIDES.add(new OverrideRegion(
                2740, 2860, 2900, 3010, 0,
                Arrays.asList(
                        TeleportData.FAIRY_CKR,
                        TeleportData.TAI_BWO_WANNAI
                ),
                "Fairy Ring CKR or Tai Bwo Wannai, then south to Kharazi Jungle"
        ));

        // Karamja - Cairn Isle (south Karamja, walk-only)
        OVERRIDES.add(new OverrideRegion(
                2750, 2840, 2950, 3020, 0,
                Arrays.asList(
                        TeleportData.KARAMJA,
                        TeleportData.FAIRY_CKR,
                        TeleportData.TAI_BWO_WANNAI
                ),
                null
        ));

        // --- Coastal Overrides ---

        // Rimmington area: Glory Karamja teleports to Musa Point (across water), not Rimmington
        OVERRIDES.add(new OverrideRegion(
                2910, 2980, 3200, 3260, 0,
                Arrays.asList(
                        TeleportData.HOUSE_RIMMINGTON,
                        TeleportData.EXPLORERS_RING,
                        TeleportData.SPIRIT_TREE_PORT_SARIM,
                        TeleportData.FAIRY_AIQ
                ),
                "Glory Karamja goes to Musa Point, not Rimmington"
        ));

        // Emir's Arena / East Al Kharid area: BIP and Mort'ton are across water
        OVERRIDES.add(new OverrideRegion(
                3300, 3400, 3200, 3290, 0,
                Arrays.asList(
                        TeleportData.MINIGAME_MAGE_TRAINING_ARENA,
                        TeleportData.AL_KHARID,
                        TeleportData.LUMBRIDGE
                ),
                "Mage Training Arena minigame tele is closest"
        ));

        // Burgh de Rott / Barrows / Mort'ton / Meiyerditch: Darkmeyer maze makes it impractical
        OVERRIDES.add(new OverrideRegion(
                3470, 3660, 3200, 3340, 0,
                Arrays.asList(
                        TeleportData.MINIGAME_SHADES_OF_MORTTON,
                        TeleportData.VER_SINHAZA,
                        TeleportData.FAIRY_BKR
                ),
                "Shades of Mort'ton minigame tele - avoid Darkmeyer maze"
        ));

        // --- Rune Altar Interior Overrides ---
        // All altar interiors suggest teleports near Mage of Zamorak for Abyss access.

        // Air Altar interior
        OVERRIDES.add(new OverrideRegion(2816, 2862, 4800, 4860, 0, abyssTeleports, ABYSS_WARNING));
        // Mind Altar interior
        OVERRIDES.add(new OverrideRegion(2764, 2814, 4820, 4870, 0, abyssTeleports, ABYSS_WARNING));
        // Water Altar interior
        OVERRIDES.add(new OverrideRegion(2688, 2740, 4820, 4870, 0, abyssTeleports, ABYSS_WARNING));
        // Earth Altar interior
        OVERRIDES.add(new OverrideRegion(2624, 2672, 4820, 4870, 0, abyssTeleports, ABYSS_WARNING));
        // Fire Altar interior
        OVERRIDES.add(new OverrideRegion(2560, 2608, 4820, 4870, 0, abyssTeleports, ABYSS_WARNING));
        // Body Altar interior
        OVERRIDES.add(new OverrideRegion(2496, 2544, 4820, 4870, 0, abyssTeleports, ABYSS_WARNING));
        // Cosmic Altar interior
        OVERRIDES.add(new OverrideRegion(2112, 2176, 4800, 4864, 0, abyssTeleports, ABYSS_WARNING));
        // Law Altar interior
        OVERRIDES.add(new OverrideRegion(2432, 2480, 4800, 4864, 0, abyssTeleports, ABYSS_WARNING));
        // Nature Altar interior
        OVERRIDES.add(new OverrideRegion(2368, 2416, 4800, 4864, 0, abyssTeleports, ABYSS_WARNING));
        // Death Altar interior
        OVERRIDES.add(new OverrideRegion(2176, 2224, 4800, 4864, 0, abyssTeleports, ABYSS_WARNING));
        // Outside the Death Altar (Thorgel, ~1863, 4639). Distance alone picks Tarn's Lair.
        OVERRIDES.add(new OverrideRegion(1840, 1890, 4620, 4660, 0, abyssTeleports,
                ABYSS_WARNING + " Death rift, then leave the altar to reach Thorgel."));
        // Blood Altar interior
        OVERRIDES.add(new OverrideRegion(3200, 3264, 4800, 4880, 0, abyssTeleports, ABYSS_WARNING));
        // Soul Altar interior
        OVERRIDES.add(new OverrideRegion(1792, 1856, 4800, 4864, 0, abyssTeleports, ABYSS_WARNING));
        // Wrath Altar interior
        OVERRIDES.add(new OverrideRegion(2304, 2368, 4800, 4864, 0, abyssTeleports, ABYSS_WARNING));

        // --- Dungeon Overrides (Underground, Y > 9000) ---

        // Lumbridge Swamp Caves
        OVERRIDES.add(new OverrideRegion(3140, 3260, 9540, 9670, 0,
                Arrays.asList(TeleportData.LUMBRIDGE),
                "Lumbridge Swamp entrance"));

        // Edgeville Dungeon
        OVERRIDES.add(new OverrideRegion(3050, 3160, 9820, 10050, 0,
                Arrays.asList(TeleportData.EDGEVILLE, TeleportData.FAIRY_DKR),
                "Edgeville trapdoor"));

        // Varrock Sewers
        OVERRIDES.add(new OverrideRegion(3150, 3290, 9855, 9920, 0,
                Arrays.asList(TeleportData.VARROCK, TeleportData.GRAND_EXCHANGE),
                "Varrock Sewers"));

        // Yanille Agility Dungeon
        OVERRIDES.add(new OverrideRegion(2550, 2620, 9550, 9630, 0,
                Arrays.asList(TeleportData.WATCHTOWER),
                "Yanille dungeon north entrance, then pray at altar to get sent down"));

        // Kourend Catacombs
        OVERRIDES.add(new OverrideRegion(1600, 1730, 9980, 10110, 0,
                Arrays.asList(TeleportData.XERICS_HEART),
                "Kourend Catacombs"));

        // Fountain of Heroes (Heroes' Guild basement)
        OVERRIDES.add(new OverrideRegion(2880, 2960, 9860, 9940, 0,
                Arrays.asList(TeleportData.FALADOR, TeleportData.CHAMPIONS_GUILD),
                "Heroes' Guild basement"));

        // Barrows Crypts
        OVERRIDES.add(new OverrideRegion(3520, 3590, 9660, 9740, 0,
                Arrays.asList(TeleportData.FAIRY_BKR, TeleportData.ECTOPHIAL),
                "Barrows"));

        // Motherlode Mine / Dwarven Mine
        OVERRIDES.add(new OverrideRegion(3010, 3100, 9710, 9810, 0,
                Arrays.asList(TeleportData.FALADOR),
                "Dwarven Mine / Motherlode"));

        // Taverley Dungeon
        OVERRIDES.add(new OverrideRegion(2820, 2950, 9760, 9870, 0,
                Arrays.asList(TeleportData.FALADOR, TeleportData.BURTHORPE),
                "Taverley Dungeon"));

        // Paterdomus Temple dungeon (Drezel)
        OVERRIDES.add(new OverrideRegion(
                3400, 3460, 9870, 9910, 0,
                Arrays.asList(
                        TeleportData.FAIRY_CKS,
                        TeleportData.LUMBERYARD,
                        TeleportData.VARROCK
                ),
                "Paterdomus Temple (Drezel)"
        ));

        // Slayer Tower Basement
        OVERRIDES.add(new OverrideRegion(3400, 3460, 9880, 9960, 0,
                Arrays.asList(TeleportData.FAIRY_CKS, TeleportData.ECTOPHIAL),
                "Slayer Tower basement"));

        // Viyeldi Caves (Legends' Quest, under Kharazi Jungle)
        OVERRIDES.add(new OverrideRegion(2757, 2810, 9288, 9340, 0,
                Arrays.asList(TeleportData.FAIRY_CKR, TeleportData.TAI_BWO_WANNAI),
                "Kharazi Jungle entrance"));

        // Stronghold of Security - all 4 floors (TODO: verify coords in-game)
        OVERRIDES.add(new OverrideRegion(1856, 1920, 5184, 5248, 0,
                Arrays.asList(TeleportData.EDGEVILLE, TeleportData.VARROCK),
                "Entrance in Barbarian Village"));
        OVERRIDES.add(new OverrideRegion(1856, 1920, 5248, 5312, 0,
                Arrays.asList(TeleportData.EDGEVILLE, TeleportData.VARROCK),
                "Entrance in Barbarian Village"));
        OVERRIDES.add(new OverrideRegion(1856, 1920, 5312, 5376, 0,
                Arrays.asList(TeleportData.EDGEVILLE, TeleportData.VARROCK),
                "Entrance in Barbarian Village"));
        OVERRIDES.add(new OverrideRegion(1856, 1920, 5376, 5440, 0,
                Arrays.asList(TeleportData.EDGEVILLE, TeleportData.VARROCK),
                "Entrance in Barbarian Village"));

        // Lava Maze Dungeon / Deep Wilderness Dungeon (TODO: verify coords in-game)
        OVERRIDES.add(new OverrideRegion(3000, 3100, 10200, 10420, 0,
                Arrays.asList(TeleportData.FEROX_ENCLAVE),
                "Deep Wilderness dungeon - Ferox as home base"));

        // Cerberus' Lair (inside Taverley Dungeon, TODO: verify coords in-game)
        OVERRIDES.add(new OverrideRegion(1216, 1340, 1216, 1280, 0,
                Arrays.asList(TeleportData.FALADOR, TeleportData.BURTHORPE),
                "Taverley Dungeon entrance"));

        // --- Dungeon Overrides (Instanced/Special, Y ~4000-6000) ---

        // Rogues' Den (under Burthorpe pub)
        OVERRIDES.add(new OverrideRegion(3030, 3080, 4960, 5020, 0,
                Arrays.asList(TeleportData.BURTHORPE, TeleportData.WARRIORS_GUILD),
                "Trapdoor in Burthorpe pub"));

        // Abyss inner ring
        OVERRIDES.add(new OverrideRegion(2980, 3060, 4820, 4880, 0,
                abyssTeleports,
                ABYSS_WARNING));

        // KBD Lair
        OVERRIDES.add(new OverrideRegion(2240, 2320, 4640, 4730, 0,
                Arrays.asList(TeleportData.EDGEVILLE, TeleportData.FEROX_ENCLAVE),
                "Wilderness lever (be careful!)"));

        // TzHaar / Mor Ul Rek
        OVERRIDES.add(new OverrideRegion(2400, 2520, 5100, 5200, 0,
                Arrays.asList(TeleportData.KARAMJA, TeleportData.FAIRY_BLP),
                "Karamja volcano entrance"));

        // Ancient Cavern
        OVERRIDES.add(new OverrideRegion(1700, 1820, 5320, 5410, 0,
                Arrays.asList(TeleportData.BARBARIAN_ASSAULT),
                "Whirlpool near Barbarian Assault"));

        // Shadow Dungeon
        OVERRIDES.add(new OverrideRegion(2590, 2680, 5030, 5120, 0,
                Arrays.asList(TeleportData.BARBARIAN_ASSAULT),
                "Ring of visibility required"));

        // God Wars Dungeon
        OVERRIDES.add(new OverrideRegion(2820, 2980, 5250, 5380, 0,
                Arrays.asList(TeleportData.TROLLHEIM),
                "God Wars Dungeon"));

        // Pyramid Plunder interior (Sophanem)
        OVERRIDES.add(new OverrideRegion(1900, 1970, 4400, 4470, 0,
                Arrays.asList(
                        TeleportData.PYRAMID_PLUNDER,
                        TeleportData.NARDAH,
                        TeleportData.HOUSE_POLLNIVNEACH
                ),
                "Pharaoh's Sceptre direct, or Nardah, then run SW to Sophanem and enter pyramid"
        ));

        // Agility Pyramid top
        OVERRIDES.add(new OverrideRegion(3010, 3080, 4660, 4740, 0,
                Arrays.asList(TeleportData.NARDAH),
                "Agility Pyramid"));

        // Watchtower upper
        OVERRIDES.add(new OverrideRegion(2890, 2970, 4680, 4760, 0,
                Arrays.asList(TeleportData.WATCHTOWER),
                "Watchtower"));

        // Iban's Temple (Underground Pass)
        OVERRIDES.add(new OverrideRegion(1960, 2060, 4670, 4760, 0,
                Arrays.asList(TeleportData.ARDOUGNE),
                "Underground Pass"));

        // Waterbirth Island Dungeon (TODO: verify underground coords in-game)
        OVERRIDES.add(new OverrideRegion(2430, 2570, 10100, 10200, 0,
                rellekkaTeleports,
                "Boat from Rellekka to Waterbirth"));

        // Miscellania & Etceteria Dungeon (TODO: verify underground coords in-game)
        OVERRIDES.add(new OverrideRegion(2490, 2640, 10200, 10320, 0,
                rellekkaTeleports,
                "Boat from Rellekka to Miscellania"));

        // --- Other Special Area Overrides ---

        // Prifddinas (elf city, special dimension)
        OVERRIDES.add(new OverrideRegion(3200, 3327, 6016, 6143, 0,
                Arrays.asList(TeleportData.PRIFDDINAS),
                "Teleport Crystal / Eternal Crystal"));

        // Iorwerth Camp / North Tirannwn: Prifddinas Crystal is in special dimension coords
        OVERRIDES.add(new OverrideRegion(
                2140, 2280, 3200, 3360, 0,
                Arrays.asList(
                        TeleportData.IORWERTH_CAMP,
                        TeleportData.PRIFDDINAS,
                        TeleportData.WEST_ARDOUGNE
                ),
                "Iorwerth Camp tablet or Crystal seed to Prifddinas"
        ));

        // Lletya (hidden elf village in Tirannwn)
        OVERRIDES.add(new OverrideRegion(2280, 2380, 3100, 3220, 0,
                Arrays.asList(TeleportData.PRIFDDINAS),
                "Teleport Crystal to Lletya"));

        // Fairy Resistance Hideout (Fairy Queen cipher clue)
        OVERRIDES.add(new OverrideRegion(2330, 2370, 4420, 4450, 0,
                Arrays.asList(
                        TeleportData.EDGEVILLE,
                        TeleportData.FAIRY_DKR
                ),
                "Fairy Ring sequence: AIR to DLR to DJQ to AJS (DJQ fails, then enter AJS)"
        ));

        // Zanaris (fairy realm, special dimension)
        OVERRIDES.add(new OverrideRegion(2368, 2496, 4352, 4480, 0,
                Arrays.asList(
                        TeleportData.EDGEVILLE,
                        TeleportData.FAIRY_DKR,
                        TeleportData.FAIRY_CKS
                ),
                "Any Fairy Ring"));

        // --- Instanced clue spots (coordinates from RuneLite's clue data) ---

        // Puro-Puro (Immenizz anagram, ~2592, 4324)
        OVERRIDES.add(new OverrideRegion(2560, 2630, 4290, 4360, 0,
                Arrays.asList(TeleportData.EDGEVILLE, TeleportData.FAIRY_DKR, TeleportData.FAIRY_CKS),
                "Any Fairy Ring to Zanaris, then the wheat field in the middle"));

        // Varrock Museum basement (terrorbird display, ~1756, 4940)
        OVERRIDES.add(new OverrideRegion(1720, 1790, 4910, 4970, 0,
                Arrays.asList(TeleportData.VARROCK),
                "Varrock Museum, stairs down to the basement"));

        // Burthorpe games room (~2207, 4952)
        OVERRIDES.add(new OverrideRegion(2180, 2230, 4930, 4975, 0,
                Arrays.asList(TeleportData.BURTHORPE),
                "Games room, stairs down in the Burthorpe games hall"));

        // Waterbirth Island Dungeon, ladder to the Dagannoth Kings (~1910, 4367)
        OVERRIDES.add(new OverrideRegion(1880, 1940, 4340, 4400, 0,
                rellekkaTeleports,
                "Boat from Rellekka to Waterbirth"));

        // Viyeldi caves, below the mossy rock (~2410, 4714)
        OVERRIDES.add(new OverrideRegion(2380, 2440, 4690, 4740, 0,
                Arrays.asList(TeleportData.FAIRY_CKR, TeleportData.TAI_BWO_WANNAI),
                "Kharazi Jungle entrance"));

        // Shadow Dungeon, north-east corner (~2744, 5116)
        OVERRIDES.add(new OverrideRegion(2681, 2780, 5080, 5150, 0,
                Arrays.asList(TeleportData.BARBARIAN_ASSAULT),
                "Ring of visibility required"));

        // Keldagrim (underground dwarf city)
        OVERRIDES.add(new OverrideRegion(2816, 2960, 10048, 10240, 0,
                Arrays.asList(TeleportData.GRAND_EXCHANGE, TeleportData.FAIRY_DKS),
                "Mine cart from GE or cave east of Rellekka"));

        // Dorgesh-Kaan (underground goblin city)
        OVERRIDES.add(new OverrideRegion(2688, 2752, 5248, 5376, 0,
                Arrays.asList(TeleportData.LUMBRIDGE),
                "Lumbridge cellar entrance"));

        // Wilderness Resource Area
        OVERRIDES.add(new OverrideRegion(
                3160, 3200, 3920, 3960, 0,
                Arrays.asList(
                        TeleportData.EDGEVILLE_LEVER,
                        TeleportData.FEROX_ENCLAVE,
                        TeleportData.EDGEVILLE
                ),
                "Edgeville Dungeon lever drops you right next to it"
        ));

        // Deep Wilderness (level ~30+)
        OVERRIDES.add(new OverrideRegion(2940, 3400, 3752, 3968, 0,
                Arrays.asList(TeleportData.LAVA_MAZE, TeleportData.FEROX_ENCLAVE, TeleportData.EDGEVILLE),
                "Burning Amulet or Wilderness Obelisk"));
    }

    public static OverrideResult getOverride(WorldPoint clueLocation) {
        for (OverrideRegion region : OVERRIDES) {
            if (region.contains(clueLocation)) {
                return new OverrideResult(region.teleports, region.note);
            }
        }
        return null;
    }

    public static class OverrideResult {
        public final List<TeleportData> teleports;
        public final String note;

        OverrideResult(List<TeleportData> teleports, String note) {
            this.teleports = teleports;
            this.note = note;
        }
    }

    private static class OverrideRegion {
        final int minX, maxX, minY, maxY, plane;
        final List<TeleportData> teleports;
        final String note;

        OverrideRegion(int minX, int maxX, int minY, int maxY, int plane, List<TeleportData> teleports, String note) {
            this.minX = minX;
            this.maxX = maxX;
            this.minY = minY;
            this.maxY = maxY;
            this.plane = plane;
            this.teleports = teleports;
            this.note = note;
        }

        boolean contains(WorldPoint point) {
            return point.getPlane() == plane
                    && point.getX() >= minX && point.getX() <= maxX
                    && point.getY() >= minY && point.getY() <= maxY;
        }
    }
}
