package me.gurwi.ultimateblacklist.cache;

import lombok.Getter;
import me.gurwi.ultimateblacklist.objects.ServerPlayer;

import java.util.*;

public class UBlacklistDataManager {

    @Getter
    private static List<ServerPlayer> serverPlayers = new ArrayList<>();

}
