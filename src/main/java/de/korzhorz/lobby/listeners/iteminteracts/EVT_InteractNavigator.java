package de.korzhorz.lobby.listeners.iteminteracts;

import de.korzhorz.lobby.configs.ConfigFiles;
import de.korzhorz.lobby.handlers.SoundHandler;
import de.korzhorz.lobby.util.ColorTranslator;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EVT_InteractNavigator implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        ItemStack item = event.getItem();
        if(item == null || !(item.hasItemMeta())) {
            return;
        }

        ItemMeta itemMeta = item.getItemMeta();

        if(itemMeta == null || !(itemMeta.getDisplayName().equals(ColorTranslator.translate(ConfigFiles.items.getString("default-inventory.navigator.name"))))) {
            return;
        }

        Player player = event.getPlayer();

        Inventory inventory = Bukkit.getServer().createInventory(null, 9*6, ColorTranslator.translate(ConfigFiles.items.getString("default-inventory.navigator.name")));
        player.openInventory(inventory);

        SoundHandler.playSound(player, "interaction");

        event.setCancelled(true);
    }

    // TODO: Interact with navigator inventory
}
