package com.justinhwang.spookyzombiegame;

import com.justinhwang.spookyzombiegame.commands.HighscoresCommand;
import com.justinhwang.spookyzombiegame.commands.SpookyZombieGameCommand;
import com.justinhwang.spookyzombiegame.events.LoginEvent;
import com.justinhwang.spookyzombiegame.events.MobSpawningEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SpookyZombieGame extends JavaPlugin {

    public HashMap<Player, Integer> players;

    private File highscoreFile;
    //private File currentGamesFile;

    private FileConfiguration config;
    private FileConfiguration highscoreFileConfig;
    //private FileConfiguration currentGames;

    @Override
    public void onEnable() {
        //ensures that a config.yml file exists, does NOT restore to default if it already exists.
        saveDefaultConfig();
        config = this.getConfig();

        //saves default version of highscores.yml if it does not exist
        highscoreFile = new File(getDataFolder(), "highscores.yml");
        highscoreFileConfig = YamlConfiguration.loadConfiguration(highscoreFile);
        if(!highscoreFile.exists())
            saveResource("highscores.yml", false);

        getCommand("spookyzombiegame").setExecutor(new SpookyZombieGameCommand(this));

        getCommand("highscores").setExecutor(new HighscoresCommand(this));

        getServer().getPluginManager().registerEvents(new MobSpawningEvent(this), this);

        getServer().getPluginManager().registerEvents(new LoginEvent(this), this);

        Bukkit.getLogger().info(ChatColor.LIGHT_PURPLE + "SpookyZombieGame plugin has been enabled");

    }

    public FileConfiguration getHighscoreFileConfig() {
        return highscoreFileConfig;
    }

    public void saveHighscores() {
        try {
            highscoreFileConfig.save(highscoreFile);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /*public FileConfiguration getCurrentGames() {
        return currentGames;
    }*/

    /*public void saveCurrentGames() {
        try {
            currentGames.save(currentGamesFile);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }*/
}
