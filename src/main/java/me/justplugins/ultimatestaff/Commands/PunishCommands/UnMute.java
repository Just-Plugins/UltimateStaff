package me.justplugins.ultimatestaff.Commands.PunishCommands;

import me.justplugins.ultimatestaff.Main;
import me.justplugins.ultimatestaff.Modules.PunishModules;
import me.justplugins.ultimatestaff.Utils.Utils;
import me.nathans212.baseplugin.CommandSystem.Command;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UnMute extends Command {
    final Main plugin;
    public UnMute(Main plugin) {
        super(plugin, "unmute");
        this.plugin = plugin;
    }

    @Override
    public String Permission() {
        return null;
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        Player player = (Player) sender;

        if (args.length != 0) {

            Player target = Bukkit.getServer().getPlayer(args[0]);
                //Ban player
                PunishModules.unMute(player,target);

        } else {

            player.sendMessage(Utils.Color(Utils.prefix() + "&fUse /unmute [player]"));

        }
        return false;
    }
}