package com.justinhwang.spookyzombiegame.events;

import com.justinhwang.spookyzombiegame.SpookyZombieGame;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.List;

public class MobDeathEvent implements Listener {
    SpookyZombieGame plugin;

    public MobDeathEvent(SpookyZombieGame plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onMobDeath(EntityDeathEvent event) {
        Bukkit.getLogger().info("EntityDeathEvent is triggered");
        LivingEntity entity = event.getEntity();
        Bukkit.getLogger().info(entity.getKiller().getName());
        if(entity.getKiller() instanceof Player) {
            Player p = (Player) entity.getKiller();
            int currentScore;
            if(plugin.players.get(p) == null)
                currentScore = 0;
            else
                currentScore = plugin.players.get(p);
            int pointValue = 0;
            if(entity instanceof Zombie) {
                pointValue = 10;
            } else if(entity instanceof Skeleton) {
                pointValue = 15;
            } else if(entity instanceof Creeper) {
                pointValue = 15;
            } else if(entity instanceof Spider) {
                pointValue = 10;
            } else if(entity instanceof Bat) {
                pointValue = 1;
            }
            p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1.0f);
            plugin.players.put(p, currentScore + pointValue);
            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GOLD + "+" + pointValue + " points           " + ChatColor.LIGHT_PURPLE + "" + plugin.players.get(p) + " total"));
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        if(plugin.players.get(p) == null)
            plugin.players.put(p, 0);
        int score = plugin.players.get(p);



        e.setDeathMessage(ChatColor.RED + "RIP! " + p.getName() + " died with " + score + " points!");
        p.sendMessage(ChatColor.LIGHT_PURPLE + "Use \"/highscores\" to see where you are on the leaderboard!");

        int formerHighScore = plugin.getHighscoreFileConfig().getInt("highscores." + p.getUniqueId());
        plugin.getHighscoreFileConfig().set("highscores." + p.getUniqueId(), Math.max(formerHighScore, score));

        List<String> players = plugin.getHighscoreFileConfig().getStringList("players");
        if(!players.contains(p.getUniqueId().toString())) {
            players.add(p.getUniqueId().toString());
            plugin.getHighscoreFileConfig().set("players", players);
        }

        plugin.saveHighscores();

        //run last, zero out the player
        plugin.players.put(p, 0);
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "kill " + e.getPlayer().getName());
    }
}
