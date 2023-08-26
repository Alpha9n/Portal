package pro.freeserver.alphakun.plugin.portal.models;

import org.bukkit.Location;

public class TeleportSet {

    String spotName;
    Location location;

    TeleportSet(String spotName, Location location) {
        this.spotName = spotName;
        this.location = location;
    }

    public String getSpotName() {
        return spotName;
    }

    public void setSpotName(String spotName) {
        this.spotName = spotName;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
