package com.goldfrosch.plugin.events;

import com.goldfrosch.plugin.MainPlugin;
import org.bukkit.event.Listener;

public class NewEvent implements Listener {
  private final MainPlugin plugin;

  public NewEvent(MainPlugin plugin){
    this.plugin = plugin;
  }
}
