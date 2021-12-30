package me.justplugins.ultimatestaff.Modules;

import me.justplugins.ultimatestaff.Modules.Configs.UserData.UserDataManager;
import org.bukkit.entity.Player;

public class StaffOnDuty {


    public void getDuty(Player player) {
        UserDataManager.UserSearch(player.getUniqueId()).get().getOnDuty();
    }
}
