package pro.freeserver.alphakun.plugin.portal.consts;

public enum TPSpotVisibility {
//    PRIVATE("private"),
    PUBLIC("public");

    public final String label;

    TPSpotVisibility(String label) {
        this.label = label;
    }

    public static boolean isTPSpotVisibilityLabel(String label) {
        for (TPSpotVisibility tpSpotVisibility: TPSpotVisibility.values()) {
            if (tpSpotVisibility.label.equalsIgnoreCase(label)) return true;
        }
        return false;
    }

    public static TPSpotVisibility convertTPSpotVisibility(String label) {
        for (TPSpotVisibility tpSpotVisibility: TPSpotVisibility.values()) {
            if (tpSpotVisibility.label.equalsIgnoreCase(label)) {
                return tpSpotVisibility;
            }
        }
        return null;
    }
}
