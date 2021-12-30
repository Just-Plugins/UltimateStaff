package me.justplugins.ultimatestaff.GUI.Reporting;

import me.justplugins.ultimatestaff.Commands.PunishCommands.PunishTarget;
import me.justplugins.ultimatestaff.GUI.PunishGUI.PlayerPunishGui;
import me.justplugins.ultimatestaff.GUI.StaffGui.MainStaffGui;
import me.justplugins.ultimatestaff.Main;
import me.justplugins.ultimatestaff.Modules.Configs.Reports.ReportManager;
import me.justplugins.ultimatestaff.Utils.Utils;
import me.nathans212.baseplugin.gui.GUIBuilder;
import me.nathans212.baseplugin.gui.Gui;
import me.nathans212.baseplugin.gui.GuiUtils;
import me.nathans212.baseplugin.gui.compatibility.CompatibleMaterial;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public class Reports extends GUIBuilder {
    final Main plugin;
    int reports;

    public Reports(Main plugin, Player player) {
        super(plugin, player);
        this.plugin = plugin;
    }

    @Override
    public String Title() {
        return Utils.Color("&8StaffGui > Reports");
    }

    @Override
    public int Rows() {
        return 6;
    }

    @Override
    public void onOpen(Player player, Gui gui) {
        for (String a : ReportManager.getPlayers()) {
            if (reports == 49) {
                break;
            }
            Player pl = Bukkit.getPlayer(ReportManager.getPlayers().get(reports));

            setButton(reports, GuiUtils.createButtonItem(Utils.getHead(pl), Utils.Color("&r&l" + pl.getDisplayName()),""), guiClickEvent -> {});

            setAction(reports, ClickType.LEFT, guiClickEvent1 -> {
                PunishTarget.setPunishTarget(pl);
                new PlayerPunishGui(plugin,player);
                ReportManager.CloseReport(ReportManager.getIDs().get(guiClickEvent1.slot));
            });
            setAction(reports, ClickType.CONTROL_DROP, guiClickEvent1 -> {
                ReportManager.CloseReport(ReportManager.getIDs().get(guiClickEvent1.slot));
                new Reports(plugin,player);
            });

            updateItemLore(reports,
                    Utils.Color("&9UUID: &f" + ReportManager.getUUIDs().get(reports).toString()),
                    Utils.Color("&9ID: &f" + ReportManager.getIDs().get(reports).toString()),
                    Utils.Color("&9Reason: &f" + ReportManager.getReasons().get(reports)),
                    Utils.Color("&9Proof: &f" + ReportManager.getProofs().get(reports)),

                    Utils.Color(""),
                    Utils.Color("&cReport Date: &f" + ReportManager.getDates().get(reports)),

                    Utils.Color(""),
                    Utils.Color("&7&oCTRL Q to Remove the report."),
                    Utils.Color("&7&oLeft Click to Punish the player.")
            );
            reports++;
        }

        //Go Back Button
        setButton(49, GuiUtils.createButtonItem(CompatibleMaterial.ARROW, Utils.Color("&f&lGo Back"),Utils.Color("&7Click to go Back")), (event) -> {
            new MainStaffGui(plugin, player);
        });
        setButton(53, GuiUtils.createButtonItem(CompatibleMaterial.COMPASS, Utils.Color("&9&lSearch Player"), Utils.Color("&7&oClick to search with Report ID")), guiClickEvent -> {
            player.sendMessage("Not Setted up yet!");
        });
    }
}
