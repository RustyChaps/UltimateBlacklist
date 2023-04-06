package me.gurwi.ultimateblacklist.database;

import me.gurwi.ultimateblacklist.UltimateBlacklist;
import me.gurwi.ultimateblacklist.objects.ServerPlayer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public abstract class SQLManager {

    protected final String UBLACKLIST_TABLE = "unblacklist_data";
    protected Connection connection;

    public SQLManager(UltimateBlacklist plugin) {
    }

    public void setup() throws SQLException {
        try {
            if (connection != null && !connection.isClosed()) {
                return;
            }
            setConnection(getJdbcUrl());
            this.createTables();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public abstract void createTables() throws SQLException;

    public abstract Connection getConnection() throws SQLException, ClassNotFoundException;


    // METHODS

    public void savePlayer(ProxiedPlayer player) {

        try {
            PreparedStatement statement = getConnection().prepareStatement("INSERT INTO " + UBLACKLIST_TABLE + "(playerUUID VARCHAR(36), username LONGTEXT, ip LONGTEXT, isBlacklisted BOOLEAN) VALUES (?, ?, ?, ?)");

            statement.setString(1, player.getUniqueId().toString());
            statement.setString(2, player.getName());
            statement.setString(3, player.getSocketAddress().toString());
            statement.setBoolean(4, false);

            statement.executeUpdate();
            statement.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public Boolean isSaved(ProxiedPlayer player) {

        try {
            PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM " + UBLACKLIST_TABLE + " WHERE playerUUID = ?");
            statement.setString(1, player.getUniqueId().toString());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                statement.close();
                return true;
            } else {
                statement.close();
                return false;
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public ServerPlayer getServerPlayer(UUID uuid) {

        try {
            PreparedStatement statement = getConnection().prepareStatement("SELECT * FROM " + UBLACKLIST_TABLE + " WHERE playerUUID = ?");
            statement.setString(1, uuid.toString());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                statement.close();
                return new ServerPlayer(resultSet.getString("username"), UUID.fromString(resultSet.getString("playerUUID")), resultSet.getString("ip"), resultSet.getBoolean("isBlacklisted"));

            } else {
                statement.close();
                return null;
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void blacklistAdd(ServerPlayer serverPlayer) {

        try {
            PreparedStatement statement = getConnection().prepareStatement("UPDATE " + UBLACKLIST_TABLE + " SET isBlacklisted = ? WHERE playerUUID = ?");

            statement.setBoolean(1, true);
            statement.setString(2, serverPlayer.getUuid().toString());

            statement.executeUpdate();
            statement.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void blacklistRemove(ServerPlayer serverPlayer) {

        try {
            PreparedStatement statement = getConnection().prepareStatement("UPDATE " + UBLACKLIST_TABLE + " SET isBlacklisted = ? WHERE playerUUID = ?");

            statement.setBoolean(1, false);
            statement.setString(2, serverPlayer.getUuid().toString());

            statement.executeUpdate();
            statement.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    ////

    private void setConnection(Connection connection) {
        this.connection = connection;
    }
    protected abstract Connection getJdbcUrl() throws SQLException, ClassNotFoundException;

}
