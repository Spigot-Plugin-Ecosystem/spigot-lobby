package de.korzhorz.lobby.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

public class EVT_PickupItemEvent implements Listener {
    @EventHandler
    public void onPickupItem(EntityPickupItemEvent event) {
        event.setCancelled(true);
    }
}
