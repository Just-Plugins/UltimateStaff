package me.justplugins.ultimatestaff.Commands;

import com.songoda.core.commands.AbstractCommand;
import com.songoda.core.gui.GuiManager;
import me.justplugins.ultimatestaff.GUI.Reporting.ReportMenu;
import me.justplugins.ultimatestaff.Main;
import me.justplugins.ultimatestaff.Utils.Permissions;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class Reports extends AbstractCommand {
    Main plugin;
    public Reports(Main plugin) {
        super(CommandType.PLAYER_ONLY,"reports");
        this.plugin = plugin;
    }

    @Override
    protected ReturnType runCommand(CommandSender commandSender, String... strings) {
        Player player = (Player) commandSender;
        new GuiManager(plugin).showGUI(player, new ReportMenu(plugin,null));
        return ReturnType.SUCCESS;
    }

    @Override
    protected List<String> onTab(CommandSender commandSender, String... strings) {
        return null;
    }

    @Override
    public String getPermissionNode() {
        return Permissions.REPORT.getPermission();
    }

    @Override
    public String getSyntax() {
        return "/reports";
    }

    @Override
    public String getDescription() {
        return "Opens the reports list";
    }
}
