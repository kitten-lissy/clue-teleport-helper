package com.clueteleporthelper;

import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.PluginDependency;
import net.runelite.client.plugins.cluescrolls.ClueScrollPlugin;
import net.runelite.client.plugins.cluescrolls.clues.ClueScroll;
import net.runelite.client.plugins.cluescrolls.clues.LocationClueScroll;
import net.runelite.client.plugins.cluescrolls.clues.CrypticClue;
import net.runelite.client.plugins.cluescrolls.clues.HotColdClue;
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
    private boolean multiStep = false;
    private List<String> multiStepNotes = new ArrayList<>();

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
        multiStep = false;
        multiStepNotes.clear();

        ClueScroll clue = clueScrollPlugin.getClue();
        if (clue == null || clue instanceof HotColdClue) {
            inventoryOverlay.updateRelevantItems(currentBestTeleports);
            return;
        }

        // Check for multi-step clues (three-step cryptics)
        WorldPoint[] allLocations = getAllClueLocations(clue);
        if (allLocations != null && allLocations.length > 1) {
            multiStep = true;
            overrideActive = true;
            overrideNote = "Multi-step clue — " + allLocations.length + " steps remaining";
            for (WorldPoint loc : allLocations) {
                WorldPoint ground = new WorldPoint(loc.getX(), loc.getY(), 0);
                // Check overrides first (handles instanced/special areas)
                LocationOverrides.OverrideResult override = LocationOverrides.getOverride(ground);
                TeleportData best;
                if (override != null && !override.teleports.isEmpty()) {
                    best = override.teleports.get(0);
                } else {
                    best = Arrays.stream(TeleportData.values())
                            .filter(tp -> tp.getLocation() != null)
                            .min(Comparator.comparingInt(tp -> tp.getLocation().distanceTo(ground)))
                            .orElse(null);
                }
                if (best != null) {
                    currentBestTeleports.add(best);
                }
            }
            inventoryOverlay.updateRelevantItems(currentBestTeleports);
            return;
        }

        WorldPoint clueLocation = getClueLocation(clue);
        if (clueLocation == null) {
            // No location — check kill tasks and skill challenges by text
            String clueText = getClueText(clue);

            // Kill task check (cryptic clues like "In a while...")
            KillTaskData.KillTask killTask = KillTaskData.getKillTask(clueText);
            if (killTask != null) {
                currentBestTeleports = new ArrayList<>(killTask.teleports);
                overrideActive = true;
                overrideNote = killTask.monsterNote + " — " + killTask.locationNote;
                inventoryOverlay.updateRelevantItems(currentBestTeleports);
                return;
            }

            // Skill challenge check (Sherlock/Charlie tasks)
            SkillChallengeData.SkillChallenge challenge = SkillChallengeData.getChallenge(clueText);
            if (challenge != null) {
                currentBestTeleports = new ArrayList<>(challenge.teleports);
                overrideActive = true;
                overrideNote = challenge.locationNote;
                inventoryOverlay.updateRelevantItems(currentBestTeleports);
                return;
            }

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

    /**
     * Try to extract text from a clue by scanning all String fields and getter methods.
     */
    private String getClueText(ClueScroll clue) {
        StringBuilder allText = new StringBuilder();

        // Try common getter methods
        String[] methodNames = {"getText", "getChallenge", "getChallengeText",
                "getQuestion", "getQuestionText", "toString"};
        for (String name : methodNames) {
            try {
                java.lang.reflect.Method m = clue.getClass().getMethod(name);
                Object result = m.invoke(clue);
                if (result != null) {
                    allText.append(result.toString()).append(" ");
                }
            } catch (Exception ignored) {
            }
        }

        // Also scan all declared fields for strings
        for (java.lang.reflect.Field f : clue.getClass().getDeclaredFields()) {
            if (f.getType() == String.class) {
                try {
                    f.setAccessible(true);
                    Object val = f.get(clue);
                    if (val != null) {
                        allText.append(val.toString()).append(" ");
                    }
                } catch (Exception ignored) {
                }
            }
        }

        String result = allText.toString().trim();
        return result.isEmpty() ? null : result;
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

    private WorldPoint[] getAllClueLocations(ClueScroll clue) {
        if (clue instanceof LocationClueScroll) {
            WorldPoint[] locations = ((LocationClueScroll) clue).getLocations(clueScrollPlugin);
            if (locations != null && locations.length > 1) {
                return locations;
            }
        }
        return null;
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

    public boolean isMultiStep() {
        return multiStep;
    }

    public List<String> getMultiStepNotes() {
        return multiStepNotes;
    }

    public WorldPoint getClueScrollLocation() {
        ClueScroll clue = clueScrollPlugin.getClue();
        if (clue == null) {
            return null;
        }
        return getClueLocation(clue);
    }
}