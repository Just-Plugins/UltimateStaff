package me.justplugins.ultimatestaff.Commands.PunishCommands;

import com.songoda.core.commands.AbstractCommand;
import com.songoda.core.gui.GuiManager;
import me.justplugins.ultimatestaff.GUI.PunishGUI.PlayerPunishGui;
import me.justplugins.ultimatestaff.Main;
import me.justplugins.ultimatestaff.Utils.Permissions;
import me.justplugins.ultimatestaff.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;


public class PunishTarget extends AbstractCommand {
    final Main plugin;
    public PunishTarget(Main plugin) {
        super(CommandType.PLAYER_ONLY, "punish", "pun");
        this.plugin = plugin;
    }

    @Override
    protected ReturnType runCommand(CommandSender commandSender, String... strings) {
        Player player = (Player) commandSender;

        if (strings.length > 0) {
            Player target = Bukkit.getServer().getPlayer(strings[0]);
            if (target != null) {
                new GuiManager(plugin).showGUI(player,new PlayerPunishGui(plugin,player,target,null));
                return ReturnType.SUCCESS;
            } else {
                player.sendMessage(Utils.Color(Utils.prefix() + "&fThat player is not online!"));
                return ReturnType.NEEDS_PLAYER;
            }
        } else {
            player.sendMessage(Utils.Color(Utils.prefix() + "&fUse /punish [player]"));
            return ReturnType.SYNTAX_ERROR;
        }
    }

    @Override
    protected List<String> onTab(CommandSender commandSender, String... strings) {
        return null;
    }

    @Override
    public String getPermissionNode() {
        return Permissions.PUNISH_PLAYER.getPermission();
    }

    @Override
    public String getSyntax() {
        return "/punish <Player>";
    }

    @Override
    public String getDescription() {
        return "Punishes a player";
    }
}


