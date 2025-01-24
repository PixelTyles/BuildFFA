package de.pixeltyles.buildffasystem.commands;

import de.pixeltyles.buildffasystem.BuildFFASystem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class ArenaCommand implements CommandExecutor {
    private BuildFFASystem plugin;
    private List playersInArena;

    public ArenaCommand(BuildFFASystem main, List<UUID> players) {
        this.plugin = main;
        this.playersInArena =  BuildFFASystem.playersInArena;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof Player) {
            if (args.length == 1) {
                Player player = (Player) commandSender;

                if (args[0].equalsIgnoreCase("leave")) {
                    if (playersInArena.contains(player.getUniqueId())) {
                        playersInArena.remove(player.getUniqueId());

                        Location spawn = new Location(Bukkit.getWorld("world"), 40.700, 60, -20.083);
                        spawn.setPitch(0);
                        spawn.setYaw(90);

                        player.teleport(spawn);

                        player.sendMessage("Du wurdest zum Spawn tpt.");
                    } else {
                        player.sendMessage("Du bist nicht in der Arena");
                    }
                } else if (args[0].equalsIgnoreCase("join")) {
                    if (playersInArena.contains(player.getUniqueId())) {
                        player.sendMessage("Du bist bereits in der Arena.");
                    } else {
                        playersInArena.add(player.getUniqueId());

                        Location arena = new Location(Bukkit.getWorld("world"), 45.350, 79, -62.467);
                        arena.setPitch(0);
                        arena.setYaw(90);

                        player.teleport(arena);
                        player.sendMessage("Du bist nun in der Arena");
                        player.sendMessage(ChatColor.RED + "Falsche Anwendung!");
                    }
                } else {
                    commandSender.sendMessage(ChatColor.RED + "Falsche Anwendung!");
                }
            }

        }
            return true;
        }
    }
