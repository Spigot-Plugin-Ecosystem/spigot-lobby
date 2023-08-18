package de.korzhorz.lobby.configs;

import de.korzhorz.lobby.Main;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ConfigFiles {
    public static ConfigFile config;
    public static ConfigFile messages;
    public static ConfigFile updater;
    public static ConfigFile locations;
    
    public static void loadFiles() {
        config = new ConfigFile("config.yml");
        messages = new ConfigFile("messages.yml");
        updater = new ConfigFile("updater.yml");
        locations = new ConfigFile("locations.yml");
    }
    
    public static void initFileContents() {
        // Config
        config.setDefault("chat.permission-required", true);
        config.setDefault("chat.force-disabled", false);

        config.setDefault("realtime", true);

        config.save();

        // Messages
        messages.setDefault("prefix", "&6&lLobby &8»");

        messages.setDefault("events.setup-incomplete", "&cDas Setup der Lobby ist noch nicht abgeschlossen.");

        messages.save();

        // Updater
        updater.setDefault("latest", JavaPlugin.getPlugin(Main.class).getDescription().getVersion());
        updater.setDefault("last-checked", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        updater.save();

        // Locations
        locations.save();
    }
}
