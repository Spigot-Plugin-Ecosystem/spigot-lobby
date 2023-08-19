package de.korzhorz.lobby.handlers;

import de.korzhorz.lobby.Main;
import de.korzhorz.lobby.enums.LobbyItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class VisibilityHandler {
    private static final ArrayList<Player> hidingOtherPlayers = new ArrayList<>();

    public static boolean getVisibilityOption(Player player) {
        return !(VisibilityHandler.hidingOtherPlayers.contains(player));
    }

    public static void setVisibilityOption(Player player, boolean visible) {
        if(visible) {
            VisibilityHandler.hidingOtherPlayers.remove(player);

            player.getInventory().setItem(LobbyItem.PLAYERS_VISIBLE.getSlot(), ItemHandler.getLobbyItem(LobbyItem.PLAYERS_VISIBLE));
        } else {
            VisibilityHandler.hidingOtherPlayers.add(player);

            player.getInventory().setItem(LobbyItem.PLAYERS_INVISIBLE.getSlot(), ItemHandler.getLobbyItem(LobbyItem.PLAYERS_INVISIBLE));
        }

        VisibilityHandler.updateVisibility(player);
    }

    public static void updateVisibility(Player player) {
        for(Player otherPlayer : player.getServer().getOnlinePlayers()) {
            if(otherPlayer == player) {
                continue;
            }

            if(VisibilityHandler.getVisibilityOption(player)) {
                player.showPlayer(JavaPlugin.getPlugin(Main.class), otherPlayer);
            } else {
                player.hidePlayer(JavaPlugin.getPlugin(Main.class), otherPlayer);
            }
        }
    }

    public static void hideJoiningPlayer(Player joiningPlayer) {
        for(Player player : VisibilityHandler.hidingOtherPlayers) {
            if(player == joiningPlayer) {
                continue;
            }

            player.hidePlayer(JavaPlugin.getPlugin(Main.class), joiningPlayer);
        }
    }

    public static void showLeavingPlayer(Player leavingPlayer) {
        for(Player player : VisibilityHandler.hidingOtherPlayers) {
            if(player == leavingPlayer) {
                continue;
            }

            player.showPlayer(JavaPlugin.getPlugin(Main.class), leavingPlayer);
        }
    }

    public static void showAll() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            VisibilityHandler.setVisibilityOption(player, true);
        }
    }
}
