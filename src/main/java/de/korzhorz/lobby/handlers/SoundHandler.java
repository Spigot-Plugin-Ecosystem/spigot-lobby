package de.korzhorz.lobby.handlers;

import de.korzhorz.lobby.configs.ConfigFiles;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SoundHandler {
    public static void playSound(Player player, String sound) {
        if(ConfigFiles.config.getBoolean("sounds." + sound + ".enabled")) {
            player.playSound(player, Sound.valueOf(ConfigFiles.config.getString("sounds." + sound + ".sound")), 1, 1);
        }
    }
}
