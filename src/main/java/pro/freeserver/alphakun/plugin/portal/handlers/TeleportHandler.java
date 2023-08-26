package pro.freeserver.alphakun.plugin.portal.handlers;

import org.bukkit.entity.Player;
import pro.freeserver.alphakun.plugin.portal.models.TeleportSet;

public class TeleportHandler {
    public boolean playerTeleport(Player player, TeleportSet teleportSet) {
        return player.teleport(teleportSet.getLocation());
    }

    public TeleportSet getTeleportSet(String spotName) {
        return null;
    }
}
