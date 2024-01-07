package pro.freeserver.alphakun.plugin.portal.handlers;

import org.bukkit.entity.Player;
import pro.freeserver.alphakun.plugin.portal.models.TeleportSet;

import java.util.HashSet;
import java.util.Objects;
import java.util.logging.Level;

import static pro.freeserver.alphakun.plugin.portal.Portal.config;
import static pro.freeserver.alphakun.plugin.portal.Portal.portalInstance;
import static pro.freeserver.alphakun.plugin.portal.handlers.JsonHandler.saveJsonFile;

public class TeleportHandler {
    public HashSet<TeleportSet> teleportSetList;

    public TeleportHandler() {
        this.teleportSetList = new HashSet<>();
    }

    public void saveTeleportHandler() {
        var isSuccessful = saveJsonFile(config.portalDataFileName, this);
        if (!isSuccessful) {
            portalInstance.getLogger().log(Level.WARNING, "The teleport list was not saved because it did not exist.");
        }
    }

    public boolean addTeleport(TeleportSet teleportSet) {
        if (this.isExist(teleportSet)) return false;
        teleportSetList.add(teleportSet);
        this.saveTeleportHandler();
        return true;
    }

    public boolean removeTeleport(String name) {
        boolean result = this.teleportSetList.remove(this.getTeleportSet(name));
        if (result) {
            this.saveTeleportHandler();
        }
        return result;
    }

    public boolean isExist(TeleportSet teleportSet) {
        if (Objects.isNull(teleportSet)) {return false;}
        for (var definedSpot : teleportSetList) {
            if (definedSpot.getSpotName().equalsIgnoreCase(teleportSet.getSpotName())) {
                return true;
            }
        }
        return false;
    }

    public boolean isExist(String name) {
        return isExist(new TeleportSet(name));
    }

    public boolean playerTeleport(Player player, TeleportSet teleportSet) {
        if (isExist(teleportSet)) {
            return player.teleport(teleportSet.getLocation());
        }
        return false;
    }

    public TeleportSet getTeleportSet(String spotName) {
        for(TeleportSet teleportSet:this.teleportSetList) {
            if (teleportSet.getSpotName().equalsIgnoreCase(spotName)) {
                return teleportSet;
            }
        }
        return null;
    }
}
