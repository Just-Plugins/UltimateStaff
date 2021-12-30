package me.justplugins.ultimatestaff.Commands;

import me.justplugins.ultimatestaff.Main;
import me.justplugins.ultimatestaff.Modules.InventoryModules;
import me.justplugins.ultimatestaff.Utils.Permissions;
import me.justplugins.ultimatestaff.Utils.Utils;
import me.nathans212.baseplugin.CommandSystem.Command;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InvenSee extends Command {
    final Main plugin;
    public InvenSee(Main plugin) {
        super(plugin, "invensee");
        this.plugin = plugin;
    }

    @Override
    public String Permission() {
        return Permissions.INVENTORY_SEE.getPermission();
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        Player player = (Player) sender;

        if (args.length > 0) {
            Player target = Bukkit.getPlayer(args[0]);
            if (target != null) {
                InventoryModules.seeInventory(player, target);
            } else {
                player.sendMessage(Utils.Color(Utils.prefix() + "&fThat player is not online!"));
            }
        } else {
            player.sendMessage(Utils.Color(Utils.prefix() + "&fUse /invensee [player]"));
        }

        return false;
    }
}
