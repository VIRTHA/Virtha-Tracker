package com.virtha.tracker;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.ChatColor;

public class VirthaCommand implements CommandExecutor {

    private final ConfigManager configManager;

    public VirthaCommand(ConfigManager configManager) {
        this.configManager = configManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
            if (sender.hasPermission("virthatracker.admin")) {
                configManager.loadConfig();
                sender.sendMessage(ChatColor.GREEN + "[VirthaTracker] Configuration reloaded.");
                return true;
            } else {
                sender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
                return true;
            }
        }
        
        sender.sendMessage(ChatColor.AQUA + "VirthaTracker Plugin - Use /virtha reload");
        return true;
    }
}
