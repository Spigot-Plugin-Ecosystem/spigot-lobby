package de.korzhorz.lobby;

import de.korzhorz.lobby.commands.CMD_DelWarp;
import de.korzhorz.lobby.commands.CMD_SetLobby;
import de.korzhorz.lobby.commands.CMD_SetWarp;
import de.korzhorz.lobby.configs.ConfigFiles;
import de.korzhorz.lobby.configs.Messages;
import de.korzhorz.lobby.handlers.VisibilityHandler;
import de.korzhorz.lobby.listeners.*;
import de.korzhorz.lobby.listeners.iteminteracts.EVT_InteractLobbySwitch;
import de.korzhorz.lobby.listeners.iteminteracts.EVT_InteractNavigator;
import de.korzhorz.lobby.listeners.iteminteracts.EVT_InteractToggleVisibility;
import de.korzhorz.lobby.util.ColorTranslator;
import de.korzhorz.lobby.util.GitHubUpdater;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

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

        // Set the ingame time to the real time
        if(ConfigFiles.config.getBoolean("realtime")) {
            Date currentDate = new Date();
            int hour = Integer.parseInt(new SimpleDateFormat("HH").format(currentDate));
            hour = ((hour - 6) % 24) * 1000;
            int minute = Integer.parseInt(new SimpleDateFormat("mm").format(currentDate));
            minute = (int) ((minute / 60f) * 1000);
            int time = hour + minute;

            if(!(ConfigFiles.locations.contains("spawn.world"))) {
                return;
            }

            World world = Bukkit.getWorld(ConfigFiles.locations.getString("spawn.world"));

            if(world == null) {
                return;
            }

            world.setTime(time);
            world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);

            Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
                @Override
                public void run() {
                    if(ConfigFiles.locations.contains("spawn.world")) {
                        World world = Bukkit.getWorld(ConfigFiles.locations.getString("spawn.world"));

                        if(world == null) {
                            return;
                        }

                        long time = world.getTime() + 1;
                        world.setTime(time);
                    }
                }
            }, 0, 72);
        }
    }

    @Override
    public void onDisable() {
        VisibilityHandler.showAll();
    }
    
    public void loadCommands() {
        Objects.requireNonNull(this.getCommand("setlobby")).setExecutor(new CMD_SetLobby());
        Objects.requireNonNull(this.getCommand("setwarp")).setExecutor(new CMD_SetWarp());
        Objects.requireNonNull(this.getCommand("delwarp")).setExecutor(new CMD_DelWarp());
    }
    
    public void loadEvents() {
        Bukkit.getPluginManager().registerEvents(new EVT_PlayerChatEvent(), this);
        Bukkit.getPluginManager().registerEvents(new EVT_PlayerJoinEvent(), this);
        Bukkit.getPluginManager().registerEvents(new EVT_PlayerQuitEvent(), this);
        Bukkit.getPluginManager().registerEvents(new EVT_EntityDamageEvent(), this);
        Bukkit.getPluginManager().registerEvents(new EVT_FoodLevelChangeEvent(), this);
        Bukkit.getPluginManager().registerEvents(new EVT_DeathEvent(), this);
        Bukkit.getPluginManager().registerEvents(new EVT_DropItemEvent(), this);
        Bukkit.getPluginManager().registerEvents(new EVT_DropItemEvent(), this);
        Bukkit.getPluginManager().registerEvents(new EVT_ItemHeldEvent(), this);

        Bukkit.getPluginManager().registerEvents(new EVT_InteractNavigator(), this);
        Bukkit.getPluginManager().registerEvents(new EVT_InteractLobbySwitch(), this);
        Bukkit.getPluginManager().registerEvents(new EVT_InteractToggleVisibility(), this);
    }
}
