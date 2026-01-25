package com.clueteleporthelper;

import net.runelite.api.Client;
import net.runelite.api.ItemID;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.widgets.WidgetItem;
import net.runelite.client.ui.overlay.WidgetItemOverlay;
import net.runelite.client.ui.overlay.components.LineComponent;
import net.runelite.client.ui.overlay.components.PanelComponent;
import net.runelite.client.ui.overlay.components.TitleComponent;

import javax.inject.Inject;
import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ClueTeleportHelperInventoryOverlay extends WidgetItemOverlay {

    private final Client client;
    private final ClueTeleportHelperPlugin plugin;
    private final PanelComponent panelComponent = new PanelComponent();
    private Set<Integer> relevantItemIds = new HashSet<>();

    @Inject
    public ClueTeleportHelperInventoryOverlay(Client client, ClueTeleportHelperPlugin plugin) {
        this.client = client;
        this.plugin = plugin;
        showOnInventory();
    }

    @Override
    public void renderItemOverlay(Graphics2D graphics, int itemId, WidgetItem widgetItem) {
        if (relevantItemIds.contains(itemId)) {
            Rectangle bounds = widgetItem.getCanvasBounds();

            // Hot pink fill (more transparent)
            graphics.setColor(new Color(255, 105, 180, 80));
            graphics.fill(bounds);

            // Hot pink border (solid, thicker)
            graphics.setColor(new Color(255, 20, 147));
            graphics.setStroke(new BasicStroke(3));
            graphics.draw(bounds);
        }
    }

    public void updateRelevantItems(List<TeleportData> bestTeleports) {
        relevantItemIds.clear();

        if (bestTeleports == null || bestTeleports.isEmpty()) {
            return;
        }

        // Collect ALL item IDs from ALL best teleports
        for (TeleportData teleport : bestTeleports) {
            relevantItemIds.addAll(teleport.getItemIds());
        }

        // Add items that work for multiple teleports
        relevantItemIds.add(ItemID.LAW_RUNE);

        // If any fairy ring is in the list, add dramen/lunar staff
        if (bestTeleports.stream().anyMatch(t -> t.getName().contains("Fairy Ring"))) {
            relevantItemIds.add(ItemID.DRAMEN_STAFF);
            relevantItemIds.add(ItemID.LUNAR_STAFF);
        }
    }
}