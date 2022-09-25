package vorkubinks.pluginsenor.config;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import vorkubinks.pluginsenor.Pluginsenor;

import java.io.File;

public class Config {
    private final Pluginsenor pluginsenor;
    private int teleportTimeout;
    private Location spawnLocation;

    public Config(Pluginsenor pluginsenor, Location spawnLocation, int teleportTimeout) {
        this.pluginsenor = pluginsenor;
        this.teleportTimeout = teleportTimeout;
        this.spawnLocation = spawnLocation;
    }

    /**
     * Init the config object
     * @param plugin the plugin
     * @return the config
     */
    public static Config initConfig(Pluginsenor plugin) {
        plugin.getConfig().options().copyDefaults();
        plugin.saveDefaultConfig();
        FileConfiguration c = plugin.getConfig();
        Location loc = c.getLocation("spawn");
        int teleport_timeout = (int) c.get("teleport_timeout");
        return new Config(plugin, loc, teleport_timeout);
    }

    /**
     * Reloads the config file
     * @param plugin the plugin
     */
    public void reloadConfig(Pluginsenor plugin, Player player) {
        File file = new File(plugin.getDataFolder(), "/config.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        this.teleportTimeout = (int) config.get("teleport_timeout");
        this.spawnLocation = config.getLocation("spawn");
    }

    public int getTeleportTimeout() {
        return this.teleportTimeout;
    }

    /**
     * Sets the new teleport timeout in both this object and the plugin's config
     * @param newTimeout the new timeout
     */
    public void setTeleportTimeout(int newTimeout) {
        this.teleportTimeout = newTimeout;
        pluginsenor.getConfig().set("timeout_teleport", newTimeout);
        pluginsenor.saveConfig();
    }

    public Location getSpawnLocation() {
        return this.spawnLocation;
    }

    /**
     * Sets the new spawn location in both this object and the plugin's config
     * @param newLocation the new location
     */
    public void setSpawnLocation(Location newLocation) {
        this.spawnLocation = newLocation;
        pluginsenor.getConfig().set("spawn", newLocation);
        pluginsenor.saveConfig();
    }
}
