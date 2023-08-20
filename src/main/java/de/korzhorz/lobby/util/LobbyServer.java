package de.korzhorz.lobby.util;

import de.korzhorz.lobby.configs.ConfigFiles;
import de.korzhorz.lobby.handlers.ItemHandler;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

public class LobbyServer {
    private String name;
    private String displayName;
    private boolean premium;
    private boolean active;
    private int slot;

    public LobbyServer() {
        this.name = "";
        this.displayName = "";
        this.premium = false;
        this.active = false;
        this.slot = 0;
    }

    public LobbyServer(String name, String displayName, boolean premium, boolean active, int slot) {
        this.name = name;
        this.displayName = displayName;
        this.premium = premium;
        this.active = active;
        this.slot = slot;
    }

    public String getName() {
        return this.name;
    }

    public String getDisplayName() {
        return ColorTranslator.translate(this.displayName);
    }

    public boolean getPremium() {
        return this.premium;
    }

    public boolean getActive() {
        return this.active;
    }

    public int getSlot() {
        return this.slot;
    }

    public ItemStack getSwitchItemStack() {
        String material = this.getPremium() ? ConfigFiles.items.getString("lobby-switch.premium.material") : ConfigFiles.items.getString("lobby-switch.default.material");
        ItemStack itemStack = ItemHandler.getItem(material, this.getDisplayName(), 1);

        if(this.getActive()) {
            GlowEnchantment glow = new GlowEnchantment(new NamespacedKey(Bukkit.getPluginManager().getPlugin("Lobby"), "glow"));
            itemStack.addUnsafeEnchantment(glow, 1);
        }

        return itemStack;
    }
}
