package com.clueteleporthelper;

import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.PluginDependency;
import net.runelite.client.plugins.cluescrolls.ClueScrollPlugin;
import net.runelite.client.plugins.cluescrolls.clues.ClueScroll;
import net.runelite.client.plugins.cluescrolls.clues.LocationClueScroll;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.api.Client;
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

        // Check manual overrides first (islands, rune altars, etc.)
        LocationOverrides.OverrideResult overrideResult = LocationOverrides.getOverride(clueLocation);
        if (overrideResult != null) {
            currentBestTeleports = new ArrayList<>(overrideResult.teleports);
            overrideActive = true;
            overrideNote = overrideResult.note;
        } else {
            overrideActive = false;
            overrideNote = null;
            currentBestTeleports = Arrays.stream(TeleportData.values())
                    .filter(tp -> tp.getLocation() != null)
                    .sorted(Comparator.comparingInt(tp -> tp.getLocation().distanceTo(clueLocation)))
                    .limit(MAX_TELEPORTS)
                    .collect(Collectors.toList());
        }

        // Update inventory overlay with new items
        inventoryOverlay.updateRelevantItems(currentBestTeleports);
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