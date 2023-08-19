package de.korzhorz.lobby.handlers;

import de.korzhorz.lobby.enums.LobbyItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

public class ItemHandler {
    public static ItemStack getLobbyItem(LobbyItem lobbyItem) {
        return ItemHandler.getItem(lobbyItem.getMaterial(), lobbyItem.getDisplayName(), 1);
    }

    public static ItemStack getItem(String material, String displayName, int amount) {
        ItemStack itemStack = new ItemStack(Objects.requireNonNull(Material.matchMaterial(material)), amount);
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName(displayName);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
