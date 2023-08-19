package de.korzhorz.lobby.listeners;

import de.korzhorz.lobby.configs.ConfigFiles;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

public class EVT_ItemHeldEvent implements Listener {
    @EventHandler
    public void onItemHeld(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();

        if(ConfigFiles.config.getBoolean("sounds.change-slot.enabled")) {
            player.playSound(player, Sound.valueOf(ConfigFiles.config.getString("sounds.change-slot.sound")), 1, 1);
        }
    }
}
