package com.justinhwang.spookyzombiegame.commands;

import com.justinhwang.spookyzombiegame.SpookyZombieGame;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HighscoresCommand implements CommandExecutor {
    private SpookyZombieGame plugin;

    public HighscoresCommand(SpookyZombieGame plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        List<String> playerUUIDList = copyList((List<String>) plugin.getHighscoreFileConfig().getList("players"));

        Bukkit.getLogger().info(playerUUIDList.toString());

        sender.sendMessage(ChatColor.LIGHT_PURPLE + "--------------------------------------------------\n"+
                           ChatColor.GOLD + "                             Top Scorers\n"+
                           ChatColor.LIGHT_PURPLE + "--------------------------------------------------\n");

        if(playerUUIDList.size() == 0) {
            Bukkit.getLogger().info("No highscores to report!");
            sender.sendMessage(ChatColor.LIGHT_PURPLE + "No highscores to report!");

            plugin.getHighscoreFileConfig().set("highscores." + Bukkit.getPlayer("jhwang04").getUniqueId(), 100);

            List<String> newPlayerList = plugin.getHighscoreFileConfig().getStringList("players");
            if(newPlayerList == null)
                newPlayerList = new ArrayList<String>();
            newPlayerList.add(Bukkit.getPlayer("jhwang04").getUniqueId().toString());
            Bukkit.getLogger().info(newPlayerList.toString());
            plugin.getHighscoreFileConfig().set("players", newPlayerList);

            plugin.saveHighscores();
        } else {
            for(int i = 1; i <= 3; i++) {
                if (playerUUIDList.size() > 0) {
                    String highestUUID = null;
                    for (String uuid : playerUUIDList) {
                        if (highestUUID == null || plugin.getHighscoreFileConfig().getInt("highscores." + uuid) >= plugin.getHighscoreFileConfig().getInt("highscores." + highestUUID))
                            highestUUID = uuid;
                    }
                    sender.sendMessage(ChatColor.LIGHT_PURPLE + "#" + i + ") " + ChatColor.GOLD + Bukkit.getOfflinePlayer(UUID.fromString(highestUUID)).getName() + " - " + ChatColor.LIGHT_PURPLE + plugin.getHighscoreFileConfig().getInt("highscores." + highestUUID) + " pts");
                    playerUUIDList.remove(highestUUID);
                } else {
                    sender.sendMessage(ChatColor.LIGHT_PURPLE + "#" + i + ") " + ChatColor.WHITE + "N/A");
                }
            }
        }
        if(sender instanceof Player) {
            Player p = (Player) sender;
            sender.sendMessage("\n" + ChatColor.GOLD + sender.getName() + " - " + ChatColor.LIGHT_PURPLE + plugin.getHighscoreFileConfig().getInt("highscores." + p.getUniqueId()) + " pts");
        }

        return true;
    }

    private List<String> copyList(List<String> list) {
        List<String> newList = new ArrayList<String>();
        for(String string : list) {
            newList.add(string);
        }
        return newList;
    }
}
