package pro.freeserver.alphakun.plugin.portal.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Teleport implements CommandExecutor {
    int freeDistance = 16;
    String commandLabel = "warp";
    @Override
    public boolean onCommand(@NotNull CommandSender sender,
                             @NotNull Command command,
                             @NotNull String label,
                             @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (!(command.getLabel().equalsIgnoreCase(commandLabel))) return false;
            if (args[0].isEmpty()){

            }
        }
        return false;
    }
}
