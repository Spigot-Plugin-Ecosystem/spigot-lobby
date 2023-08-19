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
    public static ConfigFile items;
    
    public static void loadFiles() {
        config = new ConfigFile("config.yml");
        messages = new ConfigFile("messages.yml");
        updater = new ConfigFile("updater.yml");
        locations = new ConfigFile("locations.yml");
        items = new ConfigFile("items.yml");
    }
    
    public static void initFileContents() {
        // Config
        config.setDefault("chat.permission-required", true);
        config.setDefault("chat.force-disabled", false);

        config.setDefault("realtime", true);

        config.setDefault("sounds.change-slot.enabled", true);
        config.setDefault("sounds.change-slot.sound", "BLOCK_NOTE_BLOCK_IRON_XYLOPHONE");

        config.save();

        // Messages
        messages.setDefault("prefix", "&6&lLobby &8»");

        messages.setDefault("events.setup-incomplete", "&cDas Setup der Lobby ist noch nicht abgeschlossen.");

        messages.setDefault("commands.errors.no-player", "&cDu musst ein Spieler sein um diesen Befehl auszuführen.");
        messages.setDefault("commands.errors.no-permission", "&cDu hast keine Rechte um diesen Befehl auszuführen.");
        messages.setDefault("commands.errors.bad-usage", "&cBenutze: &7%usage%");
        messages.setDefault("commands.errors.save-failed", "&cDie Änderungen konnten nicht gespeichert werden.");

        messages.setDefault("commands.set-lobby.success", "&aDer Spawn wurde erfolgreich gesetzt.");

        messages.save();

        // Updater
        updater.setDefault("latest", JavaPlugin.getPlugin(Main.class).getDescription().getVersion());
        updater.setDefault("last-checked", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        updater.save();

        // Locations
        locations.save();

        // Items
        items.setDefault("default-inventory.navigator.name", "&6&lNavigator");
        items.setDefault("default-inventory.navigator.material", "COMPASS");
        items.setDefault("default-inventory.navigator.slot", 0);
        items.setDefault("default-inventory.lobby-switch.name", "&b&lLobby wechseln");
        items.setDefault("default-inventory.lobby-switch.material", "NETHER_STAR");
        items.setDefault("default-inventory.lobby-switch.slot", 4);
        items.setDefault("default-inventory.toggle-visibility.slot", 8);
        items.setDefault("default-inventory.toggle-visibility.visible.name", "&a&lAlle Spieler sichtbar");
        items.setDefault("default-inventory.toggle-visibility.visible.material", "LIME_DYE");
        items.setDefault("default-inventory.toggle-visibility.invisible.name", "&8&lAlle Spieler unsichtbar");
        items.setDefault("default-inventory.toggle-visibility.invisible.material", "GRAY_DYE");

        items.save();
    }
}
