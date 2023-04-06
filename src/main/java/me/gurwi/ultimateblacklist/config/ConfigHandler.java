package me.gurwi.ultimateblacklist.config;

import me.gurwi.ultimateblacklist.UltimateBlacklist;
import me.gurwi.ultimateblacklist.utils.customlogger.CustomLogger;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConfigHandler {

    private static Configuration configuration;

    private static final String dbTypeDefaultValue = "SQLITE";
    private static final List<String> availableDBTypes = Arrays.asList("SQLITE", "MYSQL");
    private static String currentDBType;

    private static List<String> configErrors = new ArrayList<>();

    public static void loadConfig() {
        createConfig();
        registerConfig();
    }

    private static void createConfig() {
        if (!UltimateBlacklist.getInstance().getDataFolder().exists()) {
            UltimateBlacklist.getInstance().getDataFolder().mkdir();
        }

        File file = new File(UltimateBlacklist.getInstance().getDataFolder(), "config.yml");

        if (!file.exists()) {
            try (InputStream in = UltimateBlacklist.getInstance().getResourceAsStream("config.yml")) {
                Files.copy(in, file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private static void registerConfig() {
        try {
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(UltimateBlacklist.getInstance().getDataFolder(), "config.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // OTHER

    public static void setupDBType() {

        if (!availableDBTypes.contains(ConfigManager.DB_TYPE.getString().toUpperCase())) {
            currentDBType = dbTypeDefaultValue;
            configErrors.add("Invalid DB Type using the default one §f-> §c" + dbTypeDefaultValue + " §4(Current " + ConfigManager.DB_TYPE.getString() + ")");
            return;
        }

        currentDBType = ConfigManager.DB_TYPE.getString().toUpperCase();

    }

    // GETTERS

    public static Configuration getConfiguration() {
        return configuration;
    }

    public static void debugResult() {
        configErrors.forEach(CustomLogger::configError);
    }

    public static String getCurrentDBType() {
        return currentDBType.toUpperCase();
    }

}
