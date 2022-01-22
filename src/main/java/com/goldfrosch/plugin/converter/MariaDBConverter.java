package com.goldfrosch.plugin.converter;

import com.goldfrosch.plugin.config.object.Database;
import org.bukkit.plugin.Plugin;
import org.mariadb.jdbc.MariaDbPoolDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class MariaDBConverter {
    public static DataSource initMariaDBDataSource(Plugin plugin, Database database) throws SQLException  {
        MariaDbPoolDataSource dataSource = new MariaDbPoolDataSource();

        dataSource.setServerName(database.getHost());
        dataSource.setPassword(database.getPassword());
        dataSource.setPortNumber(database.getPort());
        dataSource.setDatabaseName(database.getDatabase());
        dataSource.setUser(database.getUser());

        //최대 이용 코어 사이즈 설정(나중에 알아볼 필요 있음)
        dataSource.setMaxPoolSize(8);

        return dataSource;
    }
}
