package de.korzhorz.lobby.util;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class InventoryData {
    private final Player player;
    private final ItemStack[] inventory;
    private final ItemStack[] armor;
    private final GameMode gameMode;
    
    public InventoryData(Player player) {
        this.player = player;
        this.inventory = player.getInventory().getContents();
        this.armor = player.getInventory().getArmorContents();
        this.gameMode = player.getGameMode();
    }
    
    public void restore() {
        this.player.getInventory().setContents(this.inventory);
        this.player.getInventory().setArmorContents(this.armor);
        this.player.setGameMode(this.gameMode);
    }
}
