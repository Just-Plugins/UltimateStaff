package me.justplugins.ultimatestaff.Commands;

import com.songoda.core.commands.AbstractCommand;
import com.songoda.core.gui.GuiManager;
import me.justplugins.ultimatestaff.GUI.StaffGui.MainStaffGui;
import me.justplugins.ultimatestaff.Main;
import me.justplugins.ultimatestaff.Utils.Permissions;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class StaffGui extends AbstractCommand {
    final Main plugin;
    public StaffGui(Main plugin) {
        super(CommandType.PLAYER_ONLY, "staffmenu");
        this.plugin = plugin;
    }

    @Override
    protected ReturnType runCommand(CommandSender commandSender, String... strings) {
        new GuiManager(plugin).showGUI((Player) commandSender,new MainStaffGui(plugin, (Player) commandSender));
        return ReturnType.SUCCESS;
    }

    @Override
    protected List<String> onTab(CommandSender commandSender, String... strings) {
        return null;
    }

    @Override
    public String getPermissionNode() {
        return Permissions.STAFFGUI.getPermission();
    }

    @Override
    public String getSyntax() {
        return "staffmenu";
    }

    @Override
    public String getDescription() {
        return "Opens the StaffMenu";
    }
}
