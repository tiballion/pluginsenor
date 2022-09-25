package vorkubinks.pluginsenor.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import vorkubinks.pluginsenor.Pluginsenor;
import vorkubinks.pluginsenor.config.Config;

public class CommandReloadConfig implements CommandExecutor {
    private final Pluginsenor pluginsenor;
    private final Config config;

    public CommandReloadConfig(Pluginsenor pluginsenor, Config config) {
        this.pluginsenor = pluginsenor;
        this.config = config;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player player) {
            config.reloadConfig(pluginsenor);
            player.sendMessage("Plugino has been successfully reloaded.");
        }
        return true;
    }
}
