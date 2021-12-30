package me.justplugins.ultimatestaff.GUI.StaffGui;

import me.justplugins.ultimatestaff.Main;
import me.justplugins.ultimatestaff.Modules.Configs.UserData.UserDataManager;
import me.justplugins.ultimatestaff.Modules.Configs.UserData.userdata;
import me.justplugins.ultimatestaff.Utils.Utils;
import me.nathans212.baseplugin.gui.GUIBuilder;
import me.nathans212.baseplugin.gui.Gui;
import me.nathans212.baseplugin.gui.GuiUtils;
import me.nathans212.baseplugin.gui.compatibility.CompatibleMaterial;
import org.bukkit.entity.Player;

import java.util.Optional;

public class StaffSettings extends GUIBuilder {
    final Main plugin;

    public StaffSettings(Main plugin, Player player) {
        super(plugin, player);
        this.plugin = plugin;
    }

    @Override
    public String Title() {
        return Utils.Color("&8StaffGui > Staff Settings");
    }

    @Override
    public int Rows() {
        return 5;
    }

    @Override
    public void onOpen(Player player, Gui gui) {
        //Silent Join
        Optional<userdata> user = UserDataManager.UserSearch(player.getUniqueId());
        if(!user.isPresent()){
            new UserDataManager(plugin).createUser(player, false, false, false);
            user = Optional.of(UserDataManager.UserSearch(player.getUniqueId()).get());
        }

        setButton(20, GuiUtils.createButtonItem(CompatibleMaterial.NOTE_BLOCK, Utils.Color("&a&lSilent Join"), Utils.Color("&7Status: &f&l" + UserDataManager.UserSearch(player.getUniqueId()).get().getSilentJoin())), (event) -> {
            new UserDataManager(plugin).createUser(player, UserDataManager.UserSearch(player.getUniqueId()).get().getNameNotify(), !UserDataManager.UserSearch(player.getUniqueId()).get().getSilentJoin(), UserDataManager.UserSearch(player.getUniqueId()).get().getOnDuty());
            updateItemLore(20, Utils.Color("&7Status: &f&l" + UserDataManager.UserSearch(player.getUniqueId()).get().getSilentJoin()));
        });


        // Name Notifying
        setButton(24, GuiUtils.createButtonItem(CompatibleMaterial.NAME_TAG, Utils.Color("&a&lNameNotify"), Utils.Color("&7Status: &f&l" + UserDataManager.UserSearch(player.getUniqueId()).get().getNameNotify())), (event) -> {
            new UserDataManager(plugin).createUser(player, !UserDataManager.UserSearch(player.getUniqueId()).get().getNameNotify(), UserDataManager.UserSearch(player.getUniqueId()).get().getSilentJoin(),UserDataManager.UserSearch(player.getUniqueId()).get().getOnDuty());
            updateItemLore(24, Utils.Color("&7Status: &f&l" + UserDataManager.UserSearch(player.getUniqueId()).get().getNameNotify()));
        });

        // OnDuty
        setButton(22, GuiUtils.createButtonItem(CompatibleMaterial.COMPASS, Utils.Color("&a&lOnDuty"), Utils.Color("&7Status: &f&l" + UserDataManager.UserSearch(player.getUniqueId()).get().getOnDuty())), (event) -> {
            new UserDataManager(plugin).createUser(player, UserDataManager.UserSearch(player.getUniqueId()).get().getNameNotify(), UserDataManager.UserSearch(player.getUniqueId()).get().getSilentJoin(),!UserDataManager.UserSearch(player.getUniqueId()).get().getOnDuty());
            updateItemLore(22, Utils.Color("&7Status: &f&l" + UserDataManager.UserSearch(player.getUniqueId()).get().getOnDuty()));
        });


        //Go Back Button
        setButton(40, GuiUtils.createButtonItem(CompatibleMaterial.ARROW, Utils.Color("&f&lGo Back"),Utils.Color("&7Click to go Back")), (event) -> {
            new MainStaffGui(plugin, player);
        });
    }
}