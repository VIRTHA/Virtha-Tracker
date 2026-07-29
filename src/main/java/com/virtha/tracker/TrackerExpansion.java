package com.virtha.tracker;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TrackerExpansion extends PlaceholderExpansion {

    private final VirthaTrackerPlugin plugin;
    private final ConfigManager configManager;
    private final DistanceCache distanceCache;

    public TrackerExpansion(VirthaTrackerPlugin plugin, ConfigManager configManager, DistanceCache distanceCache) {
        this.plugin = plugin;
        this.configManager = configManager;
        this.distanceCache = distanceCache;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "virtha";
    }

    @Override
    public @NotNull String getAuthor() {
        return plugin.getDescription().getAuthors().get(0);
    }

    @Override
    public @NotNull String getVersion() {
        return plugin.getDescription().getVersion();
    }
    
    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public @Nullable String onRequest(OfflinePlayer offlinePlayer, @NotNull String params) {
        if (offlinePlayer == null || !offlinePlayer.isOnline()) {
            return null;
        }

        Player player = offlinePlayer.getPlayer();
        if (player == null) return null;

        // distance_xyz_<x>_<y>_<z>
        // distance_xz_<x>_<z>
        // distance_player_<name>
        // arrow_xyz_<x>_<y>_<z>
        // arrow_xz_<x>_<z>
        // arrow_player_<name>
        boolean isDistance = params.startsWith("distance_");
        boolean isArrow = params.startsWith("arrow_");
        if (!isDistance && !isArrow) {
            return null;
        }
        
        // Caching
        String cacheKey = player.getUniqueId() + "_" + params;
        String cached = distanceCache.get(cacheKey);
        if (cached != null) {
            return cached;
        }

        String result;
        if (isDistance) {
            result = calculateDistance(player, params.substring(9)); // strip "distance_"
        } else {
            result = calculateArrow(player, params.substring(6)); // strip "arrow_"
        }

        if (result != null) {
            distanceCache.put(cacheKey, result);
        }
        return result;
    }

    private String calculateDistance(Player player, String targetStr) {
        Location targetLoc = null;

        try {
            if (targetStr.startsWith("xyz_")) {
                String[] parts = targetStr.substring(4).split("_");
                if (parts.length == 3) {
                    double x = Double.parseDouble(parts[0]);
                    double y = Double.parseDouble(parts[1]);
                    double z = Double.parseDouble(parts[2]);
                    targetLoc = new Location(player.getWorld(), x, y, z);
                }
            } else if (targetStr.startsWith("xz_")) {
                String[] parts = targetStr.substring(3).split("_");
                if (parts.length == 2) {
                    double x = Double.parseDouble(parts[0]);
                    double z = Double.parseDouble(parts[1]);
                    targetLoc = new Location(player.getWorld(), x, player.getLocation().getY(), z);
                }
            } else if (targetStr.startsWith("player_")) {
                String targetName = targetStr.substring(7);
                Player targetPlayer = Bukkit.getPlayerExact(targetName);
                if (targetPlayer == null || !targetPlayer.isOnline()) {
                    return configManager.getPlayerOfflineMsg();
                }
                targetLoc = targetPlayer.getLocation();
            }
        } catch (NumberFormatException e) {
            return null; // Invalid placeholder format
        }

        if (targetLoc == null) {
            return null;
        }

        if (!player.getWorld().equals(targetLoc.getWorld())) {
            return configManager.getDifferentWorldMsg();
        }

        double distance = player.getLocation().distance(targetLoc);
        return formatDistance(distance);
    }

    private String calculateArrow(Player player, String targetStr) {
        Location targetLoc = null;

        try {
            if (targetStr.startsWith("xyz_")) {
                String[] parts = targetStr.substring(4).split("_");
                if (parts.length == 3) {
                    double x = Double.parseDouble(parts[0]);
                    double y = Double.parseDouble(parts[1]);
                    double z = Double.parseDouble(parts[2]);
                    targetLoc = new Location(player.getWorld(), x, y, z);
                }
            } else if (targetStr.startsWith("xz_")) {
                String[] parts = targetStr.substring(3).split("_");
                if (parts.length == 2) {
                    double x = Double.parseDouble(parts[0]);
                    double z = Double.parseDouble(parts[1]);
                    targetLoc = new Location(player.getWorld(), x, player.getLocation().getY(), z);
                }
            } else if (targetStr.startsWith("player_")) {
                String targetName = targetStr.substring(7);
                Player targetPlayer = Bukkit.getPlayerExact(targetName);
                if (targetPlayer == null || !targetPlayer.isOnline()) {
                    return configManager.getPlayerOfflineMsg();
                }
                targetLoc = targetPlayer.getLocation();
            }
        } catch (NumberFormatException e) {
            return null; // Invalid placeholder format
        }

        if (targetLoc == null) {
            return null;
        }

        if (!player.getWorld().equals(targetLoc.getWorld())) {
            return configManager.getDifferentWorldMsg();
        }

        return DirectionCalculator.calculateArrow(player.getLocation(), targetLoc, configManager);
    }

    private String formatDistance(double distance) {
        String numStr;
        if (configManager.isRoundDistance()) {
            numStr = String.valueOf(Math.round(distance));
        } else {
            numStr = String.format("%.2f", distance);
        }
        
        return configManager.getDistanceTextFormat().replace("%distance%", numStr);
    }
}
