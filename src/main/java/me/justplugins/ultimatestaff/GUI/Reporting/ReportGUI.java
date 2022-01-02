package me.justplugins.ultimatestaff.GUI.Reporting;

import me.justplugins.ultimatestaff.Commands.Report;
import me.justplugins.ultimatestaff.Main;
import me.justplugins.ultimatestaff.Modules.Configs.Config;
import me.justplugins.ultimatestaff.Modules.Configs.Reports.ReportManager;
import me.justplugins.ultimatestaff.Modules.Configs.Reports.Reports;
import me.justplugins.ultimatestaff.Utils.Utils;
import me.nathans212.baseplugin.gui.GUIBuilder;
import me.nathans212.baseplugin.gui.Gui;
import me.nathans212.baseplugin.gui.GuiUtils;
import me.nathans212.baseplugin.gui.compatibility.CompatibleMaterial;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static me.justplugins.ultimatestaff.GUI.Reporting.ReportMode.withProof;
import static org.bukkit.Bukkit.getServer;

public class ReportGUI extends GUIBuilder implements Listener {
    final Main plugin;
    ArrayList<String> reasonsSelected = new ArrayList<>();
    int reasons;
    static String output = "null";
    private static final ArrayList<Player> chat_tracking = new ArrayList<>();
    final Date now = new Date();
    final SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

    public ReportGUI(Main plugin, Player player) {
        super(plugin, player);
        this.plugin = plugin;
        getServer().getPluginManager().registerEvents(this, plugin);
    }

    @Override
    public String Title() {
        return Utils.Color("&8Reporting > " + Report.ReportTarget().getDisplayName());
    }

    @Override
    public int Rows() {
        return 5;
    }

    @Override
    public void onOpen(Player player, Gui gui) {
        for (String r : Config.Config.getStringList("Reports.Reasons")) {
            if (reasons == 30) break;

            setButton(reasons, GuiUtils.createButtonItem(CompatibleMaterial.WHITE_STAINED_GLASS_PANE,Utils.Color("&f&l"+Config.Config.getStringList("Reports.Reasons").get(reasons)), Utils.Color("&7Click to add")), guiClickEvent -> {
                if (reasonsSelected.contains(Config.Config.getStringList("Reports.Reasons").get(guiClickEvent.slot))) {
                    updateItem(guiClickEvent.slot,CompatibleMaterial.WHITE_STAINED_GLASS_PANE,Utils.Color("&f&l"+Config.Config.getStringList("Reports.Reasons").get(guiClickEvent.slot)), Utils.Color("&7Click to add"));
                    reasonsSelected.remove(Config.Config.getStringList("Reports.Reasons").get(guiClickEvent.slot));
                } else {
                    updateItem(guiClickEvent.slot,CompatibleMaterial.LIME_STAINED_GLASS_PANE,Utils.Color("&a&l"+Config.Config.getStringList("Reports.Reasons").get(guiClickEvent.slot)), Utils.Color("&7Click to remove"));
                    reasonsSelected.add(Config.Config.getStringList("Reports.Reasons").get(guiClickEvent.slot));
                }
            });
            reasons++;
        }

        setButton(44, GuiUtils.createButtonItem(CompatibleMaterial.EMERALD_BLOCK, Utils.Color("&a&lDone"), Utils.Color("&7Click to report " + Report.ReportTarget().getDisplayName())), guiClickEvent -> {
            ReportManager.createReport(Report.ReportTarget(), Report.ReportTarget().getUniqueId(), Utils.idGen(), reasonsSelected.toString(),getChatOutput(), format.format(now));
            gui.close();
            if (ReportManager.getOpenReports() == 1) {
                Utils.SendStaffMessage("&fThere is &f&l" + ReportManager.getOpenReports() + " &fopen report!");
            } else {
                Utils.SendStaffMessage("&fThere are &f&l" + ReportManager.getOpenReports() + " &fopen reports!");
            }
            player.sendMessage(Utils.Color(Utils.prefix() + "&fThanks for reporting!"));
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 69,2);
        });

        if (withProof) {
            setButton(36, GuiUtils.createButtonItem(CompatibleMaterial.WRITABLE_BOOK, Utils.Color("&lProof"), Utils.Color("&7" + getChatOutput())), guiClickEvent -> {
                chat_tracking.add(player);
                player.sendMessage(Utils.Color(Utils.prefix() + "&fYou can add a link to an image or video of proof that the player is hacking."));
                gui.close();
            });
        } else {
            output = "No proof was added.";
        }

    }

    @EventHandler
    public void onChatEvent(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();

        if (chat_tracking.contains(player)) {
            e.setCancelled(true);
            output = e.getMessage();
            new ReportGUI(plugin,player);
            withProof = true;
        }
    }

    public static String getChatOutput() {
        return output;
    }

    public static void setChatOutput(String set) {
        output = set;
    }
}
