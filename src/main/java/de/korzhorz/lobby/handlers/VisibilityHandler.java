package de.korzhorz.lobby.handlers;

import de.korzhorz.lobby.Main;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class VisibilityHandler {
    private static final ArrayList<Player> hidingOtherPlayers = new ArrayList<Player>();

    public static boolean getVisibilityOption(Player player) {
        return !(VisibilityHandler.hidingOtherPlayers.contains(player));
    }

    public static void setVisibilityOption(Player player, boolean visible) {
        if(visible) {
            VisibilityHandler.hidingOtherPlayers.remove(player);
        } else {
            VisibilityHandler.hidingOtherPlayers.add(player);
        }

        VisibilityHandler.updateVisibility(player);
    }

    public static void updateVisibility(Player player) {
        for(Player otherPlayer : player.getServer().getOnlinePlayers()) {
            if(VisibilityHandler.getVisibilityOption(player)) {
                player.showPlayer(JavaPlugin.getPlugin(Main.class), otherPlayer);
            } else {
                player.hidePlayer(JavaPlugin.getPlugin(Main.class), otherPlayer);
            }
        }
    }

    public static void hideJoiningPlayer(Player joiningPlayer) {
        for(Player player : VisibilityHandler.hidingOtherPlayers) {
            player.hidePlayer(JavaPlugin.getPlugin(Main.class), joiningPlayer);
        }
    }

    public static void showLeavingPlayer(Player leavingPlayer) {
        for(Player player : VisibilityHandler.hidingOtherPlayers) {
            player.showPlayer(JavaPlugin.getPlugin(Main.class), leavingPlayer);
        }
    }
}
