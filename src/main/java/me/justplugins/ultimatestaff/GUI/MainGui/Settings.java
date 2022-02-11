package me.justplugins.ultimatestaff.GUI.MainGui;

import com.songoda.core.compatibility.CompatibleMaterial;
import com.songoda.core.compatibility.CompatibleSound;
import com.songoda.core.gui.DoubleGui;
import com.songoda.core.gui.Gui;
import com.songoda.core.gui.GuiManager;
import com.songoda.core.gui.GuiUtils;
import me.justplugins.ultimatestaff.Main;
import me.justplugins.ultimatestaff.Utils.GuiPages;
import me.justplugins.ultimatestaff.Utils.Utils;
import org.bukkit.entity.Player;

public class Settings extends Gui {
    Main plugin;
    Player player;
    public Settings (Main plugin, Player player, SettingsPages page) {
        this.plugin = plugin;
        this.player = player;
        setTitle(Utils.Color("&8Settings > Customization"));
        setRows(5);
        setDefaultItem(CompatibleMaterial.AIR.getItem());
        setDefaultSound(CompatibleSound.BLOCK_NOTE_BLOCK_BIT);


        //Sub Category's
        setButton(8, GuiUtils.createButtonItem(CompatibleMaterial.CRAFTING_TABLE, Utils.Color("&a&lCustomization"),Utils.Color("&7Click to show Settings")), (event) -> {
            new GuiManager(plugin).showGUI(player,new Settings(plugin, player,SettingsPages.CUSTOMIZATION_PAGE));
        });

        setButton(26, GuiUtils.createButtonItem(CompatibleMaterial.BOOK, Utils.Color("&a&lLanguage"),Utils.Color("&7Click to show Settings")), (event) -> {
            new GuiManager(plugin).showGUI(player,new Settings(plugin, player,SettingsPages.LANGUAGE));
        });

        setButton(44, GuiUtils.createButtonItem(CompatibleMaterial.COMMAND_BLOCK, Utils.Color("&a&lMySQL Database"),Utils.Color("&7Click to show Settings")), (event) -> {
            new GuiManager(plugin).showGUI(player,new Settings(plugin, player,SettingsPages.MYSQL_PAGE));
        });

        //Go Back Button
        setButton(36, GuiUtils.createButtonItem(CompatibleMaterial.ARROW, Utils.Color("&f&lGo Back"),Utils.Color("&7Click to go Back")), (event) -> {
            new GuiManager(plugin).showGUI(player,new MainGui(plugin, player));
        });
        if (page.equals(SettingsPages.CUSTOMIZATION_PAGE)) page1();
        if (page.equals(SettingsPages.LANGUAGE)) page2();
        if (page.equals(SettingsPages.MYSQL_PAGE)) page3();

    }


    private void page1() {
        //Customization
        setButton(22, GuiUtils.createButtonItem(CompatibleMaterial.NAME_TAG, Utils.Color("&f&lPrefix"),Utils.Color("&9Left Click &7to change the Prefix\n&r\n&9Prefix: " + Utils.prefix())), (event) -> {
            player.sendMessage("Not setted up yet :(");
        });
    }

    private void page2() {

    }

    private void page3() {

    }

    public enum SettingsPages {
        MYSQL_PAGE,
        CUSTOMIZATION_PAGE,
        LANGUAGE
    }
}
