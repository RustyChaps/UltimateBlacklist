package me.gurwi.ultimateblacklist.utils;

import me.gurwi.ultimateblacklist.UltimateBlacklist;
import me.gurwi.ultimateblacklist.config.ConfigHandler;
import me.gurwi.ultimateblacklist.database.DBHandler;
import me.gurwi.ultimateblacklist.database.remote.MySQL;
import me.gurwi.ultimateblacklist.listeners.UserJoin;
import me.gurwi.ultimateblacklist.utils.customlogger.CustomLogger;
import net.md_5.bungee.api.ProxyServer;

public class PluginCustomLoader {

    private static void loadConfig() {
        CustomLogger.load("Loading Config...");

        ConfigHandler.loadConfig();
        ConfigHandler.setupDBType();
        ConfigHandler.debugResult();
    }

    private static void loadDB() {
        CustomLogger.load("Loading DataBase...");

        DBHandler.setupDB();
    }

    private static void loadListeners() {
        CustomLogger.load("Loading events...");

        UltimateBlacklist.getInstance().getProxy().getPluginManager().registerListener(UltimateBlacklist.getInstance(), new UserJoin());
    }

    private static void loadCommands() {
        CustomLogger.load("Loading commands...");



    }

    public static void loadPlugin() {

        loadConfig();
        loadDB();
        loadListeners();
        loadCommands();

    }

    public static void disablePlugin() {
        if (ConfigHandler.getCurrentDBType().equalsIgnoreCase("MYSQL")) {
            MySQL mysql = (MySQL) DBHandler.getSQL();
            mysql.closePool();
        }
    }

}
