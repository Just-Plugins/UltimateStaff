package me.justplugins.ultimatestaff.GUI.StaffGui;

import com.songoda.core.compatibility.CompatibleMaterial;
import com.songoda.core.compatibility.CompatibleSound;
import com.songoda.core.gui.Gui;
import com.songoda.core.gui.GuiManager;
import com.songoda.core.gui.GuiUtils;
import me.justplugins.ultimatestaff.GUI.Reporting.ReportMenu;
import me.justplugins.ultimatestaff.Main;
import me.justplugins.ultimatestaff.Modules.InventoryModules;
import me.justplugins.ultimatestaff.Utils.Permissions;
import me.justplugins.ultimatestaff.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

import static me.justplugins.ultimatestaff.Utils.Utils.getHead;

public class StaffOnlineGui extends Gui {
    final Main plugin;

    public StaffOnlineGui(Main plugin, Player player) {
        this.plugin = plugin;
        setTitle(Utils.Color("&8StaffGui > Online Players"));
        setRows(6);
        setDefaultItem(CompatibleMaterial.AIR.getItem());
        setDefaultSound(CompatibleSound.BLOCK_NOTE_BLOCK_BIT);

        int p = 0;
        for (Player pl : Bukkit.getOnlinePlayers()) {
            if (p == 49) {
                break;
            }
            if (pl.hasPermission(Permissions.STAFF_MEMBER.getPermission())) {
                setButton(p, GuiUtils.createButtonItem(getHead(pl), Utils.Color("&l" + pl.getDisplayName())), ClickType.RIGHT, guiClickEvent -> player.teleport(pl));
                setAction(p, ClickType.LEFT, guiClickEvent -> InventoryModules.seeInventory(player, pl));

                updateItemLore(p,
                        Utils.Color("&9Rank: &f" + pl.getPlayer().getPlayerListName()),
                        Utils.Color("&9Gamemode: &f" + pl.getPlayer().getGameMode().name()),
                        Utils.Color("&9World: &f") + pl.getLocation().getWorld().getName(),
                        Utils.Color("&9Server: &f") + pl.getServer().getServerName(),
                        Utils.Color(""),
                        Utils.Color("&9Ping: &f69ms"),
                        Utils.Color("&9Health: &f" + pl.getHealthScale()),
                        Utils.Color(""),
                        Utils.Color("&cIsStaff: &f" + pl.hasPermission(Permissions.STAFF_MEMBER.getPermission())),
                        Utils.Color("&cStaffMode: &ffalse"),
                        Utils.Color(""),
                        Utils.Color("&7&oRight Click to teleport"),
                        Utils.Color("&7&oLeft Click to look in their inventory.")
                );
                p++;
            }

            setButton(49, GuiUtils.createButtonItem(CompatibleMaterial.ARROW, Utils.Color("&f&lGo Back"),Utils.Color("&7Click to go Back")), (event) -> {
                new GuiManager(plugin).showGUI(player,new MainStaffGui(plugin, player));
            });
            setButton(53, GuiUtils.createButtonItem(CompatibleMaterial.PUMPKIN, Utils.Color("&c&lStaff Only"), Utils.Color("&7&oClick to see everyone")), guiClickEvent -> {
                new GuiManager(plugin).showGUI(player,new PlayersOnlineGui(plugin, player));
            });
        }
    }
}
