package com.virtha.tracker;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class ConfigManager {

    private final JavaPlugin plugin;
    
    private String differentWorldMsg;
    private String playerOfflineMsg;
    private String distanceTextFormat;
    private boolean roundDistance;
    private long cacheTimeMs;

    public ConfigManager(JavaPlugin plugin) {
        this.plugin = plugin;
        plugin.saveDefaultConfig();
        loadConfig();
    }

    public void loadConfig() {
        plugin.reloadConfig();
        FileConfiguration config = plugin.getConfig();
        
        differentWorldMsg = config.getString("format.different_world", "N/A");
        playerOfflineMsg = config.getString("format.player_offline", "Offline");
        distanceTextFormat = config.getString("format.distance_text", "%distance%m");
        roundDistance = config.getBoolean("format.round_distance", true);
        
        cacheTimeMs = config.getLong("optimization.cache_time_ms", 500L);
    }

    public String getDifferentWorldMsg() {
        return differentWorldMsg;
    }

    public String getPlayerOfflineMsg() {
        return playerOfflineMsg;
    }

    public String getDistanceTextFormat() {
        return distanceTextFormat;
    }

    public boolean isRoundDistance() {
        return roundDistance;
    }

    public long getCacheTimeMs() {
        return cacheTimeMs;
    }
}
