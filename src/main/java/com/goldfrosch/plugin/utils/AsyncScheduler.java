package com.goldfrosch.plugin.utils;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.logging.Level;

public final class AsyncScheduler<T> {
    private static final BukkitScheduler scheduler = Bukkit.getScheduler();
    private static ExecutorService executor = Executors.newCachedThreadPool();
    private Plugin plugin;
    private Supplier<T> asyncSupplier;
    private Consumer<Throwable> asyncErrorHandler;

    private AsyncScheduler(Plugin plugin, Supplier<T> asyncSupplier, Consumer<Throwable> asyncErrorHandler) {
        this.asyncSupplier = asyncSupplier;
        this.plugin = plugin;
        this.asyncErrorHandler = asyncErrorHandler;
    }

    //Throwable 객체를 이용해서 void형식으로 return해 특정 메소드를 실행시키는 방식이
    //Consumer<T>인거 같다.
    private static Consumer<Throwable> getDangerLog(Plugin plugin) {
        return e -> plugin.getLogger().log(Level.SEVERE, plugin.getName() + " generated an exception in a BukkitAsyncAction.", e);
    }
}
