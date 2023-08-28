package de.korzhorz.lobby.listeners;

import de.korzhorz.lobby.Main;
import de.korzhorz.lobby.configs.ConfigFiles;
import de.korzhorz.lobby.handlers.InventoryHandler;
import de.korzhorz.lobby.handlers.VisibilityHandler;
import de.korzhorz.lobby.util.ColorTranslator;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EVT_PlayerJoinEvent implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.setJoinMessage(null);

        Player player = event.getPlayer();

        if(ConfigFiles.locations.contains("spawn.world")) {
            Location spawn = ConfigFiles.locations.getLocation("spawn");
            player.teleport(spawn);

            player.setHealth(20);
            player.setFoodLevel(20);

            player.setGameMode(GameMode.SURVIVAL);

            InventoryHandler.clearInventory(player);
            InventoryHandler.setLobbyInventory(player);

            VisibilityHandler.hideJoiningPlayer(player);
            VisibilityHandler.updateVisibility(player);

            Bukkit.getScheduler().scheduleSyncDelayedTask(JavaPlugin.getPlugin(Main.class), new Runnable() {
                @Override
                public void run() {
                    int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(new Date()));
                    player.setLevel(year);
                    player.setExp(.999999f);
                }
            }, 1);

            return;
        } else if(!(player.hasPermission("lobby.setup"))) {
            return;
        }

        player.sendMessage(ColorTranslator.translate(ConfigFiles.messages.getString("prefix") + " " + ConfigFiles.messages.getString("events.setup-incomplete")));
    }
}
