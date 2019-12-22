package dev.azarattum.spigot.shulkerrespawnplugin;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Plugin to make shulkers respawn
 */
public class Plugin extends JavaPlugin {
    @Override
    public void onEnable() {
        // Register event API
        PluginManager manger = this.getServer().getPluginManager();
        manger.registerEvents(new SpawnInterceptor(), this);

        String version = this.getDescription().getVersion();
        getLogger().info(String.format("ShulkerRespawn v%s enabled", version));
    }

    @Override
    public void onDisable() {
        String version = this.getDescription().getVersion();
        getLogger().info(String.format("ShulkerRespawn v%s disabled", version));
    }
}
