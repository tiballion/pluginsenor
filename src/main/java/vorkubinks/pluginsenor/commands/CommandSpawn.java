package vorkubinks.pluginsenor.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import vorkubinks.pluginsenor.Pluginsenor;
import vorkubinks.pluginsenor.tasks.teleportTask;

import java.util.HashMap;

public class CommandSpawn implements CommandExecutor {
    private final Pluginsenor pluginsenor;
    private static HashMap<Player, Integer> teleportingPlayers = new HashMap<>();

    public CommandSpawn(Pluginsenor pluginsenor) {
        this.pluginsenor = pluginsenor;
    }

    public static HashMap<Player, Integer> getTeleportingPlayers () {
        return teleportingPlayers;
    }

    public static void setTeleportingPlayers (HashMap<Player, Integer> newTeleportingPlayers) {
        teleportingPlayers = newTeleportingPlayers;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player player) {

            Location location = pluginsenor.getConfig().getLocation("spawn");

            if(location == null) {
                player.sendMessage("The spawn location hasn't been set on this server yet.");
            } else {
                if(teleportingPlayers.containsKey(player)) {
                    player.sendMessage("You already have a teleportation pending.");
                } else {
                    BukkitTask task = new teleportTask(this.pluginsenor, location, player).runTaskLater(this.pluginsenor, 60);
                    teleportingPlayers.put(player, task.getTaskId());
                    player.sendMessage("Teleporting you to spawn, please wait 3 seconds");
                }
            }
        }
        return true;
    }
}
