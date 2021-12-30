package me.justplugins.ultimatestaff.GUI.StaffGui;

import me.justplugins.ultimatestaff.Main;
import me.justplugins.ultimatestaff.Modules.Configs.Config;
import me.justplugins.ultimatestaff.Utils.Utils;
import me.nathans212.baseplugin.gui.GUIBuilder;
import me.nathans212.baseplugin.gui.Gui;
import me.nathans212.baseplugin.gui.GuiUtils;
import me.nathans212.baseplugin.gui.compatibility.CompatibleMaterial;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

public class ManageReasons extends GUIBuilder {
    final Main plugin;
    int p;
    public ManageReasons(Main plugin, Player player) {
        super(plugin, player);
        this.plugin = plugin;
    }

    @Override
    public String Title() {
        return Utils.Color("&8StaffGui > Manage Reasons");
    }

    @Override
    public int Rows() {
        return 6;
    }

    @Override
    public void onOpen(Player player, Gui gui) {
        for (String r : Config.Config.getStringList("Punishments.Reasons")) {
            if (p == 49) break;

            setButton(p, GuiUtils.createButtonItem(CompatibleMaterial.PAPER, Utils.Color("&l" + Config.Config.getStringList("Punishments.Reasons").get(p)),Utils.Color("&7&nPress Q to remove")),ClickType.DROP, guiClickEvent -> {
                Config.Config.getStringList("Punishments.Reasons").remove(Config.Config.getStringList("Punishments.Reasons").get(guiClickEvent.slot));
                player.playSound(player.getLocation(), Sound.ENTITY_CHICKEN_EGG,500,1);
                new ManageReasons(plugin,player);
            });


            p++;
        }





        //Go Back Button
        setButton(49, GuiUtils.createButtonItem(CompatibleMaterial.ARROW, Utils.Color("&f&lGo Back"),Utils.Color("&7Click to go Back")), (event) -> {
            new MainStaffGui(plugin, player);
        });
    }
}
