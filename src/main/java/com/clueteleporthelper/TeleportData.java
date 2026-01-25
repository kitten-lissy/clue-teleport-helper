package com.clueteleporthelper;

import net.runelite.api.coords.WorldPoint;
import net.runelite.api.ItemID;
import java.util.*;

public enum TeleportData {
    // Normal Magic Teleports
    VARROCK("Varrock", new WorldPoint(3213, 3424, 0),
            ItemID.VARROCK_TELEPORT, ItemID.LAW_RUNE),
    LUMBRIDGE("Lumbridge", new WorldPoint(3222, 3218, 0),
            ItemID.LUMBRIDGE_TELEPORT, ItemID.LAW_RUNE),
    FALADOR("Falador", new WorldPoint(2965, 3381, 0),
            ItemID.FALADOR_TELEPORT, ItemID.LAW_RUNE),
    CAMELOT("Camelot", new WorldPoint(2757, 3477, 0),
            ItemID.CAMELOT_TELEPORT, ItemID.LAW_RUNE),
    ARDOUGNE("Ardougne", new WorldPoint(2664, 3306, 0),
            ItemID.ARDOUGNE_TELEPORT, ItemID.LAW_RUNE),
    WATCHTOWER("Watchtower", new WorldPoint(2547, 3114, 0)),
    TROLLHEIM("Trollheim", new WorldPoint(2891, 3678, 0)),

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
    WARRIORS_GUILD("Warriors' Guild", new WorldPoint(2883, 3549, 0),
            ItemID.COMBAT_BRACELET, ItemID.COMBAT_BRACELET1, ItemID.COMBAT_BRACELET2,
            ItemID.COMBAT_BRACELET3, ItemID.COMBAT_BRACELET4, ItemID.COMBAT_BRACELET5, ItemID.COMBAT_BRACELET6),
    CHAMPIONS_GUILD("Champions' Guild", new WorldPoint(3189, 3368, 0),
            ItemID.COMBAT_BRACELET, ItemID.COMBAT_BRACELET1, ItemID.COMBAT_BRACELET2,
            ItemID.COMBAT_BRACELET3, ItemID.COMBAT_BRACELET4, ItemID.COMBAT_BRACELET5, ItemID.COMBAT_BRACELET6),
    MONASTERY("Monastery", new WorldPoint(3053, 3487, 0),
            ItemID.COMBAT_BRACELET, ItemID.COMBAT_BRACELET1, ItemID.COMBAT_BRACELET2,
            ItemID.COMBAT_BRACELET3, ItemID.COMBAT_BRACELET4, ItemID.COMBAT_BRACELET5, ItemID.COMBAT_BRACELET6),
    RANGING_GUILD("Ranging Guild", new WorldPoint(2654, 3441, 0),
            ItemID.COMBAT_BRACELET, ItemID.COMBAT_BRACELET1, ItemID.COMBAT_BRACELET2,
            ItemID.COMBAT_BRACELET3, ItemID.COMBAT_BRACELET4, ItemID.COMBAT_BRACELET5, ItemID.COMBAT_BRACELET6),

    // Skills Necklace
    FISHING_GUILD("Fishing Guild", new WorldPoint(2613, 3390, 0),
            ItemID.SKILLS_NECKLACE, ItemID.SKILLS_NECKLACE1, ItemID.SKILLS_NECKLACE2,
            ItemID.SKILLS_NECKLACE3, ItemID.SKILLS_NECKLACE4, ItemID.SKILLS_NECKLACE5, ItemID.SKILLS_NECKLACE6),
    MINING_GUILD("Mining Guild", new WorldPoint(3049, 9762, 0),
            ItemID.SKILLS_NECKLACE, ItemID.SKILLS_NECKLACE1, ItemID.SKILLS_NECKLACE2,
            ItemID.SKILLS_NECKLACE3, ItemID.SKILLS_NECKLACE4, ItemID.SKILLS_NECKLACE5, ItemID.SKILLS_NECKLACE6),
    CRAFTING_GUILD("Crafting Guild", new WorldPoint(2934, 3294, 0),
            ItemID.SKILLS_NECKLACE, ItemID.SKILLS_NECKLACE1, ItemID.SKILLS_NECKLACE2,
            ItemID.SKILLS_NECKLACE3, ItemID.SKILLS_NECKLACE4, ItemID.SKILLS_NECKLACE5, ItemID.SKILLS_NECKLACE6),
    COOKING_GUILD("Cooking Guild", new WorldPoint(3145, 3438, 0),
            ItemID.SKILLS_NECKLACE, ItemID.SKILLS_NECKLACE1, ItemID.SKILLS_NECKLACE2,
            ItemID.SKILLS_NECKLACE3, ItemID.SKILLS_NECKLACE4, ItemID.SKILLS_NECKLACE5, ItemID.SKILLS_NECKLACE6),
    WOODCUTTING_GUILD("Woodcutting Guild", new WorldPoint(1662, 3505, 0),
            ItemID.SKILLS_NECKLACE, ItemID.SKILLS_NECKLACE1, ItemID.SKILLS_NECKLACE2,
            ItemID.SKILLS_NECKLACE3, ItemID.SKILLS_NECKLACE4, ItemID.SKILLS_NECKLACE5, ItemID.SKILLS_NECKLACE6),
    FARMING_GUILD("Farming Guild", new WorldPoint(1249, 3717, 0),
            ItemID.SKILLS_NECKLACE, ItemID.SKILLS_NECKLACE1, ItemID.SKILLS_NECKLACE2,
            ItemID.SKILLS_NECKLACE3, ItemID.SKILLS_NECKLACE4, ItemID.SKILLS_NECKLACE5, ItemID.SKILLS_NECKLACE6),

    // Games Necklace
    BARBARIAN_ASSAULT("Barbarian Assault", new WorldPoint(2520, 3571, 0),
            ItemID.GAMES_NECKLACE1, ItemID.GAMES_NECKLACE2, ItemID.GAMES_NECKLACE3,
            ItemID.GAMES_NECKLACE4, ItemID.GAMES_NECKLACE5, ItemID.GAMES_NECKLACE6,
            ItemID.GAMES_NECKLACE7, ItemID.GAMES_NECKLACE8),
    BURTHORPE("Burthorpe", new WorldPoint(2898, 3554, 0),
            ItemID.GAMES_NECKLACE1, ItemID.GAMES_NECKLACE2, ItemID.GAMES_NECKLACE3,
            ItemID.GAMES_NECKLACE4, ItemID.GAMES_NECKLACE5, ItemID.GAMES_NECKLACE6,
            ItemID.GAMES_NECKLACE7, ItemID.GAMES_NECKLACE8),
    CORPOREAL_BEAST("Corporeal Beast", new WorldPoint(2967, 4384, 0),
            ItemID.GAMES_NECKLACE1, ItemID.GAMES_NECKLACE2, ItemID.GAMES_NECKLACE3,
            ItemID.GAMES_NECKLACE4, ItemID.GAMES_NECKLACE5, ItemID.GAMES_NECKLACE6,
            ItemID.GAMES_NECKLACE7, ItemID.GAMES_NECKLACE8),
    WINTERTODT("Wintertodt", new WorldPoint(1624, 3938, 0),
            ItemID.GAMES_NECKLACE1, ItemID.GAMES_NECKLACE2, ItemID.GAMES_NECKLACE3,
            ItemID.GAMES_NECKLACE4, ItemID.GAMES_NECKLACE5, ItemID.GAMES_NECKLACE6,
            ItemID.GAMES_NECKLACE7, ItemID.GAMES_NECKLACE8),

    // Ring of Dueling
    CASTLE_WARS("Castle Wars", new WorldPoint(2441, 3091, 0),
            ItemID.RING_OF_DUELING1, ItemID.RING_OF_DUELING2, ItemID.RING_OF_DUELING3,
            ItemID.RING_OF_DUELING4, ItemID.RING_OF_DUELING5, ItemID.RING_OF_DUELING6,
            ItemID.RING_OF_DUELING7, ItemID.RING_OF_DUELING8),
    FEROX_ENCLAVE("Ferox Enclave", new WorldPoint(3151, 3636, 0),
            ItemID.RING_OF_DUELING1, ItemID.RING_OF_DUELING2, ItemID.RING_OF_DUELING3,
            ItemID.RING_OF_DUELING4, ItemID.RING_OF_DUELING5, ItemID.RING_OF_DUELING6,
            ItemID.RING_OF_DUELING7, ItemID.RING_OF_DUELING8),

    // Digsite Pendant
    DIGSITE("Digsite", new WorldPoint(3339, 3445, 0),
            ItemID.DIGSITE_PENDANT_1, ItemID.DIGSITE_PENDANT_2, ItemID.DIGSITE_PENDANT_3,
            ItemID.DIGSITE_PENDANT_4, ItemID.DIGSITE_PENDANT_5),

    // Necklace of Passage
    WIZARDS_TOWER("Wizards' Tower", new WorldPoint(3114, 3181, 0),
            ItemID.NECKLACE_OF_PASSAGE1, ItemID.NECKLACE_OF_PASSAGE2, ItemID.NECKLACE_OF_PASSAGE3,
            ItemID.NECKLACE_OF_PASSAGE4, ItemID.NECKLACE_OF_PASSAGE5),
    OUTPOST("Outpost", new WorldPoint(2431, 3348, 0),
            ItemID.NECKLACE_OF_PASSAGE1, ItemID.NECKLACE_OF_PASSAGE2, ItemID.NECKLACE_OF_PASSAGE3,
            ItemID.NECKLACE_OF_PASSAGE4, ItemID.NECKLACE_OF_PASSAGE5),
    EAGLES_EYRIE("Eagles' Eyrie", new WorldPoint(3406, 3157, 0),
            ItemID.NECKLACE_OF_PASSAGE1, ItemID.NECKLACE_OF_PASSAGE2, ItemID.NECKLACE_OF_PASSAGE3,
            ItemID.NECKLACE_OF_PASSAGE4, ItemID.NECKLACE_OF_PASSAGE5),

    // Special Items
    ECTOPHIAL("Ectofuntus", new WorldPoint(3660, 3522, 0),
            ItemID.ECTOPHIAL),
    XERICS_GLADE("Xeric's Glade", new WorldPoint(1754, 3564, 0),
            ItemID.XERICS_TALISMAN),
    XERICS_INFERNO("Xeric's Inferno", new WorldPoint(1504, 3819, 0),
            ItemID.XERICS_TALISMAN),
    XERICS_HEART("Xeric's Heart", new WorldPoint(1641, 3670, 0),
            ItemID.XERICS_TALISMAN),
    XERICS_LOOKOUT("Xeric's Lookout", new WorldPoint(1576, 3528, 0),
            ItemID.XERICS_TALISMAN),

    // Achievement Diary
    ARDOUGNE_FARM("Ardougne Farm", new WorldPoint(2673, 3375, 0),
            ItemID.ARDOUGNE_CLOAK_1, ItemID.ARDOUGNE_CLOAK_2, ItemID.ARDOUGNE_CLOAK_3, ItemID.ARDOUGNE_CLOAK_4),
    EXPLORERS_RING("Cabbage patch", new WorldPoint(3052, 3292, 0),
            ItemID.EXPLORERS_RING_1, ItemID.EXPLORERS_RING_2, ItemID.EXPLORERS_RING_3, ItemID.EXPLORERS_RING_4),

    // Teleport Scrolls
    LUMBERYARD("Lumberyard", new WorldPoint(3303, 3487, 0),
            ItemID.LUMBERYARD_TELEPORT),
    MORTTON("Mort'ton", new WorldPoint(3489, 3288, 0),
            ItemID.MORTTON_TELEPORT),
    TAI_BWO_WANNAI("Tai Bwo Wannai", new WorldPoint(2788, 3066, 0),
            ItemID.TAI_BWO_WANNAI_TELEPORT),
    FELDIP_HILLS("Feldip Hills", new WorldPoint(2542, 2925, 0),
            ItemID.FELDIP_HILLS_TELEPORT),
    MOS_LEHARMLESS("Mos Le'Harmless", new WorldPoint(3701, 2996, 0),
            ItemID.MOS_LEHARMLESS_TELEPORT),
    PISCATORIS("Piscatoris", new WorldPoint(2339, 3648, 0),
            ItemID.PISCATORIS_TELEPORT),
    NARDAH("Nardah", new WorldPoint(3421, 2917, 0),
            ItemID.NARDAH_TELEPORT),
    WATSON("Watson", new WorldPoint(1645, 3579, 0),
            ItemID.WATSON_TELEPORT),

    // Fairy Rings (ALL major ones)
    FAIRY_AIQ("Fairy Ring AIQ (Asgarnia)", new WorldPoint(2996, 3114, 0)),
    FAIRY_AIR("Fairy Ring AIR (Islands)", new WorldPoint(2700, 3247, 0)),
    FAIRY_AJQ("Fairy Ring AJQ (Dungeons)", new WorldPoint(2735, 5221, 0)),
    FAIRY_AJR("Fairy Ring AJR (Fremennik)", new WorldPoint(2780, 3613, 0)),
    FAIRY_AJS("Fairy Ring AJS (Penguins)", new WorldPoint(2500, 3896, 0)),
    FAIRY_AKQ("Fairy Ring AKQ (Piscatoris)", new WorldPoint(2319, 3619, 0)),
    FAIRY_AKS("Fairy Ring AKS (Feldip)", new WorldPoint(2571, 2956, 0)),
    FAIRY_ALP("Fairy Ring ALP (Lighthouse)", new WorldPoint(2503, 3636, 0)),
    FAIRY_ALQ("Fairy Ring ALQ (Morytania)", new WorldPoint(3597, 3495, 0)),
    FAIRY_ALS("Fairy Ring ALS (McGrubor)", new WorldPoint(2644, 3495, 0)),
    FAIRY_BIP("Fairy Ring BIP (Islands)", new WorldPoint(3410, 3324, 0)),
    FAIRY_BIQ("Fairy Ring BIQ (Kalphite)", new WorldPoint(3251, 3095, 0)),
    FAIRY_BJR("Fairy Ring BJR (Realm)", new WorldPoint(2650, 4730, 0)),
    FAIRY_BKP("Fairy Ring BKP (South Karamja)", new WorldPoint(2385, 3035, 0)),
    FAIRY_BKQ("Fairy Ring BKQ (Enchanted Valley)", new WorldPoint(3041, 4532, 0)),
    FAIRY_BKR("Fairy Ring BKR (Mort Myre)", new WorldPoint(3469, 3431, 0)),
    FAIRY_BKS("Fairy Ring BKS (Zanaris)", new WorldPoint(2412, 4434, 0)),
    FAIRY_BLP("Fairy Ring BLP (TzHaar)", new WorldPoint(2437, 5126, 0)),
    FAIRY_CIP("Fairy Ring CIP (Islands)", new WorldPoint(2513, 3884, 0)),
    FAIRY_CIQ("Fairy Ring CIQ (Northwest)", new WorldPoint(2528, 3127, 0)),
    FAIRY_CJR("Fairy Ring CJR (Sinclair)", new WorldPoint(2705, 3576, 0)),
    FAIRY_CKP("Fairy Ring CKP (Cosmic Altar)", new WorldPoint(2075, 4848, 0)),
    FAIRY_CKR("Fairy Ring CKR (Shilo)", new WorldPoint(2801, 3003, 0)),
    FAIRY_CKS("Fairy Ring CKS (Canifis)", new WorldPoint(3447, 3470, 0)),
    FAIRY_CLP("Fairy Ring CLP (South Islands)", new WorldPoint(3082, 3206, 0)),
    FAIRY_DIS("Fairy Ring DIS (Wizards')", new WorldPoint(3108, 3149, 0)),
    FAIRY_DJP("Fairy Ring DJP (Tower of Life)", new WorldPoint(2658, 3230, 0)),
    FAIRY_DJR("Fairy Ring DJR (Sinclair)", new WorldPoint(2676, 3587, 0)),
    FAIRY_DKP("Fairy Ring DKP (Gnome Village)", new WorldPoint(2900, 3111, 0)),
    FAIRY_DKR("Fairy Ring DKR (Edgeville)", new WorldPoint(3131, 3496, 0)),
    FAIRY_DKS("Fairy Ring DKS (Snowy)", new WorldPoint(2744, 3719, 0)),
    FAIRY_DLQ("Fairy Ring DLQ (North)", new WorldPoint(3423, 3016, 0)),
    FAIRY_DLR("Fairy Ring DLR (Poison Waste)", new WorldPoint(2213, 3099, 0));

    private final String name;
    private final WorldPoint location;
    private final List<Integer> itemIds;

    TeleportData(String name, WorldPoint location, int... itemIds) {
        this.name = name;
        this.location = location;
        this.itemIds = new ArrayList<>();
        for (int id : itemIds) {
            this.itemIds.add(id);
        }
    }

    public String getName() {
        return name;
    }

    public WorldPoint getLocation() {
        return location;
    }

    public String getDestination() {
        return name;
    }

    public List<Integer> getItemIds() {
        return itemIds;
    }
}