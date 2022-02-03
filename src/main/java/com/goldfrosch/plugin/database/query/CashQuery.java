package com.goldfrosch.plugin.database.query;

import com.goldfrosch.plugin.database.utils.AsyncScheduler;
import com.goldfrosch.plugin.database.utils.PluginDataHolder;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CashQuery extends PluginDataHolder {
    public CashQuery(Plugin plugin, DataSource source) {
        super(plugin, source);
    }

    public AsyncScheduler<Boolean> addCash(Player player, long amount) {
        String query = "INSERT INTO player_cash SET uuid  = ?, cash = ? ON DUPLICATE KEY UPDATE cash = cash + ?;";
        return AsyncScheduler.supplyAsync(plugin(), () -> {
            try (
                    Connection conn = conn();
                    PreparedStatement stmt = conn.prepareStatement(query)
            ) {
                stmt.setString(1, player.getUniqueId().toString());
                stmt.setLong(2, amount);
                stmt.setLong(3, amount);
                stmt.execute();
                return true;
            } catch (SQLException e) {
                logSQLError("Could not add coins", e);
            }
            return false;
        });
    }
}
