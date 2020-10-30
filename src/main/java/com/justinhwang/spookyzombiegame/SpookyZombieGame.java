package com.justinhwang.spookyzombiegame;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class SpookyZombieGame extends JavaPlugin {

    private File highscoreFile;

    private FileConfiguration config;
    private FileConfiguration highscoreFileConfig;

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
}
