package de.korzhorz.lobby.handlers;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import de.korzhorz.lobby.Main;
import de.korzhorz.lobby.configs.ConfigFiles;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class BungeeCordHandler implements PluginMessageListener {
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if(!(channel.equals("BungeeCord"))) {
            return;
        }

        ByteArrayDataInput byteArrayDataInput = ByteStreams.newDataInput(message);
        String subChannel = byteArrayDataInput.readUTF();
        if(subChannel.equals("GetServer")) {
            // Set default config values for lobbies
            String serverName = byteArrayDataInput.readUTF();
            ArrayList<String> lobbiesList = new ArrayList<>();
            lobbiesList.add(serverName);
            ConfigFiles.lobbies.setDefault("lobbies-list", lobbiesList);
            ConfigFiles.lobbies.setDefault("lobbies." + serverName + ".displayname", serverName);
            ConfigFiles.lobbies.setDefault("lobbies." + serverName + ".lobby-switch-slot", 0);
            ConfigFiles.lobbies.setDefault("lobbies." + serverName + ".premium", false);
            ConfigFiles.lobbies.setDefault("active", serverName);
            ConfigFiles.lobbies.save();
        }
    }

    public void getServerName() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.writeUTF("GetServer");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bukkit.getServer().sendPluginMessage(JavaPlugin.getPlugin(Main.class), "BungeeCord", byteArrayOutputStream.toByteArray());
    }

    public void connectToServer(Player player, String server) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.writeUTF("Connect");
            dataOutputStream.writeUTF(server);
        } catch (IOException e) {
            e.printStackTrace();
        }
        player.sendPluginMessage(JavaPlugin.getPlugin(Main.class), "BungeeCord", byteArrayOutputStream.toByteArray());
    }
}
