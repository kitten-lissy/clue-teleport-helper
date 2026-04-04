package com.clueteleporthelper;

import net.runelite.api.coords.WorldPoint;
import org.junit.Test;
import static org.junit.Assert.*;

public class LocationOverridesTest {

    private LocationOverrides.OverrideResult getOverride(int x, int y) {
        return LocationOverrides.getOverride(new WorldPoint(x, y, 0));
    }

    private void assertOverrideContains(LocationOverrides.OverrideResult result, String expectedNote, TeleportData... expectedTeleports) {
        assertNotNull("Override should not be null", result);
        if (expectedNote != null) {
            assertEquals(expectedNote, result.note);
        }
        for (TeleportData tp : expectedTeleports) {
            assertTrue("Expected teleport: " + tp.getName(), result.teleports.contains(tp));
        }
    }

    // --- Island Overrides ---

    @Test
    public void testEntrana() {
        assertOverrideContains(getOverride(2840, 3350), "Boat from Port Sarim", TeleportData.DRAYNOR_VILLAGE);
    }

    @Test
    public void testFossilIsland() {
        assertOverrideContains(getOverride(3740, 3800), "Boat from Digsite", TeleportData.DIGSITE);
    }

    @Test
    public void testApeAtoll() {
        assertOverrideContains(getOverride(2750, 2750), "Spirit Tree to Gnome Stronghold, then Glider", TeleportData.SPIRIT_TREE_GNOME_STRONGHOLD);
    }

    @Test
    public void testNeitiznot() {
        assertOverrideContains(getOverride(2330, 3800), "Boat from Rellekka", TeleportData.FAIRY_AJR);
    }

    @Test
    public void testLunarIsle() {
        assertOverrideContains(getOverride(2120, 3910), "Boat from Rellekka", TeleportData.FAIRY_DKS);
    }

    // --- Coastal Overrides ---

    @Test
    public void testRimmington() {
        assertOverrideContains(getOverride(2954, 3224), "Glory Karamja goes to Musa Point, not Rimmington", TeleportData.HOUSE_RIMMINGTON, TeleportData.EXPLORERS_RING);
    }

    // --- Rune Altar Interiors ---

    @Test
    public void testAirAltar() {
        assertOverrideContains(getOverride(2840, 4830), "Via Abyss (Wilderness - be careful!)", TeleportData.EDGEVILLE);
    }

    @Test
    public void testNatureAltar() {
        assertOverrideContains(getOverride(2390, 4830), "Via Abyss (Wilderness - be careful!)", TeleportData.EDGEVILLE);
    }

    // --- Underground Dungeons (Y > 9000) ---

    @Test
    public void testLumbridgeSwampCaves() {
        assertOverrideContains(getOverride(3200, 9600), "Lumbridge Swamp entrance", TeleportData.LUMBRIDGE);
    }

    @Test
    public void testEdgevilleDungeon() {
        assertOverrideContains(getOverride(3100, 9900), "Edgeville trapdoor", TeleportData.EDGEVILLE, TeleportData.FAIRY_DKR);
    }

    @Test
    public void testVarrockSewers() {
        assertOverrideContains(getOverride(3220, 9890), "Varrock Sewers", TeleportData.VARROCK, TeleportData.GRAND_EXCHANGE);
    }

    @Test
    public void testYanilleAgilityDungeon() {
        assertOverrideContains(getOverride(2580, 9590), "Yanille dungeon north entrance → pray at altar to get sent down", TeleportData.WATCHTOWER);
    }

    @Test
    public void testKourendCatacombs() {
        assertOverrideContains(getOverride(1660, 10050), "Kourend Catacombs", TeleportData.XERICS_HEART);
    }

    @Test
    public void testHeroesGuildBasement() {
        assertOverrideContains(getOverride(2920, 9900), "Heroes' Guild basement", TeleportData.FALADOR, TeleportData.CHAMPIONS_GUILD);
    }

    @Test
    public void testBarrowsCrypts() {
        assertOverrideContains(getOverride(3550, 9700), "Barrows", TeleportData.FAIRY_BKR, TeleportData.ECTOPHIAL);
    }

    @Test
    public void testMotherlodeMine() {
        assertOverrideContains(getOverride(3050, 9760), "Dwarven Mine / Motherlode", TeleportData.FALADOR);
    }

    @Test
    public void testTaverleyDungeon() {
        assertOverrideContains(getOverride(2880, 9820), "Taverley Dungeon", TeleportData.FALADOR, TeleportData.BURTHORPE);
    }

    @Test
    public void testPaterdomusDungeon() {
        assertOverrideContains(getOverride(3439, 9896), "Paterdomus Temple (Drezel)", TeleportData.FAIRY_CKS, TeleportData.LUMBERYARD);
    }

    @Test
    public void testStrongholdOfSecurity() {
        assertOverrideContains(getOverride(1888, 5216), "Entrance in Barbarian Village", TeleportData.EDGEVILLE, TeleportData.VARROCK);
    }

    @Test
    public void testLavaMazeDungeon() {
        assertOverrideContains(getOverride(3050, 10300), "Deep Wilderness dungeon - Ferox as home base", TeleportData.FEROX_ENCLAVE);
    }

    @Test
    public void testCerberusLair() {
        assertOverrideContains(getOverride(1270, 1250), "Taverley Dungeon entrance", TeleportData.FALADOR, TeleportData.BURTHORPE);
    }

    @Test
    public void testViyeldiCaves() {
        assertOverrideContains(getOverride(2792, 9327), "Kharazi Jungle entrance", TeleportData.FAIRY_CKR, TeleportData.TAI_BWO_WANNAI);
    }

    @Test
    public void testSlayerTowerBasement() {
        assertOverrideContains(getOverride(3430, 9920), "Slayer Tower basement", TeleportData.FAIRY_CKS, TeleportData.ECTOPHIAL);
    }

    // --- Instanced/Special Dungeons (Y ~4000-6000) ---

    @Test
    public void testRoguesDen() {
        assertOverrideContains(getOverride(3056, 4991), "Trapdoor in Burthorpe pub", TeleportData.BURTHORPE, TeleportData.WARRIORS_GUILD);
    }

    @Test
    public void testAbyssInnerRing() {
        assertOverrideContains(getOverride(3020, 4850), "Via Abyss (Wilderness - be careful!)", TeleportData.EDGEVILLE, TeleportData.FAIRY_DKR);
    }

    @Test
    public void testKBDLair() {
        assertOverrideContains(getOverride(2280, 4680), "Wilderness lever (be careful!)", TeleportData.EDGEVILLE, TeleportData.FEROX_ENCLAVE);
    }

    @Test
    public void testTzHaar() {
        assertOverrideContains(getOverride(2460, 5150), "Karamja volcano entrance", TeleportData.KARAMJA, TeleportData.FAIRY_BLP);
    }

    @Test
    public void testAncientCavern() {
        assertOverrideContains(getOverride(1760, 5360), "Whirlpool near Barbarian Assault", TeleportData.BARBARIAN_ASSAULT);
    }

    @Test
    public void testShadowDungeon() {
        assertOverrideContains(getOverride(2630, 5070), "Ring of visibility required", TeleportData.BARBARIAN_ASSAULT);
    }

    @Test
    public void testGWD() {
        assertOverrideContains(getOverride(2900, 5310), "God Wars Dungeon", TeleportData.TROLLHEIM);
    }

    @Test
    public void testAgilityPyramid() {
        assertOverrideContains(getOverride(3040, 4700), "Agility Pyramid", TeleportData.NARDAH);
    }

    @Test
    public void testWatchtowerUpper() {
        assertOverrideContains(getOverride(2930, 4720), "Watchtower", TeleportData.WATCHTOWER);
    }

    @Test
    public void testIbansTemple() {
        assertOverrideContains(getOverride(2010, 4710), "Underground Pass", TeleportData.ARDOUGNE);
    }

    // --- Other Special Areas ---

    @Test
    public void testPrifddinas() {
        assertOverrideContains(getOverride(3264, 6065), "Teleport Crystal / Eternal Crystal", TeleportData.PRIFDDINAS);
    }

    @Test
    public void testLletya() {
        assertOverrideContains(getOverride(2330, 3172), "Teleport Crystal to Lletya", TeleportData.PRIFDDINAS);
    }

    @Test
    public void testZanaris() {
        assertOverrideContains(getOverride(2412, 4434), "Any Fairy Ring", TeleportData.EDGEVILLE, TeleportData.FAIRY_DKR);
    }

    @Test
    public void testKeldagrim() {
        assertOverrideContains(getOverride(2880, 10150), "Mine cart from GE or cave east of Rellekka", TeleportData.GRAND_EXCHANGE);
    }

    @Test
    public void testWaterbirthDungeon() {
        assertOverrideContains(getOverride(2500, 10150), "Boat from Rellekka to Waterbirth", TeleportData.FAIRY_AJR, TeleportData.FAIRY_DKS);
    }

    @Test
    public void testMiscellaniaDungeon() {
        assertOverrideContains(getOverride(2560, 10260), "Boat from Rellekka to Miscellania", TeleportData.FAIRY_AJR, TeleportData.FAIRY_DKS);
    }

    @Test
    public void testDorgeshKaan() {
        assertOverrideContains(getOverride(2720, 5300), "Lumbridge cellar entrance", TeleportData.LUMBRIDGE);
    }

    @Test
    public void testDeepWilderness() {
        assertOverrideContains(getOverride(3100, 3900), "Burning Amulet or Wilderness Obelisk", TeleportData.LAVA_MAZE, TeleportData.FEROX_ENCLAVE, TeleportData.EDGEVILLE);
    }

    // --- Negative tests: mainland coords should NOT trigger overrides ---

    @Test
    public void testVarrockSurface() {
        assertNull("Varrock surface should not have an override", getOverride(3213, 3424));
    }

    @Test
    public void testFaladorSurface() {
        assertNull("Falador surface should not have an override", getOverride(2965, 3381));
    }

    @Test
    public void testLumbridgeSurface() {
        assertNull("Lumbridge surface should not have an override", getOverride(3222, 3218));
    }
}
