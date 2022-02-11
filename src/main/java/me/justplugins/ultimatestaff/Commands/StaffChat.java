package me.justplugins.ultimatestaff.Commands;

import com.songoda.core.commands.AbstractCommand;
import com.songoda.core.locale.Message;
import me.justplugins.ultimatestaff.Main;
import me.justplugins.ultimatestaff.Utils.Permissions;
import me.justplugins.ultimatestaff.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class StaffChat extends AbstractCommand {
    final Main plugin;

    public StaffChat(Main plugin) {
        super(CommandType.PLAYER_ONLY, "staffchat", "sc");
        this.plugin = plugin;
    }

    @Override
    protected ReturnType runCommand(CommandSender commandSender, String... strings) {
        Player p = (Player) commandSender;
        try {
            if (strings.length == 0) {
                p.sendMessage(Utils.Color(Utils.prefix() + "&fUse /staffchat [message]"));
                return ReturnType.SYNTAX_ERROR;
            }
            String message = String.join(" ", strings);
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
        return null;
    }

    @Override
    protected List<String> onTab(CommandSender commandSender, String... strings) {
        return null;
    }

    @Override
    public String getPermissionNode() {
        return Permissions.STAFF_CHAT.getPermission();
    }

    @Override
    public String getSyntax() {
        return "/staffchat <Message>";
    }

    @Override
    public String getDescription() {
        return "lets you talk in the staffchat";
    }
}
