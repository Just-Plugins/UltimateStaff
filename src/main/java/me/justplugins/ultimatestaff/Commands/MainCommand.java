package me.justplugins.ultimatestaff.Commands;

import me.justplugins.ultimatestaff.GUI.MainGui.MainGui;
import me.justplugins.ultimatestaff.Main;
import me.justplugins.ultimatestaff.Utils.Permissions;
import me.nathans212.baseplugin.CommandSystem.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MainCommand extends Command {
    final Main plugin;
    public MainCommand(Main plugin) {
        super(plugin, "UltimateStaff");
        this.plugin = plugin;
    }

    @Override
    public String Permission() {
        return Permissions.MAIN_COMMAND.getPermission();
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        Player player = (Player) sender;

        new MainGui(plugin,player);

        return false;
    }

}
