package com.virtha.tracker;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DistanceCache {

    private final JavaPlugin plugin;
    private final ConfigManager configManager;
    private final Map<String, CacheEntry> cache = new ConcurrentHashMap<>();
    private BukkitTask cleanupTask;

    public DistanceCache(JavaPlugin plugin, ConfigManager configManager) {
        this.plugin = plugin;
        this.configManager = configManager;
        startCleanupTask();
    }

    public void put(String key, String value) {
        cache.put(key, new CacheEntry(value, System.currentTimeMillis()));
    }

    public String get(String key) {
        CacheEntry entry = cache.get(key);
        if (entry != null) {
            long age = System.currentTimeMillis() - entry.timestamp;
            if (age <= configManager.getCacheTimeMs()) {
                return entry.value;
            } else {
                cache.remove(key);
            }
        }
        return null;
    }
    
    public void clear() {
        cache.clear();
    }

    private void startCleanupTask() {
        // Run every 20 seconds (400 ticks) to clean up old entries and prevent memory leaks
        cleanupTask = Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, () -> {
            long now = System.currentTimeMillis();
            long maxAge = configManager.getCacheTimeMs();
            cache.entrySet().removeIf(entry -> (now - entry.getValue().timestamp) > maxAge);
        }, 400L, 400L);
    }
    
    public void stopCleanupTask() {
        if (cleanupTask != null) {
            cleanupTask.cancel();
        }
    }

    private static class CacheEntry {
        final String value;
        final long timestamp;

        CacheEntry(String value, long timestamp) {
            this.value = value;
            this.timestamp = timestamp;
        }
    }
}
