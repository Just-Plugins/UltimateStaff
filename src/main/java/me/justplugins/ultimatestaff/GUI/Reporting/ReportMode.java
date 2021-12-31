package me.justplugins.ultimatestaff.GUI.Reporting;

import me.justplugins.ultimatestaff.Commands.Report;
import me.justplugins.ultimatestaff.Main;
import me.justplugins.ultimatestaff.Utils.Utils;
import me.nathans212.baseplugin.gui.GUIBuilder;
import me.nathans212.baseplugin.gui.Gui;
import me.nathans212.baseplugin.gui.GuiUtils;
import me.nathans212.baseplugin.gui.compatibility.CompatibleMaterial;
import org.bukkit.entity.Player;

import static me.justplugins.ultimatestaff.Utils.Utils.getHead;

public class ReportMode extends GUIBuilder {
    final Main plugin;
    static Boolean withProof;
    public ReportMode(Main plugin, Player player) {
        super(plugin, player);
        this.plugin = plugin;
    }

    @Override
    public String Title() {
        return Utils.Color("&8Reporting > " + Report.ReportTarget().getDisplayName());
    }

    @Override
    public int Rows() {
        return 3;
    }

    @Override
    public void onOpen(Player player, Gui gui) {

        ReportGUI.setChatOutput("&7Click to add proof");

        setButton(4, GuiUtils.createButtonItem(getHead(Report.ReportTarget()), Utils.Color("&l" + Report.ReportTarget().getDisplayName())), guiClickEvent -> {
        });

        setButton(11, GuiUtils.createButtonItem(CompatibleMaterial.BOOK, Utils.Color("&a&lReport without Proof"), Utils.Color("&7Click to add Reasons")), guiClickEvent -> {
            withProof = false;
            new ReportGUI(plugin, player);
        });

        setButton(15, GuiUtils.createButtonItem(CompatibleMaterial.KNOWLEDGE_BOOK, Utils.Color("&a&lReport with Proof"), Utils.Color("&7Click to add Reasons and a link to a video\n&7 of proof that the player is hacking!")), guiClickEvent -> {
            withProof = true;
            new ReportGUI(plugin, player);
        });

    }
}
