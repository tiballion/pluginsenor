package vorkubinks.pluginsenor.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import vorkubinks.pluginsenor.Pluginsenor;
import vorkubinks.pluginsenor.config.Config;

public class CommandSetSpawn implements CommandExecutor {
    private final Pluginsenor pluginsenor;
    private Config config;

    public CommandSetSpawn(Pluginsenor pluginsenor, Config config) {
        this.pluginsenor = pluginsenor;
        this.config = config;
    }

    /**
     * Sets the spawn of a given player to a given location
     * @param location the location
     * @param player the player
     */
    private void setSpawn(Location location, Player player) {
        config.setSpawnLocation(location);
        player.sendMessage("Spawn location has been set.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            Location location = player.getLocation();
            // if no arguments are passed the spawn is set to the player location
            // if 3 arguments are passed the spawn is set to them
            // else it sends the player an error
            if(args.length == 0) {
                setSpawn(location, player);
            } else if(args.length == 3) {
                double locX = Double.parseDouble(args[0]);
                double locY = Double.parseDouble(args[1]);
                double locZ = Double.parseDouble(args[2]);
                Location loc = new Location(location.getWorld(), locX, locY, locZ);
                setSpawn(loc, player);
            } else {
                player.sendMessage("This command expects either 0 or 3 parameters (x, y, z)");
            }
        }
        return true;
    }
}
