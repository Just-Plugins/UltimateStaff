package me.justplugins.ultimatestaff.GUI.StaffGui;

import me.justplugins.ultimatestaff.GUI.PunishGUI.ActivePunishments;
import me.justplugins.ultimatestaff.GUI.Reporting.Reports;
import me.justplugins.ultimatestaff.Main;
import me.justplugins.ultimatestaff.Utils.Permissions;
import me.justplugins.ultimatestaff.Utils.Utils;
import me.nathans212.baseplugin.gui.GUIBuilder;
import me.nathans212.baseplugin.gui.Gui;
import me.nathans212.baseplugin.gui.GuiUtils;
import me.nathans212.baseplugin.gui.compatibility.CompatibleMaterial;
import org.bukkit.entity.Player;

public class MainStaffGui extends GUIBuilder {
    final Main plugin;

    public MainStaffGui(Main plugin, Player player) {
        super(plugin, player);
        this.plugin = plugin;
    }

    @Override
    public String Title() {
        return Utils.Color("&8StaffGui");
    }

    @Override
    public int Rows() {
        return 5;
    }

    @Override
    public void onOpen(Player player, Gui gui) {

        //Players Online
        setButton(4, GuiUtils.createButtonItem(CompatibleMaterial.PLAYER_HEAD, Utils.Color("&a&lOnline Players"), Utils.Color("&7Click to see the full list")), (event) -> {
            new PlayersOnlineGui(plugin, player);
        });

        //Staff Settings
        setButton(19, GuiUtils.createButtonItem(CompatibleMaterial.COMPARATOR, Utils.Color("&a&lStaff Settings"), Utils.Color("&7Click to change your\n&7personal settings")), (event) -> {
            new StaffSettings(plugin, player);
        });


        if (player.hasPermission(Permissions.EDIT_PUNISH_TEMPLATES.getPermission())) {
            setButton(30, GuiUtils.createButtonItem(CompatibleMaterial.CHEST, Utils.Color("&a&lManage Reasons"),Utils.Color("&7Click to edit the punish\n&7reason templates")), (event) -> {
                new ManageReasons(plugin, player);
            });
        } else {
            setButton(30, GuiUtils.createButtonItem(CompatibleMaterial.BARRIER, Utils.Color("&a&lManage Reasons"),
                    Utils.Color("&cYou don't have any\n&cpermissions to do this!")), (event) -> {
            });
        }

        //Active Punishments
        setButton(32, GuiUtils.createButtonItem(CompatibleMaterial.DIAMOND_BLOCK, Utils.Color("&a&lActive Punishments"), Utils.Color("&7Click to see active punishments")), guiClickEvent -> {
            ActivePunishments.setState(3);
            new ActivePunishments(plugin,player);
        });

        //Reports
        setButton(25, GuiUtils.createButtonItem(CompatibleMaterial.BOOK, Utils.Color("&a&lReports"), Utils.Color("&7Click to see the full list")), (event) -> {
            new Reports(plugin,player);
        });

    }
}
