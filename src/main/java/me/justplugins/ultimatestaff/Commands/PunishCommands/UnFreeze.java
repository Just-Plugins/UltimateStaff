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

public class UnFreeze extends AbstractCommand {
    final Main plugin;
    public UnFreeze(Main plugin) {
        super(CommandType.CONSOLE_OK, "unfreeze");
        this.plugin = plugin;
    }

    @Override
    protected ReturnType runCommand(CommandSender commandSender, String... strings) {
        Player player = (Player) commandSender;

        if (strings.length > 0) {
            Player target = Bukkit.getServer().getPlayer(strings[0]);

            if (target != null) {

                PunishModules.unFreeze(player,target);
                return ReturnType.SUCCESS;
            } else {

                player.sendMessage(Utils.Color(Utils.prefix() + "&fThat player is not online!"));
                return ReturnType.NEEDS_PLAYER;
            }
        } else {

            player.sendMessage(Utils.Color(Utils.prefix() + "&fUse /unfreeze [player]"));
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
        return "/unfreeze <Player>";
    }

    @Override
    public String getDescription() {
        return "Unfreezes a player";
    }
}
