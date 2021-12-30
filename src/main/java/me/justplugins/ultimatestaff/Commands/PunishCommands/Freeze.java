package me.justplugins.ultimatestaff.Commands.PunishCommands;

import me.justplugins.ultimatestaff.Main;
import me.justplugins.ultimatestaff.Modules.PunishModules;
import me.justplugins.ultimatestaff.Utils.Utils;
import me.nathans212.baseplugin.CommandSystem.Command;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Freeze extends Command {
    final Main plugin;
    public Freeze(Main plugin) {
        super(plugin, "freeze");
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

            Player target = Bukkit.getServer().getPlayer(args[0]);

            if (target != null) {

                String reason = String.join(" ", args).replace(args[0], "");

                PunishModules.Freeze(player,target,reason);

            } else {

                player.sendMessage(Utils.Color(Utils.prefix() + "&fThat player is not online!"));

            }
        } else {

            player.sendMessage(Utils.Color(Utils.prefix() + "&fUse /freeze [player]"));

        }
        return false;
    }
}
