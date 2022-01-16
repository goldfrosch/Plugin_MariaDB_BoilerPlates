package com.goldfrosch.plugin;

import com.goldfrosch.plugin.commands.Commands;
import com.goldfrosch.plugin.events.NewEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import org.mariadb.jdbc.MariaDbPoolDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

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

}
