package me.justplugins.ultimatestaff.GUI.MainGui;

import me.justplugins.ultimatestaff.GUI.MainGui.Settings.Customization;
import me.justplugins.ultimatestaff.GUI.StaffGui.MainStaffGui;
import me.justplugins.ultimatestaff.Main;
import me.justplugins.ultimatestaff.Utils.Utils;
import me.nathans212.baseplugin.gui.*;
import me.nathans212.baseplugin.gui.compatibility.CompatibleMaterial;
import org.bukkit.entity.Player;

public class MainGui extends GUIBuilder {
    final Main plugin;
    public MainGui(Main plugin, Player player) {
        super(plugin, player);
        this.plugin = plugin;
    }

    @Override
    public String Title() {
        return Utils.Color("&8UltimateStaff");
    }

    @Override
    public int Rows() {
        return 5;
    }

    @Override
    public void onOpen(Player player, Gui gui) {

        setButton(11, GuiUtils.createButtonItem(CompatibleMaterial.CHEST, Utils.Color("&a&lStaff Gui"), Utils.Color("&7Click to show the StaffGUI")), guiClickEvent -> {
            new MainStaffGui(plugin, player);
        });

        setButton(22, GuiUtils.createButtonItem(CompatibleMaterial.COMMAND_BLOCK, Utils.Color("&a&lModules"), Utils.Color("&7Click to show the Modules")), guiClickEvent -> {
           new ModulesGUI(plugin, player);
        });

        setButton(15, GuiUtils.createButtonItem(CompatibleMaterial.CHAIN_COMMAND_BLOCK, Utils.Color("&6&lDiscordBot"), Utils.Color("&7Coming Soon!")), (event) -> {
            player.sendMessage(Utils.Color(Utils.prefix() + "Currently there is no DiscordBot feature yet! Join the JustPlugins Discord server for more updates!"));
        });

        setButton(40, GuiUtils.createButtonItem(CompatibleMaterial.COMPARATOR,Utils.Color("&f&lSettings"), Utils.Color("&7Click to show Settings")), (event) -> {
            new Customization(plugin, player);
        });
    }
}
