package com.clueteleporthelper;

import net.runelite.api.ItemID;

import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

/**
 * Snapshot of what the player can use: owned item IDs, active spellbook and
 * the quest unlocks that gate item-free transport. Kept free of client calls
 * so it can be unit tested; the plugin fills it on the client thread.
 */
public class PlayerTeleports {
    /** Value of the spellbook varbit for the standard spellbook (OSRS wiki, varbit 4070). */
    public static final int STANDARD_SPELLBOOK = 0;

    /** Runes are listed on spells for highlighting, but owning one does not make a spell usable. */
    private static final Set<Integer> RUNES = Collections.singleton(ItemID.LAW_RUNE);

    private static final Set<TeleportData> QUETZALS;
    static {
        Set<TeleportData> q = EnumSet.noneOf(TeleportData.class);
        for (TeleportData tp : TeleportData.values()) {
            if (tp.name().startsWith("QUETZAL_")) {
                q.add(tp);
            }
        }
        QUETZALS = Collections.unmodifiableSet(q);
    }

    private final Set<Integer> ownedItems = new HashSet<>();
    private boolean bankKnown;
    private int spellbook = STANDARD_SPELLBOOK;
    private boolean fairyRingsUnlocked = true;
    private boolean spiritTreesUnlocked = true;
    private boolean quetzalsUnlocked = true;
    private boolean prifddinasUnlocked = true;

    public void setOwnedItems(Set<Integer> items, boolean bankKnown) {
        ownedItems.clear();
        ownedItems.addAll(items);
        this.bankKnown = bankKnown;
    }

    public boolean isBankKnown() {
        return bankKnown;
    }

    public void setSpellbook(int spellbook) {
        this.spellbook = spellbook;
    }

    public void setQuestUnlocks(boolean fairyRings, boolean spiritTrees, boolean quetzals, boolean prifddinas) {
        this.fairyRingsUnlocked = fairyRings;
        this.spiritTreesUnlocked = spiritTrees;
        this.quetzalsUnlocked = quetzals;
        this.prifddinasUnlocked = prifddinas;
    }

    /**
     * Whether the player can use this teleport right now (or after a bank trip).
     * Until the bank has been seen once, item ownership is not judged, so nothing
     * is hidden by mistake.
     */
    public boolean canUse(TeleportData tp) {
        switch (tp.getMethod()) {
            case FAIRY_RING:
                return fairyRingsUnlocked;
            case SPIRIT_TREE:
                return spiritTreesUnlocked;
            case MINIGAME:
            case LEVER:
                return true;
            case STANDARD_SPELL:
                return spellbook == STANDARD_SPELLBOOK || ownsAnyItem(tp);
            default:
                break;
        }
        if (QUETZALS.contains(tp) && !quetzalsUnlocked) {
            return false;
        }
        if (tp == TeleportData.HOUSE_PRIFDDINAS && !prifddinasUnlocked) {
            return false;
        }
        return !bankKnown || ownsAnyItem(tp);
    }

    private boolean ownsAnyItem(TeleportData tp) {
        for (int id : tp.getItemIds()) {
            if (!RUNES.contains(id) && ownedItems.contains(id)) {
                return true;
            }
        }
        return false;
    }

    /** Every item ID any teleport refers to. Used to keep the stored bank list small. */
    public static Set<Integer> allTeleportItemIds() {
        Set<Integer> ids = new HashSet<>();
        for (TeleportData tp : TeleportData.values()) {
            ids.addAll(tp.getItemIds());
        }
        return ids;
    }
}
