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

        for (int i = 0; i < bestTeleports.size(); i++) {
            TeleportData teleport = bestTeleports.get(i);
            int distance = teleport.getLocation().distanceTo(clueLocation);

            String displayText = (i + 1) + ". " + teleport.getName();
            String distanceText = distance + " tiles";

            panelComponent.getChildren().add(LineComponent.builder()
                    .left(displayText)
                    .leftColor(new Color(255, 182, 193))
                    .right(distanceText)
                    .rightColor(getDistanceColor(distance))
                    .build());
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