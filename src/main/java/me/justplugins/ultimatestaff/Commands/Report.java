package me.justplugins.ultimatestaff.Commands;

import com.songoda.core.commands.AbstractCommand;
import com.songoda.core.gui.GuiManager;
import me.justplugins.ultimatestaff.GUI.Reporting.ReportMode;
import me.justplugins.ultimatestaff.Main;
import me.justplugins.ultimatestaff.Utils.Permissions;
import me.justplugins.ultimatestaff.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Report extends AbstractCommand {
    Main plugin;
    public Report(Main plugin) {
        super(CommandType.PLAYER_ONLY,"report");
        this.plugin = plugin;
    }

    @Override
    protected ReturnType runCommand(CommandSender commandSender, String... strings) {
        Player player = (Player) commandSender;

        if (strings.length > 0) {
            if (Bukkit.getServer().getPlayer(strings[0]) != null) {
                new GuiManager(plugin).showGUI(player,new ReportMode(plugin, player,Bukkit.getServer().getPlayer(strings[0])));
                return ReturnType.SUCCESS;
            } else {
                player.sendMessage(Utils.prefix() + Utils.Color("&cThat username does not exist, or the user is not online!"));
                return ReturnType.FAILURE;
            }
        } else {
            player.sendMessage(Utils.Color(Utils.prefix() + "&fUse " + ReturnType.SYNTAX_ERROR));
        }

        return ReturnType.FAILURE;
    }

    @Override
    protected List<String> onTab(CommandSender commandSender, String... strings) {
        ArrayList<String> players = new ArrayList<>();
        for (Player pl : Bukkit.getOnlinePlayers()) {
            players.add(pl.getName());
            break;
        }
        return players;
    }

    @Override
    public String getPermissionNode() {
        return Permissions.REPORT_PLAYER.getPermission();
    }

    @Override
    public String getSyntax() {
        return "/report <Player>";
    }

    @Override
    public String getDescription() {
        return "Reports a player";
    }
}
