package com.justinhwang.spookyzombiegame.commands;

import com.justinhwang.spookyzombiegame.SpookyZombieGame;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SpookyZombieGameCommand implements CommandExecutor {
    SpookyZombieGame plugin;

    public SpookyZombieGameCommand(SpookyZombieGame plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0) {
            sender.sendMessage("config.enabled is set to " + plugin.getConfig().getBoolean("enabled"));
        } else {
            if(args[0].equals("enable")) {
                plugin.getConfig().set("enabled", true);
                plugin.saveConfig();
                sender.sendMessage("config.enabled has been set to " + plugin.getConfig().getBoolean("enabled"));
            } else if(args[0].equals("disable")) {
                plugin.getConfig().set("enabled", false);
                plugin.saveConfig();
                sender.sendMessage("config.enabled has been set to " + plugin.getConfig().getBoolean("enabled"));
            } else {
                sender.sendMessage(ChatColor.RED + "Usage: /spookyzombiegame <enable/disable>");
            }
        }

        return true;
    }
}
