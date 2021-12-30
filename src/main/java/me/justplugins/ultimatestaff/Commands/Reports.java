package me.justplugins.ultimatestaff.Commands;

import me.justplugins.ultimatestaff.Main;
import me.nathans212.baseplugin.CommandSystem.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Reports extends Command {
    final Main plugin;
    public Reports(Main plugin) {
        super(plugin,"reports");
        this.plugin = plugin;
    }

    @Override
    public String Permission() {
        return null;
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        new me.justplugins.ultimatestaff.GUI.Reporting.Reports(plugin, (Player) sender);
        return false;
    }
}
