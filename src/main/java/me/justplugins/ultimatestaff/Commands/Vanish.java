package me.justplugins.ultimatestaff.Commands;

import me.justplugins.ultimatestaff.Main;
import me.justplugins.ultimatestaff.Modules.VanishModule;
import me.justplugins.ultimatestaff.Utils.Permissions;
import me.nathans212.baseplugin.CommandSystem.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Vanish extends Command {

    final Main plugin;

    public Vanish(Main plugin) {
        super(plugin, "vanish", "v");
        this.plugin = plugin;
    }

    @Override
    public String Permission() {
        return Permissions.VANSIH.getPermission();
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        Player player = (Player) sender;
        new VanishModule(plugin).VanishToggle(player);
        return false;
    }
}
