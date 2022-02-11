package me.justplugins.ultimatestaff.GUI.Reporting;

import com.songoda.core.compatibility.CompatibleMaterial;
import com.songoda.core.compatibility.CompatibleSound;
import com.songoda.core.gui.Gui;
import com.songoda.core.gui.GuiManager;
import com.songoda.core.gui.GuiUtils;
import com.songoda.core.input.ChatPrompt;
import me.justplugins.ultimatestaff.Commands.Report;
import me.justplugins.ultimatestaff.Main;
import me.justplugins.ultimatestaff.Modules.Configs.Reports.ReportManager;
import me.justplugins.ultimatestaff.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import static me.justplugins.ultimatestaff.Utils.Utils.getHead;
import static org.bukkit.Bukkit.getServer;

public class ReportMode extends Gui {
    final Main plugin;
    static Boolean withProof;
    public ReportMode(Main plugin, Player player, Player target) {
        this.plugin = plugin;
        setTitle("Setting Reports");
        setRows(6);
        setDefaultItem(CompatibleMaterial.AIR.getItem());
        setDefaultSound(CompatibleSound.BLOCK_NOTE_BLOCK_BIT);

        setButton(4, GuiUtils.createButtonItem(getHead(target), Utils.Color("&l" + target)), guiClickEvent -> {
        });

        setButton(11, GuiUtils.createButtonItem(CompatibleMaterial.BOOK, Utils.Color("&a&lReport without Proof"), Utils.Color("&7Click to add Reasons")), guiClickEvent -> {
            new GuiManager(plugin).showGUI(player,new ReportGUI(plugin, player,target,false));
        });

        setButton(15, GuiUtils.createButtonItem(CompatibleMaterial.KNOWLEDGE_BOOK, Utils.Color("&a&lReport with Proof"), Utils.Color("&7Click to add Reasons and a link to a video\n&7 of proof that the player is hacking!")), guiClickEvent -> {
            new GuiManager(plugin).showGUI(player,new ReportGUI(plugin, player,target,true));
        });

    }

}
