package pro.freeserver.alphakun.plugin.portal.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import pro.freeserver.alphakun.plugin.portal.consts.MessageReason;
import pro.freeserver.alphakun.plugin.portal.consts.PortalCommandArgments;
import pro.freeserver.alphakun.plugin.portal.consts.TPSpotVisibility;
import pro.freeserver.alphakun.plugin.portal.models.TeleportSet;
import pro.freeserver.alphakun.plugin.portal.utils.ChatUtils;

import java.util.Arrays;
import java.util.Objects;

import static pro.freeserver.alphakun.plugin.portal.Portal.config;
import static pro.freeserver.alphakun.plugin.portal.Portal.teleportHandler;
import static pro.freeserver.alphakun.plugin.portal.consts.TPSpotVisibility.convertTPSpotVisibility;
import static pro.freeserver.alphakun.plugin.portal.utils.ChatUtils.chatSplitter;

public class PortalCommand implements CommandExecutor {
    String commandLabel = "portal";
    @Override
    public boolean onCommand(@NotNull CommandSender sender,
                             @NotNull Command command,
                             @NotNull String label,
                             @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (command.getLabel().equalsIgnoreCase(commandLabel)) {
                if (args.length == 0){
                    sendHelpMessage(player, MessageReason.INFO);
                    return true;
                }
                String firstArg = args[0];
                if (firstArg.equalsIgnoreCase(PortalCommandArgments.SET_TP.label)) {
                    return validateSetTPArgs(args, player);
                }
                if (firstArg.equalsIgnoreCase(PortalCommandArgments.REMOVE_TP.label)) {
                    return validateRemoveTPArgs(args, player);
                }
                if (firstArg.equalsIgnoreCase(PortalCommandArgments.LIST.label)) {
                    return validateListArgs(args, player);
                }
                if (firstArg.equalsIgnoreCase(PortalCommandArgments.TP.label)) {
                    String secondArg = args[1];
                    return teleportPlayer(secondArg, player);
                }
            }
        }
        return false;
    }

    // TODO("rename oldName newName とかでリネームできるといいかも");

    private boolean validateListArgs(String[] args, Player player) {
        if (args.length == 1) {
            player.sendMessage(ChatUtils.chatSplitter(30, "テレポート先", TextColor.fromHexString(config.pluginColor))); // TODO("configで設定できるようにする")
            if (teleportHandler.teleportSetList.isEmpty()) {
                player.sendMessage(MessageReason.WARN.prefix + "テレポート先が存在しません。");
            }
            for (var teleportSet: teleportHandler.teleportSetList) { // TODO("スタイリングする")
                String message = teleportSet.spotName;
                Component component = ChatUtils.onClickCommand(message, "portal", Arrays.asList("tp", teleportSet.spotName));
                player.sendMessage(component);
            }
            player.sendMessage(ChatUtils.chatSplitter(30, TextColor.fromHexString(config.pluginColor)));
            return true;
        }
        return false;
    }

    private boolean validateSetTPArgs(String[] args, Player player) {
        TeleportSet tempTeleportSet = new TeleportSet();

        // 構文チェック
        if (args.length == 1) { // setTPの引数が足りない場合
            sendHelpMessage(player, MessageReason.SYNTAX_INCORRECT);
            return false;
        }

        if (args.length >= 2) {
            TPSpotVisibility tpSpotVisibility;
            if (args.length == 2) { // 公開範囲の設定がない場合の処理
                tpSpotVisibility = TPSpotVisibility.PUBLIC;
            } else { // 公開範囲の設定がある場合の処理
                tpSpotVisibility = convertTPSpotVisibility(args[2]);
            }
            if (Objects.isNull(tpSpotVisibility)) return false; // コマンドにて指定した可視性が正しくない時

            if (tpSpotVisibility.equals(TPSpotVisibility.PUBLIC)) { // publicの場合のバリデーション
                tempTeleportSet
                        .setSpotName(args[1])
                        .setLocation(player.getLocation());
            }
            // TODO("privateの処理をここに追加する");
        }

        boolean isSuccessful = teleportHandler.addTeleport(tempTeleportSet);
        if (!isSuccessful) { // コマンドにて指定したテレポート先の名称がすでに存在する時
            player.sendMessage(MessageReason.ERROR.prefix + config.nameDuplicateErrorMessage);
            return false;
        }
        player.sendMessage(MessageReason.INFO.prefix + config.setTPSuccessfulMessage);
        return true;
        // TODO("名前が被らないようにチェック、privateの場合は作った人で被らないように設定、publicの場合は全体で被らないように設定");
    }

    private boolean validateRemoveTPArgs(String[] args, Player player) {
        if (!teleportHandler.removeTeleport(args[1])) {
            player.sendMessage(MessageReason.ERROR.prefix + args[1] + config.teleportRemoveFailMessage);
            return false;
        }
        player.sendMessage(MessageReason.INFO.prefix + args[1] + config.teleportRemoveSuccessfulMessage);
        return true;
    }

    private boolean teleportPlayer(String teleportName, Player player) {
        boolean isSuccess = teleportHandler.playerTeleport(player, teleportHandler.getTeleportSet(teleportName));
        if (!isSuccess) {
            player.sendMessage(MessageReason.WARN.prefix + teleportName + config.teleportFailMessage);
            return false;
        }

        Player afterLocationPlayer = Bukkit.getPlayer(player.getUniqueId()); // テレポート後のプレイヤー位置を取得する。
        player.playSound(Objects.requireNonNull(afterLocationPlayer), config.teleportSound, 1.0f, 1.0f);
        player.sendMessage(MessageReason.INFO.prefix + teleportName + config.teleportSuccessfulMessage);
        return true;
    }

    private void sendHelpMessage(CommandSender sender, MessageReason reason) {
        if (reason != MessageReason.INFO) {
            sender.sendMessage(reason.prefix + reason.message);
        }
        for (String message: config.helpMessageList) {
            sender.sendMessage(message);
        }
    }
}
