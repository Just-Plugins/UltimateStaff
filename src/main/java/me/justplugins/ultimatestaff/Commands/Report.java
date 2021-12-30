package me.justplugins.ultimatestaff.Commands;

import me.justplugins.ultimatestaff.GUI.Reporting.ReportMode;
import me.justplugins.ultimatestaff.Main;
import me.justplugins.ultimatestaff.Utils.Permissions;
import me.justplugins.ultimatestaff.Utils.Utils;
import me.nathans212.baseplugin.CommandSystem.Command;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Report extends Command {
    final Main plugin;
    static Player target;
    public Report(Main plugin) {
        super(plugin, "report");
        this.plugin = plugin;
    }

    @Override
    public String Permission() {
        return Permissions.REPORT_PLAYER.getPermission();
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        Player player = (Player) sender;
        if (args.length > 0) {
            target = Bukkit.getServer().getPlayer(args[0]);
            if (target != null) {
                new ReportMode(plugin, player);
            } else {
                player.sendMessage(Utils.Color(Utils.prefix() + "&fThat player is not online!"));
            }
        } else {
            player.sendMessage(Utils.Color(Utils.prefix() + "&fUse /report [player]"));
        }

        return false;
    }

    public static Player ReportTarget() {
        return target;
    }
}
