package com.clueteleporthelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * One line of the panel: a teleport, plus any other teleports that land on
 * (almost) the same tile, the distance to the target and an optional label.
 */
public class TeleportSuggestion {

    private final List<TeleportData> teleports = new ArrayList<>();
    private final Integer distance;
    private final String label;
    private boolean usable;

    TeleportSuggestion(TeleportData primary, Integer distance, String label, boolean usable) {
        this.teleports.add(primary);
        this.distance = distance;
        this.label = label;
        this.usable = usable;
    }

    void addAlternative(TeleportData tp, boolean altUsable) {
        if (teleports.contains(tp)) {
            return;
        }
        if (altUsable && !usable) {
            // Lead with something the player can actually use.
            teleports.add(0, tp);
            usable = true;
        } else {
            teleports.add(tp);
        }
    }

    public TeleportData getPrimary() {
        return teleports.get(0);
    }

    /** All teleports on this line, the primary first. */
    public List<TeleportData> getTeleports() {
        return Collections.unmodifiableList(teleports);
    }

    /** Straight-line tiles from the primary teleport to the target, or null when not meaningful. */
    public Integer getDistance() {
        return distance;
    }

    /** Prefix such as "Step 2", or null. */
    public String getLabel() {
        return label;
    }

    public boolean isUsable() {
        return usable;
    }

    public boolean isWilderness() {
        return getPrimary().isWilderness();
    }

    public String getDestination() {
        return getPrimary().getDestination();
    }

    /** Method hints of every teleport on the line, for example "Games Neck / Minigame". */
    public String getMethodLabel() {
        Set<String> labels = new LinkedHashSet<>();
        for (TeleportData tp : teleports) {
            String m = tp.getMethodLabel();
            if (m != null) {
                labels.add(m);
            }
        }
        return labels.isEmpty() ? null : String.join(" / ", labels);
    }
}
