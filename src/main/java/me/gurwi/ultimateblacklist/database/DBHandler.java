package me.gurwi.ultimateblacklist.database;

import me.gurwi.ultimateblacklist.UltimateBlacklist;
import me.gurwi.ultimateblacklist.config.ConfigHandler;
import me.gurwi.ultimateblacklist.config.ConfigManager;
import me.gurwi.ultimateblacklist.database.remote.MySQL;
import me.gurwi.ultimateblacklist.utils.customlogger.CustomLogger;

import java.sql.SQLException;

public class DBHandler {

    private static SQLManager sql;

    public static void setupDB() {
        String database = ConfigManager.MYSQL_DB.getString();
        String username = ConfigManager.MYSQL_USER.getString();
        String password = ConfigManager.MYSQL_PASSWORD.getString();
        String host = ConfigManager.MYSQL_HOST.getString();
        int port = ConfigManager.MYSQL_PORT.getInt();
        boolean ssl = ConfigManager.MYSQL_USESSL.getBoolean();
        sql = (ConfigHandler.getCurrentDBType().equals("MYSQL")) ? new MySQL(UltimateBlacklist.getInstance(), database, username, password, host, port, ssl) : new SQLite(UltimateBlacklist.getInstance());
        try {sql.setup();} catch (SQLException throwables) {throwables.printStackTrace();}
        CustomLogger.success("DBManager configured successfully. Current DBType §f-> §a" + ConfigHandler.getCurrentDBType());

    }

    public static SQLManager getSQL() {
        return sql;
    }

}
