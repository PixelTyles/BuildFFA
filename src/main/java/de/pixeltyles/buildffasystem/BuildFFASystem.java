package de.pixeltyles.buildffasystem;

import com.sun.org.apache.xpath.internal.operations.Bool;
import de.pixeltyles.buildffasystem.commands.ArenaCommand;
import de.pixeltyles.buildffasystem.listeners.ConnectionListener;
import de.pixeltyles.buildffasystem.tabcompleter.ArenaCompleter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public final class BuildFFASystem extends JavaPlugin {

    private BuildFFASystem instance;

    public static List<UUID> playersInArena = new ArrayList<>();

    @Override
    public void onEnable() {
        getCommand("arena").setExecutor(new ArenaCommand(this, playersInArena));
        getCommand("arena").setTabCompleter(new ArenaCompleter());

        Bukkit.getServer().getPluginManager().registerEvents(new ConnectionListener(this, playersInArena), this);
    }


    @Override
    public void onDisable() {

    }

    public BuildFFASystem getInstance() {
        return instance;
    }

}
