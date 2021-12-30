package me.justplugins.ultimatestaff.Commands;

import me.justplugins.ultimatestaff.Main;
import me.justplugins.ultimatestaff.Utils.Permissions;
import me.justplugins.ultimatestaff.Utils.Utils;
import me.nathans212.baseplugin.CommandSystem.Command;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StaffChat extends Command {
    final Main plugin;

    public StaffChat(Main plugin) {
        super(plugin, "staffchat", "sc");
        this.plugin = plugin;
    }

    @Override
    public String Permission() {
        return Permissions.STAFF_CHAT.getPermission();
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        Player p = (Player) sender;
        try {
            if (args.length == 0) {
                p.sendMessage(Utils.Color(Utils.prefix() + "&fUse /staffchat [message]"));
                return false;
            }
            String message = String.join(" ", args);
            if (p.hasPermission(Permissions.STAFF_CHAT.getPermission())) {
                for (Player player: Bukkit.getOnlinePlayers()) {
                    if(player.hasPermission(Permissions.STAFFMODE.getPermission())){
                        player.sendMessage(Utils.Color("&a&lStaffChat &b" + player.getName() + " &7: &f" + message));
                    }
                }
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }
}
