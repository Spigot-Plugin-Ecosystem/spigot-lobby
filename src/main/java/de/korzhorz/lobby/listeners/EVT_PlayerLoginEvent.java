package de.korzhorz.lobby.listeners;

import de.korzhorz.lobby.configs.ConfigFiles;
import de.korzhorz.lobby.util.ColorTranslator;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class EVT_PlayerLoginEvent implements Listener {
    @EventHandler
    public void onPlayerConnect(PlayerLoginEvent event) {
        Player player = event.getPlayer();

        if(ConfigFiles.config.getBoolean("premium") && !(player.hasPermission("lobby.premium"))) {
            event.disallow(PlayerLoginEvent.Result.KICK_OTHER, ColorTranslator.translate(ConfigFiles.messages.getString("events.premium-lobby")));
        }
    }
}
