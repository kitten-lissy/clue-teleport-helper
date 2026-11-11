package com.clueteleporthelper;

import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayPriority;
import net.runelite.client.ui.overlay.components.LineComponent;
import net.runelite.client.ui.overlay.components.PanelComponent;
import net.runelite.client.ui.overlay.components.TitleComponent;

import javax.inject.Inject;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.List;

public class ClueTeleportHelperPanelOverlay extends Overlay {

    private static final Color UNUSABLE = new Color(128, 128, 128);

    private final ClueTeleportHelperPlugin plugin;
    private final ClueTeleportHelperConfig config;
    private final PanelComponent panelComponent = new PanelComponent();

    @Inject
    public ClueTeleportHelperPanelOverlay(ClueTeleportHelperPlugin plugin, ClueTeleportHelperConfig config) {
        this.plugin = plugin;
        this.config = config;
        setPosition(OverlayPosition.TOP_LEFT);
        setPriority(OverlayPriority.HIGH);
    }

    @Override
    public Dimension render(Graphics2D graphics) {
        List<TeleportSuggestion> lines = plugin.getSuggestions();
        String note = plugin.getNote();

        // Kill tasks and skill challenges have no tile, so the panel must not depend on one.
        if (lines.isEmpty() && note == null) {
            return null;
        }

        panelComponent.getChildren().clear();
        panelComponent.setPreferredSize(new Dimension(config.panelWidth(), 0));

        panelComponent.getChildren().add(TitleComponent.builder()
                .text("Best Teleports")
                .color(config.titleColour())
                .build());

        if (note != null) {
            panelComponent.getChildren().add(LineComponent.builder()
                    .left(note)
                    .leftColor(config.noteColour())
                    .build());
        }

        boolean showMethod = config.showMethod();
        boolean showDistance = config.showDistance();

        for (int i = 0; i < lines.size(); i++) {
            TeleportSuggestion line = lines.get(i);

            String prefix = line.getLabel() != null ? line.getLabel() + ": " : (i + 1) + ". ";
            String left = prefix + line.getDestination() + (line.isWilderness() ? " (Wildy)" : "");
            Color leftColor = !line.isUsable() ? UNUSABLE
                    : line.isWilderness() ? config.wildernessColour()
                    : config.destinationColour();

            Integer distance = showDistance ? line.getDistance() : null;
            String distanceText = distance != null ? distance + " tiles" : null;
            Color distanceColor = distance != null ? distanceColor(distance) : null;

            if (showMethod) {
                panelComponent.getChildren().add(LineComponent.builder()
                        .left(left)
                        .leftColor(leftColor)
                        .right(line.getMethodLabel())
                        .rightColor(line.isUsable() ? config.methodColour() : UNUSABLE)
                        .build());
                if (distanceText != null) {
                    panelComponent.getChildren().add(LineComponent.builder()
                            .right(distanceText)
                            .rightColor(distanceColor)
                            .build());
                }
            } else {
                // Without the method hint there is room for the distance on the same line.
                panelComponent.getChildren().add(LineComponent.builder()
                        .left(left)
                        .leftColor(leftColor)
                        .right(distanceText)
                        .rightColor(distanceColor)
                        .build());
            }
        }

        String footnote = plugin.getFootnote();
        if (footnote != null) {
            panelComponent.getChildren().add(LineComponent.builder()
                    .left(footnote)
                    .leftColor(UNUSABLE)
                    .build());
        }

        return panelComponent.render(graphics);
    }

    private Color distanceColor(int distance) {
        if (!config.colourDistances()) {
            return config.distanceColour();
        }
        return distanceShade(distance);
    }

    /** Deep pink up to 20 tiles, hot pink up to 50, light pink beyond. */
    static Color distanceShade(int distance) {
        if (distance <= 20) {
            return new Color(255, 20, 147);
        } else if (distance <= 50) {
            return new Color(255, 105, 180);
        } else {
            return new Color(255, 182, 193);
        }
    }
}
