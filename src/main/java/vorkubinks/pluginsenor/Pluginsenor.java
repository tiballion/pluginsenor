package vorkubinks.pluginsenor;

import org.bukkit.plugin.java.JavaPlugin;
import vorkubinks.pluginsenor.commands.CommandSetSpawn;
import vorkubinks.pluginsenor.commands.CommandSpawn;
import vorkubinks.pluginsenor.listeners.onPlayerMoveListener;

public final class Pluginsenor extends JavaPlugin {

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        this.getCommand("spawn").setExecutor(new CommandSpawn(this));
        this.getCommand("setspawn").setExecutor(new CommandSetSpawn(this));
        new onPlayerMoveListener(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
