package me.justplugins.ultimatestaff.GUI.StaffGui;

import com.songoda.core.compatibility.CompatibleMaterial;
import com.songoda.core.compatibility.CompatibleSound;
import com.songoda.core.gui.Gui;
import com.songoda.core.gui.GuiManager;
import com.songoda.core.gui.GuiUtils;
import me.justplugins.ultimatestaff.GUI.Reporting.ReportMenu;
import me.justplugins.ultimatestaff.Main;
import me.justplugins.ultimatestaff.Modules.Configs.UserData.UserDataManager;
import me.justplugins.ultimatestaff.Modules.Configs.UserData.userdata;
import me.justplugins.ultimatestaff.Utils.Utils;
import org.bukkit.entity.Player;

import java.util.Optional;

public class StaffSettings extends Gui {
    final Main plugin;

    public StaffSettings(Main plugin, Player player) {
        this.plugin = plugin;
        setTitle(Utils.Color("&8StaffGui > Staff Settings"));
        setRows(5);
        setDefaultItem(CompatibleMaterial.AIR.getItem());
        setDefaultSound(CompatibleSound.BLOCK_NOTE_BLOCK_BIT);

        //Silent Join
        Optional<userdata> user = UserDataManager.UserSearch(player.getUniqueId());
        if(!user.isPresent()){
            new UserDataManager(plugin).createUser(player);
            user = Optional.of(UserDataManager.UserSearch(player.getUniqueId()).get());
        }

        setButton(20, GuiUtils.createButtonItem(CompatibleMaterial.NOTE_BLOCK, Utils.Color("&a&lSilent Join"), Utils.Color("&7Status: &f&l" + UserDataManager.UserSearch(player.getUniqueId()).get().getSilentJoin())), (event) -> {
            UserDataManager.setSilentJoin(player,!UserDataManager.UserSearch(player.getUniqueId()).get().getSilentJoin());
            updateItemLore(20, Utils.Color("&7Status: &f&l" + UserDataManager.UserSearch(player.getUniqueId()).get().getSilentJoin()));
        });


        // Name Notifying
        setButton(24, GuiUtils.createButtonItem(CompatibleMaterial.NAME_TAG, Utils.Color("&a&lNameNotify"), Utils.Color("&7Status: &f&l" + UserDataManager.UserSearch(player.getUniqueId()).get().getNameNotify())), (event) -> {
            UserDataManager.setNameNotify(player, !UserDataManager.UserSearch(player.getUniqueId()).get().getNameNotify());
            updateItemLore(24, Utils.Color("&7Status: &f&l" + UserDataManager.UserSearch(player.getUniqueId()).get().getNameNotify()));
        });

        // OnDuty
        setButton(22, GuiUtils.createButtonItem(CompatibleMaterial.COMPASS, Utils.Color("&a&lOnDuty"), Utils.Color("&7Status: &f&l" + UserDataManager.UserSearch(player.getUniqueId()).get().getOnDuty())), (event) -> {
            UserDataManager.setOnDuty(player,!UserDataManager.UserSearch(player.getUniqueId()).get().getOnDuty());
            updateItemLore(22, Utils.Color("&7Status: &f&l" + UserDataManager.UserSearch(player.getUniqueId()).get().getOnDuty()));
        });


        //Go Back Button
        setButton(40, GuiUtils.createButtonItem(CompatibleMaterial.ARROW, Utils.Color("&f&lGo Back"),Utils.Color("&7Click to go Back")), (event) -> {
            new GuiManager(plugin).showGUI(player,new MainStaffGui(plugin, player));
        });
    }
}