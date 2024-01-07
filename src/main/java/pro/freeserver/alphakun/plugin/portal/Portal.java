package pro.freeserver.alphakun.plugin.portal;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import pro.freeserver.alphakun.plugin.portal.commands.PortalCommand;
import pro.freeserver.alphakun.plugin.portal.commands.PortalTabCompletion;
import pro.freeserver.alphakun.plugin.portal.handlers.JsonHandler;
import pro.freeserver.alphakun.plugin.portal.handlers.TeleportHandler;
import pro.freeserver.alphakun.plugin.portal.models.ConfigModel;

import java.io.File;
import java.util.Objects;

public final class Portal extends JavaPlugin {
//    TODO("ユーザーがテレポートする時はGUIからで、テレポート可能な場所はGUIに一覧として表示")
//    TODO("設定は右、テレポは左");
    public static TeleportHandler teleportHandler = null;
    public static ConfigModel config;
    public static Plugin portalInstance;

    @Override
    public void onEnable() {
        portalInstance = this;
        saveDefaultConfig();
        config = new ConfigModel(getConfig());
        loadPortal();
        registerCommands();
    }

    @Override
    public void onDisable() {
        teleportHandler.saveTeleportHandler();
    }

    private void loadPortal() {
        File file = new File(getDataFolder(), config.portalDataFileName + ".json");
        if (file.exists()) {
            teleportHandler = JsonHandler.readJsonFile(config.portalDataFileName);
        }
        if (Objects.isNull(teleportHandler)) {
            teleportHandler = new TeleportHandler();
        }
    }

    private void registerCommands() {
        Objects.requireNonNull(getCommand("portal")).setExecutor(new PortalCommand());
        Objects.requireNonNull(getCommand("portal")).setTabCompleter(new PortalTabCompletion());
    }
}

