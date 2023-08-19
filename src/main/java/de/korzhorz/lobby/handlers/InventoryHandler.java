package de.korzhorz.lobby.handlers;

import de.korzhorz.lobby.enums.LobbyItem;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class InventoryHandler {
    public static void clearInventory(Player player) {
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        player.setExp(0);
        player.setLevel(0);
    }

    public static void setLobbyInventory(Player player) {
        InventoryHandler.clearInventory(player);

        for(LobbyItem lobbyItem : LobbyItem.values()) {
            if(lobbyItem == LobbyItem.PLAYERS_VISIBLE || lobbyItem == LobbyItem.PLAYERS_INVISIBLE) {
                continue;
            }

            player.getInventory().setItem(lobbyItem.getSlot(), ItemHandler.getLobbyItem(lobbyItem));
        }

        ItemStack playersVisible = ItemHandler.getLobbyItem(LobbyItem.PLAYERS_VISIBLE);
        ItemStack playersInvisible = ItemHandler.getLobbyItem(LobbyItem.PLAYERS_INVISIBLE);

        if(VisibilityHandler.getVisibilityOption(player)) {
            player.getInventory().setItem(LobbyItem.PLAYERS_VISIBLE.getSlot(), playersVisible);
        } else {
            player.getInventory().setItem(LobbyItem.PLAYERS_INVISIBLE.getSlot(), playersInvisible);
        }
    }
}
