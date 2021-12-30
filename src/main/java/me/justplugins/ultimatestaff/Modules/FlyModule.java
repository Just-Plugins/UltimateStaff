package me.justplugins.ultimatestaff.Modules;

import me.justplugins.ultimatestaff.Main;
import me.justplugins.ultimatestaff.Utils.Utils;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class FlyModule {
    final Main plugin;

    public FlyModule(Main plugin) {
        this.plugin = plugin;
    }

    public void FlyToggle(Player player) {
        try {
            player.setAllowFlight(!Main.flyinglist.contains(player));

            if (Main.flyinglist.contains(player)) {
                player.sendTitle("", Utils.Color("&fFly &7| &cOff"), 0, 30, 20);
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 10, 0);
                Main.flyinglist.remove(player);
                player.setFlying(false);

                return;
            }

            if (!Main.flyinglist.contains(player)) {
                player.sendTitle("", Utils.Color("&fFly &7| &aOn"), 0, 30, 20);
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 10, 1);
                Main.flyinglist.add(player);
                player.setFlying(true);

                return;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
