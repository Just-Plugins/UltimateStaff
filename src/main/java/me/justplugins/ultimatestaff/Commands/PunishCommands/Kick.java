package me.justplugins.ultimatestaff.Commands.PunishCommands;

import com.songoda.core.commands.AbstractCommand;
import me.justplugins.ultimatestaff.Main;
import me.justplugins.ultimatestaff.Modules.PunishModules;
import me.justplugins.ultimatestaff.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Kick extends AbstractCommand {
    final Main plugin;
    public Kick(Main plugin) {
        super(CommandType.CONSOLE_OK, "kick");
        this.plugin = plugin;
    }

    @Override
    protected ReturnType runCommand(CommandSender commandSender, String... strings) {
        Player player = (Player) commandSender;

        if (strings.length > 0) {

            Player target = Bukkit.getServer().getPlayer(strings[0]);

            if (target != null) {

                PunishModules.Kick(player,target,String.join(" ", strings).replace(strings[0], ""));
                return ReturnType.SUCCESS;

            } else {
                return ReturnType.SYNTAX_ERROR;
            }
        } else {
            return ReturnType.SYNTAX_ERROR;
        }
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
        return null;
    }

    @Override
    public String getSyntax() {
        return "/kick <Player> <Reason>";
    }

    @Override
    public String getDescription() {
        return "Kicks a player";
    }
}
