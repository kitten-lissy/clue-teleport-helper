package com.clueteleporthelper;

import net.runelite.api.ItemID;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class TeleportDataTest {

    @Test
    public void wildernessFlags() {
        assertTrue(TeleportData.CHAOS_TEMPLE.isWilderness());
        assertTrue(TeleportData.BANDIT_CAMP.isWilderness());
        assertTrue(TeleportData.LAVA_MAZE.isWilderness());
        assertTrue(TeleportData.CEMETERY.isWilderness());
        assertTrue(TeleportData.EDGEVILLE_LEVER.isWilderness());
        assertTrue(TeleportData.ARDOUGNE_LEVER.isWilderness());
        // Ferox Enclave is a safe zone.
        assertFalse(TeleportData.FEROX_ENCLAVE.isWilderness());
        assertFalse(TeleportData.MINIGAME_CLAN_WARS.isWilderness());
        assertFalse(TeleportData.EDGEVILLE.isWilderness());
    }

    @Test
    public void methods() {
        assertEquals(TeleportData.Method.STANDARD_SPELL, TeleportData.WATCHTOWER.getMethod());
        assertEquals(TeleportData.Method.STANDARD_SPELL, TeleportData.TROLLHEIM.getMethod());
        assertEquals(TeleportData.Method.ITEM, TeleportData.HOUSE_TROLLHEIM.getMethod());
        assertEquals(TeleportData.Method.FAIRY_RING, TeleportData.FAIRY_BLR.getMethod());
        assertEquals(TeleportData.Method.SPIRIT_TREE, TeleportData.SPIRIT_TREE_HOSIDIUS.getMethod());
        assertEquals(TeleportData.Method.MINIGAME, TeleportData.MINIGAME_SOUL_WARS.getMethod());
        assertEquals(TeleportData.Method.LEVER, TeleportData.ARDOUGNE_LEVER.getMethod());
        assertTrue(TeleportData.FAIRY_BLR.isNoItem());
        assertFalse(TeleportData.EDGEVILLE.isNoItem());
    }

    @Test
    public void spellsCarryTheirRunesAndTabs() {
        assertTrue(TeleportData.WATCHTOWER.getItemIds().contains(ItemID.WATCHTOWER_TELEPORT));
        assertTrue(TeleportData.WATCHTOWER.getItemIds().contains(ItemID.LAW_RUNE));
        assertTrue(TeleportData.TROLLHEIM.getItemIds().contains(ItemID.LAW_RUNE));
        // Only spells list law runes, so law runes light up only when a spell is suggested.
        for (TeleportData tp : TeleportData.values()) {
            if (tp.getItemIds().contains(ItemID.LAW_RUNE)) {
                assertEquals(tp + " lists law runes", TeleportData.Method.STANDARD_SPELL, tp.getMethod());
            }
        }
    }

    @Test
    public void namesSplitIntoDestinationAndMethod() {
        assertEquals("Edgeville", TeleportData.EDGEVILLE.getDestination());
        assertEquals("Glory", TeleportData.EDGEVILLE.getMethodLabel());
        assertEquals("Legends' Guild", TeleportData.FAIRY_BLR.getDestination());
        assertEquals("Fairy Ring BLR", TeleportData.FAIRY_BLR.getMethodLabel());
        assertEquals("Hosidius", TeleportData.SPIRIT_TREE_HOSIDIUS.getDestination());
        for (TeleportData tp : TeleportData.values()) {
            assertNotNull(tp + " has no method label", tp.getMethodLabel());
            assertNotNull(tp + " has no location", tp.getLocation());
        }
    }

    @Test
    public void fairyRingCodesAreUnique() {
        Set<String> codes = new HashSet<>();
        for (TeleportData tp : TeleportData.values()) {
            if (tp.getMethod() == TeleportData.Method.FAIRY_RING) {
                String code = tp.name().substring("FAIRY_".length());
                assertTrue("Duplicate ring " + code, codes.add(code));
                assertEquals("Fairy Ring " + code, tp.getMethodLabel());
            }
        }
    }

    @Test
    public void overlayTextAvoidsArrows() {
        for (TeleportData tp : TeleportData.values()) {
            assertFalse(tp.getName().contains("→"));
        }
    }
}
