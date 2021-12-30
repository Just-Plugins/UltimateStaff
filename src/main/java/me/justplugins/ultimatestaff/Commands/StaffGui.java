package me.justplugins.ultimatestaff.Commands;

import me.justplugins.ultimatestaff.GUI.StaffGui.MainStaffGui;
import me.justplugins.ultimatestaff.Main;
import me.justplugins.ultimatestaff.Utils.Permissions;
import me.nathans212.baseplugin.CommandSystem.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StaffGui extends Command {
    final Main plugin;
    public StaffGui(Main plugin) {
        super(plugin, "staffgui");
        this.plugin = plugin;
    }

    @Override
    public String Permission() {
        return Permissions.STAFFGUI.getPermission();
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        Player player = (Player) sender;

        new MainStaffGui(plugin, player);


        return false;
    }
}
