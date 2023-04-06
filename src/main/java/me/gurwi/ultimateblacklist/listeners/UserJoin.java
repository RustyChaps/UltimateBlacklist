package me.gurwi.ultimateblacklist.listeners;

import me.gurwi.ultimateblacklist.database.DBHandler;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PreLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class UserJoin implements Listener {

    @EventHandler
    public void onJoin(PreLoginEvent event) {

        ProxiedPlayer player = ProxyServer.getInstance().getPlayer(event.getConnection().getUniqueId());
        DBHandler.getSQL().savePlayer(player);

    }

}
