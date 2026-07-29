package com.radarx;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;

public class RadarXCommand implements CommandExecutor {

    private final ConfigManager configManager;

    public RadarXCommand(ConfigManager configManager) {
        this.configManager = configManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
            if (sender.hasPermission("radarx.admin")) {
                configManager.loadConfig();
                sender.sendMessage(ChatColor.GREEN + "[RadarX] Configuration reloaded.");
                return true;
            } else {
                sender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
                return true;
            }
        }
        
        sender.sendMessage(ChatColor.AQUA + "RadarX Plugin - Use /radarx reload");
        return true;
    }
}
