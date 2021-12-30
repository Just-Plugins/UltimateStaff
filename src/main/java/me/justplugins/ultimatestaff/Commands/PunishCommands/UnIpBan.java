package me.justplugins.ultimatestaff.Commands.PunishCommands;

import me.justplugins.ultimatestaff.Main;
import me.justplugins.ultimatestaff.Modules.PunishModules;
import me.justplugins.ultimatestaff.Utils.Utils;
import me.nathans212.baseplugin.CommandSystem.Command;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UnIpBan extends Command {
    final Main plugin;
    public UnIpBan(Main plugin) {
        super(plugin, "unipban");
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

            OfflinePlayer target = Bukkit.getServer().getOfflinePlayer(args[0]);

            if (target != null) {
                //unIpBan player
                PunishModules.unIpBan(player,target);

            } else {

                player.sendMessage(Utils.Color(Utils.prefix() + "&fThat player is not online!"));

            }
        } else {

            player.sendMessage(Utils.Color(Utils.prefix() + "&fUse /unipban [player]"));

        }
        return false;
    }
}
