package com.radarx;

import org.bukkit.plugin.java.JavaPlugin;

public class RadarXPlugin extends JavaPlugin {

    private ConfigManager configManager;
    private DistanceCache distanceCache;

    @Override
    public void onEnable() {
        configManager = new ConfigManager(this);
        distanceCache = new DistanceCache(this, configManager);
        
        if (getCommand("radarx") != null) {
            getCommand("radarx").setExecutor(new RadarXCommand(configManager));
        }
        
        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new TrackerExpansion(this, configManager, distanceCache).register();
        }
        
        getLogger().info("RadarX has been enabled!");
    }

    @Override
    public void onDisable() {
        if (distanceCache != null) {
            distanceCache.stopCleanupTask();
        }
        getLogger().info("RadarX has been disabled!");
    }
}
