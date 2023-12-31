package de.korzhorz.lobby.listeners;

import de.korzhorz.lobby.handlers.VisibilityHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class EVT_PlayerQuitEvent implements Listener {
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);

        Player player = event.getPlayer();

        VisibilityHandler.showLeavingPlayer(player);
    }
}
