package me.gurwi.ultimateblacklist.config;

import net.md_5.bungee.api.ChatColor;

import java.util.List;

public enum ConfigManager {

    DB_TYPE("DataBase.DBType"),

    MYSQL_HOST("DataBase.MySql.storageHost"),
    MYSQL_PORT("DataBase.MySql.storagePort"),
    MYSQL_DB("DataBase.MySql.storageDatabase"),
    MYSQL_USER("DataBase.MySql.storageUser"),
    MYSQL_PASSWORD("DataBase.MySql.storagePassword"),
    MYSQL_USESSL("DataBase.MySql.useSSL");

    // METHODS

    private final String path;

    private ConfigManager(String path) {
        this.path = path;
    }

    public List<String> getListString() {
        return ConfigHandler.getConfiguration().getStringList(this.path);
    }

    public String getFormattedString() {
        return ChatColor.translateAlternateColorCodes('&', ConfigHandler.getConfiguration().getString(this.path));
    }

    public String getString() {
        return ConfigHandler.getConfiguration().getString(this.path);
    }

    public Boolean getBoolean() {
        return ConfigHandler.getConfiguration().getBoolean(this.path);
    }

    public Integer getInt() {
        return ConfigHandler.getConfiguration().getInt(this.path);
    }

}
