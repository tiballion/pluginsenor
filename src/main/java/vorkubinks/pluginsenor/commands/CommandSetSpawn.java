package vorkubinks.pluginsenor.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import vorkubinks.pluginsenor.Pluginsenor;

public class CommandSetSpawn implements CommandExecutor {
    private final Pluginsenor pluginsenor;

    public CommandSetSpawn(Pluginsenor pluginsenor) {
        this.pluginsenor = pluginsenor;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length == 0) {
                Location location = player.getLocation();
                pluginsenor.getConfig().set("spawn", location);
                pluginsenor.saveConfig();
                player.sendMessage("Spawn location has been set");
            } else if(args.length == 3) {
                //Location location =
            } else {
                player.sendMessage("This command expects either 0 or 3 parameters (x, y, z)");
            }
        }
        return true;
    }
}
