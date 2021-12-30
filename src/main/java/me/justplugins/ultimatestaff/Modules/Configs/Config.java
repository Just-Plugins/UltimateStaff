package me.justplugins.ultimatestaff.Modules.Configs;

import me.justplugins.ultimatestaff.Main;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Config {
    final Main plugin;
    public static File MainConfigFile;
    public static FileConfiguration Config;

    public Config(Main plugin){
        this.plugin = plugin;
        createModuleConfig();
    }
    private void createModuleConfig() {
        MainConfigFile = new File(plugin.getDataFolder(), "Config.yml");
        if (!MainConfigFile.exists()) {
            MainConfigFile.getParentFile().mkdirs();
            plugin.saveResource("Config.yml", false);
        }

        Config = new YamlConfiguration();
        try {
            Config.load(MainConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}
