package pro.freeserver.alphakun.plugin.portal.utils;

import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ChatUtils {
    public static final String LINK_COLOR = "#3971c4";

    public static Component onClickCommand(String text, String commandLabel, List<String> commandArgs) {
        String argment = String.join(" ", commandArgs);
        System.out.println(argment);
        return Component
                .text(text)
                .decorate(TextDecoration.UNDERLINED)
                .color(TextColor.fromHexString(LINK_COLOR))
                .hoverEvent(HoverEvent.showText(Component.text("クリックで /" + commandLabel + " " + argment + "を入力します。")))
                .clickEvent(ClickEvent.suggestCommand("/"+ commandLabel + " " + argment));
    }

    public static Component chatSplitter(int width, TextColor color) {
        return chatSplitter(width, null, color);
    }

    public static Component chatSplitter(int width, String title, TextColor color) {
        StringBuilder splitter = new StringBuilder();
        while (width --> 0) splitter.append("=");
        if (Objects.nonNull(title)) {
            var endList = width - title.length();
            if (endList <= 0) endList = 0;
            splitter = new StringBuilder(splitter.substring(0, endList));
            splitter.insert(splitter.length()/2, "[§f" + title + "§r]");
        }
        return Component
                .text(splitter.toString())
                .color(color);
    }
}
