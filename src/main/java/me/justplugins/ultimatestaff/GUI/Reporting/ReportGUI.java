package me.justplugins.ultimatestaff.GUI.Reporting;

import me.justplugins.ultimatestaff.Commands.Report;
import me.justplugins.ultimatestaff.Main;
import me.justplugins.ultimatestaff.Modules.Configs.Config;
import me.justplugins.ultimatestaff.Modules.Configs.Reports.ReportManager;
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
            if (reasons == 30) {
                break;
            }

            setButton(reasons, GuiUtils.createButtonItem(CompatibleMaterial.PAPER,Config.Config.getStringList("Reports.Reasons").get(reasons), "Click to execute"), guiClickEvent -> {

                ReportManager.createReport(Report.ReportTarget(), Report.ReportTarget().getUniqueId(), Utils.idGen(), Config.Config.getStringList("Reports.Reasons").get(guiClickEvent.slot),getChatOutput(), format.format(now));
                gui.close();
                player.sendMessage(Utils.Color("&fThanks for reporting!"));
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 69,2);
            });
            reasons++;
        }

        if (withProof) {
            setButton(40, GuiUtils.createButtonItem(CompatibleMaterial.WRITABLE_BOOK, Utils.Color("&lProof"), Utils.Color("&7" + getChatOutput())), guiClickEvent -> {
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
