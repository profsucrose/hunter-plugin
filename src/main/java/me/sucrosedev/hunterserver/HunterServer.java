package me.sucrosedev.hunterserver;

import me.sucrosedev.hunterserver.commands.MakeTarget;
import me.sucrosedev.hunterserver.listeners.HunterManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class HunterServer extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getServer().getPluginManager().registerEvents(new HunterManager(), this);
        this.getCommand("maketarget").setExecutor(new MakeTarget());
    }

}
