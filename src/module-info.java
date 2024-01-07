module portal {
    requires com.google.gson;

    exports pro.freeserver.alphakun.plugin.portal;

    opens pro.freeserver.alphakun.plugin.portal to com.google.gson;
    opens org.bukkit.Location to com.google.gson;
}