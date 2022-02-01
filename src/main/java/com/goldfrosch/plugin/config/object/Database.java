package com.goldfrosch.plugin.config.object;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;

import java.util.LinkedHashMap;
import java.util.Map;

@SerializableAs("GoldCash")
public class Database implements ConfigurationSerializable {
    private final String host;
    private final int port;
    private final String database;
    private final String user;
    private final String password;

    public Database(Map<String, Object> map) {
        host = String.valueOf(map.getOrDefault("host", "localhost"));
        port = (int) map.getOrDefault("port", "3306");
        database = String.valueOf(map.getOrDefault("database", "mariadb"));
        user = String.valueOf(map.getOrDefault("user", "root"));
        password = String.valueOf(map.getOrDefault("password", "1234"));
    }

    public Database() {
        host = "localhost";
        port = 3306;
        database = "mariadb";
        user = "root";
        password = "1234";
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("host", host);
        map.put("port", port);
        map.put("database", database);
        map.put("user", user);
        map.put("password", password);
        return map;
    }


    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getDatabase() {
        return database;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
