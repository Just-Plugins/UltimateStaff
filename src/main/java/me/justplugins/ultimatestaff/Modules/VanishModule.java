package me.justplugins.ultimatestaff.Modules;

import me.justplugins.ultimatestaff.Main;
import me.justplugins.ultimatestaff.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static org.bukkit.Bukkit.getServer;

public class VanishModule implements Listener {
    final Main plugin;

    public VanishModule(Main plugin) {
        this.plugin = plugin;
        getServer().getPluginManager().registerEvents(this, plugin);
    }

    public void VanishToggle(Player player) {

        if (!Main.vanishlist.contains(player)) {
            Main.vanishlist.add(player);
            player.sendTitle("", Utils.Color("&fVanish Mode &7| &aOn"), 0, 50, 20);
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 10, 1);
            for (Player people : Bukkit.getOnlinePlayers()) {
                people.hidePlayer(player);
            }
            return;
        }

        if (Main.vanishlist.contains(player)) {
            Main.vanishlist.remove(player);
            player.sendTitle("", Utils.Color("&fVanish Mode &7| &cOff"), 0, 50, 20);
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 10, 0);
            for (Player people : Bukkit.getOnlinePlayers()) {
                people.showPlayer(player);
            }
        }
    }

    public void VanishOn(Player player) {
        if (!Main.vanishlist.contains(player)) {
            Main.vanishlist.add(player);
            player.sendTitle("", Utils.Color("&fVanish Mode &7| &aOn"), 0, 50, 20);
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 10, 1);
            for (Player people : Bukkit.getOnlinePlayers()) {
                people.hidePlayer(player);
            }
        }
    }

    public void VanishOff(Player player) {
        if (Main.vanishlist.contains(player)) {
            Main.vanishlist.remove(player);
            player.sendTitle("", Utils.Color("&fVanish Mode &7| &cOff"), 0, 50, 20);
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 10, 0);
            for (Player people : Bukkit.getOnlinePlayers()) {
                people.showPlayer(player);
            }
        }
    }

    @EventHandler
    public void playerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        for (int i = 0; i < Main.vanishlist.size(); i++) {
            player.hidePlayer(Main.vanishlist.get(i));
        }
    }
}
