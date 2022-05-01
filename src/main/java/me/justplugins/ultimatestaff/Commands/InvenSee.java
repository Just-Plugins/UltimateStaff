package me.justplugins.ultimatestaff.Commands;

import com.songoda.core.commands.AbstractCommand;
import me.justplugins.ultimatestaff.Main;
import me.justplugins.ultimatestaff.Modules.InventoryModules;
import me.justplugins.ultimatestaff.Utils.Permissions;
import me.justplugins.ultimatestaff.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class InvenSee extends AbstractCommand {
    Main plugin;
    public InvenSee(Main plugin) {
        super(CommandType.PLAYER_ONLY,"invensee");
        this.plugin = plugin;
    }

    @Override
    protected ReturnType runCommand(CommandSender commandSender, String... strings) {
        Player player = (Player) commandSender;

        if (strings.length > 0) {
            Player target = Bukkit.getPlayer(strings[0]);
            if (target != null) {
                InventoryModules.seeInventory(player, target);
                return ReturnType.SUCCESS;
            } else {
                return ReturnType.NEEDS_PLAYER;
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
        return Permissions.INVENTORY_SEE.getPermission();
    }

    @Override
    public String getSyntax() {
        return "/invensee <Player>";
    }

    @Override
    public String getDescription() {
        return "Looks inside a players inventory";
    }
}
