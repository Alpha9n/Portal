package pro.freeserver.alphakun.plugin.portal.models;

import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class ConfigModel {
    public static final String FILE_NAME_PATH = "fileName.";
    public static final String GENERAL_SETTINGS = "generalSettings.";
    public static final String MESSAGE_SETTINGS = "messageSettings.";

    public String portalDataFileName;
    public int freeTeleportDistance;
    public Sound teleportSound;
    public String pluginColor;
    public List<String> helpMessageList;
    public String setTPSuccessfulMessage;
    public String teleportSuccessfulMessage;
    public String teleportFailMessage;
    public String nameDuplicateErrorMessage;
    public String teleportRemoveSuccessfulMessage;
    public String teleportRemoveFailMessage;
    public String syntaxErrorMessage;


    public ConfigModel(FileConfiguration config) {
        portalDataFileName              = config.getString(FILE_NAME_PATH + "portalData");
        freeTeleportDistance            = config.getInt(GENERAL_SETTINGS + "freeTeleportDistance");
        teleportSound                   = toSound(config.getString(GENERAL_SETTINGS + "teleportSound"));
        pluginColor                     = config.getString(GENERAL_SETTINGS + "pluginColor");
        helpMessageList                 = config.getStringList(MESSAGE_SETTINGS + "helpMessageList");
        setTPSuccessfulMessage          = config.getString(MESSAGE_SETTINGS + "setTPSuccessfulMessage");
        teleportSuccessfulMessage       = config.getString(MESSAGE_SETTINGS + "teleportSuccessfulMessage");
        teleportFailMessage             = config.getString(MESSAGE_SETTINGS + "teleportFailMessage");
        nameDuplicateErrorMessage       = config.getString(MESSAGE_SETTINGS + "nameDuplicateErrorMessage");
        teleportRemoveSuccessfulMessage = config.getString(MESSAGE_SETTINGS + "teleportRemoveSuccessfulMessage");
        teleportRemoveFailMessage       = config.getString(MESSAGE_SETTINGS + "teleportRemoveFailMessage");
        syntaxErrorMessage              = config.getString(MESSAGE_SETTINGS + "syntaxErrorMessage");
    }

    private Sound toSound(String soundName) {
        try {
            return Sound.valueOf(soundName);
        } catch (IllegalArgumentException e) {
            return Sound.ENTITY_ENDERMAN_TELEPORT;
        }
    }
}
