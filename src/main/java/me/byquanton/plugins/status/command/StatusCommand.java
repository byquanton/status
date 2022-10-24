package me.byquanton.plugins.status.command;

import cloud.commandframework.arguments.standard.StringArgument;
import cloud.commandframework.context.CommandContext;
import cloud.commandframework.minecraft.extras.TextColorArgument;
import me.byquanton.plugins.status.StatusPlugin;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Optional;

public class StatusCommand extends CommandHandler {
    public StatusCommand(StatusPlugin statusPlugin, CommandManager commandManager) {
        super(statusPlugin, commandManager);
    }

    @Override
    public void register() {


        StringArgument.Builder<CommandSender> statusArgument = StringArgument.newBuilder("message");
        statusArgument.withSuggestionsProvider((context, string) -> List.of("LIVE", "REC", "AFK", "RP"));

        commandManager.command(commandManager.commandBuilder("status")
                .argument(statusArgument)
                .argument(TextColorArgument.optional("color"))
                .handler(this::status)
        );

        commandManager.command(commandManager.commandBuilder("clearstatus")
                .handler(this::clearStatus)
        );
    }

    private void clearStatus(CommandContext<CommandSender> context) {
        if(context.getSender() instanceof Player player) {
            player.playerListName(player.name());
        }
    }

    private void status(CommandContext<CommandSender> context) {
        String message = context.get("message");
        Optional<TextColor> color = context.getOptional("color");

        if(context.getSender() instanceof Player player){
            String formatString = StatusPlugin.plugin.getConfig().getString("status-format");
            Component status = MiniMessage.miniMessage().deserialize(message);
            switch (message.toLowerCase()){
                case "live" -> status = status.color(NamedTextColor.RED);
                case "rec" -> status = status.color(NamedTextColor.BLUE);
                case "afk" -> status = status.color(NamedTextColor.GRAY);
                case "rp" -> status = status.color(NamedTextColor.GREEN);
            }
            if(color.isPresent()){
                status = status.color(color.get());
            }

            if(formatString == null){
                formatString = "<gray>[</gray><status><gray>]</gray> <name>";
            }
            Component playerListName = MiniMessage.miniMessage().deserialize(formatString, Placeholder.unparsed("name", player.getName()), Placeholder.component("status", status));
            player.playerListName(playerListName);
            player.sendMessage(Component.text("Your status has been set to: ").append(status));
        }

    }
}
