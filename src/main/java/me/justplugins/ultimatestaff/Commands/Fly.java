package me.justplugins.ultimatestaff.Commands;

import me.justplugins.ultimatestaff.Main;
import me.justplugins.ultimatestaff.Modules.FlyModule;
import me.justplugins.ultimatestaff.Utils.Permissions;
import me.nathans212.baseplugin.CommandSystem.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Fly extends Command {
    final Main plugin;

    public Fly(Main plugin) {
        super(plugin, "fly");
        this.plugin = plugin;
    }

    @Override
    public String Permission() {
        return Permissions.FLY.getPermission();
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        Player player = (Player) sender;
        new FlyModule(plugin).FlyToggle(player);
        return false;
    }
}
