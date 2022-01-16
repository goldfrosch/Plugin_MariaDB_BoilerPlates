package com.goldfrosch.plugin.commands;

import com.goldfrosch.plugin.MainPlugin;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class Commands extends AbstractCommand{
  public Commands(MainPlugin plugin, String Command) {
    super(plugin,Command);
  }

  @Override
  public List<String> onTabComplete(CommandSender sender, org.bukkit.command.Command command, String alias, String[] args) {
    return null;
  }

  @Override
  public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
    String prefix = plugin.getConfig().getString("message.prefix").replace("&", "§");
    if (sender instanceof Player) {
      Player player = (Player) sender;
      if(label.equalsIgnoreCase("cmd")){
        if(args.length == 0){
          player.sendMessage(prefix + "TEST");
        }
      }
    }
    return false;
  }
}
