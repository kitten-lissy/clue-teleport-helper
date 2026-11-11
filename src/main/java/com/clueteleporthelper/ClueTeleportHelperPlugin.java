package com.clueteleporthelper;

import com.google.inject.Provides;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.Item;
import net.runelite.api.ItemContainer;
import net.runelite.api.ItemID;
import net.runelite.api.Quest;
import net.runelite.api.QuestState;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.ItemContainerChanged;
import net.runelite.api.events.VarbitChanged;
import net.runelite.api.gameval.InventoryID;
import net.runelite.api.gameval.VarbitID;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.events.RuneScapeProfileChanged;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDependency;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.cluescrolls.ClueScrollPlugin;
import net.runelite.client.plugins.cluescrolls.ClueScrollService;
import net.runelite.client.plugins.cluescrolls.clues.ClueScroll;
import net.runelite.client.plugins.cluescrolls.clues.CrypticClue;
import net.runelite.client.plugins.cluescrolls.clues.HotColdClue;
import net.runelite.client.plugins.cluescrolls.clues.LocationClueScroll;
import net.runelite.client.plugins.cluescrolls.clues.SkillChallengeClue;
import net.runelite.client.plugins.cluescrolls.clues.ThreeStepCrypticClue;
import net.runelite.client.ui.overlay.OverlayManager;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;

@PluginDescriptor(
        name = "Clue Teleport Helper",
        description = "Shows the best teleports for your active clue scroll",
        tags = {"clue", "teleport", "helper", "treasure", "trail"}
)
@PluginDependency(ClueScrollPlugin.class)
public class ClueTeleportHelperPlugin extends Plugin {

    /** Per-account key holding the teleport items last seen in the bank. */
    static final String BANK_ITEMS_KEY = "bankTeleportItems";

    private static final int[] KEY_MEDIUM_IDS = {
            ItemID.KEY_MEDIUM, ItemID.KEY_MEDIUM_2834, ItemID.KEY_MEDIUM_2836,
            ItemID.KEY_MEDIUM_2838, ItemID.KEY_MEDIUM_2840, ItemID.KEY_MEDIUM_3606,
            ItemID.KEY_MEDIUM_3608, ItemID.KEY_MEDIUM_7297, ItemID.KEY_MEDIUM_7299,
            ItemID.KEY_MEDIUM_7302, ItemID.KEY_MEDIUM_19761
    };

    private static final Set<Integer> TELEPORT_ITEM_IDS = PlayerTeleports.allTeleportItemIds();

    @Inject
    private Client client;

    @Inject
    private ClientThread clientThread;

    @Inject
    private ClueScrollService clueScrollService;

    @Inject
    private ConfigManager configManager;

    @Inject
    private ClueTeleportHelperConfig config;

    @Inject
    private OverlayManager overlayManager;

    @Inject
    private ClueTeleportHelperPanelOverlay panelOverlay;

    @Inject
    private ClueTeleportHelperInventoryOverlay inventoryOverlay;

    // Results, read by the overlays on the render thread.
    private volatile List<TeleportSuggestion> suggestions = Collections.emptyList();
    private volatile String note;
    private volatile String footnote;

    // Change tracking, client thread only.
    private ClueScroll lastClue;
    private int lastClueState;
    private boolean dirty = true;
    private boolean questsStale = true;

    // Item caches, client thread only.
    private Set<Integer> inventoryIds = new HashSet<>();
    private Set<Integer> equipmentIds = new HashSet<>();
    private Set<Integer> bankIds = new HashSet<>();
    private boolean bankKnown;
    private final PlayerTeleports player = new PlayerTeleports();

    @Provides
    ClueTeleportHelperConfig provideConfig(ConfigManager configManager) {
        return configManager.getConfig(ClueTeleportHelperConfig.class);
    }

    @Override
    protected void startUp() {
        overlayManager.add(panelOverlay);
        overlayManager.add(inventoryOverlay);
        dirty = true;
        questsStale = true;
        clientThread.invokeLater(() -> {
            loadBankCache();
            inventoryIds = idsOf(client.getItemContainer(InventoryID.INV));
            equipmentIds = idsOf(client.getItemContainer(InventoryID.WORN));
            update();
        });
    }

    @Override
    protected void shutDown() {
        overlayManager.remove(panelOverlay);
        overlayManager.remove(inventoryOverlay);
        publish(Collections.emptyList(), null, null);
        lastClue = null;
        inventoryIds = new HashSet<>();
        equipmentIds = new HashSet<>();
        bankIds = new HashSet<>();
        bankKnown = false;
    }

    // ---------------------------------------------------------------- events

    @Subscribe
    public void onGameTick(GameTick event) {
        update();
    }

    @Subscribe
    public void onItemContainerChanged(ItemContainerChanged event) {
        int id = event.getContainerId();
        if (id == InventoryID.INV) {
            inventoryIds = idsOf(event.getItemContainer());
            dirty = true;
        } else if (id == InventoryID.WORN) {
            equipmentIds = idsOf(event.getItemContainer());
            dirty = true;
        } else if (id == InventoryID.BANK) {
            Set<Integer> seen = idsOf(event.getItemContainer());
            seen.retainAll(TELEPORT_ITEM_IDS);
            if (!bankKnown || !seen.equals(bankIds)) {
                bankIds = seen;
                bankKnown = true;
                saveBankCache();
            }
            dirty = true;
        }
    }

    @Subscribe
    public void onVarbitChanged(VarbitChanged event) {
        int id = event.getVarbitId();
        if (id == VarbitID.SPELLBOOK || id == VariableLocationData.VIGGORA_VARBIT) {
            dirty = true;
        }
    }

    @Subscribe
    public void onConfigChanged(ConfigChanged event) {
        if (ClueTeleportHelperConfig.GROUP.equals(event.getGroup())) {
            dirty = true;
        }
    }

    @Subscribe
    public void onRuneScapeProfileChanged(RuneScapeProfileChanged event) {
        loadBankCache();
        dirty = true;
    }

    @Subscribe
    public void onGameStateChanged(GameStateChanged event) {
        if (event.getGameState() == GameState.LOGGED_IN) {
            questsStale = true;
            dirty = true;
        }
    }

    // ------------------------------------------------------------- recompute

    /** Recompute only when the clue, its progress, the items, a varbit or the settings changed. */
    private void update() {
        ClueScroll clue = clueScrollService.getClue();
        int state = clueState(clue);
        if (clue != lastClue || state != lastClueState) {
            if (clue != lastClue) {
                questsStale = true;
            }
            lastClue = clue;
            lastClueState = state;
            dirty = true;
        }
        if (dirty) {
            dirty = false;
            recompute(clue);
        }
    }

    /** Cheap fingerprint of progress inside a clue that can change without a new clue object. */
    private static int clueState(ClueScroll clue) {
        if (clue instanceof SkillChallengeClue) {
            return ((SkillChallengeClue) clue).isChallengeCompleted() ? 1 : 0;
        }
        if (clue instanceof ThreeStepCrypticClue) {
            int done = 0;
            for (Map.Entry<CrypticClue, Boolean> step : ((ThreeStepCrypticClue) clue).getClueSteps()) {
                if (Boolean.TRUE.equals(step.getValue())) {
                    done++;
                }
            }
            return done;
        }
        return 0;
    }

    private void recompute(ClueScroll clue) {
        if (clue == null || clue instanceof HotColdClue) {
            publish(Collections.emptyList(), null, null);
            return;
        }

        TeleportRanker ranker = buildRanker();
        String foot = ownershipFootnote();

        // Three-step cryptic clues: one line per unfinished step.
        if (isMultiStep(clue)) {
            List<WorldPoint> targets = new ArrayList<>();
            List<String> labels = new ArrayList<>();
            List<Map.Entry<CrypticClue, Boolean>> steps = ((ThreeStepCrypticClue) clue).getClueSteps();
            for (int i = 0; i < steps.size(); i++) {
                Map.Entry<CrypticClue, Boolean> step = steps.get(i);
                if (Boolean.TRUE.equals(step.getValue())) {
                    continue;
                }
                WorldPoint loc = locationOf(step.getKey());
                if (loc != null) {
                    targets.add(ground(loc));
                    labels.add("Step " + (i + 1));
                }
            }
            List<TeleportSuggestion> lines = ranker.rankSteps(targets, labels, ClueTeleportHelperPlugin::overrideTeleports);
            publish(lines, "Three-step cryptic: " + targets.size() + " step(s) left", foot);
            return;
        }

        // Sherlock and Charlie skill challenges.
        if (clue instanceof SkillChallengeClue) {
            SkillChallengeClue sc = (SkillChallengeClue) clue;
            boolean completed = sc.isChallengeCompleted();
            SkillChallengeData.SkillChallenge challenge = SkillChallengeData.getChallenge(
                    sc.getChallenge(), sc.getChatboxChallenge(), sc.getRawChallenge(), sc.getReturnText());
            List<TeleportData> preferred;
            String text;
            if (challenge != null) {
                preferred = challenge.teleports(completed);
                text = challenge.note(completed);
            } else if (isCharlie(sc)) {
                preferred = SkillChallengeData.CHARLIE_TELEPORTS;
                text = "Charlie the Tramp, south entrance of Varrock";
            } else {
                preferred = SkillChallengeData.SHERLOCK_TELEPORTS;
                text = "Return to Sherlock";
            }
            publish(ranker.rank(null, preferred, false), text, foot);
            return;
        }

        String crypticText = clue instanceof CrypticClue ? ((CrypticClue) clue).getText() : null;

        // Location, if the clue gives one without needing the Clue Scroll plugin itself.
        WorldPoint location = null;
        boolean lookupFailed = false;
        try {
            location = clueLocation(clue);
        } catch (RuntimeException e) {
            lookupFailed = true;
        }

        String viggoraNote = null;
        if (location == null && VariableLocationData.isViggora(crypticText)) {
            int value = client.getVarbitValue(VariableLocationData.VIGGORA_VARBIT);
            location = VariableLocationData.viggoraLocation(value);
            viggoraNote = VariableLocationData.viggoraNote(value);
        }

        if (location == null) {
            // Kill-a-monster cryptic clues carry no tile, only text.
            KillTaskData.KillTask killTask = KillTaskData.getKillTask(crypticText);
            if (killTask != null) {
                publish(ranker.rank(null, killTask.teleports, false),
                        killTask.monsterNote + ": " + killTask.locationNote, foot);
                return;
            }
            String text = lookupFailed ? "Location depends on game state. See the Clue Scroll overlay" : null;
            publish(Collections.emptyList(), text, null);
            return;
        }

        // Upper floors share x and y with the ground floor, and teleports land on the ground.
        WorldPoint target = ground(location);

        // Kill-for-key medium clues: kill phase until the key is in the inventory.
        KeyClueData.KeyClue keyClue = KeyClueData.getKeyClue(target);
        if (keyClue != null) {
            boolean hasKey = hasKey();
            publish(ranker.rank(target, hasKey ? keyClue.crateTeleports : keyClue.killTeleports, true),
                    hasKey ? keyClue.searchNote : keyClue.killNote, foot);
            return;
        }

        LocationOverrides.OverrideResult override = LocationOverrides.getOverride(target);
        if (override != null) {
            publish(ranker.rank(target, override.teleports, true), override.note, foot);
        } else {
            publish(ranker.rank(target, Collections.emptyList(), true), viggoraNote, foot);
        }
    }

    /**
     * Only three-step cryptic clues have several steps. Coordinate clues in Prifddinas
     * also report two points (the city and its mirror region), but that is one dig spot.
     */
    static boolean isMultiStep(ClueScroll clue) {
        return clue instanceof ThreeStepCrypticClue;
    }

    /**
     * The clue's single tile, asked for without the Clue Scroll plugin (RuneLite only shares
     * ClueScrollService with other plugins). Uses getLocation, not getLocations, so the
     * Prifddinas mirror point is ignored. Clue types that need the plugin to answer throw,
     * and the caller treats that as "location depends on game state".
     */
    static WorldPoint clueLocation(ClueScroll clue) {
        if (!(clue instanceof LocationClueScroll)) {
            return null;
        }
        return ((LocationClueScroll) clue).getLocation(null);
    }

    private static List<TeleportData> overrideTeleports(WorldPoint target) {
        LocationOverrides.OverrideResult override = LocationOverrides.getOverride(target);
        return override == null ? Collections.emptyList() : override.teleports;
    }

    private static boolean isCharlie(SkillChallengeClue sc) {
        String chat = sc.getChatboxChallenge();
        return chat != null && chat.startsWith("I really need");
    }

    private static WorldPoint locationOf(CrypticClue clue) {
        try {
            return clue.getLocation(null);
        } catch (RuntimeException e) {
            return null;
        }
    }

    static WorldPoint ground(WorldPoint p) {
        return new WorldPoint(p.getX(), p.getY(), 0);
    }

    private void publish(List<TeleportSuggestion> lines, String note, String footnote) {
        this.suggestions = Collections.unmodifiableList(new ArrayList<>(lines));
        this.note = note;
        this.footnote = lines.isEmpty() ? null : footnote;
        inventoryOverlay.updateRelevantItems(this.suggestions);
    }

    // ------------------------------------------------------------ filtering

    private TeleportRanker buildRanker() {
        ClueTeleportHelperConfig.OwnedFilter mode = config.ownedFilter();
        boolean avoidWilderness = config.avoidWilderness();
        boolean fairyRings = config.includeFairyRings();
        boolean spiritTrees = config.includeSpiritTrees();
        boolean minigames = config.includeMinigameTeleports();
        boolean levers = config.includeLevers();

        Predicate<TeleportData> toggles = tp -> {
            if (avoidWilderness && tp.isWilderness()) {
                return false;
            }
            switch (tp.getMethod()) {
                case FAIRY_RING:
                    return fairyRings;
                case SPIRIT_TREE:
                    return spiritTrees;
                case MINIGAME:
                    return minigames;
                case LEVER:
                    return levers;
                default:
                    return true;
            }
        };

        Predicate<TeleportData> usable;
        if (mode == ClueTeleportHelperConfig.OwnedFilter.SHOW_ALL) {
            usable = tp -> true;
        } else {
            refreshPlayer();
            usable = player::canUse;
        }
        Predicate<TeleportData> allowed = mode == ClueTeleportHelperConfig.OwnedFilter.ONLY_OWNED
                ? toggles.and(usable) : toggles;
        return new TeleportRanker(config.teleportCount(), config.combineDuplicates(), allowed, usable);
    }

    private String ownershipFootnote() {
        if (config.ownedFilter() != ClueTeleportHelperConfig.OwnedFilter.SHOW_ALL && !bankKnown) {
            return "Open your bank once so owned teleports are known";
        }
        return null;
    }

    /** Fill the player snapshot. Runs on the client thread (Quest.getState runs a client script). */
    private void refreshPlayer() {
        Set<Integer> owned = new HashSet<>(inventoryIds);
        owned.addAll(equipmentIds);
        owned.addAll(bankIds);
        player.setOwnedItems(owned, bankKnown);
        player.setSpellbook(client.getVarbitValue(VarbitID.SPELLBOOK));
        if (questsStale && client.getGameState() == GameState.LOGGED_IN) {
            player.setQuestUnlocks(
                    Quest.FAIRYTALE_II__CURE_A_QUEEN.getState(client) != QuestState.NOT_STARTED,
                    Quest.TREE_GNOME_VILLAGE.getState(client) == QuestState.FINISHED,
                    Quest.TWILIGHTS_PROMISE.getState(client) != QuestState.NOT_STARTED,
                    Quest.SONG_OF_THE_ELVES.getState(client) == QuestState.FINISHED);
            questsStale = false;
        }
    }

    // ----------------------------------------------------------------- items

    private boolean hasKey() {
        for (int keyId : KEY_MEDIUM_IDS) {
            if (inventoryIds.contains(keyId)) {
                return true;
            }
        }
        return false;
    }

    private static Set<Integer> idsOf(ItemContainer container) {
        Set<Integer> ids = new HashSet<>();
        if (container != null) {
            for (Item item : container.getItems()) {
                if (item.getId() > 0) {
                    ids.add(item.getId());
                }
            }
        }
        return ids;
    }

    private void loadBankCache() {
        String stored = configManager.getRSProfileConfiguration(ClueTeleportHelperConfig.GROUP, BANK_ITEMS_KEY);
        bankIds = parseIds(stored);
        bankKnown = stored != null;
    }

    private void saveBankCache() {
        configManager.setRSProfileConfiguration(ClueTeleportHelperConfig.GROUP, BANK_ITEMS_KEY, formatIds(bankIds));
    }

    static Set<Integer> parseIds(String stored) {
        Set<Integer> ids = new HashSet<>();
        if (stored == null || stored.isEmpty()) {
            return ids;
        }
        for (String part : stored.split(",")) {
            try {
                ids.add(Integer.parseInt(part.trim()));
            } catch (NumberFormatException ignored) {
                // Skip anything that is not a number.
            }
        }
        return ids;
    }

    static String formatIds(Set<Integer> ids) {
        StringBuilder sb = new StringBuilder();
        for (int id : new TreeSet<>(ids)) {
            if (sb.length() > 0) {
                sb.append(',');
            }
            sb.append(id);
        }
        return sb.toString();
    }

    // ------------------------------------------------------------- accessors

    public List<TeleportSuggestion> getSuggestions() {
        return suggestions;
    }

    public String getNote() {
        return note;
    }

    public String getFootnote() {
        return footnote;
    }
}
