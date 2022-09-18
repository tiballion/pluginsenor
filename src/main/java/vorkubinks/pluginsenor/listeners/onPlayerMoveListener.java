package vorkubinks.pluginsenor.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import vorkubinks.pluginsenor.Pluginsenor;
import vorkubinks.pluginsenor.commands.CommandSpawn;

import java.util.HashMap;

public class onPlayerMoveListener implements Listener {

    private final Pluginsenor pluginsenor;

    public onPlayerMoveListener(Pluginsenor pluginsenor) {
        this.pluginsenor = pluginsenor;
        this.pluginsenor.getServer().getPluginManager().registerEvents(this, pluginsenor);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        // if the player is in the teleporting player hashmap, the function remove him and cancels the teleport
        HashMap<Player, Integer> teleportingPlayers = CommandSpawn.getTeleportingPlayers();
        Player player = event.getPlayer();
        if(teleportingPlayers.containsKey(player)) {
            int taskId = teleportingPlayers.get(player);
            Bukkit.getServer().getScheduler().cancelTask(taskId);
            player.sendMessage("Your teleportation has been cancelled");
            teleportingPlayers.remove(player);
            CommandSpawn.setTeleportingPlayers(teleportingPlayers);
        }
    }
}
