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

    private String arrowFront;
    private String arrowFrontRight;
    private String arrowRight;
    private String arrowBackRight;
    private String arrowBack;
    private String arrowBackLeft;
    private String arrowLeft;
    private String arrowFrontLeft;

    public ConfigManager(JavaPlugin plugin) {
        this.plugin = plugin;
        if (plugin != null) {
            plugin.saveDefaultConfig();
            loadConfig();
        }
    }

    public void loadConfig() {
        if (plugin == null) return;
        plugin.reloadConfig();
        FileConfiguration config = plugin.getConfig();
        
        differentWorldMsg = config.getString("format.different_world", "N/A");
        playerOfflineMsg = config.getString("format.player_offline", "Offline");
        distanceTextFormat = config.getString("format.distance_text", "%distance%m");
        roundDistance = config.getBoolean("format.round_distance", true);
        
        cacheTimeMs = config.getLong("optimization.cache_time_ms", 500L);

        arrowFront = config.getString("arrows.front", "↑");
        arrowFrontRight = config.getString("arrows.front_right", "↗");
        arrowRight = config.getString("arrows.right", "→");
        arrowBackRight = config.getString("arrows.back_right", "↘");
        arrowBack = config.getString("arrows.back", "↓");
        arrowBackLeft = config.getString("arrows.back_left", "↙");
        arrowLeft = config.getString("arrows.left", "←");
        arrowFrontLeft = config.getString("arrows.front_left", "↖");
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

    public String getArrowFront() { return arrowFront; }
    public String getArrowFrontRight() { return arrowFrontRight; }
    public String getArrowRight() { return arrowRight; }
    public String getArrowBackRight() { return arrowBackRight; }
    public String getArrowBack() { return arrowBack; }
    public String getArrowBackLeft() { return arrowBackLeft; }
    public String getArrowLeft() { return arrowLeft; }
    public String getArrowFrontLeft() { return arrowFrontLeft; }
}
