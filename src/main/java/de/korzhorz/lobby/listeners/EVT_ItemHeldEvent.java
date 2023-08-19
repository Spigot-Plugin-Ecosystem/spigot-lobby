package de.korzhorz.lobby.listeners;

import de.korzhorz.lobby.handlers.SoundHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

public class EVT_ItemHeldEvent implements Listener {
    @EventHandler
    public void onItemHeld(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();

        SoundHandler.playSound(player, "change-slot");
    }
}
