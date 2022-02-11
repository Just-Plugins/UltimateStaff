package me.justplugins.ultimatestaff.GUI.MainGui;

import com.songoda.core.compatibility.CompatibleMaterial;
import com.songoda.core.compatibility.CompatibleSound;
import com.songoda.core.gui.Gui;
import com.songoda.core.gui.GuiManager;
import com.songoda.core.gui.GuiUtils;
import me.justplugins.ultimatestaff.GUI.StaffGui.MainStaffGui;
import me.justplugins.ultimatestaff.Main;
import me.justplugins.ultimatestaff.Utils.GuiPages;
import me.justplugins.ultimatestaff.Utils.Utils;
import org.bukkit.entity.Player;

public class MainGui extends Gui {
    final Main plugin;

    public MainGui(Main plugin, Player player) {
        this.plugin = plugin;
        setTitle(Utils.Color("&8UltimateStaff"));
        setRows(5);
        setDefaultItem(CompatibleMaterial.AIR.getItem());
        setDefaultSound(CompatibleSound.BLOCK_NOTE_BLOCK_BIT);


        setButton(11, GuiUtils.createButtonItem(CompatibleMaterial.CHEST, Utils.Color("&a&lStaff Gui"), Utils.Color("&7Click to show the StaffGUI")), guiClickEvent -> {
            new GuiManager(plugin).showGUI(player,new MainStaffGui(plugin, player));
        });

        setButton(22, GuiUtils.createButtonItem(CompatibleMaterial.COMMAND_BLOCK, Utils.Color("&a&lModules"), Utils.Color("&7Click to show the Modules")), guiClickEvent -> {
            new GuiManager(plugin).showGUI(player,new ModulesGUI(plugin, player));
        });

        setButton(15, GuiUtils.createButtonItem(CompatibleMaterial.CHAIN_COMMAND_BLOCK, Utils.Color("&6&lDiscordBot"), Utils.Color("&7Coming Soon!")), (event) -> {
            player.sendMessage(Utils.Color(Utils.prefix() + "Currently there is no DiscordBot feature yet! Join the JustPlugins Discord server for more updates!"));
        });

        setButton(40, GuiUtils.createButtonItem(CompatibleMaterial.COMPARATOR, Utils.Color("&f&lSettings"), Utils.Color("&7Click to show Settings")), (event) -> {
            new GuiManager(plugin).showGUI(player,new Settings(plugin, player, Settings.SettingsPages.CUSTOMIZATION_PAGE));
        });
    }
}
