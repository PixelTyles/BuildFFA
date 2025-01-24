package de.pixeltyles.buildffasystem.tabcompleter;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class ArenaCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        List<String> results = new ArrayList<>();

        if (args.length == 1) {
            results.add("leave");
            results.add("join");
        }

        return results;
    }
}
