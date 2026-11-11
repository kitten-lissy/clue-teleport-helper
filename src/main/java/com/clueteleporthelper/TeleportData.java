package com.clueteleporthelper;

import net.runelite.api.coords.WorldPoint;
import net.runelite.api.ItemID;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public enum TeleportData {
    // Normal Magic Teleports
    VARROCK("Varrock (Spell/Tab)", new WorldPoint(3213, 3424, 0),
            ItemID.VARROCK_TELEPORT, ItemID.LAW_RUNE),
    LUMBRIDGE("Lumbridge (Spell/Tab)", new WorldPoint(3222, 3218, 0),
            ItemID.LUMBRIDGE_TELEPORT, ItemID.LAW_RUNE),
    FALADOR("Falador (Spell/Tab)", new WorldPoint(2965, 3381, 0),
            ItemID.FALADOR_TELEPORT, ItemID.LAW_RUNE),
    CAMELOT("Camelot (Spell/Tab)", new WorldPoint(2757, 3477, 0),
            ItemID.CAMELOT_TELEPORT, ItemID.LAW_RUNE),
    ARDOUGNE("Ardougne (Spell/Tab)", new WorldPoint(2664, 3306, 0),
            ItemID.ARDOUGNE_TELEPORT, ItemID.LAW_RUNE),
    WATCHTOWER("Watchtower (Spell/Tab)", new WorldPoint(2547, 3114, 0),
            ItemID.WATCHTOWER_TELEPORT, ItemID.LAW_RUNE),
    TROLLHEIM("Trollheim (Spell)", new WorldPoint(2891, 3678, 0),
            ItemID.LAW_RUNE),

    // Amulet of Glory teleports
    EDGEVILLE("Edgeville (Glory)", new WorldPoint(3087, 3496, 0),
            ItemID.AMULET_OF_GLORY, ItemID.AMULET_OF_GLORY1, ItemID.AMULET_OF_GLORY2,
            ItemID.AMULET_OF_GLORY3, ItemID.AMULET_OF_GLORY4, ItemID.AMULET_OF_GLORY5,
            ItemID.AMULET_OF_GLORY6, ItemID.AMULET_OF_ETERNAL_GLORY),
    KARAMJA("Karamja (Glory)", new WorldPoint(2918, 3176, 0),
            ItemID.AMULET_OF_GLORY, ItemID.AMULET_OF_GLORY1, ItemID.AMULET_OF_GLORY2,
            ItemID.AMULET_OF_GLORY3, ItemID.AMULET_OF_GLORY4, ItemID.AMULET_OF_GLORY5,
            ItemID.AMULET_OF_GLORY6, ItemID.AMULET_OF_ETERNAL_GLORY),
    DRAYNOR_VILLAGE("Draynor Village (Glory)", new WorldPoint(3105, 3251, 0),
            ItemID.AMULET_OF_GLORY, ItemID.AMULET_OF_GLORY1, ItemID.AMULET_OF_GLORY2,
            ItemID.AMULET_OF_GLORY3, ItemID.AMULET_OF_GLORY4, ItemID.AMULET_OF_GLORY5,
            ItemID.AMULET_OF_GLORY6, ItemID.AMULET_OF_ETERNAL_GLORY),
    AL_KHARID("Al-Kharid (Glory)", new WorldPoint(3293, 3163, 0),
            ItemID.AMULET_OF_GLORY, ItemID.AMULET_OF_GLORY1, ItemID.AMULET_OF_GLORY2,
            ItemID.AMULET_OF_GLORY3, ItemID.AMULET_OF_GLORY4, ItemID.AMULET_OF_GLORY5,
            ItemID.AMULET_OF_GLORY6, ItemID.AMULET_OF_ETERNAL_GLORY),

    // Ring of Wealth
    GRAND_EXCHANGE("Grand Exchange (Wealth)", new WorldPoint(3162, 3480, 0),
            ItemID.RING_OF_WEALTH, ItemID.RING_OF_WEALTH_1, ItemID.RING_OF_WEALTH_2,
            ItemID.RING_OF_WEALTH_3, ItemID.RING_OF_WEALTH_4, ItemID.RING_OF_WEALTH_5),
    FALADOR_PARK("Falador Park (Wealth)", new WorldPoint(2995, 3375, 0),
            ItemID.RING_OF_WEALTH, ItemID.RING_OF_WEALTH_1, ItemID.RING_OF_WEALTH_2,
            ItemID.RING_OF_WEALTH_3, ItemID.RING_OF_WEALTH_4, ItemID.RING_OF_WEALTH_5),
    MISCELLANIA("Miscellania (Wealth)", new WorldPoint(2535, 3862, 0),
            ItemID.RING_OF_WEALTH, ItemID.RING_OF_WEALTH_1, ItemID.RING_OF_WEALTH_2,
            ItemID.RING_OF_WEALTH_3, ItemID.RING_OF_WEALTH_4, ItemID.RING_OF_WEALTH_5),

    // Combat Bracelet
    WARRIORS_GUILD("Warriors' Guild (Combat Brace)", new WorldPoint(2883, 3549, 0),
            ItemID.COMBAT_BRACELET, ItemID.COMBAT_BRACELET1, ItemID.COMBAT_BRACELET2,
            ItemID.COMBAT_BRACELET3, ItemID.COMBAT_BRACELET4, ItemID.COMBAT_BRACELET5, ItemID.COMBAT_BRACELET6),
    CHAMPIONS_GUILD("Champions' Guild (Combat Brace)", new WorldPoint(3189, 3368, 0),
            ItemID.COMBAT_BRACELET, ItemID.COMBAT_BRACELET1, ItemID.COMBAT_BRACELET2,
            ItemID.COMBAT_BRACELET3, ItemID.COMBAT_BRACELET4, ItemID.COMBAT_BRACELET5, ItemID.COMBAT_BRACELET6),
    MONASTERY("Monastery (Combat Brace)", new WorldPoint(3053, 3487, 0),
            ItemID.COMBAT_BRACELET, ItemID.COMBAT_BRACELET1, ItemID.COMBAT_BRACELET2,
            ItemID.COMBAT_BRACELET3, ItemID.COMBAT_BRACELET4, ItemID.COMBAT_BRACELET5, ItemID.COMBAT_BRACELET6),
    RANGING_GUILD("Ranging Guild (Combat Brace)", new WorldPoint(2654, 3441, 0),
            ItemID.COMBAT_BRACELET, ItemID.COMBAT_BRACELET1, ItemID.COMBAT_BRACELET2,
            ItemID.COMBAT_BRACELET3, ItemID.COMBAT_BRACELET4, ItemID.COMBAT_BRACELET5, ItemID.COMBAT_BRACELET6),

    // Skills Necklace
    FISHING_GUILD("Fishing Guild (Skills Neck)", new WorldPoint(2613, 3390, 0),
            ItemID.SKILLS_NECKLACE, ItemID.SKILLS_NECKLACE1, ItemID.SKILLS_NECKLACE2,
            ItemID.SKILLS_NECKLACE3, ItemID.SKILLS_NECKLACE4, ItemID.SKILLS_NECKLACE5, ItemID.SKILLS_NECKLACE6),
    MINING_GUILD("Mining Guild (Skills Neck)", new WorldPoint(3049, 9762, 0),
            ItemID.SKILLS_NECKLACE, ItemID.SKILLS_NECKLACE1, ItemID.SKILLS_NECKLACE2,
            ItemID.SKILLS_NECKLACE3, ItemID.SKILLS_NECKLACE4, ItemID.SKILLS_NECKLACE5, ItemID.SKILLS_NECKLACE6),
    CRAFTING_GUILD("Crafting Guild (Skills Neck)", new WorldPoint(2934, 3294, 0),
            ItemID.SKILLS_NECKLACE, ItemID.SKILLS_NECKLACE1, ItemID.SKILLS_NECKLACE2,
            ItemID.SKILLS_NECKLACE3, ItemID.SKILLS_NECKLACE4, ItemID.SKILLS_NECKLACE5, ItemID.SKILLS_NECKLACE6),
    COOKING_GUILD("Cooking Guild (Skills Neck)", new WorldPoint(3145, 3438, 0),
            ItemID.SKILLS_NECKLACE, ItemID.SKILLS_NECKLACE1, ItemID.SKILLS_NECKLACE2,
            ItemID.SKILLS_NECKLACE3, ItemID.SKILLS_NECKLACE4, ItemID.SKILLS_NECKLACE5, ItemID.SKILLS_NECKLACE6),
    WOODCUTTING_GUILD("Woodcutting Guild (Skills Neck)", new WorldPoint(1662, 3505, 0),
            ItemID.SKILLS_NECKLACE, ItemID.SKILLS_NECKLACE1, ItemID.SKILLS_NECKLACE2,
            ItemID.SKILLS_NECKLACE3, ItemID.SKILLS_NECKLACE4, ItemID.SKILLS_NECKLACE5, ItemID.SKILLS_NECKLACE6),
    FARMING_GUILD("Farming Guild (Skills Neck)", new WorldPoint(1249, 3717, 0),
            ItemID.SKILLS_NECKLACE, ItemID.SKILLS_NECKLACE1, ItemID.SKILLS_NECKLACE2,
            ItemID.SKILLS_NECKLACE3, ItemID.SKILLS_NECKLACE4, ItemID.SKILLS_NECKLACE5, ItemID.SKILLS_NECKLACE6),

    // Games Necklace
    BARBARIAN_ASSAULT("Barbarian Assault (Games Neck)", new WorldPoint(2520, 3571, 0),
            ItemID.GAMES_NECKLACE1, ItemID.GAMES_NECKLACE2, ItemID.GAMES_NECKLACE3,
            ItemID.GAMES_NECKLACE4, ItemID.GAMES_NECKLACE5, ItemID.GAMES_NECKLACE6,
            ItemID.GAMES_NECKLACE7, ItemID.GAMES_NECKLACE8),
    BURTHORPE("Burthorpe (Games Neck)", new WorldPoint(2898, 3554, 0),
            ItemID.GAMES_NECKLACE1, ItemID.GAMES_NECKLACE2, ItemID.GAMES_NECKLACE3,
            ItemID.GAMES_NECKLACE4, ItemID.GAMES_NECKLACE5, ItemID.GAMES_NECKLACE6,
            ItemID.GAMES_NECKLACE7, ItemID.GAMES_NECKLACE8),
    CORPOREAL_BEAST("Corporeal Beast (Games Neck)", new WorldPoint(2967, 4384, 0),
            ItemID.GAMES_NECKLACE1, ItemID.GAMES_NECKLACE2, ItemID.GAMES_NECKLACE3,
            ItemID.GAMES_NECKLACE4, ItemID.GAMES_NECKLACE5, ItemID.GAMES_NECKLACE6,
            ItemID.GAMES_NECKLACE7, ItemID.GAMES_NECKLACE8),
    WINTERTODT("Wintertodt (Games Neck)", new WorldPoint(1624, 3938, 0),
            ItemID.GAMES_NECKLACE1, ItemID.GAMES_NECKLACE2, ItemID.GAMES_NECKLACE3,
            ItemID.GAMES_NECKLACE4, ItemID.GAMES_NECKLACE5, ItemID.GAMES_NECKLACE6,
            ItemID.GAMES_NECKLACE7, ItemID.GAMES_NECKLACE8),

    // Ring of Dueling
    CASTLE_WARS("Castle Wars (Dueling)", new WorldPoint(2441, 3091, 0),
            ItemID.RING_OF_DUELING1, ItemID.RING_OF_DUELING2, ItemID.RING_OF_DUELING3,
            ItemID.RING_OF_DUELING4, ItemID.RING_OF_DUELING5, ItemID.RING_OF_DUELING6,
            ItemID.RING_OF_DUELING7, ItemID.RING_OF_DUELING8),
    FEROX_ENCLAVE("Ferox Enclave (Dueling)", new WorldPoint(3151, 3636, 0),
            ItemID.RING_OF_DUELING1, ItemID.RING_OF_DUELING2, ItemID.RING_OF_DUELING3,
            ItemID.RING_OF_DUELING4, ItemID.RING_OF_DUELING5, ItemID.RING_OF_DUELING6,
            ItemID.RING_OF_DUELING7, ItemID.RING_OF_DUELING8),

    // Digsite Pendant
    DIGSITE("Digsite (Pendant)", new WorldPoint(3339, 3445, 0),
            ItemID.DIGSITE_PENDANT_1, ItemID.DIGSITE_PENDANT_2, ItemID.DIGSITE_PENDANT_3,
            ItemID.DIGSITE_PENDANT_4, ItemID.DIGSITE_PENDANT_5),

    // Necklace of Passage
    WIZARDS_TOWER("Wizards' Tower (Passage)", new WorldPoint(3114, 3181, 0),
            ItemID.NECKLACE_OF_PASSAGE1, ItemID.NECKLACE_OF_PASSAGE2, ItemID.NECKLACE_OF_PASSAGE3,
            ItemID.NECKLACE_OF_PASSAGE4, ItemID.NECKLACE_OF_PASSAGE5),
    OUTPOST("Outpost (Passage)", new WorldPoint(2431, 3348, 0),
            ItemID.NECKLACE_OF_PASSAGE1, ItemID.NECKLACE_OF_PASSAGE2, ItemID.NECKLACE_OF_PASSAGE3,
            ItemID.NECKLACE_OF_PASSAGE4, ItemID.NECKLACE_OF_PASSAGE5),
    EAGLES_EYRIE("Eagles' Eyrie (Passage)", new WorldPoint(3406, 3157, 0),
            ItemID.NECKLACE_OF_PASSAGE1, ItemID.NECKLACE_OF_PASSAGE2, ItemID.NECKLACE_OF_PASSAGE3,
            ItemID.NECKLACE_OF_PASSAGE4, ItemID.NECKLACE_OF_PASSAGE5),

    // Special Items
    ECTOPHIAL("Ectofuntus (Ectophial)", new WorldPoint(3660, 3522, 0),
            ItemID.ECTOPHIAL),
    XERICS_GLADE("Xeric's Glade (Talisman)", new WorldPoint(1754, 3564, 0),
            ItemID.XERICS_TALISMAN),
    XERICS_INFERNO("Xeric's Inferno (Talisman)", new WorldPoint(1504, 3819, 0),
            ItemID.XERICS_TALISMAN),
    XERICS_HEART("Xeric's Heart (Talisman)", new WorldPoint(1641, 3670, 0),
            ItemID.XERICS_TALISMAN),
    XERICS_LOOKOUT("Xeric's Lookout (Talisman)", new WorldPoint(1576, 3528, 0),
            ItemID.XERICS_TALISMAN),

    // Achievement Diary
    ARDOUGNE_FARM("Ardougne Farm (Ardy Cloak)", new WorldPoint(2673, 3375, 0),
            ItemID.ARDOUGNE_CLOAK_1, ItemID.ARDOUGNE_CLOAK_2, ItemID.ARDOUGNE_CLOAK_3, ItemID.ARDOUGNE_CLOAK_4),
    EXPLORERS_RING("Cabbage Patch (Explorer's Ring)", new WorldPoint(3052, 3292, 0),
            ItemID.EXPLORERS_RING_1, ItemID.EXPLORERS_RING_2, ItemID.EXPLORERS_RING_3, ItemID.EXPLORERS_RING_4),

    // Teleport Scrolls
    LUMBERYARD("Lumberyard (Scroll)", new WorldPoint(3303, 3487, 0),
            ItemID.LUMBERYARD_TELEPORT),
    MORTTON("Mort'ton (Scroll)", new WorldPoint(3489, 3288, 0),
            ItemID.MORTTON_TELEPORT),
    TAI_BWO_WANNAI("Tai Bwo Wannai (Scroll)", new WorldPoint(2788, 3066, 0),
            ItemID.TAI_BWO_WANNAI_TELEPORT),
    FELDIP_HILLS("Feldip Hills (Scroll)", new WorldPoint(2542, 2925, 0),
            ItemID.FELDIP_HILLS_TELEPORT),
    MOS_LEHARMLESS("Mos Le'Harmless (Scroll)", new WorldPoint(3701, 2996, 0),
            ItemID.MOS_LEHARMLESS_TELEPORT),
    PISCATORIS("Piscatoris (Scroll)", new WorldPoint(2339, 3648, 0),
            ItemID.PISCATORIS_TELEPORT),
    NARDAH("Nardah (Scroll)", new WorldPoint(3421, 2917, 0),
            ItemID.NARDAH_TELEPORT),
    WATSON("Watson (Scroll)", new WorldPoint(1645, 3579, 0),
            ItemID.WATSON_TELEPORT),

    // Fairy Rings (no items - needs a dramen or lunar staff)
    // Labels follow RuneLite's FairyRing enum. Rings added in 2026-11 take their
    // coordinates from RuneLite's world map FairyRingLocation enum (client 1.13.6).
    FAIRY_AIQ("Mudskipper Point (Fairy Ring AIQ)", new WorldPoint(2996, 3114, 0)),
    FAIRY_AIR("Island SE of Ardougne (Fairy Ring AIR)", new WorldPoint(2700, 3247, 0)),
    FAIRY_AIS("Auburn Valley (Fairy Ring AIS)", new WorldPoint(1430, 3323, 0)),
    FAIRY_AJP("Avium Savannah (Fairy Ring AJP)", new WorldPoint(1648, 3011, 0)),
    FAIRY_AJQ("Cave S of Dorgesh-Kaan (Fairy Ring AJQ)", new WorldPoint(2735, 5221, 0)),
    FAIRY_AJR("Slayer Cave (Fairy Ring AJR)", new WorldPoint(2780, 3613, 0)),
    FAIRY_AJS("Penguins (Fairy Ring AJS)", new WorldPoint(2500, 3896, 0)),
    FAIRY_AKP("Necropolis (Fairy Ring AKP)", new WorldPoint(3283, 2704, 0)),
    FAIRY_AKQ("Piscatoris (Fairy Ring AKQ)", new WorldPoint(2319, 3619, 0)),
    FAIRY_AKR("Hosidius Vinery (Fairy Ring AKR)", new WorldPoint(1823, 3539, 0)),
    FAIRY_AKS("Feldip Hunter (Fairy Ring AKS)", new WorldPoint(2571, 2956, 0)),
    FAIRY_ALP("Lighthouse (Fairy Ring ALP)", new WorldPoint(2503, 3636, 0)),
    FAIRY_ALQ("Haunted Woods (Fairy Ring ALQ)", new WorldPoint(3597, 3495, 0)),
    FAIRY_ALS("McGrubor's Wood (Fairy Ring ALS)", new WorldPoint(2644, 3495, 0)),
    FAIRY_BIP("Island SW of Mort Myre (Fairy Ring BIP)", new WorldPoint(3410, 3324, 0)),
    FAIRY_BIQ("Kalphite Hive (Fairy Ring BIQ)", new WorldPoint(3251, 3095, 0)),
    FAIRY_BIS("Ardougne Zoo (Fairy Ring BIS)", new WorldPoint(2635, 3268, 0)),
    FAIRY_BJP("Isle of Souls (Fairy Ring BJP)", new WorldPoint(2264, 2976, 0)),
    FAIRY_BJR("Fisher Realm (Fairy Ring BJR)", new WorldPoint(2650, 4730, 0)),
    FAIRY_BJS("Zul-Andra (Fairy Ring BJS)", new WorldPoint(2147, 3069, 0)),
    FAIRY_BKP("S of Castle Wars (Fairy Ring BKP)", new WorldPoint(2385, 3035, 0)),
    FAIRY_BKQ("Enchanted Valley (Fairy Ring BKQ)", new WorldPoint(3041, 4532, 0)),
    FAIRY_BKR("Mort Myre (Fairy Ring BKR)", new WorldPoint(3469, 3431, 0)),
    FAIRY_BKS("Zanaris (Fairy Ring BKS)", new WorldPoint(2412, 4434, 0)),
    FAIRY_BLP("TzHaar (Fairy Ring BLP)", new WorldPoint(2437, 5126, 0)),
    FAIRY_BLR("Legends' Guild (Fairy Ring BLR)", new WorldPoint(2739, 3353, 0)),
    FAIRY_BLS("S of Mount Quidamortem (Fairy Ring BLS)", new WorldPoint(1293, 3495, 0)),
    FAIRY_CIP("Miscellania (Fairy Ring CIP)", new WorldPoint(2513, 3884, 0)),
    FAIRY_CIQ("NW Yanille (Fairy Ring CIQ)", new WorldPoint(2528, 3127, 0)),
    FAIRY_CIR("NE of Farming Guild (Fairy Ring CIR)", new WorldPoint(1303, 3762, 0)),
    FAIRY_CIS("N of Arceuus Library (Fairy Ring CIS)", new WorldPoint(1636, 3869, 0)),
    FAIRY_CJQ("The Great Conch (Fairy Ring CJQ)", new WorldPoint(3178, 2445, 0)),
    FAIRY_CJR("Sinclair Mansion (Fairy Ring CJR)", new WorldPoint(2705, 3576, 0)),
    FAIRY_CKP("Cosmic Entity's Plane (Fairy Ring CKP)", new WorldPoint(2075, 4848, 0)),
    FAIRY_CKQ("Aldarin (Fairy Ring CKQ)", new WorldPoint(1358, 2943, 0)),
    FAIRY_CKR("S of Tai Bwo Wannai (Fairy Ring CKR)", new WorldPoint(2801, 3003, 0)),
    FAIRY_CKS("Canifis (Fairy Ring CKS)", new WorldPoint(3447, 3470, 0)),
    FAIRY_CLP("Draynor Island (Fairy Ring CLP)", new WorldPoint(3082, 3206, 0)),
    FAIRY_CLR("Ape Atoll (Fairy Ring CLR)", new WorldPoint(2737, 2739, 0)),
    FAIRY_CLS("Hazelmere's Island (Fairy Ring CLS)", new WorldPoint(2681, 3083, 0)),
    FAIRY_DIS("Wizards' Tower (Fairy Ring DIS)", new WorldPoint(3108, 3149, 0)),
    FAIRY_DJP("Tower of Life (Fairy Ring DJP)", new WorldPoint(2658, 3230, 0)),
    FAIRY_DJR("Chasm of Fire (Fairy Ring DJR)", new WorldPoint(1452, 3659, 0)),
    FAIRY_DKP("S of Musa Point (Fairy Ring DKP)", new WorldPoint(2900, 3111, 0)),
    FAIRY_DKR("Edgeville (Fairy Ring DKR)", new WorldPoint(3131, 3496, 0)),
    FAIRY_DKS("Polar Hunter Area (Fairy Ring DKS)", new WorldPoint(2744, 3719, 0)),
    FAIRY_DLP("Grimstone Dungeon (Fairy Ring DLP)", new WorldPoint(2923, 10455, 0)),
    FAIRY_DLQ("N of Nardah (Fairy Ring DLQ)", new WorldPoint(3423, 3016, 0)),
    FAIRY_DLR("Poison Waste (Fairy Ring DLR)", new WorldPoint(2213, 3099, 0)),

    // House Teleport Tabs (Redirected)
    HOUSE_RIMMINGTON("Rimmington (House Tab)", new WorldPoint(2954, 3224, 0),
            ItemID.RIMMINGTON_TELEPORT),
    HOUSE_TAVERLEY("Taverley (House Tab)", new WorldPoint(2894, 3465, 0),
            ItemID.TAVERLEY_TELEPORT),
    HOUSE_POLLNIVNEACH("Pollnivneach (House Tab)", new WorldPoint(3340, 3003, 0),
            ItemID.POLLNIVNEACH_TELEPORT),
    HOUSE_RELLEKKA("Rellekka (House Tab)", new WorldPoint(2670, 3632, 0),
            ItemID.RELLEKKA_TELEPORT),
    HOUSE_BRIMHAVEN("Brimhaven (House Tab)", new WorldPoint(2750, 3178, 0),
            ItemID.BRIMHAVEN_TELEPORT),
    HOUSE_YANILLE("Yanille (House Tab)", new WorldPoint(2544, 3095, 0),
            ItemID.YANILLE_TELEPORT),
    HOUSE_HOSIDIUS("Hosidius (House Tab)", new WorldPoint(1744, 3517, 0),
            ItemID.HOSIDIUS_TELEPORT),
    HOUSE_TROLLHEIM("Trollheim (House Tab)", new WorldPoint(2891, 3678, 0),
            ItemID.TROLLHEIM_TELEPORT),
    HOUSE_PRIFDDINAS("Prifddinas (House Tab)", new WorldPoint(3264, 6065, 0),
            ItemID.PRIFDDINAS_TELEPORT),

    // Quetzal Whistle (Varlamore transport)
    QUETZAL_ALDARIN("Aldarin (Quetzal)", new WorldPoint(1389, 2899, 0),
            ItemID.BASIC_QUETZAL_WHISTLE, ItemID.ENHANCED_QUETZAL_WHISTLE, ItemID.PERFECTED_QUETZAL_WHISTLE),
    QUETZAL_AUBURNVALE("Auburnvale (Quetzal)", new WorldPoint(1410, 3363, 0),
            ItemID.BASIC_QUETZAL_WHISTLE, ItemID.ENHANCED_QUETZAL_WHISTLE, ItemID.PERFECTED_QUETZAL_WHISTLE),
    QUETZAL_CIVITAS("Civitas illa Fortis (Quetzal)", new WorldPoint(1696, 3141, 0),
            ItemID.BASIC_QUETZAL_WHISTLE, ItemID.ENHANCED_QUETZAL_WHISTLE, ItemID.PERFECTED_QUETZAL_WHISTLE),
    QUETZAL_HUNTER_GUILD("Hunter Guild (Quetzal)", new WorldPoint(1584, 3055, 0),
            ItemID.BASIC_QUETZAL_WHISTLE, ItemID.ENHANCED_QUETZAL_WHISTLE, ItemID.PERFECTED_QUETZAL_WHISTLE),
    QUETZAL_GORGE("Quetzacalli Gorge (Quetzal)", new WorldPoint(1512, 3222, 0),
            ItemID.BASIC_QUETZAL_WHISTLE, ItemID.ENHANCED_QUETZAL_WHISTLE, ItemID.PERFECTED_QUETZAL_WHISTLE),
    QUETZAL_SUNSET_COAST("Sunset Coast (Quetzal)", new WorldPoint(1548, 2997, 0),
            ItemID.BASIC_QUETZAL_WHISTLE, ItemID.ENHANCED_QUETZAL_WHISTLE, ItemID.PERFECTED_QUETZAL_WHISTLE),
    QUETZAL_TAL_TEKLAN("Tal Teklan (Quetzal)", new WorldPoint(1225, 3089, 0),
            ItemID.BASIC_QUETZAL_WHISTLE, ItemID.ENHANCED_QUETZAL_WHISTLE, ItemID.PERFECTED_QUETZAL_WHISTLE),
    QUETZAL_TEOMAT("The Teomat (Quetzal)", new WorldPoint(1437, 3169, 0),
            ItemID.BASIC_QUETZAL_WHISTLE, ItemID.ENHANCED_QUETZAL_WHISTLE, ItemID.PERFECTED_QUETZAL_WHISTLE),
    QUETZAL_CAM_TORUM("Cam Torum (Quetzal)", new WorldPoint(1448, 3108, 0),
            ItemID.BASIC_QUETZAL_WHISTLE, ItemID.ENHANCED_QUETZAL_WHISTLE, ItemID.PERFECTED_QUETZAL_WHISTLE),
    QUETZAL_WYRM_REMAINS("Colossal Wyrm Remains (Quetzal)", new WorldPoint(1672, 2933, 0),
            ItemID.BASIC_QUETZAL_WHISTLE, ItemID.ENHANCED_QUETZAL_WHISTLE, ItemID.PERFECTED_QUETZAL_WHISTLE),
    QUETZAL_COLOSSEUM("Fortis Colosseum (Quetzal)", new WorldPoint(1776, 3111, 0),
            ItemID.BASIC_QUETZAL_WHISTLE, ItemID.ENHANCED_QUETZAL_WHISTLE, ItemID.PERFECTED_QUETZAL_WHISTLE),
    QUETZAL_KASTORI("Kastori (Quetzal)", new WorldPoint(1343, 3020, 0),
            ItemID.BASIC_QUETZAL_WHISTLE, ItemID.ENHANCED_QUETZAL_WHISTLE, ItemID.PERFECTED_QUETZAL_WHISTLE),
    QUETZAL_OUTER_FORTIS("Outer Fortis (Quetzal)", new WorldPoint(1703, 3037, 0),
            ItemID.BASIC_QUETZAL_WHISTLE, ItemID.ENHANCED_QUETZAL_WHISTLE, ItemID.PERFECTED_QUETZAL_WHISTLE),
    QUETZAL_SALVAGER("Salvager Overlook (Quetzal)", new WorldPoint(1613, 3302, 0),
            ItemID.BASIC_QUETZAL_WHISTLE, ItemID.ENHANCED_QUETZAL_WHISTLE, ItemID.PERFECTED_QUETZAL_WHISTLE),

    // Arceuus Tablets (tradeable on GE)
    ARCEUUS_LIBRARY("Arceuus Library (Tablet)", new WorldPoint(1632, 3837, 0),
            ItemID.ARCEUUS_LIBRARY_TELEPORT),
    DRAYNOR_MANOR("Draynor Manor (Tablet)", new WorldPoint(3107, 3354, 0),
            ItemID.DRAYNOR_MANOR_TELEPORT),
    MIND_ALTAR("Mind Altar (Tablet)", new WorldPoint(2979, 3510, 0),
            ItemID.MIND_ALTAR_TELEPORT),
    SALVE_GRAVEYARD("Salve Graveyard (Tablet)", new WorldPoint(3433, 3461, 0),
            ItemID.SALVE_GRAVEYARD_TELEPORT),
    FENKENSTRAINS_CASTLE("Fenkenstrain's Castle (Tablet)", new WorldPoint(3546, 3530, 0),
            ItemID.FENKENSTRAINS_CASTLE_TELEPORT),
    WEST_ARDOUGNE("West Ardougne (Tablet)", new WorldPoint(2500, 3290, 0),
            ItemID.WEST_ARDOUGNE_TELEPORT),
    HARMONY_ISLAND("Harmony Island (Tablet)", new WorldPoint(3797, 2866, 0),
            ItemID.HARMONY_ISLAND_TELEPORT),
    CEMETERY("Cemetery (Tablet)", new WorldPoint(2978, 3763, 0),
            ItemID.CEMETERY_TELEPORT),
    BARROWS_TABLET("Barrows (Tablet)", new WorldPoint(3564, 3315, 0),
            ItemID.BARROWS_TELEPORT),
    BATTLEFRONT("Battlefront (Tablet)", new WorldPoint(1347, 3740, 0),
            ItemID.BATTLEFRONT_TELEPORT),
    APE_ATOLL("Ape Atoll (Tablet)", new WorldPoint(2798, 2799, 0),
            ItemID.APE_ATOLL_TELEPORT),
    IORWERTH_CAMP("Iorwerth Camp (Tablet)", new WorldPoint(2191, 3263, 0),
            ItemID.IORWERTH_CAMP_TELEPORT),
    KOUREND_CASTLE("Kourend Castle (Tablet)", new WorldPoint(1639, 3676, 0),
            ItemID.KOUREND_CASTLE_TELEPORT),
    CIVITAS_ILLA_FORTIS("Civitas illa Fortis (Tablet)", new WorldPoint(1679, 3136, 0),
            ItemID.CIVITAS_ILLA_FORTIS_TELEPORT),

    // Teleport Crystal
    PRIFDDINAS("Prifddinas (Crystal)", new WorldPoint(3264, 6065, 0),
            ItemID.ETERNAL_TELEPORT_CRYSTAL, ItemID.TELEPORT_CRYSTAL,
            ItemID.TELEPORT_CRYSTAL_5, ItemID.TELEPORT_CRYSTAL_4,
            ItemID.TELEPORT_CRYSTAL_3, ItemID.TELEPORT_CRYSTAL_2,
            ItemID.TELEPORT_CRYSTAL_1),

    // Spirit Trees (no items - interact with tree directly)
    SPIRIT_TREE_GNOME_STRONGHOLD("Gnome Stronghold (Spirit Tree)", new WorldPoint(2459, 3446, 0)),
    SPIRIT_TREE_GNOME_VILLAGE("Gnome Village (Spirit Tree)", new WorldPoint(2538, 3166, 0)),
    SPIRIT_TREE_KHAZARD("Battlefield of Khazard (Spirit Tree)", new WorldPoint(2554, 3259, 0)),
    SPIRIT_TREE_GRAND_EXCHANGE("Grand Exchange (Spirit Tree)", new WorldPoint(3184, 3510, 0)),
    SPIRIT_TREE_FELDIP("Feldip Hills (Spirit Tree)", new WorldPoint(2486, 2850, 0)),
    SPIRIT_TREE_POISON_WASTE("Poison Waste (Spirit Tree)", new WorldPoint(2339, 3111, 0)),
    SPIRIT_TREE_ETCETERIA("Etceteria (Spirit Tree)", new WorldPoint(2613, 3858, 0)),
    SPIRIT_TREE_PORT_SARIM("Port Sarim (Spirit Tree)", new WorldPoint(3060, 3258, 0)),
    SPIRIT_TREE_BRIMHAVEN("Brimhaven (Spirit Tree)", new WorldPoint(2802, 3203, 0)),
    SPIRIT_TREE_HOSIDIUS("Hosidius (Spirit Tree)", new WorldPoint(1693, 3542, 0)),
    SPIRIT_TREE_FARMING_GUILD("Farming Guild (Spirit Tree)", new WorldPoint(1253, 3750, 0)),

    // Wilderness Levers (no items - lever pull)
    EDGEVILLE_LEVER("Edgeville Dungeon Lever (Deserted Keep)", new WorldPoint(3154, 3924, 0)),
    ARDOUGNE_LEVER("Ardougne Lever (Deserted Keep)", new WorldPoint(3154, 3924, 0)),

    // Minigame Teleports (no items - grouping menu)
    MINIGAME_BARBARIAN_ASSAULT("Barbarian Assault (Minigame)", new WorldPoint(2521, 3570, 0)),
    MINIGAME_BLAST_FURNACE("Blast Furnace (Minigame)", new WorldPoint(2930, 10193, 0)),
    MINIGAME_CASTLE_WARS("Castle Wars (Minigame)", new WorldPoint(2442, 3093, 0)),
    MINIGAME_CLAN_WARS("Clan Wars (Minigame)", new WorldPoint(3149, 3634, 0)),
    MINIGAME_FISHING_TRAWLER("Fishing Trawler (Minigame)", new WorldPoint(2658, 3157, 0)),
    MINIGAME_GIANTS_FOUNDRY("Giants' Foundry (Minigame)", new WorldPoint(3361, 3149, 0)),
    MINIGAME_GUARDIANS_OF_THE_RIFT("Guardians of the Rift (Minigame)", new WorldPoint(3615, 9477, 0)),
    MINIGAME_MAGE_TRAINING_ARENA("Mage Training Arena (Minigame)", new WorldPoint(3363, 3295, 0)),
    MINIGAME_NIGHTMARE_ZONE("Nightmare Zone (Minigame)", new WorldPoint(2609, 3114, 0)),
    MINIGAME_PEST_CONTROL("Pest Control (Minigame)", new WorldPoint(2658, 2663, 0)),
    MINIGAME_RAT_PITS_ARDOUGNE("Rat Pits Ardougne (Minigame)", new WorldPoint(2561, 3320, 0)),
    MINIGAME_RAT_PITS_VARROCK("Rat Pits Varrock (Minigame)", new WorldPoint(3267, 3400, 0)),
    MINIGAME_RAT_PITS_PORT_SARIM("Rat Pits Port Sarim (Minigame)", new WorldPoint(3018, 3232, 0)),
    MINIGAME_SHADES_OF_MORTTON("Shades of Mort'ton (Minigame)", new WorldPoint(3506, 3304, 0)),
    MINIGAME_SORCERESS_GARDEN("Sorceress's Garden (Minigame)", new WorldPoint(3321, 3138, 0)),
    MINIGAME_SOUL_WARS("Soul Wars (Minigame)", new WorldPoint(2018, 5929, 0)),
    MINIGAME_TITHE_FARM("Tithe Farm (Minigame)", new WorldPoint(2399, 5177, 0)),
    MINIGAME_TROUBLE_BREWING("Trouble Brewing (Minigame)", new WorldPoint(3801, 3015, 0)),
    MINIGAME_MASTERING_MIXOLOGY("Mastering Mixology (Minigame)", new WorldPoint(1388, 2918, 0)),

    // Burning Amulet (Wilderness)
    CHAOS_TEMPLE("Chaos Temple (Burning)", new WorldPoint(3234, 3634, 0),
            ItemID.BURNING_AMULET5, ItemID.BURNING_AMULET4, ItemID.BURNING_AMULET3,
            ItemID.BURNING_AMULET2, ItemID.BURNING_AMULET1),
    BANDIT_CAMP("Bandit Camp (Burning)", new WorldPoint(3038, 3651, 0),
            ItemID.BURNING_AMULET5, ItemID.BURNING_AMULET4, ItemID.BURNING_AMULET3,
            ItemID.BURNING_AMULET2, ItemID.BURNING_AMULET1),
    LAVA_MAZE("Lava Maze (Burning)", new WorldPoint(3028, 3842, 0),
            ItemID.BURNING_AMULET5, ItemID.BURNING_AMULET4, ItemID.BURNING_AMULET3,
            ItemID.BURNING_AMULET2, ItemID.BURNING_AMULET1),

    // Slayer Ring
    STRONGHOLD_SLAYER_CAVE("Stronghold Slayer Cave (Slayer Ring)", new WorldPoint(2431, 3422, 0),
            ItemID.SLAYER_RING_ETERNAL, ItemID.SLAYER_RING_8, ItemID.SLAYER_RING_7,
            ItemID.SLAYER_RING_6, ItemID.SLAYER_RING_5, ItemID.SLAYER_RING_4,
            ItemID.SLAYER_RING_3, ItemID.SLAYER_RING_2, ItemID.SLAYER_RING_1),
    SLAYER_TOWER("Slayer Tower (Slayer Ring)", new WorldPoint(3421, 3537, 0),
            ItemID.SLAYER_RING_ETERNAL, ItemID.SLAYER_RING_8, ItemID.SLAYER_RING_7,
            ItemID.SLAYER_RING_6, ItemID.SLAYER_RING_5, ItemID.SLAYER_RING_4,
            ItemID.SLAYER_RING_3, ItemID.SLAYER_RING_2, ItemID.SLAYER_RING_1),
    FREMENNIK_SLAYER_DUNGEON("Fremennik Slayer Dungeon (Slayer Ring)", new WorldPoint(2802, 9999, 0),
            ItemID.SLAYER_RING_ETERNAL, ItemID.SLAYER_RING_8, ItemID.SLAYER_RING_7,
            ItemID.SLAYER_RING_6, ItemID.SLAYER_RING_5, ItemID.SLAYER_RING_4,
            ItemID.SLAYER_RING_3, ItemID.SLAYER_RING_2, ItemID.SLAYER_RING_1),
    TARNS_LAIR("Tarn's Lair (Slayer Ring)", new WorldPoint(2028, 4636, 0),
            ItemID.SLAYER_RING_ETERNAL, ItemID.SLAYER_RING_8, ItemID.SLAYER_RING_7,
            ItemID.SLAYER_RING_6, ItemID.SLAYER_RING_5, ItemID.SLAYER_RING_4,
            ItemID.SLAYER_RING_3, ItemID.SLAYER_RING_2, ItemID.SLAYER_RING_1),
    DARK_BEASTS("Dark Beasts (Slayer Ring)", new WorldPoint(3185, 4601, 0),
            ItemID.SLAYER_RING_ETERNAL, ItemID.SLAYER_RING_8, ItemID.SLAYER_RING_7,
            ItemID.SLAYER_RING_6, ItemID.SLAYER_RING_5, ItemID.SLAYER_RING_4,
            ItemID.SLAYER_RING_3, ItemID.SLAYER_RING_2, ItemID.SLAYER_RING_1),

    // Drakan's Medallion (Morytania)
    VER_SINHAZA("Ver Sinhaza (Drakan's)", new WorldPoint(3649, 3230, 0),
            ItemID.DRAKANS_MEDALLION),
    DARKMEYER("Darkmeyer (Drakan's)", new WorldPoint(3592, 3337, 0),
            ItemID.DRAKANS_MEDALLION),

    // Kharedst's Memoirs / Book of the Dead (Kourend)
    HOSIDIUS("Lunch by the Lancalliums (Memoirs/Hosidius)", new WorldPoint(1520, 3478, 0),
            ItemID.KHAREDSTS_MEMOIRS, ItemID.BOOK_OF_THE_DEAD),
    PISCARILIUS("The Fisher's Flute (Memoirs/Piscarilius)", new WorldPoint(1713, 3612, 0),
            ItemID.KHAREDSTS_MEMOIRS, ItemID.BOOK_OF_THE_DEAD),
    SHAYZIEN("History and Hearsay (Memoirs/Shayzien)", new WorldPoint(1804, 3748, 0),
            ItemID.KHAREDSTS_MEMOIRS, ItemID.BOOK_OF_THE_DEAD),
    LOVAKENGJ("Jewellery of Jubilation (Memoirs/Lovakengj)", new WorldPoint(1478, 3576, 0),
            ItemID.KHAREDSTS_MEMOIRS, ItemID.BOOK_OF_THE_DEAD),
    ARCEUUS("A Dark Disposition (Memoirs/Arceuus)", new WorldPoint(1681, 3747, 0),
            ItemID.KHAREDSTS_MEMOIRS, ItemID.BOOK_OF_THE_DEAD),

    // Stony Basalt / Icy Basalt
    TROLL_STRONGHOLD("Troll Stronghold (Basalt)", new WorldPoint(2845, 3694, 0),
            ItemID.STONY_BASALT),
    WEISS("Weiss (Basalt)", new WorldPoint(2846, 3938, 0),
            ItemID.ICY_BASALT),

    // Pharaoh's Sceptre (Desert)
    PYRAMID_PLUNDER("Pyramid Plunder (Sceptre)", new WorldPoint(1934, 4428, 0),
            ItemID.PHARAOHS_SCEPTRE, ItemID.PHARAOHS_SCEPTRE_9045, ItemID.PHARAOHS_SCEPTRE_9046,
            ItemID.PHARAOHS_SCEPTRE_9047, ItemID.PHARAOHS_SCEPTRE_9048, ItemID.PHARAOHS_SCEPTRE_9049,
            ItemID.PHARAOHS_SCEPTRE_9050, ItemID.PHARAOHS_SCEPTRE_9051,
            ItemID.PHARAOHS_SCEPTRE_UNCHARGED, ItemID.PHARAOHS_SCEPTRE_26948, ItemID.PHARAOHS_SCEPTRE_26950),
    AGILITY_PYRAMID("Agility Pyramid (Sceptre)", new WorldPoint(3341, 2827, 0),
            ItemID.PHARAOHS_SCEPTRE, ItemID.PHARAOHS_SCEPTRE_9045, ItemID.PHARAOHS_SCEPTRE_9046,
            ItemID.PHARAOHS_SCEPTRE_9047, ItemID.PHARAOHS_SCEPTRE_9048, ItemID.PHARAOHS_SCEPTRE_9049,
            ItemID.PHARAOHS_SCEPTRE_9050, ItemID.PHARAOHS_SCEPTRE_9051,
            ItemID.PHARAOHS_SCEPTRE_UNCHARGED, ItemID.PHARAOHS_SCEPTRE_26948, ItemID.PHARAOHS_SCEPTRE_26950),
    ANCIENT_PYRAMID("Ancient Pyramid (Sceptre)", new WorldPoint(3232, 2897, 0),
            ItemID.PHARAOHS_SCEPTRE, ItemID.PHARAOHS_SCEPTRE_9045, ItemID.PHARAOHS_SCEPTRE_9046,
            ItemID.PHARAOHS_SCEPTRE_9047, ItemID.PHARAOHS_SCEPTRE_9048, ItemID.PHARAOHS_SCEPTRE_9049,
            ItemID.PHARAOHS_SCEPTRE_9050, ItemID.PHARAOHS_SCEPTRE_9051,
            ItemID.PHARAOHS_SCEPTRE_UNCHARGED, ItemID.PHARAOHS_SCEPTRE_26948, ItemID.PHARAOHS_SCEPTRE_26950);

    /**
     * How a teleport is used. Drives the "no item" toggles and the spellbook filter.
     */
    public enum Method {
        STANDARD_SPELL,
        ITEM,
        FAIRY_RING,
        SPIRIT_TREE,
        MINIGAME,
        LEVER
    }

    /**
     * Destinations inside the Wilderness (checked against the OSRS wiki: Burning amulet
     * lists level 15, 17 and 41; Cemetery Teleport lands at level 31; the levers lead to
     * the Deserted Keep). Ferox Enclave is a safe zone and is deliberately not listed.
     */
    private static final Set<TeleportData> WILDERNESS = Collections.unmodifiableSet(EnumSet.of(
            CHAOS_TEMPLE, BANDIT_CAMP, LAVA_MAZE, CEMETERY, EDGEVILLE_LEVER, ARDOUGNE_LEVER));

    private static final Set<TeleportData> STANDARD_SPELLS = Collections.unmodifiableSet(EnumSet.of(
            VARROCK, LUMBRIDGE, FALADOR, CAMELOT, ARDOUGNE, WATCHTOWER, TROLLHEIM));

    private final String name;
    private final WorldPoint location;
    private final List<Integer> itemIds;

    TeleportData(String name, WorldPoint location, int... itemIds) {
        this.name = name;
        this.location = location;
        List<Integer> ids = new ArrayList<>();
        for (int id : itemIds) {
            ids.add(id);
        }
        this.itemIds = Collections.unmodifiableList(ids);
    }

    /** Full display name, for example "Edgeville (Glory)". */
    public String getName() {
        return name;
    }

    public WorldPoint getLocation() {
        return location;
    }

    /** The place part of the name, for example "Edgeville". */
    public String getDestination() {
        int paren = name.lastIndexOf('(');
        return paren > 0 ? name.substring(0, paren).trim() : name;
    }

    /** The item or method part of the name, for example "Glory". Null when there is none. */
    public String getMethodLabel() {
        int paren = name.lastIndexOf('(');
        if (paren > 0 && name.endsWith(")")) {
            return name.substring(paren + 1, name.length() - 1);
        }
        return null;
    }

    public List<Integer> getItemIds() {
        return itemIds;
    }

    public Method getMethod() {
        if (STANDARD_SPELLS.contains(this)) {
            return Method.STANDARD_SPELL;
        }
        String id = name();
        if (id.startsWith("FAIRY_")) {
            return Method.FAIRY_RING;
        }
        if (id.startsWith("SPIRIT_TREE_")) {
            return Method.SPIRIT_TREE;
        }
        if (id.startsWith("MINIGAME_")) {
            return Method.MINIGAME;
        }
        if (id.endsWith("_LEVER")) {
            return Method.LEVER;
        }
        return Method.ITEM;
    }

    /** True for teleports that need no item (fairy rings, spirit trees, minigame teleports, levers). */
    public boolean isNoItem() {
        Method m = getMethod();
        return m == Method.FAIRY_RING || m == Method.SPIRIT_TREE || m == Method.MINIGAME || m == Method.LEVER;
    }

    public boolean isWilderness() {
        return WILDERNESS.contains(this);
    }
}
