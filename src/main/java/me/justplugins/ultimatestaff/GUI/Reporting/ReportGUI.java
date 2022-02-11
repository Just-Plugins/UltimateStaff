package me.justplugins.ultimatestaff.GUI.Reporting;

import com.songoda.core.compatibility.CompatibleMaterial;
import com.songoda.core.compatibility.CompatibleSound;
import com.songoda.core.gui.Gui;
import com.songoda.core.gui.GuiManager;
import com.songoda.core.gui.GuiUtils;
import com.songoda.core.input.ChatPrompt;
import me.justplugins.ultimatestaff.Commands.Report;
import me.justplugins.ultimatestaff.Main;
import me.justplugins.ultimatestaff.Modules.Configs.Config;
import me.justplugins.ultimatestaff.Modules.Configs.Reports.ReportManager;
import me.justplugins.ultimatestaff.Modules.Configs.Reports.Reports;
import me.justplugins.ultimatestaff.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitScheduler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static me.justplugins.ultimatestaff.GUI.Reporting.ReportMode.withProof;
import static org.bukkit.Bukkit.getServer;

public class ReportGUI extends Gui {
    final Main plugin;
    ArrayList<String> reasonsSelected = new ArrayList<>();
    int reasons;
    String output;

    public ReportGUI(Main plugin, Player player, Player target,Boolean withProof) {
        this.plugin = plugin;
        setTitle(Utils.Color("&8Reporting > " + target.getName()));
        setRows(5);
        setDefaultItem(CompatibleMaterial.AIR.getItem());
        setDefaultSound(CompatibleSound.BLOCK_NOTE_BLOCK_BIT);


        for (String r : Config.Config.getStringList("Reports.Reasons")) {
            if (reasons == 30) break;

            setButton(reasons, GuiUtils.createButtonItem(CompatibleMaterial.WHITE_STAINED_GLASS_PANE, Utils.Color("&f&l" + Config.Config.getStringList("Reports.Reasons").get(reasons)), Utils.Color("&7Click to add")), guiClickEvent -> {
                if (reasonsSelected.contains(Config.Config.getStringList("Reports.Reasons").get(guiClickEvent.slot))) {
                    updateItem(guiClickEvent.slot, CompatibleMaterial.WHITE_STAINED_GLASS_PANE, Utils.Color("&f&l" + Config.Config.getStringList("Reports.Reasons").get(guiClickEvent.slot)), Utils.Color("&7Click to add"));
                    reasonsSelected.remove(Config.Config.getStringList("Reports.Reasons").get(guiClickEvent.slot));
                } else {
                    updateItem(guiClickEvent.slot, CompatibleMaterial.LIME_STAINED_GLASS_PANE, Utils.Color("&a&l" + Config.Config.getStringList("Reports.Reasons").get(guiClickEvent.slot)), Utils.Color("&7Click to remove"));
                    reasonsSelected.add(Config.Config.getStringList("Reports.Reasons").get(guiClickEvent.slot));
                }
            });
            reasons++;
        }

        setButton(44, GuiUtils.createButtonItem(CompatibleMaterial.EMERALD_BLOCK, Utils.Color("&a&lDone"), Utils.Color("&7Click to report " + target.getName())), guiClickEvent -> {
            ReportManager.createReport(target, Utils.idGen(), reasonsSelected.toString(), "");
            close();
            if (ReportManager.getOpenReports() == 1) {
                Utils.SendStaffMessage("&fThere is &f&l" + ReportManager.getOpenReports() + " &fopen report!");
            } else {
                Utils.SendStaffMessage("&fThere are &f&l" + ReportManager.getOpenReports() + " &fopen reports!");
            }
            player.sendMessage(Utils.Color(Utils.prefix() + "&fThanks for reporting!"));
            player.playSound(player.getLocation(), CompatibleSound.BLOCK_NOTE_BLOCK_PLING.getSound(), 69, 2);
        });

        if (withProof) {
            setButton(36, GuiUtils.createButtonItem(CompatibleMaterial.WRITABLE_BOOK, Utils.Color("&lProof"), Utils.Color("&7")), guiClickEvent -> {
                userInput(plugin,this,guiManager,player);
            });
        }
    }


    public void userInput(Main plugin, Gui activeGui, GuiManager guiManager, Player player) {
        if (activeGui != null)
            activeGui.exit();
        BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(plugin, () -> {
            player.sendTitle("Add proof","Add a video of proof",0,15,0);
            try {
                Thread.sleep(15L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            player.sendTitle("Add proof","Type /cancel to cancel!",0,15,0);
            try {
                Thread.sleep(15L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 0L, 20L);

        inventory.setItem(15,GuiUtils.createButtonItem(CompatibleMaterial.PAPER,Utils.Color("&f&lSearch...")));
        ChatPrompt.showPrompt(plugin, player, response -> {
            Bukkit.getScheduler().cancelTasks(plugin);
            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                output = response.getMessage();
                guiManager.showGUI(player, activeGui);
            }, 0L);
        }).setOnCancel(() -> {
            player.sendMessage(Utils.Color("&cCanceled player input!"));
            Bukkit.getScheduler().cancelTasks(plugin);
        });
    }
}
