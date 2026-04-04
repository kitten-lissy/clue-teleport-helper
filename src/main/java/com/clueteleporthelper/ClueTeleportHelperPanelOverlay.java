package com.clueteleporthelper;

import net.runelite.api.Client;
import net.runelite.api.coords.WorldPoint;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayPriority;
import net.runelite.client.ui.overlay.components.LineComponent;
import net.runelite.client.ui.overlay.components.PanelComponent;
import net.runelite.client.ui.overlay.components.TitleComponent;

import javax.inject.Inject;
import java.awt.*;
import java.util.List;

public class ClueTeleportHelperPanelOverlay extends Overlay {

    private final Client client;
    private final ClueTeleportHelperPlugin plugin;
    private final PanelComponent panelComponent = new PanelComponent();

    @Inject
    public ClueTeleportHelperPanelOverlay(Client client, ClueTeleportHelperPlugin plugin) {
        this.client = client;
        this.plugin = plugin;
        setPosition(OverlayPosition.TOP_LEFT);
        setPriority(OverlayPriority.HIGH);
    }

    @Override
    public Dimension render(Graphics2D graphics) {
        List<TeleportData> bestTeleports = plugin.getBestTeleports();

        if (bestTeleports == null || bestTeleports.isEmpty()) {
            return null;
        }

        WorldPoint clueLocation = plugin.getClueScrollLocation();
        if (clueLocation == null) {
            return null;
        }

        panelComponent.getChildren().clear();
        panelComponent.setPreferredSize(new Dimension(280, 0));

        panelComponent.getChildren().add(TitleComponent.builder()
                .text("Best Teleports")
                .color(new Color(255, 105, 180))
                .build());

        boolean isOverride = plugin.isOverrideActive();
        String overrideNote = plugin.getOverrideNote();

        if (isOverride && overrideNote != null) {
            panelComponent.getChildren().add(LineComponent.builder()
                    .left(overrideNote)
                    .leftColor(new Color(255, 215, 0))
                    .build());
        }

        for (int i = 0; i < bestTeleports.size(); i++) {
            TeleportData teleport = bestTeleports.get(i);
            String fullName = teleport.getName();

            // Split "Destination (Item)" into two parts
            String destination = fullName;
            String itemHint = null;
            int parenStart = fullName.lastIndexOf('(');
            if (parenStart > 0) {
                destination = fullName.substring(0, parenStart).trim();
                itemHint = fullName.substring(parenStart + 1, fullName.length() - 1);
            }

            String displayText = (i + 1) + ". " + destination;

            // Line 1: Destination (light pink) | Item (hot pink)
            panelComponent.getChildren().add(LineComponent.builder()
                    .left(displayText)
                    .leftColor(new Color(255, 182, 193))
                    .right(itemHint)
                    .rightColor(new Color(255, 105, 180))
                    .build());

            // Line 2: Distance in tiles
            if (teleport.getLocation() != null) {
                WorldPoint groundClue = new WorldPoint(
                        clueLocation.getX(), clueLocation.getY(), 0);
                int distance = teleport.getLocation().distanceTo(groundClue);
                panelComponent.getChildren().add(LineComponent.builder()
                        .right(distance + " tiles")
                        .rightColor(new Color(255, 160, 122))
                        .build());
            }
        }

        return panelComponent.render(graphics);
    }

    private Color getDistanceColor(int distance) {
        if (distance <= 20) {
            return new Color(255, 20, 147);
        } else if (distance <= 50) {
            return new Color(255, 105, 180);
        } else {
            return new Color(255, 182, 193);
        }
    }
}