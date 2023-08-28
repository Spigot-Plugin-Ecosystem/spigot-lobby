package de.korzhorz.lobby.commands;

import de.korzhorz.lobby.configs.ConfigFiles;
import de.korzhorz.lobby.configs.Messages;
import de.korzhorz.lobby.util.ColorTranslator;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_SetLobby implements CommandExecutor {
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

        if(args.length != 0) {
            String message = messages.get("commands.errors.bad-usage");
            message = message.replaceAll("%usage%", cmd.getUsage());
            player.sendMessage(ColorTranslator.translate(messages.get("prefix") + "&r " + message));
            return true;
        }

        Location spawn = player.getLocation();
        ConfigFiles.locations.set("spawn", spawn);
        ConfigFiles.locations.save();

        player.sendMessage(ColorTranslator.translate(messages.get("prefix") + "&r " + messages.get("commands.set-lobby.success")));

        // Restart the server
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "restart");

        return true;
    }
}
