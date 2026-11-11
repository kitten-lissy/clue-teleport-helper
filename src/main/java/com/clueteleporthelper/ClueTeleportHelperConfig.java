package com.clueteleporthelper;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;
import net.runelite.client.config.Range;

import java.awt.Color;

@ConfigGroup(ClueTeleportHelperConfig.GROUP)
public interface ClueTeleportHelperConfig extends Config {
    String GROUP = "clueteleporthelper";

    enum OwnedFilter {
        SHOW_ALL("Show all teleports"),
        ONLY_OWNED("Only teleports I have"),
        GREY_OUT("Grey out the rest");

        private final String label;

        OwnedFilter(String label) {
            this.label = label;
        }

        @Override
        public String toString() {
            return label;
        }
    }

    @ConfigSection(
        name = "Panel",
        description = "What the teleport panel shows",
        position = 0
    )
    String panelSection = "panel";

    @ConfigSection(
        name = "Teleport choices",
        description = "Which teleports may be suggested",
        position = 1
    )
    String filterSection = "filters";

    @ConfigSection(
        name = "Item highlight",
        description = "Highlighting of teleport items",
        position = 2
    )
    String highlightSection = "highlight";

    @ConfigSection(
        name = "Colours",
        description = "Panel colours",
        position = 3,
        closedByDefault = true
    )
    String colourSection = "colours";

    // ---- Panel ----

    @Range(min = 1, max = 10)
    @ConfigItem(
        keyName = "teleportCount",
        name = "Teleports shown",
        description = "How many teleports to list for a clue step",
        position = 0,
        section = panelSection
    )
    default int teleportCount() {
        return 5;
    }

    @ConfigItem(
        keyName = "showDistance",
        name = "Show tile distance",
        description = "Show the straight-line distance in tiles from each teleport to the clue",
        position = 1,
        section = panelSection
    )
    default boolean showDistance() {
        return true;
    }

    @ConfigItem(
        keyName = "colourDistances",
        name = "Colour-code distances",
        description = "Shade distances by how close they are (20 tiles or less, 50 or less, further)",
        position = 2,
        section = panelSection
    )
    default boolean colourDistances() {
        return true;
    }

    @ConfigItem(
        keyName = "showMethod",
        name = "Show item or method",
        description = "Show which item or method each teleport uses",
        position = 3,
        section = panelSection
    )
    default boolean showMethod() {
        return true;
    }

    @ConfigItem(
        keyName = "combineDuplicates",
        name = "Combine same destination",
        description = "Show teleports that land within a few tiles of each other on one line",
        position = 4,
        section = panelSection
    )
    default boolean combineDuplicates() {
        return true;
    }

    @Range(min = 150, max = 500)
    @ConfigItem(
        keyName = "panelWidth",
        name = "Panel width",
        description = "Width of the teleport panel in pixels",
        position = 5,
        section = panelSection
    )
    default int panelWidth() {
        return 280;
    }

    // ---- Teleport choices ----

    @ConfigItem(
        keyName = "avoidWilderness",
        name = "Avoid Wilderness teleports",
        description = "Never suggest teleports that land in the Wilderness (Ferox Enclave counts as safe)",
        position = 0,
        section = filterSection
    )
    default boolean avoidWilderness() {
        return true;
    }

    @ConfigItem(
        keyName = "ownedFilter",
        name = "Owned teleports",
        description = "Only teleports I have: hides items you do not own (inventory, worn and bank),"
            + " spells from other spellbooks, and fairy rings, spirit trees, quetzals or"
            + " the Prifddinas tab before the quest that unlocks them."
            + " Open your bank once so the plugin can see what is in it.",
        position = 1,
        section = filterSection
    )
    default OwnedFilter ownedFilter() {
        return OwnedFilter.SHOW_ALL;
    }

    @ConfigItem(
        keyName = "includeFairyRings",
        name = "Include fairy rings",
        description = "Suggest fairy rings",
        position = 2,
        section = filterSection
    )
    default boolean includeFairyRings() {
        return true;
    }

    @ConfigItem(
        keyName = "includeSpiritTrees",
        name = "Include spirit trees",
        description = "Suggest spirit trees",
        position = 3,
        section = filterSection
    )
    default boolean includeSpiritTrees() {
        return true;
    }

    @ConfigItem(
        keyName = "includeMinigameTeleports",
        name = "Include minigame teleports",
        description = "Suggest minigame (grouping) teleports",
        position = 4,
        section = filterSection
    )
    default boolean includeMinigameTeleports() {
        return true;
    }

    @ConfigItem(
        keyName = "includeLevers",
        name = "Include levers",
        description = "Suggest the Edgeville and Ardougne levers",
        position = 5,
        section = filterSection
    )
    default boolean includeLevers() {
        return true;
    }

    // ---- Item highlight ----

    @ConfigItem(
        keyName = "highlightInventory",
        name = "Highlight in inventory",
        description = "Highlight suggested teleport items in your inventory",
        position = 0,
        section = highlightSection
    )
    default boolean highlightInventory() {
        return true;
    }

    @ConfigItem(
        keyName = "highlightBank",
        name = "Highlight in bank",
        description = "Highlight suggested teleport items in your bank",
        position = 1,
        section = highlightSection
    )
    default boolean highlightBank() {
        return true;
    }

    @ConfigItem(
        keyName = "highlightEquipment",
        name = "Highlight worn items",
        description = "Highlight suggested teleport items in the worn equipment tab",
        position = 2,
        section = highlightSection
    )
    default boolean highlightEquipment() {
        return true;
    }

    @ConfigItem(
        keyName = "highlightBorderColour",
        name = "Outline colour",
        description = "Outline colour of highlighted items",
        position = 3,
        section = highlightSection
    )
    default Color highlightBorderColour() {
        return new Color(255, 20, 147);
    }

    @ConfigItem(
        keyName = "highlightFillColour",
        name = "Fill colour",
        description = "Fill colour of highlighted items",
        position = 4,
        section = highlightSection
    )
    default Color highlightFillColour() {
        return new Color(255, 105, 180);
    }

    @Range(min = 0, max = 255)
    @ConfigItem(
        keyName = "highlightFillOpacity",
        name = "Fill opacity",
        description = "0 is see-through, 255 is solid",
        position = 5,
        section = highlightSection
    )
    default int highlightFillOpacity() {
        return 80;
    }

    @Range(min = 0, max = 6)
    @ConfigItem(
        keyName = "highlightBorderWidth",
        name = "Outline width",
        description = "Outline thickness in pixels (0 for none)",
        position = 6,
        section = highlightSection
    )
    default int highlightBorderWidth() {
        return 3;
    }

    // ---- Colours ----

    @ConfigItem(
        keyName = "titleColour",
        name = "Title",
        description = "Panel title colour",
        position = 0,
        section = colourSection
    )
    default Color titleColour() {
        return new Color(255, 105, 180);
    }

    @ConfigItem(
        keyName = "destinationColour",
        name = "Destination",
        description = "Colour of teleport destinations",
        position = 1,
        section = colourSection
    )
    default Color destinationColour() {
        return new Color(255, 182, 193);
    }

    @ConfigItem(
        keyName = "methodColour",
        name = "Item or method",
        description = "Colour of the item or method hint",
        position = 2,
        section = colourSection
    )
    default Color methodColour() {
        return new Color(255, 105, 180);
    }

    @ConfigItem(
        keyName = "distanceColour",
        name = "Distance",
        description = "Distance colour when colour-coding is off",
        position = 3,
        section = colourSection
    )
    default Color distanceColour() {
        return new Color(255, 160, 122);
    }

    @ConfigItem(
        keyName = "noteColour",
        name = "Notes",
        description = "Colour of route notes",
        position = 4,
        section = colourSection
    )
    default Color noteColour() {
        return new Color(255, 215, 0);
    }

    @ConfigItem(
        keyName = "wildernessColour",
        name = "Wilderness warning",
        description = "Colour of teleports that land in the Wilderness",
        position = 5,
        section = colourSection
    )
    default Color wildernessColour() {
        return new Color(255, 64, 64);
    }
}
