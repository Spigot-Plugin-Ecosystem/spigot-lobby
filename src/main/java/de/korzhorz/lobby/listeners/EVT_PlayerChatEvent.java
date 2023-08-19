package de.korzhorz.lobby.listeners;

import de.korzhorz.lobby.configs.ConfigFiles;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class EVT_PlayerChatEvent implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        boolean chatPermissionRequired = ConfigFiles.config.getBoolean("chat.permission-required");
        boolean chatForceDisabled = ConfigFiles.config.getBoolean("chat.force-disabled");

        event.setCancelled(chatForceDisabled || (chatPermissionRequired && !(player.hasPermission("lobby.chat"))));
    }
}
