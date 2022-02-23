package me.justplugins.ultimatestaff.GUI.Reporting;


import com.songoda.core.compatibility.CompatibleMaterial;
import com.songoda.core.compatibility.CompatibleSound;
import com.songoda.core.gui.Gui;
import com.songoda.core.gui.GuiManager;
import com.songoda.core.gui.GuiUtils;
import me.justplugins.ultimatestaff.GUI.PunishGUI.PlayerPunishGui;
import me.justplugins.ultimatestaff.GUI.StaffGui.MainStaffGui;
import me.justplugins.ultimatestaff.Main;
import me.justplugins.ultimatestaff.Modules.Configs.Reports.ReportManager;
import me.justplugins.ultimatestaff.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

import java.util.Collections;
import java.util.Objects;

public class ActiveReportsGUI extends Gui {
    Main plugin;
    int reports;

    public ActiveReportsGUI(Main plugin, Player player) {
        this.plugin = plugin;
        setTitle(Utils.Color("&8StaffMenu > Reports"));
        setRows(6);
        setDefaultItem(CompatibleMaterial.AIR.getItem());
        setDefaultSound(CompatibleSound.BLOCK_NOTE_BLOCK_BIT);

        for (String a : ReportManager.getPlayers()) {
            if (reports == 49) break;
            Player pl = Bukkit.getPlayer(ReportManager.getPlayers().get(reports));

            setButton(reports, GuiUtils.createButtonItem(Utils.getHead(pl), Utils.Color("&r&l" + pl.getDisplayName()),""), guiClickEvent -> {});

            setAction(reports, ClickType.LEFT, guiClickEvent -> {
                Player target = Bukkit.getPlayer(ReportManager.getPlayers().get(guiClickEvent.slot));
                new GuiManager(plugin).showGUI(player,new PlayerPunishGui(plugin,player,target, Collections.singletonList(ReportManager.ReportSearch(ReportManager.getIDs().get(guiClickEvent.slot)).get().getReason())));
                ReportManager.CloseReport(ReportManager.getIDs().get(guiClickEvent.slot));
            });
            setAction(reports, ClickType.DROP, guiClickEvent -> {
                player.sendMessage(Utils.Color(Utils.prefix() + "&aReport closed!"));
                ReportManager.CloseReport(ReportManager.getIDs().get(guiClickEvent.slot));
                new GuiManager(plugin).showGUI(player,new ActiveReportsGUI(plugin, player));
            });

            updateItemLore(reports,
                    Utils.Color("&9UUID: &f" + ReportManager.getUUIDs().get(reports).toString()),
                    Utils.Color("&9ID: &f" + ReportManager.getIDs().get(reports).toString()),
                    Utils.Color("&9Reason: &f" + ReportManager.getReasons().get(reports)),
                    Utils.Color("&9Proof: &f" + ReportManager.getProofs().get(reports)),

                    Utils.Color(""),
                    Utils.Color("&cReport Date: &f" + ReportManager.getDates().get(reports)),

                    Utils.Color(""),
                    Utils.Color("&7&oQ to Remove the report."),
                    Utils.Color("&7&oLeft Click to Punish the player.")
            );
            reports++;
        }

        //Go Back Button
        setButton(49, GuiUtils.createButtonItem(CompatibleMaterial.ARROW, Utils.Color("&f&lGo Back"),Utils.Color("&7Click to go Back")), (event) -> {
            new GuiManager(plugin).showGUI(player,new MainStaffGui(plugin, player));
        });
        setButton(53, GuiUtils.createButtonItem(CompatibleMaterial.COMPASS, Utils.Color("&9&lSearch Player"), Utils.Color("&7&oClick to search with Report ID")), guiClickEvent -> {
            player.sendMessage("Not Setted up yet!");
        });
    }
}
