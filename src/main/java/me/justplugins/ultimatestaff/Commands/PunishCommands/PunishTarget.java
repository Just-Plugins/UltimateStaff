package me.justplugins.ultimatestaff.Commands.PunishCommands;

import me.justplugins.ultimatestaff.GUI.PunishGUI.PlayerPunishGui;
import me.justplugins.ultimatestaff.Main;
import me.justplugins.ultimatestaff.Utils.Permissions;
import me.justplugins.ultimatestaff.Utils.Utils;
import me.nathans212.baseplugin.CommandSystem.Command;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PunishTarget extends Command {
    private static Player target;
    final Main plugin;
    public PunishTarget(Main plugin) {
        super(plugin, "punish", "pun");
        this.plugin = plugin;
    }

    @Override
    public String Permission() {
        return Permissions.PUNISH_PLAYER.getPermission();
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        Player player = (Player) sender;

        if (args.length > 0) {
            target = Bukkit.getServer().getPlayer(args[0]);
            if (target != null) {
                //PlayerPunishGui.setChatOutput("&7Click to set the reason");
                new PlayerPunishGui(plugin, player);
            } else {
                player.sendMessage(Utils.Color(Utils.prefix() + "&fThat player is not online!"));
            }
        } else {
            player.sendMessage(Utils.Color(Utils.prefix() + "&fUse /punish [player]"));
        }

        return false;
    }

    public static Player punishtarget() {
        return target;
    }
    public static void setPunishTarget(Player player) {
        target = player;
    }
}


