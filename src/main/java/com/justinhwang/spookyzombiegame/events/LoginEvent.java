package com.justinhwang.spookyzombiegame.events;

import com.justinhwang.spookyzombiegame.SpookyZombieGame;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.awt.*;
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

        giveWeapon(p);

    }

    @EventHandler
    public void respawnEvent(PlayerRespawnEvent event) {
        giveWeapon(event.getPlayer());
    }

    private void giveWeapon(Player p) {
        ItemStack weapon = new ItemStack(Material.NETHERITE_PICKAXE);
        ItemMeta meta = weapon.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_PURPLE + "Zombie Killer");
        meta.setUnbreakable(true);
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GOLD + "- Kill mobs for points");
        lore.add(ChatColor.GOLD + "- Points reset on death");
        lore.add(ChatColor.GOLD + "- Use /highscores to see");
        lore.add(ChatColor.GOLD + "the top players!");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.MENDING, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        weapon.setItemMeta(meta);

        ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
        helmet.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 5);
        helmet.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 5);
        helmet.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 5);
        LeatherArmorMeta helmetMeta = (LeatherArmorMeta) helmet.getItemMeta();
        helmetMeta.setUnbreakable(true);
        helmetMeta.setColor(Color.fromRGB(255, 165, 0));
        helmetMeta.setDisplayName(ChatColor.GOLD + "Fancy hat");
        helmet.setItemMeta(helmetMeta);

        p.getInventory().setItem(0, weapon);
        p.getInventory().setItem(39, helmet);
    }
}
