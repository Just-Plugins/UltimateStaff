package me.justplugins.ultimatestaff.GUI.MainGui.Settings;

import me.justplugins.ultimatestaff.GUI.MainGui.MainGui;
import me.justplugins.ultimatestaff.Main;
import me.justplugins.ultimatestaff.Utils.Utils;
import me.nathans212.baseplugin.gui.GUIBuilder;
import me.nathans212.baseplugin.gui.Gui;
import me.nathans212.baseplugin.gui.GuiUtils;
import me.nathans212.baseplugin.gui.compatibility.CompatibleMaterial;
import org.bukkit.entity.Player;

public class Customization extends GUIBuilder {
    final Main plugin;
    public Customization(Main plugin, Player player) {
        super(plugin, player);
        this.plugin = plugin;
    }

    @Override
    public String Title() {
        return Utils.Color("&8Settings > Customization");
    }

    @Override
    public int Rows() {
        return 5;
    }

    @Override
    public void onOpen(Player player, Gui gui) {

        //Main Content
        setButton(22, GuiUtils.createButtonItem(CompatibleMaterial.NAME_TAG, Utils.Color("&f&lPrefix"),Utils.Color("&9Left Click &7to change the Prefix\n&r\n&9Prefix: " + Utils.prefix())), (event) -> {
            player.sendMessage("Not setted up yet :(");
        });


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
