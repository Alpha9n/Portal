package pro.freeserver.alphakun.plugin.portal.models;

import org.bukkit.configuration.file.FileConfiguration;

public class ConfigModel {
    private String fileNamePath = "fileName.";

    public String portalData;

    public ConfigModel(FileConfiguration config) {
        portalData = config.getString(fileNamePath + "portalData");
    }
}
