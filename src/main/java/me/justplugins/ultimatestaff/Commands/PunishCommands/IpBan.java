package me.justplugins.ultimatestaff.Commands.PunishCommands;

import me.justplugins.ultimatestaff.Main;
import me.justplugins.ultimatestaff.Modules.PunishModules;
import me.justplugins.ultimatestaff.Utils.Utils;
import me.nathans212.baseplugin.CommandSystem.Command;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class IpBan extends Command {
    Main plugin;
    public IpBan(Main plugin) {
        super(plugin, "ipban");
    }

    @Override
    public String Permission() {
        return null;
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        Player player = (Player) sender;

        if (args.length > 0) {

            String reason = String.join(" ", args).replace(args[0] + args[1], "");
            Player target = Bukkit.getServer().getPlayer(args[0]);
            String duration = args[1];

            if (target != null) {
                //Ban player
                PunishModules.IPBan(player, target, null, reason);

            } else {

                player.sendMessage(Utils.Color(Utils.prefix() + "&fThat player is not online!"));

            }
        } else {

            player.sendMessage(Utils.Color(Utils.prefix() + "&fUse /ipban [player] [duration] [reason]"));

        }
        return false;
    }
}
