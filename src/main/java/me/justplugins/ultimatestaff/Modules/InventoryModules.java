package me.justplugins.ultimatestaff.Modules;

import org.bukkit.entity.Player;

public class InventoryModules {

    public static void seeInventory(Player player, Player target) {
        player.openInventory(target.getInventory());
    }

    public static void removeInventory(Player target) {
        target.getInventory().clear();
    }
}
