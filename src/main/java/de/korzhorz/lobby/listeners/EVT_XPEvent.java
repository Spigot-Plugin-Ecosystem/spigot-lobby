package de.korzhorz.lobby.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;

public class EVT_XPEvent implements Listener {
    @EventHandler
    public void onXPChange(PlayerExpChangeEvent event) {
        event.setAmount(0);
    }
}
