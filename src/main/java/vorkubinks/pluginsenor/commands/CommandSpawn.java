package vorkubinks.pluginsenor.commands;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import vorkubinks.pluginsenor.Pluginsenor;

public class CommandSpawn implements CommandExecutor {
    private final Pluginsenor pluginsenor;

    public CommandSpawn(Pluginsenor pluginsenor) {
        this.pluginsenor = pluginsenor;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;

            Location location = pluginsenor.getConfig().getLocation("spawn");

            if(location == null) {
                player.sendMessage("The spawn location hasn't been set on this server yet.");
            } else {
                player.teleport(location);
                player.sendMessage("You have been teleported to the server spawn.");
            }
        }
        return true;
    }
}
