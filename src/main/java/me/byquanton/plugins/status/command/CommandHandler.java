package me.byquanton.plugins.status.command;


import me.byquanton.plugins.status.StatusPlugin;

public abstract class CommandHandler {
    protected final StatusPlugin statusPlugin;
    protected final CommandManager commandManager;

    protected CommandHandler(StatusPlugin survivalUtils, CommandManager commandManager) {
        this.statusPlugin = survivalUtils;
        this.commandManager = commandManager;
    }

    public abstract void register();
}
