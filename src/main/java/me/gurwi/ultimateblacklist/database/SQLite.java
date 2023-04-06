package me.gurwi.ultimateblacklist.database;

import me.gurwi.ultimateblacklist.UltimateBlacklist;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLite extends SQLManager {

    public SQLite(UltimateBlacklist plugin) {
        super(plugin);
    }

    @Override
    public Connection getJdbcUrl() throws SQLException {
        String SQLITE_FILE_NAME = "database.db";
        String dataUrl = UltimateBlacklist.getInstance().getDataFolder() + File.separator + "data" + File.separator + SQLITE_FILE_NAME;
        String url = "jdbc:sqlite:" + dataUrl;
        File dataFolder = new File(dataUrl);
        if (!dataFolder.exists()) dataFolder.getParentFile().mkdirs();
        try { Class.forName("org.sqlite.JDBC").newInstance(); } catch(Exception e) {e.printStackTrace();};
        return DriverManager.getConnection(url);
    }

    @Override
    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = getJdbcUrl();
        }
        return this.connection;
    }

    @Override
    public void createTables() {

        try (Connection connection = getConnection();

            PreparedStatement data = connection.prepareStatement("create TABLE if not exists " + UBLACKLIST_TABLE + " (playerUUID VARCHAR(36), username LONGTEXT, ip LONGTEXT, isBlacklisted BOOLEAN)")) {
            data.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

}
