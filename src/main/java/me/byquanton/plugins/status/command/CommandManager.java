package me.byquanton.plugins.status.command;

import cloud.commandframework.brigadier.CloudBrigadierManager;
import cloud.commandframework.bukkit.CloudBukkitCapabilities;
import cloud.commandframework.execution.CommandExecutionCoordinator;
import cloud.commandframework.paper.PaperCommandManager;
import com.google.common.collect.ImmutableList;
import me.byquanton.plugins.status.StatusPlugin;
import org.bukkit.command.CommandSender;

import java.util.function.UnaryOperator;

public class CommandManager extends PaperCommandManager<CommandSender> {

    public CommandManager(final StatusPlugin statusPlugin) throws Exception {
        super(
                statusPlugin,
                CommandExecutionCoordinator.simpleCoordinator(),
                UnaryOperator.identity(),
                UnaryOperator.identity()
        );

        if (this.hasCapability(CloudBukkitCapabilities.NATIVE_BRIGADIER)) {
            try {
                this.registerBrigadier();
                final CloudBrigadierManager<?, ?> brigManager = this.brigadierManager();
                if (brigManager != null) {
                    brigManager.setNativeNumberSuggestions(false);
                }
            } catch (final Exception e) {
                statusPlugin.getLogger().warning("Failed to initialize Brigadier support: " + e.getMessage());
            }
        }

        if (this.hasCapability(CloudBukkitCapabilities.ASYNCHRONOUS_COMPLETION)) {
            this.registerAsynchronousCompletions();
        }


        ImmutableList.of(
                new StatusCommand(statusPlugin, this)
        ).forEach(CommandHandler::register);


    }
}
