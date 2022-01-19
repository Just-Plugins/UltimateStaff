package me.justplugins.ultimatestaff.Commands;

import com.songoda.core.commands.AbstractCommand;
import me.justplugins.ultimatestaff.Main;
import me.justplugins.ultimatestaff.Modules.FlyModule;
import me.justplugins.ultimatestaff.Utils.Permissions;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class Fly extends AbstractCommand {
    Main plugin;
    public Fly(Main plugin) {
        super(CommandType.PLAYER_ONLY, "fly");
        this.plugin = plugin;
    }

    @Override
    protected ReturnType runCommand(CommandSender commandSender, String... strings) {
        Player player = (Player) commandSender;
        new FlyModule(plugin).FlyToggle(player);
        return null;
    }

    @Override
    protected List<String> onTab(CommandSender commandSender, String... strings) {
        return null;
    }

    @Override
    public String getPermissionNode() {
        return Permissions.FLY.getPermission();
    }

    @Override
    public String getSyntax() {
        return "/fly";
    }

    @Override
    public String getDescription() {
        return "Lets the player fly!";
    }
}
