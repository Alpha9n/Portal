package pro.freeserver.alphakun.plugin.portal.consts;

import javax.annotation.Nullable;

import static pro.freeserver.alphakun.plugin.portal.Portal.config;

public enum MessageReason {
    INFO("§f[§aINFO§f]§r: ", "§r", null),
    WARN("§f[§eWARN§f]§r: ", "§r", null),
    ERROR("§f[§cERROR§f]§r: ", "§r", null),
    SYNTAX_INCORRECT("§f[§cERROR§f]§r: ", config.syntaxErrorMessage, null);

    public final String prefix;
    public final String message;
    public final String suffix;
    MessageReason(final String prefix, final String message, @Nullable final String suffix) {
        this.prefix = prefix;
        this.message = message;
        this.suffix = suffix;
    }
}
