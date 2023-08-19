package de.korzhorz.lobby.listeners.iteminteracts;

import de.korzhorz.lobby.configs.ConfigFiles;
import de.korzhorz.lobby.handlers.SoundHandler;
import de.korzhorz.lobby.handlers.VisibilityHandler;
import de.korzhorz.lobby.util.ColorTranslator;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EVT_InteractToggleVisibility implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        ItemStack item = event.getItem();
        if(item == null || !(item.hasItemMeta())) {
            return;
        }

        ItemMeta itemMeta = item.getItemMeta();

        if(itemMeta == null) {
            return;
        }

        Player player = event.getPlayer();

        if(itemMeta.getDisplayName().equals(ColorTranslator.translate(ConfigFiles.items.getString("default-inventory.toggle-visibility.visible.name")))) {
            VisibilityHandler.setVisibilityOption(player, false);
        } else if(itemMeta.getDisplayName().equals(ColorTranslator.translate(ConfigFiles.items.getString("default-inventory.toggle-visibility.invisible.name")))) {
            VisibilityHandler.setVisibilityOption(player, true);
        } else {
            return;
        }

        SoundHandler.playSound(player, "interaction");

        event.setCancelled(true);
    }
}
