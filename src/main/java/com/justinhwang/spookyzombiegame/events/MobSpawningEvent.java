package com.justinhwang.spookyzombiegame.events;

import com.justinhwang.spookyzombiegame.SpookyZombieGame;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class MobSpawningEvent implements Listener {
    private SpookyZombieGame plugin;

    public MobSpawningEvent(SpookyZombieGame plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onMobSpawn(CreatureSpawnEvent event) {
        LivingEntity entity = event.getEntity();
        if(entity instanceof Zombie) {
            Zombie zombie = (Zombie) entity;
            zombie.getEquipment().setHelmet(new ItemStack(Material.LEATHER_HELMET));
            zombie.getEquipment().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
            zombie.getEquipment().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
            zombie.getEquipment().setBoots(new ItemStack(Material.LEATHER_BOOTS));
        } else if(entity instanceof Skeleton) {
            Skeleton skeleton = (Skeleton) entity;
            skeleton.getEquipment().setHelmet(new ItemStack(Material.LEATHER_HELMET));
            skeleton.getEquipment().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE));
            skeleton.getEquipment().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS));
            skeleton.getEquipment().setBoots(new ItemStack(Material.LEATHER_BOOTS));
        } else if(entity instanceof Creeper) {
            Creeper creeper = (Creeper) entity;
            if(Math.random() < 0.05)
                creeper.setPowered(true);
        } else if(entity instanceof Spider) {
            Spider spider = (Spider) entity;
            if(Math.random() < 0.05)
                spider.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,2000000, 2));
        } else if(entity instanceof Bat || entity instanceof Witch) {
            //do nothing, just don't cancel
        } else {
            event.setCancelled(true);
        }
    }
}
