package de.korzhorz.lobby.listeners.iteminteracts;

import de.korzhorz.lobby.Main;
import de.korzhorz.lobby.configs.ConfigFiles;
import de.korzhorz.lobby.configs.Messages;
import de.korzhorz.lobby.enums.LobbyItem;
import de.korzhorz.lobby.handlers.BungeeCordHandler;
import de.korzhorz.lobby.handlers.SoundHandler;
import de.korzhorz.lobby.util.ColorTranslator;
import de.korzhorz.lobby.util.LobbyServer;
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

public class EVT_InteractLobbySwitch implements Listener {
    Messages messages = new Messages();

    @EventHandler
    public void onItemInteract(PlayerInteractEvent event) {
        ItemStack item = event.getItem();
        if(item == null || !(item.hasItemMeta())) {
            return;
        }

        ItemMeta itemMeta = item.getItemMeta();

        if(itemMeta == null || !(itemMeta.getDisplayName().equals(ColorTranslator.translate(ConfigFiles.items.getString("default-inventory.lobby-switch.name"))))) {
            return;
        }

        Player player = event.getPlayer();

        ConfigFiles.lobbies.setDefault("lobbies-list", new ArrayList<>());
        ConfigFiles.lobbies.save();

        int inventorySize = 8;
        List<String> servers = ConfigFiles.lobbies.getStringList("lobbies-list");
        List<LobbyServer> lobbyServers = new ArrayList<>();
        for(String server : servers) {
            if(ConfigFiles.lobbies.getInt("lobbies." + server + ".lobby-switch-slot") >= 53) {
                continue;
            }

            LobbyServer lobbyServer = new LobbyServer(
                    server,
                    ConfigFiles.lobbies.getString("lobbies." + server + ".displayname"),
                    ConfigFiles.lobbies.getBoolean("lobbies." + server + ".premium"),
                    ConfigFiles.lobbies.getString("active").equals(server),
                    ConfigFiles.lobbies.getInt("lobbies." + server + ".lobby-switch-slot")
            );
            lobbyServers.add(lobbyServer);

            if(lobbyServer.getSlot() > inventorySize) {
                inventorySize = lobbyServer.getSlot();
            }
        }
        inventorySize = (int) Math.ceil(inventorySize / 9.0) * 9;

        Inventory inventory = Bukkit.getServer().createInventory(null, inventorySize, ColorTranslator.translate(ConfigFiles.items.getString("default-inventory.lobby-switch.name")));

        for(LobbyServer lobbyServer : lobbyServers) {
            inventory.setItem(lobbyServer.getSlot(), lobbyServer.getSwitchItemStack());
        }

        player.openInventory(inventory);

        SoundHandler.playSound(player, "interaction");

        event.setCancelled(true);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        InventoryView inventoryView = event.getView();
        if(!(inventoryView.getTitle().equals(LobbyItem.LOBBY_SWITCH.getDisplayName()))) {
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

        List<String> servers = ConfigFiles.lobbies.getStringList("lobbies-list");
        List<LobbyServer> lobbyServers = new ArrayList<>();
        for(String server : servers) {
            if(ConfigFiles.lobbies.getInt("lobbies." + server + ".lobby-switch-slot") >= 53) {
                continue;
            }

            LobbyServer lobbyServer = new LobbyServer(
                    server,
                    ConfigFiles.lobbies.getString("lobbies." + server + ".displayname"),
                    ConfigFiles.lobbies.getBoolean("lobbies." + server + ".premium"),
                    ConfigFiles.lobbies.getString("active").equals(server),
                    ConfigFiles.lobbies.getInt("lobbies." + server + ".lobby-switch-slot")
            );
            lobbyServers.add(lobbyServer);
        }

        Optional<LobbyServer> lobbyServerOptional = lobbyServers.stream()
                .filter(lobbyServer -> lobbyServer.getDisplayName().equals(clickedItemMeta.getDisplayName()))
                .findFirst();

        if(lobbyServerOptional.isEmpty()) {
            return;
        }

        LobbyServer lobbyServer = lobbyServerOptional.get();

        player.sendMessage(ColorTranslator.translate(messages.get("prefix") + "&r " + messages.get("events.switch-lobby")));
        player.getOpenInventory().close();

        Main.bungeeCordHandler.connectToServer(player, lobbyServer.getName());
    }
}
