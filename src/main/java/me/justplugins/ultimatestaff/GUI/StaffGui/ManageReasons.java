package me.justplugins.ultimatestaff.GUI.StaffGui;

import com.songoda.core.compatibility.CompatibleMaterial;
import com.songoda.core.compatibility.CompatibleSound;
import com.songoda.core.gui.Gui;
import com.songoda.core.gui.GuiManager;
import com.songoda.core.gui.GuiUtils;
import me.justplugins.ultimatestaff.Main;
import me.justplugins.ultimatestaff.Modules.Configs.Config;
import me.justplugins.ultimatestaff.Utils.Utils;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public class ManageReasons extends Gui {
    final Main plugin;
    int p;
    public ManageReasons(Main plugin, Player player) {
        this.plugin = plugin;
        setTitle(Utils.Color("&8StaffGui > Manage Reasons"));
        setRows(6);
        setDefaultItem(CompatibleMaterial.AIR.getItem());
        setDefaultSound(CompatibleSound.BLOCK_NOTE_BLOCK_BIT);


        for (String r : Config.Config.getStringList("Punishments.Reasons")) {
            if (p == 49) break;

            setButton(p, GuiUtils.createButtonItem(CompatibleMaterial.PAPER, Utils.Color("&l" + Config.Config.getStringList("Punishments.Reasons").get(p)),Utils.Color("&7&nPress Q to remove")),ClickType.DROP, guiClickEvent -> {
                Config.Config.getStringList("Punishments.Reasons").remove(Config.Config.getStringList("Punishments.Reasons").get(guiClickEvent.slot));
                player.playSound(player.getLocation(), Sound.ENTITY_CHICKEN_EGG,500,1);
                new GuiManager(plugin).showGUI(player,new ManageReasons(plugin, player));
            });


            p++;
        }

        //Go Back Button
        setButton(49, GuiUtils.createButtonItem(CompatibleMaterial.ARROW, Utils.Color("&f&lGo Back"),Utils.Color("&7Click to go Back")), (event) -> {
            new GuiManager(plugin).showGUI(player,new MainStaffGui(plugin, player));
        });
    }
}
