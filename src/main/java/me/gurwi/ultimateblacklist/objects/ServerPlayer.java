package me.gurwi.ultimateblacklist.objects;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ServerPlayer {

    private String username;
    private UUID uuid;
    private String ip;
    private Boolean blacklisted;

    public ServerPlayer(String username, UUID uuid, String ip, Boolean blacklisted) {
        this.username = username;
        this.uuid = uuid;
        this.ip = ip;
        this.blacklisted = blacklisted;
    }

    public static ServerPlayer getServerPlayer(UUID playerUUID) {
        return null;
    }

}
