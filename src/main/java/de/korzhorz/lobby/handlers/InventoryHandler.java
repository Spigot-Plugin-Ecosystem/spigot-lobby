package de.korzhorz.lobby.handlers;

import de.korzhorz.lobby.configs.ConfigFile;
import de.korzhorz.lobby.configs.ConfigFiles;
import de.korzhorz.lobby.util.ColorTranslator;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

public class InventoryHandler {
    public static void clearInventory(Player player) {
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        player.setExp(0);
        player.setLevel(0);
    }

    public static void setLobbyInventory(Player player) {
        InventoryHandler.clearInventory(player);

        ConfigFile items = ConfigFiles.items;

        String navigatorMaterial = items.getString("default-inventory.navigator.material");
        String lobbySwitchMaterial = items.getString("default-inventory.lobby-switch.material");
        String playersVisibleMaterial = items.getString("default-inventory.toggle-visibility.visible.material");
        String playersInvisibleMaterial = items.getString("default-inventory.toggle-visibility.invisible.material");

        String navigatorName = ColorTranslator.translate(items.getString("default-inventory.navigator.name"));
        String lobbySwitchName = ColorTranslator.translate(items.getString("default-inventory.lobby-switch.name"));
        String playersVisibleName = ColorTranslator.translate(items.getString("default-inventory.toggle-visibility.visible.name"));
        String playersInvisibleName = ColorTranslator.translate(items.getString("default-inventory.toggle-visibility.invisible.name"));

        ItemStack navigator = new ItemStack(Objects.requireNonNull(Material.matchMaterial(navigatorMaterial)));
        ItemMeta navigatorMeta = navigator.getItemMeta();
        assert navigatorMeta != null;
        navigatorMeta.setDisplayName(navigatorName);
        navigator.setItemMeta(navigatorMeta);

        ItemStack lobbySwitch = new ItemStack(Objects.requireNonNull(Material.matchMaterial(lobbySwitchMaterial)));
        ItemMeta lobbySwitchMeta = lobbySwitch.getItemMeta();
        assert lobbySwitchMeta != null;
        lobbySwitchMeta.setDisplayName(lobbySwitchName);
        lobbySwitch.setItemMeta(lobbySwitchMeta);

        ItemStack playersVisible = new ItemStack(Objects.requireNonNull(Material.matchMaterial(playersVisibleMaterial)));
        ItemMeta playersVisibleMeta = playersVisible.getItemMeta();
        assert playersVisibleMeta != null;
        playersVisibleMeta.setDisplayName(playersVisibleName);
        playersVisible.setItemMeta(playersVisibleMeta);

        ItemStack playersInvisible = new ItemStack(Objects.requireNonNull(Material.matchMaterial(playersInvisibleMaterial)));
        ItemMeta playersInvisibleMeta = playersInvisible.getItemMeta();
        assert playersInvisibleMeta != null;
        playersInvisibleMeta.setDisplayName(playersInvisibleName);
        playersInvisible.setItemMeta(playersInvisibleMeta);

        player.getInventory().setItem(items.getInt("default-inventory.navigator.slot"), navigator);
        player.getInventory().setItem(items.getInt("default-inventory.lobby-switch.slot"), lobbySwitch);

        if(VisibilityHandler.getVisibilityOption(player)) {
            player.getInventory().setItem(items.getInt("default-inventory.toggle-visibility.slot"), playersVisible);
        } else {
            player.getInventory().setItem(items.getInt("default-inventory.toggle-visibility.slot"), playersInvisible);
        }
    }
}
