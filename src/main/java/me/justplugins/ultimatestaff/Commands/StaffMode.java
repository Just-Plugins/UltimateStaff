package me.justplugins.ultimatestaff.Commands;

import com.songoda.core.commands.AbstractCommand;
import me.justplugins.ultimatestaff.Main;
import me.justplugins.ultimatestaff.Utils.Permissions;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class StaffMode extends AbstractCommand {
    final Main plugin;
    public StaffMode(Main plugin) {
        super(CommandType.PLAYER_ONLY, "staffmode");
        this.plugin = plugin;
    }

    @Override
    protected ReturnType runCommand(CommandSender commandSender, String... strings) {
        return null;
    }

    @Override
    protected List<String> onTab(CommandSender commandSender, String... strings) {
        return null;
    }

    @Override
    public String getPermissionNode() {
        return Permissions.STAFFMODE.getPermission();
    }

    @Override
    public String getSyntax() {
        return "staffmode";
    }

    @Override
    public String getDescription() {
        return "Set the player into Staff Mode";
    }
}
