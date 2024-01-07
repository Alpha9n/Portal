package pro.freeserver.alphakun.plugin.portal.commands;

import net.kyori.adventure.text.Component;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pro.freeserver.alphakun.plugin.portal.consts.PortalCommandArgments;

import java.util.ArrayList;
import java.util.List;

import static pro.freeserver.alphakun.plugin.portal.Portal.teleportHandler;
import static pro.freeserver.alphakun.plugin.portal.consts.PortalCommandArgments.getPortalCommandArgs;

public class PortalTabCompletion implements TabCompleter {
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        String commandLabel = "portal";
        List<String> resultList = new ArrayList<>();
        if (sender instanceof Player player) {
            if (command.getLabel().equalsIgnoreCase(commandLabel)) {
                if (args.length == 1) {
                    return getPortalCommandArgs();
                }
                String firstArg = args[0];
                if (args.length == 2){
                    if (firstArg.equalsIgnoreCase(PortalCommandArgments.REMOVE_TP.label)) {
                        resultList.addAll(getTPList());
                        if (resultList.isEmpty()) {
                            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 100f, 0.1f);
                            player.sendActionBar(Component.text("§c削除可能な地点が見つかりませんでした。"));
                        }
                    } else if (firstArg.equalsIgnoreCase(PortalCommandArgments.TP.label)) {
                        resultList.addAll(getTPList());
                        if (resultList.isEmpty()) {
                            player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BIT, 100f, 0.1f);
                            player.sendActionBar(Component.text("§cテレポート可能な地点が見つかりませんでした。"));
                        }
                    }
                }
            }
        }
        return resultList;
    }


    private List<String> getTPList() {
        List<String> resultList = new ArrayList<>();
        teleportHandler.teleportSetList.forEach(ts -> {
            resultList.add(ts.getSpotName());
        });
        return resultList;
    }
}
