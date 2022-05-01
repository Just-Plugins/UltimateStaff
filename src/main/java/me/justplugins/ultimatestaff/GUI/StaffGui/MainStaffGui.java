package me.justplugins.ultimatestaff.GUI.StaffGui;

import com.songoda.core.compatibility.CompatibleMaterial;
import com.songoda.core.compatibility.CompatibleSound;
import com.songoda.core.gui.Gui;
import com.songoda.core.gui.GuiManager;
import com.songoda.core.gui.GuiUtils;
import me.justplugins.ultimatestaff.GUI.PunishGUI.ActivePunishments;
import me.justplugins.ultimatestaff.GUI.Reporting.ActiveReportsGUI;
import me.justplugins.ultimatestaff.Main;
import me.justplugins.ultimatestaff.Utils.Permissions;
import me.justplugins.ultimatestaff.Utils.Utils;
import org.bukkit.entity.Player;

public class MainStaffGui extends Gui {
    final Main plugin;

    public MainStaffGui(Main plugin, Player player) {
        this.plugin = plugin;
        setTitle(Utils.Color("&8Staff Menu"));
        setRows(5);
        setDefaultItem(CompatibleMaterial.AIR.getItem());
        setDefaultSound(CompatibleSound.BLOCK_NOTE_BLOCK_BIT);


        //Players Online
        setButton(4, GuiUtils.createButtonItem(CompatibleMaterial.PLAYER_HEAD, Utils.Color("&a&lOnline Players"), Utils.Color("&7Click to see the full list")), (event) -> {
            new GuiManager(plugin).showGUI(player,new PlayersOnlineGui(plugin, player, PlayersOnlineGui.Page.PLAYERS));
        });

        //Staff Settings
        setButton(19, GuiUtils.createButtonItem(CompatibleMaterial.COMPARATOR, Utils.Color("&a&lStaff Settings"), Utils.Color("&7Click to change your\n&7personal settings")), (event) -> {
            new GuiManager(plugin).showGUI(player,new StaffSettings(plugin, player));
        });


        if (player.hasPermission(Permissions.EDIT_PUNISH_TEMPLATES.getPermission())) {
            setButton(30, GuiUtils.createButtonItem(CompatibleMaterial.CHEST, Utils.Color("&a&lManage Reasons"),Utils.Color("&7Click to edit the punish\n&7reason templates")), (event) -> {
                new GuiManager(plugin).showGUI(player,new ManageReasons(plugin, player));
            });
        } else {
            setButton(30, GuiUtils.createButtonItem(CompatibleMaterial.BARRIER, Utils.Color("&a&lManage Reasons"),
                    Utils.Color("&cYou don't have any\n&cpermissions to do this!")), (event) -> {
            });
        }

        //Active Punishments
        setButton(32, GuiUtils.createButtonItem(CompatibleMaterial.DIAMOND_BLOCK, Utils.Color("&a&lActive Punishments"), Utils.Color("&7Click to see active punishments")), guiClickEvent -> {
            ActivePunishments.setState(3);
            new GuiManager(plugin).showGUI(player,new ActivePunishments(plugin, player));
        });

        //Reports
        setButton(25, GuiUtils.createButtonItem(CompatibleMaterial.BOOK, Utils.Color("&a&lReports"), Utils.Color("&7Click to see the full list")), (event) -> {
            new GuiManager(plugin).showGUI(player,new ActiveReportsGUI(plugin, player));
        });
    }
}
