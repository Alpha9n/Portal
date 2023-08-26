package pro.freeserver.alphakun.plugin.portal;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import pro.freeserver.alphakun.plugin.portal.models.ConfigModel;
import pro.freeserver.alphakun.plugin.portal.models.TeleportSet;
import pro.freeserver.alphakun.plugin.portal.handlers.JsonHandler;

import java.util.ArrayList;

public final class Portal extends JavaPlugin {
//    TODO("ユーザーがテレポートする時はGUIからで、テレポート可能な場所はGUIに一覧として表示")
//    TODO("設定は右、テレポは左");
    public static ArrayList<TeleportSet> teleportSets;
    public static ConfigModel config;
    private static JsonHandler<ArrayList<TeleportSet>> teleportSetJson;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        config = new ConfigModel(getConfig());
        teleportSetJson = new JsonHandler<>();
        teleportSets = teleportSetJson.readJsonFile(config.portalData);
    }
}
