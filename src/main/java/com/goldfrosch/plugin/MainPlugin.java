package com.goldfrosch.plugin;

import com.goldfrosch.plugin.commands.Commands;
import com.goldfrosch.plugin.config.object.Database;
import com.goldfrosch.plugin.converter.MariaDBConverter;
import com.goldfrosch.plugin.events.NewEvent;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.logging.Level;

public class MainPlugin extends JavaPlugin implements Listener {

  private PluginDescriptionFile pdfFile = this.getDescription();
  private String pfName = pdfFile.getName() + " v" + pdfFile.getVersion();

  private DataSource dataSource;

  @Override
  public void onEnable(){
    Bukkit.getPluginManager().registerEvents(new NewEvent(this),this);
    //config파일 있는지 파악 후 생성
    if (!getDataFolder().exists()) {
      getConfig().options().copyDefaults(true);
      saveConfig();
    } else {
      saveConfig();
    }

    //sql convert
    try {
      dataSource = MariaDBConverter.initMariaDBDataSource(this, getConfig().getObject("database", Database.class, new Database()));
      consoleLog("성공적으로 연결되었습니다");
    } catch (SQLException e) {
      consoleDanger("데이터 베이스 연동 실패", e);
      getServer().getPluginManager().disablePlugin(this);
    }

    //command
    Commands cmd = new Commands(this,"cmd");
    getCommand(cmd.getCommand()).setExecutor(cmd);
    getCommand(cmd.getCommand()).setTabCompleter(cmd);

    consoleLog(pfName+"이 활성화 되었습니다");
    super.onEnable();
  }

  @Override
  public void onDisable(){
    consoleLog(pfName+"이 비활성화 되었습니다");
    super.onDisable();
  }

  public void consoleLog(String msg){
    getLogger().info(msg);
  }

  public void consoleDanger(String msg, Exception e) {
    getLogger().log(Level.SEVERE, msg, e);
  }
}
