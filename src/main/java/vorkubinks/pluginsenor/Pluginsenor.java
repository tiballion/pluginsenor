package vorkubinks.pluginsenor;

import org.bukkit.plugin.java.JavaPlugin;
import vorkubinks.pluginsenor.commands.CommandReloadConfig;
import vorkubinks.pluginsenor.commands.CommandSetSpawn;
import vorkubinks.pluginsenor.commands.CommandSpawn;
import vorkubinks.pluginsenor.config.Config;
import vorkubinks.pluginsenor.listeners.onPlayerMoveListener;

public final class Pluginsenor extends JavaPlugin {

    @Override
    public void onEnable() {
        Config c = Config.initConfig(this);
        this.getCommand("spawn").setExecutor(new CommandSpawn(this, c));
        this.getCommand("setspawn").setExecutor(new CommandSetSpawn(this, c));
        this.getCommand("plugino.reload").setExecutor(new CommandReloadConfig(this, c));
        new onPlayerMoveListener(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
