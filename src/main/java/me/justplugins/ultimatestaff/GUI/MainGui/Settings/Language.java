package me.justplugins.ultimatestaff.GUI.MainGui.Settings;

import me.justplugins.ultimatestaff.GUI.MainGui.MainGui;
import me.justplugins.ultimatestaff.Main;
import me.justplugins.ultimatestaff.Utils.Utils;
import me.nathans212.baseplugin.gui.GUIBuilder;
import me.nathans212.baseplugin.gui.Gui;
import me.nathans212.baseplugin.gui.GuiUtils;
import me.nathans212.baseplugin.gui.compatibility.CompatibleMaterial;
import org.bukkit.entity.Player;

public class Language extends GUIBuilder{
    final Main plugin;
    public Language(Main plugin, Player player) {
        super(plugin, player);
        this.plugin = plugin;
    }

    @Override
    public String Title() {
        return Utils.Color("&8Settings > Language");
    }

    @Override
    public int Rows() {
        return 5;
    }

    @Override
    public void onOpen(Player player, Gui gui) {

        //Main Content


        //Sub Category's
        setButton(8, GuiUtils.createButtonItem(CompatibleMaterial.CRAFTING_TABLE, Utils.Color("&a&lCustomization"),Utils.Color("&7Click to show Settings")), (event) -> {
            new Customization(plugin, player);
        });

        setButton(26, GuiUtils.createButtonItem(CompatibleMaterial.BOOK, Utils.Color("&a&lLanguage"),Utils.Color("&7Click to show Settings")), (event) -> {
            new Language(plugin, player);
        });

        setButton(44, GuiUtils.createButtonItem(CompatibleMaterial.COMMAND_BLOCK, Utils.Color("&a&lMySQL Database"),Utils.Color("&7Click to show Settings")), (event) -> {
            new MySQLSettings(plugin, player);
        });



        //Go Back Button
        setButton(36, GuiUtils.createButtonItem(CompatibleMaterial.ARROW, Utils.Color("&f&lGo Back"),Utils.Color("&7Click to go Back")), (event) -> {
            new MainGui(plugin, player);
        });
    }
}

