package me.justplugins.ultimatestaff.GUI.MainGui;

import com.songoda.core.compatibility.CompatibleMaterial;
import com.songoda.core.compatibility.CompatibleSound;
import com.songoda.core.gui.Gui;
import com.songoda.core.gui.GuiManager;
import com.songoda.core.gui.GuiUtils;
import me.justplugins.ultimatestaff.Main;
import me.justplugins.ultimatestaff.Utils.Utils;
import org.bukkit.entity.Player;

public class ModulesGUI extends Gui {
    final Main plugin;
    public ModulesGUI(Main plugin, Player player) {
        this.plugin = plugin;
        setTitle(Utils.Color("&8UltimateStaff > Modules"));
        setRows(5);
        setDefaultItem(CompatibleMaterial.AIR.getItem());
        setDefaultSound(CompatibleSound.BLOCK_NOTE_BLOCK_BIT);

        //Go Back Button
        setButton(40, GuiUtils.createButtonItem(CompatibleMaterial.ARROW, Utils.Color("&f&lGo Back"), Utils.Color("&7Click to go Back")), (event) -> {
            new GuiManager(plugin).showGUI(player,new MainGui(plugin, player));
        });
    }
}
