package pro.freeserver.alphakun.plugin.portal.consts;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.UUID;

public class TeleportLocation {
    public String worldUID;
    public double x;
    public double y;
    public double z;
    public float pitch;
    public float yaw;
    public TeleportLocation(Location location) {
        this.worldUID   = location.getWorld().getUID().toString();
        this.pitch      = location.getPitch();
        this.yaw        = location.getYaw();
        this.x          = location.getX();
        this.y          = location.getY();
        this.z          = location.getZ();
    }

    public Location toLocation() {
        return new Location(
                Bukkit.getWorld(UUID.fromString(worldUID)),
                x,
                y,
                z,
                yaw,
                pitch
        );
    }
}
