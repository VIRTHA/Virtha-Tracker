package com.virtha.tracker;

import org.bukkit.plugin.java.JavaPlugin;

public class VirthaTrackerPlugin extends JavaPlugin {

    private ConfigManager configManager;
    private DistanceCache distanceCache;

    @Override
    public void onEnable() {
        configManager = new ConfigManager(this);
        distanceCache = new DistanceCache(this, configManager);
        
        getCommand("virtha").setExecutor(new VirthaCommand(configManager));
        
        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new TrackerExpansion(this, configManager, distanceCache).register();
        }
        
        getLogger().info("VirthaTracker has been enabled!");
    }

    @Override
    public void onDisable() {
        if (distanceCache != null) {
            distanceCache.stopCleanupTask();
        }
        getLogger().info("VirthaTracker has been disabled!");
    }
}
