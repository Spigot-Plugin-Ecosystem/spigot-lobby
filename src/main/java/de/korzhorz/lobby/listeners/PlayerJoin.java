package de.korzhorz.lobby.listeners;

import de.korzhorz.lobby.configs.ConfigFiles;
import de.korzhorz.lobby.util.ColorTranslator;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.setJoinMessage(null);

        Player player = event.getPlayer();

        if(ConfigFiles.locations.contains("spawn.world")) {
            Location spawn = ConfigFiles.locations.getLocation("spawn");
            player.teleport(spawn);
        } else if(!(player.hasPermission("lobby.setup"))) {
            return;
        }

        player.sendMessage(ColorTranslator.translate(ConfigFiles.messages.getString("prefix") + " " + ConfigFiles.messages.getString("events.setup-incomplete")));
    }
}
