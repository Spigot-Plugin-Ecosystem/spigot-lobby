package de.korzhorz.lobby.configs;

import de.korzhorz.lobby.Main;
import de.korzhorz.lobby.util.ColorTranslator;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class ConfigFiles {
    public static ConfigFile config;
    public static ConfigFile messages;
    public static ConfigFile updater;
    public static ConfigFile locations;
    public static ConfigFile lobbies;
    public static ConfigFile items;
    
    public static void loadFiles() {
        config = new ConfigFile("config.yml");
        messages = new ConfigFile("messages.yml");
        updater = new ConfigFile("updater.yml");
        locations = new ConfigFile("locations.yml");
        lobbies = new ConfigFile("lobbies.yml");
        items = new ConfigFile("items.yml");
    }
    
    public static void initFileContents() {
        // Config
        config.setDefault("premium", false);

        config.setDefault("chat.permission-required", true);
        config.setDefault("chat.force-disabled", false);

        config.setDefault("realtime", true);

        config.setDefault("sounds.change-slot.enabled", true);
        config.setDefault("sounds.change-slot.sound", "BLOCK_NOTE_BLOCK_IRON_XYLOPHONE");
        config.setDefault("sounds.interaction.enabled", true);
        config.setDefault("sounds.interaction.sound", "BLOCK_NOTE_BLOCK_XYLOPHONE");
        config.setDefault("sounds.teleport.enabled", true);
        config.setDefault("sounds.teleport.sound", "ENTITY_ENDERMAN_TELEPORT");

        config.save();

        // Messages
        messages.setDefault("prefix", "&6&lLobby &8»");

        messages.setDefault("events.setup-incomplete", "&cDas Setup der Lobby ist noch nicht abgeschlossen.");
        messages.setDefault("events.switch-lobby", "&7Verbinde...");
        messages.setDefault("events.premium-lobby", "&cDu hast keine Berechtigung, dieser &e&lPremium-Lobby &r&cbeizutreten.");

        messages.setDefault("commands.errors.no-player", "&cDu musst ein Spieler sein um diesen Befehl auszuführen.");
        messages.setDefault("commands.errors.no-permission", "&cDu hast keine Rechte um diesen Befehl auszuführen.");
        messages.setDefault("commands.errors.bad-usage", "&cBenutze: &7%usage%");
        messages.setDefault("commands.errors.save-failed", "&cDie Änderungen konnten nicht gespeichert werden.");
        messages.setDefault("commands.errors.invalid-warp-name", "&cUngültiger Warp-Name.");
        messages.setDefault("commands.errors.no-item-in-hand", "&cDu musst ein Item in der Hand halten, wenn du diesen Befehl ausführst.");

        messages.setDefault("commands.set-lobby.success", "&aDer Spawn wurde erfolgreich gesetzt.");
        messages.setDefault("commands.set-warp.success", "&aDer Warp wurde erfolgreich gesetzt.");
        messages.setDefault("commands.del-warp.success", "&aAlle Warps mit dem Namen &7%warp% &awurden gelöscht.");

        messages.save();

        // Updater
        updater.setDefault("latest", JavaPlugin.getPlugin(Main.class).getDescription().getVersion());
        updater.setDefault("last-checked", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

        updater.save();

        // Locations
        locations.save();

        // Lobbies
        lobbies.save();

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
        items.setDefault("default-inventory.toggle-visibility.invisible.name", "&7&lAlle Spieler unsichtbar");
        items.setDefault("default-inventory.toggle-visibility.invisible.material", "GRAY_DYE");

        items.setDefault("lobby-switch.default.material", "GUNPOWDER");
        items.setDefault("lobby-switch.premium.material", "GLOWSTONE_DUST");

        items.save();
    }
}
