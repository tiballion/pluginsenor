package vorkubinks.pluginsenor.tasks;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import vorkubinks.pluginsenor.Pluginsenor;
import vorkubinks.pluginsenor.commands.CommandSpawn;

import java.util.HashMap;

public class teleportTask extends BukkitRunnable {

    private final Pluginsenor pluginsenor;
    private final Location location;
    private final Player player;

    public teleportTask(Pluginsenor pluginsenor, Location location, Player player) {
        this.pluginsenor = pluginsenor;
        this.location = location;
        this.player = player;
    }

    @Override
    public void run() {
        player.teleport(location);
        player.sendMessage("You have been teleported to the server spawn.");
        HashMap<Player, Integer> teleportingPlayers = CommandSpawn.getTeleportingPlayers();
        teleportingPlayers.remove(player);
        CommandSpawn.setTeleportingPlayers(teleportingPlayers);
    }
}
