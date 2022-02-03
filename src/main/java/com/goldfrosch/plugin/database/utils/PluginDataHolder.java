package com.goldfrosch.plugin.database.utils;

import org.bukkit.plugin.Plugin;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;

public class PluginDataHolder {
    private final Plugin plugin;
    private final DataSource source;

    public PluginDataHolder(Plugin plugin, DataSource source) {
        this.plugin = plugin;
        this.source = source;
    }

    protected Connection conn() throws SQLException {
        return source.getConnection();
    }

    protected void logSQLError(Level level, String message, SQLException ex) {
        if (level.intValue() < Level.INFO.intValue()) {
            level = Level.INFO;
        }

        plugin.getLogger().log(
                level, String.format("%s%nMessage: %s%nCode: %s%nState: %s",
                        message, ex.getMessage(), ex.getErrorCode(), ex.getSQLState()), ex);
    }

    protected void logSQLError(String message, SQLException ex) {
        logSQLError(Level.SEVERE, message, ex);
    }

    protected DataSource source() {
        return source;
    }

    protected Plugin plugin() {
        return plugin;
    }
}
