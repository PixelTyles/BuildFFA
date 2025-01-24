package de.pixeltyles.buildffasystem.listeners;

import de.pixeltyles.buildffasystem.BuildFFASystem;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.List;
import java.util.UUID;

public class ConnectionListener implements Listener {

    private BuildFFASystem plugin;
    private List playersInArena;

    public ConnectionListener(BuildFFASystem main, List<UUID> players) {
        this.plugin = main;
        this.playersInArena =  BuildFFASystem.playersInArena;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        Location spawn = new Location(Bukkit.getWorld("world"), 40.700, 60, -20.083);
        spawn.setPitch(0);
        spawn.setYaw(90);

        player.teleport(spawn);

        Scoreboard scoreboard = Bukkit.getServer().getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("BuildFFA", "dummy");

        Team isInArena = scoreboard.registerNewTeam("isInArena");
        isInArena.addEntry("IsArena");
        isInArena.setPrefix("Is in arena: ");
        isInArena.setSuffix("false");

        objective.getScore("IsArena").setScore(3);

        player.setScoreboard(scoreboard);

        Bukkit.getScheduler().runTaskTimer(plugin.getInstance(), (Runnable) () -> {
            if (playersInArena.contains(player.getUniqueId())) {
                isInArena.setSuffix("true");
            } else {
                isInArena.setSuffix("false");
            }
        }, 0, 20);
    }
}
