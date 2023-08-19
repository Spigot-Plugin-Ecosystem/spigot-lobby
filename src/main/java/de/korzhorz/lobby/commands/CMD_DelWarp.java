package de.korzhorz.lobby.commands;

import de.korzhorz.lobby.configs.ConfigFiles;
import de.korzhorz.lobby.configs.Messages;
import de.korzhorz.lobby.util.ColorTranslator;
import de.korzhorz.lobby.util.LobbyWarp;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class CMD_DelWarp implements CommandExecutor {
    Messages messages = new Messages();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender.hasPermission("lobby.setup"))) {
            sender.sendMessage(ColorTranslator.translate(messages.get("prefix") + "&r " + messages.get("commands.errors.no-permission")));
            return false;
        }

        if(args.length != 1) {
            String message = messages.get("commands.errors.bad-usage");
            message = message.replaceAll("%usage%", cmd.getUsage());
            sender.sendMessage(ColorTranslator.translate(messages.get("prefix") + "&r " + message));
            return false;
        }

        List<String> warps;
        if(ConfigFiles.locations.contains("warps")) {
            warps = ConfigFiles.locations.getStringList("warps");
        } else {
            warps = new ArrayList<>();
        }
        warps = warps.stream()
                .map(warp -> new LobbyWarp().fromString(warp))
                .filter(lobbyWarp -> !(lobbyWarp.getName().equals(args[0])))
                .map(LobbyWarp::toString)
                .toList();

        ConfigFiles.locations.set("warps", warps);
        ConfigFiles.locations.save();

        String message = messages.get("commands.del-warp.success");
        message = message.replaceAll("%warp%", args[0]);
        sender.sendMessage(ColorTranslator.translate(messages.get("prefix") + "&r " + message));

        return true;
    }
}
