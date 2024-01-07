package pro.freeserver.alphakun.plugin.portal.consts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum PortalCommandArgments {
    TP("tp", "Teleport to predefined destinations."),
    SET_TP("setTP", "Create destinations."),
    REMOVE_TP("remove", "Remove destinations."),
    LIST("list", "Show list of predefined destinations.");

    public final String label;
    public final String description;

    PortalCommandArgments(String label, String description) {
        this.label       = label;
        this.description = description;
    }

    public static List<String> getPortalCommandArgs() {
        List<String> list = new ArrayList<>();
        Arrays.stream(PortalCommandArgments.values()).iterator().forEachRemaining(it -> {
            list.add(it.label);
        });
        return list;
    }
}

