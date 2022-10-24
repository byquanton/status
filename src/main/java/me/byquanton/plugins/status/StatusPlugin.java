package me.byquanton.plugins.status;

import me.byquanton.plugins.status.command.CommandManager;
import org.bukkit.plugin.java.JavaPlugin;

public class StatusPlugin extends JavaPlugin {

    public static StatusPlugin plugin;

    @Override
    public void onEnable() {
        plugin = this;

        try {
            new CommandManager(this);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onDisable() {

    }
}
