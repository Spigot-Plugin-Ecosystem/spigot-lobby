package de.korzhorz.lobby.configs;

import de.korzhorz.lobby.Main;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ConfigFiles {
    public static ConfigFile config;
    public static ConfigFile messages;
    public static ConfigFile updater;
    
    public static void loadFiles() {
        config = new ConfigFile("config.yml");
        messages = new ConfigFile("messages.yml");
        updater = new ConfigFile("updater.yml");
    }
    
    public static void initFileContents() {
        // Config
        config.save();

        // Messages
        messages.setDefault("prefix", "&6&lLobby &8»");

        messages.save();

        // Updater
        updater.setDefault("latest", JavaPlugin.getPlugin(Main.class).getDescription().getVersion());
        updater.setDefault("last-checked", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        updater.save();
    }
}
