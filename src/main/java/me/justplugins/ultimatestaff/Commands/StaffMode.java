package me.justplugins.ultimatestaff.Commands;

import me.justplugins.ultimatestaff.Main;
import me.justplugins.ultimatestaff.Utils.Permissions;
import me.nathans212.baseplugin.CommandSystem.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StaffMode extends Command {
    final Main plugin;
    public StaffMode(Main plugin) {
        super(plugin, "staffmode");
        this.plugin = plugin;
    }

    @Override
    public String Permission() {
        return Permissions.STAFFMODE.getPermission();
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        Player p = (Player) sender;

        return false;
    }
}
