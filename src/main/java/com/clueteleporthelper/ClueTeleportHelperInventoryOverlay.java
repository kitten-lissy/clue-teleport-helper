package com.clueteleporthelper;

import net.runelite.api.ItemID;
import net.runelite.api.gameval.InterfaceID;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetItem;
import net.runelite.api.widgets.WidgetUtil;
import net.runelite.client.ui.overlay.WidgetItemOverlay;

import javax.inject.Inject;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ClueTeleportHelperInventoryOverlay extends WidgetItemOverlay {

    private final ClueTeleportHelperConfig config;
    private volatile Set<Integer> relevantItemIds = Collections.emptySet();

    @Inject
    public ClueTeleportHelperInventoryOverlay(ClueTeleportHelperConfig config) {
        this.config = config;
        showOnInventory();
        showOnBank();
        showOnEquipment();
    }

    @Override
    public void renderItemOverlay(Graphics2D graphics, int itemId, WidgetItem widgetItem) {
        if (!relevantItemIds.contains(itemId) || !shouldHighlight(widgetItem)) {
            return;
        }
        Rectangle bounds = widgetItem.getCanvasBounds();

        Color fill = config.highlightFillColour();
        graphics.setColor(new Color(fill.getRed(), fill.getGreen(), fill.getBlue(), config.highlightFillOpacity()));
        graphics.fill(bounds);

        int width = config.highlightBorderWidth();
        if (width > 0) {
            graphics.setColor(config.highlightBorderColour());
            graphics.setStroke(new BasicStroke(width));
            graphics.draw(bounds);
        }
    }

    private boolean shouldHighlight(WidgetItem widgetItem) {
        Widget widget = widgetItem.getWidget();
        int group = widget == null ? -1 : WidgetUtil.componentToInterface(widget.getId());
        if (group == InterfaceID.BANKMAIN) {
            return config.highlightBank();
        }
        if (group == InterfaceID.WORNITEMS) {
            return config.highlightEquipment();
        }
        return config.highlightInventory();
    }

    /**
     * Collect the items behind the suggested teleports. Law runes come only from the
     * spells that need them, so they light up only when a spell is suggested.
     */
    public void updateRelevantItems(List<TeleportSuggestion> suggestions) {
        Set<Integer> ids = new HashSet<>();
        boolean fairyRing = false;
        for (TeleportSuggestion line : suggestions) {
            for (TeleportData tp : line.getTeleports()) {
                ids.addAll(tp.getItemIds());
                if (tp.getMethod() == TeleportData.Method.FAIRY_RING) {
                    fairyRing = true;
                }
            }
        }
        if (fairyRing) {
            ids.add(ItemID.DRAMEN_STAFF);
            ids.add(ItemID.LUNAR_STAFF);
        }
        relevantItemIds = Collections.unmodifiableSet(ids);
    }
}
