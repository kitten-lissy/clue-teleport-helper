package com.clueteleporthelper;

import net.runelite.api.ItemID;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import static org.junit.Assert.*;

public class PlayerTeleportsTest {

    private static final int LUNAR = 2;

    @Test
    public void spellsNeedTheStandardSpellbookOrATab() {
        PlayerTeleports p = new PlayerTeleports();
        p.setOwnedItems(Collections.singleton(ItemID.LAW_RUNE), true);

        p.setSpellbook(PlayerTeleports.STANDARD_SPELLBOOK);
        assertTrue(p.canUse(TeleportData.VARROCK));

        p.setSpellbook(LUNAR);
        assertFalse("Law runes alone do not cast a standard spell on Lunar", p.canUse(TeleportData.VARROCK));

        p.setOwnedItems(Collections.singleton(ItemID.VARROCK_TELEPORT), true);
        assertTrue("A tablet works from any spellbook", p.canUse(TeleportData.VARROCK));
        assertTrue(p.canUse(TeleportData.VARROCK));
        assertFalse(p.canUse(TeleportData.TROLLHEIM));
    }

    @Test
    public void itemsAreJudgedOnlyOnceTheBankIsKnown() {
        PlayerTeleports p = new PlayerTeleports();
        p.setOwnedItems(Collections.emptySet(), false);
        assertTrue(p.canUse(TeleportData.XERICS_GLADE));

        p.setOwnedItems(Collections.emptySet(), true);
        assertFalse(p.canUse(TeleportData.XERICS_GLADE));

        p.setOwnedItems(new HashSet<>(Arrays.asList(ItemID.XERICS_TALISMAN, ItemID.AMULET_OF_GLORY4)), true);
        assertTrue(p.canUse(TeleportData.XERICS_GLADE));
        assertTrue(p.canUse(TeleportData.EDGEVILLE));
        assertFalse(p.canUse(TeleportData.ECTOPHIAL));
    }

    @Test
    public void questsGateItemFreeTransport() {
        PlayerTeleports p = new PlayerTeleports();
        p.setOwnedItems(Collections.singleton(ItemID.PRIFDDINAS_TELEPORT), true);

        p.setQuestUnlocks(true, true, true, true);
        assertTrue(p.canUse(TeleportData.FAIRY_DKR));
        assertTrue(p.canUse(TeleportData.SPIRIT_TREE_GRAND_EXCHANGE));
        assertTrue(p.canUse(TeleportData.HOUSE_PRIFDDINAS));

        p.setQuestUnlocks(false, false, false, false);
        assertFalse(p.canUse(TeleportData.FAIRY_DKR));
        assertFalse(p.canUse(TeleportData.SPIRIT_TREE_GRAND_EXCHANGE));
        assertFalse(p.canUse(TeleportData.HOUSE_PRIFDDINAS));
        assertFalse(p.canUse(TeleportData.QUETZAL_ALDARIN));
        assertTrue("Minigame teleports need no quest", p.canUse(TeleportData.MINIGAME_CASTLE_WARS));
    }

    @Test
    public void teleportItemListCoversKnownItems() {
        assertTrue(PlayerTeleports.allTeleportItemIds().contains(ItemID.XERICS_TALISMAN));
        assertTrue(PlayerTeleports.allTeleportItemIds().contains(ItemID.WATCHTOWER_TELEPORT));
    }
}
