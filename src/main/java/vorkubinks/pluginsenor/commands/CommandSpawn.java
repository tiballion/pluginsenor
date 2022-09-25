package vorkubinks.pluginsenor.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import vorkubinks.pluginsenor.Pluginsenor;
import vorkubinks.pluginsenor.config.Config;
import vorkubinks.pluginsenor.tasks.teleportTask;

import java.util.HashMap;

public class CommandSpawn implements CommandExecutor {
    private final Pluginsenor pluginsenor;
    private Config config;
    private static HashMap<Player, Integer> teleportingPlayers = new HashMap<>();

    public CommandSpawn(Pluginsenor pluginsenor, Config config) {
        this.pluginsenor = pluginsenor;
        this.config = config;
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

            Location location = config.getSpawnLocation();

            if(location == null) {
                // there is no location object in the config.yml
                player.sendMessage("The spawn location hasn't been set on this server yet.");
            } else {
                if(teleportingPlayers.containsKey(player)) {
                    // the player is already in the teleportingPlayers hashmap
                    player.sendMessage("You already have a teleportation pending.");
                } else {
                    // creating a task that is executed after the teleport_timeout set by the admin
                    // adds the player to the teleporting players hashmap
                    int teleportTimeout = (int) config.getTeleportTimeout();
                    BukkitTask task = new teleportTask(this.pluginsenor, location, player)
                            .runTaskLater(this.pluginsenor, (int) teleportTimeout*20);
                    teleportingPlayers.put(player, task.getTaskId());
                    player.sendMessage("Teleporting you to spawn, please wait " + teleportTimeout + " seconds.");
                }
            }
        }
        return true;
    }
}
