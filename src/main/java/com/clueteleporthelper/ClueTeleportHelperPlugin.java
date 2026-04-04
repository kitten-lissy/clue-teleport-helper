package com.clueteleporthelper;

import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.PluginDependency;
import net.runelite.client.plugins.cluescrolls.ClueScrollPlugin;
import net.runelite.client.plugins.cluescrolls.clues.ClueScroll;
import net.runelite.client.plugins.cluescrolls.clues.LocationClueScroll;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.api.Client;
import net.runelite.api.ItemID;
import net.runelite.api.InventoryID;
import net.runelite.api.ItemContainer;
import net.runelite.api.Item;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.GameTick;
import net.runelite.client.eventbus.Subscribe;
import javax.inject.Inject;
import java.util.*;
import java.util.stream.Collectors;

@PluginDescriptor(
        name = "Clue Teleport Helper",
        description = "Shows the best teleports for your active clue scroll",
        tags = {"clue", "teleport", "helper"}
)
@PluginDependency(ClueScrollPlugin.class)
public class ClueTeleportHelperPlugin extends Plugin {

    @Inject
    private Client client;

    @Inject
    private ClueScrollPlugin clueScrollPlugin;

    @Inject
    private OverlayManager overlayManager;

    @Inject
    private ClueTeleportHelperPanelOverlay panelOverlay;

    @Inject
    private ClueTeleportHelperInventoryOverlay inventoryOverlay;

    private static final int MAX_TELEPORTS = 5;
    private static final int[] KEY_MEDIUM_IDS = {
            ItemID.KEY_MEDIUM, ItemID.KEY_MEDIUM_2834, ItemID.KEY_MEDIUM_2836,
            ItemID.KEY_MEDIUM_2838, ItemID.KEY_MEDIUM_2840, ItemID.KEY_MEDIUM_3606,
            ItemID.KEY_MEDIUM_3608, ItemID.KEY_MEDIUM_7297, ItemID.KEY_MEDIUM_7299,
            ItemID.KEY_MEDIUM_7302, ItemID.KEY_MEDIUM_19761
    };

    private List<TeleportData> currentBestTeleports = new ArrayList<>();
    private boolean overrideActive = false;
    private String overrideNote = null;

    @Override
    protected void startUp() {
        overlayManager.add(panelOverlay);
        overlayManager.add(inventoryOverlay);
        updateRelevantTeleports();
    }

    @Override
    protected void shutDown() {
        overlayManager.remove(panelOverlay);
        overlayManager.remove(inventoryOverlay);
        currentBestTeleports.clear();
    }

    @Subscribe
    public void onGameTick(GameTick event) {
        updateRelevantTeleports();
    }

    private void updateRelevantTeleports() {
        currentBestTeleports.clear();

        ClueScroll clue = clueScrollPlugin.getClue();
        if (clue == null) {
            inventoryOverlay.updateRelevantItems(currentBestTeleports);
            return;
        }

        WorldPoint clueLocation = getClueLocation(clue);
        if (clueLocation == null) {
            inventoryOverlay.updateRelevantItems(currentBestTeleports);
            return;
        }

        // Normalize to plane 0 for distance/override checks — upper floors share
        // the same x,y as ground level, and you always teleport to ground first.
        WorldPoint groundLocation = new WorldPoint(
                clueLocation.getX(), clueLocation.getY(), 0);

        // Check kill-for-key clues first (two-phase: kill monster, then search crate)
        KeyClueData.KeyClue keyClue = KeyClueData.getKeyClue(groundLocation);
        if (keyClue != null) {
            boolean hasKey = hasKeyInInventory();
            if (hasKey) {
                currentBestTeleports = new ArrayList<>(keyClue.crateTeleports);
                overrideNote = keyClue.searchNote;
            } else {
                currentBestTeleports = new ArrayList<>(keyClue.killTeleports);
                overrideNote = keyClue.killNote;
            }
            overrideActive = true;
            fillRemainingTeleports(groundLocation);
            inventoryOverlay.updateRelevantItems(currentBestTeleports);
            return;
        }

        // Check manual overrides (islands, rune altars, etc.)
        LocationOverrides.OverrideResult overrideResult = LocationOverrides.getOverride(groundLocation);
        if (overrideResult != null) {
            currentBestTeleports = new ArrayList<>(overrideResult.teleports);
            overrideActive = true;
            overrideNote = overrideResult.note;
            fillRemainingTeleports(groundLocation);
        } else {
            overrideActive = false;
            overrideNote = null;
            currentBestTeleports = Arrays.stream(TeleportData.values())
                    .filter(tp -> tp.getLocation() != null)
                    .sorted(Comparator.comparingInt(tp -> tp.getLocation().distanceTo(groundLocation)))
                    .limit(MAX_TELEPORTS)
                    .collect(Collectors.toList());
        }

        // Update inventory overlay with new items
        inventoryOverlay.updateRelevantItems(currentBestTeleports);
    }

    /**
     * Fill remaining teleport slots (up to MAX_TELEPORTS) with the closest
     * teleports by distance, skipping any already in the list.
     */
    private void fillRemainingTeleports(WorldPoint location) {
        if (currentBestTeleports.size() >= MAX_TELEPORTS) {
            return;
        }
        Set<TeleportData> existing = new HashSet<>(currentBestTeleports);
        List<TeleportData> extras = Arrays.stream(TeleportData.values())
                .filter(tp -> tp.getLocation() != null && !existing.contains(tp))
                .sorted(Comparator.comparingInt(tp -> tp.getLocation().distanceTo(location)))
                .limit(MAX_TELEPORTS - currentBestTeleports.size())
                .collect(Collectors.toList());
        currentBestTeleports.addAll(extras);
    }

    private boolean hasKeyInInventory() {
        ItemContainer inventory = client.getItemContainer(InventoryID.INVENTORY);
        if (inventory == null) {
            return false;
        }
        for (Item item : inventory.getItems()) {
            for (int keyId : KEY_MEDIUM_IDS) {
                if (item.getId() == keyId) {
                    return true;
                }
            }
        }
        return false;
    }

    private WorldPoint getClueLocation(ClueScroll clue) {
        if (clue instanceof LocationClueScroll) {
            WorldPoint[] locations = ((LocationClueScroll) clue).getLocations(clueScrollPlugin);
            if (locations != null && locations.length > 0) {
                return locations[0];
            }
        }
        return null;
    }

    public List<TeleportData> getBestTeleports() {
        return currentBestTeleports;
    }

    public boolean isOverrideActive() {
        return overrideActive;
    }

    public String getOverrideNote() {
        return overrideNote;
    }

    public WorldPoint getClueScrollLocation() {
        ClueScroll clue = clueScrollPlugin.getClue();
        if (clue == null) {
            return null;
        }
        return getClueLocation(clue);
    }
}