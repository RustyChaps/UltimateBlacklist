package me.gurwi.ultimateblacklist;

import me.gurwi.ultimateblacklist.utils.PluginCustomLoader;
import net.md_5.bungee.api.plugin.Plugin;

public final class UltimateBlacklist extends Plugin {

    public static UltimateBlacklist instance;
    public UltimateBlacklist() {
        instance = this;
    }

    @Override
    public void onEnable() {
        long start = System.currentTimeMillis();

        // MAIN

        PluginCustomLoader.loadPlugin();

        // CONSOLE MESSAGE

        getLogger().info("");
        getLogger().info("§8§l» §9§lULTIMATEBLACKLIST §7v1.0");
        getLogger().info("");
        getLogger().info("§7Plugin enabled successfully. §3(" + (System.currentTimeMillis() - start) + "ms)");
        getLogger().info("§f§oBy @Gurwi30");
        getLogger().info("");
    }

    @Override
    public void onDisable() {
        long start = System.currentTimeMillis();

        // MAIN

        PluginCustomLoader.disablePlugin();

        // CONSOLE MESSAGE

        getLogger().info("");
        getLogger().info("§8§l» §9§lULTIMATEBLACKLIST §7v1.0");
        getLogger().info("");
        getLogger().info("§7Plugin disabled successfully. §3(" + (System.currentTimeMillis() - start) + "ms)");
        getLogger().info("§f§oBy @Gurwi30");
        getLogger().info("");
    }

    public static UltimateBlacklist getInstance() {
        return instance;
    }

}
