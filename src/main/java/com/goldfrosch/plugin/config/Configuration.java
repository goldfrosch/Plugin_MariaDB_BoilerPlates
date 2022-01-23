package com.goldfrosch.plugin.config;

import com.goldfrosch.plugin.config.object.Database;
import org.bukkit.plugin.Plugin;

public class Configuration {
    private final Plugin plugin;

    private Database database;
    private String driver;

    public Configuration(Plugin plugin) {
        this.plugin = plugin;
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public String getDriver() {
        return driver;
    }

    public Database getDatabase() {
        return database;
    }
}
