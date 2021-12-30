package me.justplugins.ultimatestaff.Commands.PunishCommands;

import me.justplugins.ultimatestaff.Main;
import me.justplugins.ultimatestaff.Modules.PunishModules;
import me.justplugins.ultimatestaff.Utils.Utils;
import me.nathans212.baseplugin.CommandSystem.Command;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Kick extends Command {
    final Main plugin;
    public Kick(Main plugin) {
        super(plugin, "kick");
        this.plugin = plugin;
    }

    @Override
    public String Permission() {
        return null;
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        Player player = (Player) sender;

        if (args.length > 0) {

            String reason = String.join(" ", args).replace(args[0], "");
            Player target = Bukkit.getServer().getPlayer(args[0]);

            if (target != null) {

                PunishModules.Kick(player,target,reason);

            } else {

                player.sendMessage(Utils.Color(Utils.prefix() + "&fThat player is not online!"));

            }
        } else {

            player.sendMessage(Utils.Color(Utils.prefix() + "&fUse /kick [player] [reason]"));

        }
        return false;
    }
}
