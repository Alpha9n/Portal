package pro.freeserver.alphakun.plugin.portal.models;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.jetbrains.annotations.NotNull;
import pro.freeserver.alphakun.plugin.portal.consts.TPSpotVisibility;
import pro.freeserver.alphakun.plugin.portal.consts.TeleportLocation;

import java.util.UUID;

public class TeleportSet {
    public String spotName;
    public TeleportLocation location;
    public UUID ownerUniqueId;
    @NonNull public TPSpotVisibility visibility;

    public TeleportSet(String spotName, Location location, Player locationOwner, @NotNull TPSpotVisibility visibility) {
        this.spotName       = spotName;
        this.location       = new TeleportLocation(location);
        this.ownerUniqueId = locationOwner.getUniqueId();
        this.visibility     = visibility;
    }

    public TeleportSet(Location location) {
        this.location       = new TeleportLocation(location);
        this.spotName       = "untitled";
        this.ownerUniqueId  = null;
        this.visibility     = TPSpotVisibility.PUBLIC;
    }

    public TeleportSet(String spotName) {
        this.location       = null;
        this.spotName       = spotName;
        this.ownerUniqueId  = null;
        this.visibility     = TPSpotVisibility.PUBLIC;
    }

    public TeleportSet() {
        this.location       = null;
        this.spotName       = null;
        this.ownerUniqueId  = null;
        this.visibility     = TPSpotVisibility.PUBLIC;
    }

    public String getSpotName() {
        return spotName;
    }

    public TeleportSet setSpotName(String spotName) {
        this.spotName = spotName;
        return this;
    }

    public OfflinePlayer getLocationOwner() {
        return Bukkit.getOfflinePlayer(this.ownerUniqueId);
    }

    public TeleportSet setUniqueId(Player locationOwner) {
        this.ownerUniqueId = locationOwner.getUniqueId();
        return this;
    }

    public @NotNull TPSpotVisibility getVisibility() {
        return visibility;
    }

    public TeleportSet setVisibility(TPSpotVisibility visibility) {
        this.visibility = visibility;
        return this;
    }

    public Location getLocation() {
        return location.toLocation();
    }

    public TeleportSet setLocation(Location location) {
        this.location = new TeleportLocation(location);
        return this;
    }
}
