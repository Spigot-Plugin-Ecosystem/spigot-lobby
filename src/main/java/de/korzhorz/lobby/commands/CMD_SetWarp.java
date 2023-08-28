package de.korzhorz.lobby.commands;

import de.korzhorz.lobby.configs.ConfigFiles;
import de.korzhorz.lobby.configs.Messages;
import de.korzhorz.lobby.util.ColorTranslator;
import de.korzhorz.lobby.util.LobbyWarp;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CMD_SetWarp implements CommandExecutor {
    Messages messages = new Messages();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(ColorTranslator.translate(messages.get("prefix") + "&r " + messages.get("commands.errors.no-player")));
            return true;
        }

        Player player = (Player) sender;

        if(!(player.hasPermission("lobby.setup"))) {
            player.sendMessage(ColorTranslator.translate(messages.get("prefix") + "&r " + messages.get("commands.errors.no-permission")));
            return true;
        }

        if(args.length < 3) {
            String message = messages.get("commands.errors.bad-usage");
            message = message.replaceAll("%usage%", cmd.getUsage());
            player.sendMessage(ColorTranslator.translate(messages.get("prefix") + "&r " + message));
            return true;
        }

        if(args[0].contains(":")) {
            String message = messages.get("commands.errors.invalid-warp-name");
            player.sendMessage(ColorTranslator.translate(messages.get("prefix") + "&r " + message));
            return true;
        }

        Material material = player.getInventory().getItemInMainHand().getType();

        if(material.isAir()) {
            String message = messages.get("commands.errors.no-item-in-hand");
            player.sendMessage(ColorTranslator.translate(messages.get("prefix") + "&r " + message));
            return true;
        }

        StringBuilder displayName = new StringBuilder();
        for(int i = 2; i < args.length; i++) {
            displayName.append(args[i]).append(" ");
        }
        LobbyWarp warp = new LobbyWarp(
                args[0],
                player.getLocation(),
                material,
                Integer.parseInt(args[1]),
                displayName.toString().trim()
        );

        List<String> warps;
        if(ConfigFiles.locations.contains("warps")) {
            warps = ConfigFiles.locations.getStringList("warps");
        } else {
            warps = new ArrayList<>();
        }
        warps.add(warp.toString());
        ConfigFiles.locations.set("warps", warps);
        ConfigFiles.locations.save();

        player.sendMessage(ColorTranslator.translate(messages.get("prefix") + "&r " + messages.get("commands.set-warp.success")));

        return true;
    }
}
