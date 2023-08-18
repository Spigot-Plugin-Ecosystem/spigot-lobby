package de.korzhorz.lobby;

import de.korzhorz.lobby.configs.ConfigFiles;
import de.korzhorz.lobby.configs.Messages;
import de.korzhorz.lobby.util.ColorTranslator;
import de.korzhorz.lobby.util.GitHubUpdater;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    Messages messages = new Messages();

    @Override
    public void onEnable() {
        this.getServer().getConsoleSender().sendMessage(ColorTranslator.translate("&7[&6Lobby&7] &7Enabling"));
        
        this.getDataFolder().mkdir();
        
        // Configuration Files
        this.getServer().getConsoleSender().sendMessage(ColorTranslator.translate("&7[&6Lobby&7] &7Loading files"));
        ConfigFiles.loadFiles();
        ConfigFiles.initFileContents();
        this.getServer().getConsoleSender().sendMessage(ColorTranslator.translate("&7[&6Lobby&7] &aFiles loaded"));
        
        // Commands
        this.getServer().getConsoleSender().sendMessage(ColorTranslator.translate("&7[&6Lobby&7] &7Loading commands"));
        loadCommands();
        this.getServer().getConsoleSender().sendMessage(ColorTranslator.translate("&7[&6Lobby&7] &aCommands loaded"));
        
        // Events
        this.getServer().getConsoleSender().sendMessage(ColorTranslator.translate("&7[&6Lobby&7] &7Loading events"));
        loadEvents();
        this.getServer().getConsoleSender().sendMessage(ColorTranslator.translate("&7[&6Lobby&7] &aEvents loaded"));
        
        // Update Checker
        if(GitHubUpdater.updateAvailable()) {
            this.getServer().getConsoleSender().sendMessage(ColorTranslator.translate("&7[&6Lobby&7]"));
            this.getServer().getConsoleSender().sendMessage(ColorTranslator.translate("&7[&6Lobby&7] &9A new update for this plugin is available"));
            this.getServer().getConsoleSender().sendMessage(ColorTranslator.translate("&7[&6Lobby&7]"));
        }
        
        this.getServer().getConsoleSender().sendMessage(ColorTranslator.translate("&7[&6Lobby&7] &aPlugin enabled &7- Version: &6v" + this.getDescription().getVersion()));
        this.getServer().getConsoleSender().sendMessage(ColorTranslator.translate("&7[&6Lobby&7] &aDeveloped by &6KorzHorz"));
    }

    @Override
    public void onDisable() {

    }
    
    public void loadCommands() {

    }
    
    public void loadEvents() {

    }
}
