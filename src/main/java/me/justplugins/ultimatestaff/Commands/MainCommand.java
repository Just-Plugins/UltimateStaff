package me.justplugins.ultimatestaff.Commands;

import com.songoda.core.commands.AbstractCommand;
import me.justplugins.ultimatestaff.GUI.MainGui.MainGui;
import me.justplugins.ultimatestaff.Main;
import me.justplugins.ultimatestaff.Utils.Permissions;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class MainCommand extends AbstractCommand {
    Main plugin;
    public MainCommand(Main plugin) {
        super(CommandType.PLAYER_ONLY,true,"ultimatestaff");
        this.plugin = plugin;
    }

    @Override
    protected ReturnType runCommand(CommandSender commandSender, String... strings) {
        Player player = (Player) commandSender;
        new MainGui(plugin,player);
        return null;
    }

    @Override
    protected List<String> onTab(CommandSender commandSender, String... strings) {
        return null;
    }

    @Override
    public String getPermissionNode() {
        return Permissions.MAIN_COMMAND.getPermission();
    }

    @Override
    public String getSyntax() {
        return "/ultimatestaff";
    }

    @Override
    public String getDescription() {
        return "Opens the main menu for UltimateStaff";
    }
}
