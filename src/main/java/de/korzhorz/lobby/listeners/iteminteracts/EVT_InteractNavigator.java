package de.korzhorz.lobby.listeners.iteminteracts;

import de.korzhorz.lobby.configs.ConfigFiles;
import de.korzhorz.lobby.enums.LobbyItem;
import de.korzhorz.lobby.handlers.SoundHandler;
import de.korzhorz.lobby.util.LobbyWarp;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EVT_InteractNavigator implements Listener {
    @EventHandler
    public void onItemInteract(PlayerInteractEvent event) {
        ItemStack item = event.getItem();
        if(item == null || !(item.hasItemMeta())) {
            return;
        }

        ItemMeta itemMeta = item.getItemMeta();

        if(itemMeta == null || !(itemMeta.getDisplayName().equals(LobbyItem.NAVIGATOR.getDisplayName()))) {
            return;
        }

        Player player = event.getPlayer();

        Inventory inventory = Bukkit.getServer().createInventory(null, 9*5, LobbyItem.NAVIGATOR.getDisplayName());

        List<String> warps;
        if(ConfigFiles.locations.contains("warps")) {
            warps = ConfigFiles.locations.getStringList("warps");
        } else {
            warps = new ArrayList<>();
        }
        warps.stream()
                .map(warp -> new LobbyWarp().fromString(warp))
                .forEach(lobbyWarp -> {
                    ItemStack warpItem = new ItemStack(lobbyWarp.getMaterial());
                    ItemMeta warpItemMeta = warpItem.getItemMeta();
                    assert warpItemMeta != null;
                    warpItemMeta.setDisplayName(lobbyWarp.getDisplayName());
                    warpItem.setItemMeta(warpItemMeta);
                    inventory.setItem(lobbyWarp.getSlot(), warpItem);
                });

        player.openInventory(inventory);

        SoundHandler.playSound(player, "interaction");

        event.setCancelled(true);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        InventoryView inventoryView = event.getView();
        if(!(inventoryView.getTitle().equals(LobbyItem.NAVIGATOR.getDisplayName()))) {
            return;
        }

        event.setCancelled(true);

        HumanEntity humanEntity = event.getWhoClicked();
        if(!(humanEntity instanceof Player player)) {
            return;
        }

        ItemStack clickedItem = event.getCurrentItem();
        if(clickedItem == null || clickedItem.getType().isAir() || !(clickedItem.hasItemMeta())) {
            return;
        }

        ItemMeta clickedItemMeta = clickedItem.getItemMeta();
        if(clickedItemMeta == null) {
            return;
        }

        List<String> warps;
        if(ConfigFiles.locations.contains("warps")) {
            warps = ConfigFiles.locations.getStringList("warps");
        } else {
            warps = new ArrayList<>();
        }
        Optional<LobbyWarp> lobbyWarpOptional = warps.stream()
                .map(warp -> new LobbyWarp().fromString(warp))
                .filter(lobbyWarp -> lobbyWarp.getDisplayName().equals(clickedItemMeta.getDisplayName()))
                .findFirst();

        if(lobbyWarpOptional.isEmpty()) {
            return;
        }

        LobbyWarp lobbyWarp = lobbyWarpOptional.get();
        player.teleport(lobbyWarp.getLocation());

        SoundHandler.playSound(player, "teleport");
    }
}
