package com.justinhwang.spookyzombiegame.events;

import com.justinhwang.spookyzombiegame.SpookyZombieGame;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class LoginEvent implements Listener {
    SpookyZombieGame plugin;

    public LoginEvent(SpookyZombieGame plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void joinEvent(PlayerJoinEvent event) {
        Player p = event.getPlayer();

        p.setGameMode(GameMode.ADVENTURE);

        p.getInventory().clear();

        ItemStack weapon = new ItemStack(Material.IRON_PICKAXE);
        ItemMeta meta = weapon.getItemMeta();
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Zombie Killer");
        meta.setUnbreakable(true);
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GOLD + "- Kill mobs for points");
        lore.add(ChatColor.GOLD + "- Points reset on death");
        lore.add(ChatColor.GOLD + "- Use /highscores ");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.MENDING, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        weapon.setItemMeta(meta);

        p.getInventory().setItem(0, weapon);

    }
}
