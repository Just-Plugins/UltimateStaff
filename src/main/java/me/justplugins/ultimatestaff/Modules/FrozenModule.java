package me.justplugins.ultimatestaff.Modules;

import me.justplugins.ultimatestaff.Main;
import me.justplugins.ultimatestaff.Modules.Configs.UserData.UserDataManager;
import me.justplugins.ultimatestaff.Modules.Configs.UserData.userdata;
import me.justplugins.ultimatestaff.Utils.Permissions;
import me.justplugins.ultimatestaff.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Optional;

import static org.bukkit.Bukkit.getServer;

public class FrozenModule implements Listener {
    final Main plugin;
    private Optional<userdata> user;

    public FrozenModule(Main plugin) {
        this.plugin = plugin;
        getServer().getPluginManager().registerEvents(this, plugin);

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        user = Optional.of(UserDataManager.UserSearch(player.getUniqueId()).get());

        if(user.get().getUserFrozen()){
            player.sendTitle(Utils.Color("&f&lYou have been Frozen!"), Utils.Color("&c&lPlease wait for staff"));
            Bukkit.broadcast(user.get().getPlayerName() + " has joined and is frozen because" + user.get().getUserFrozenReason(), Permissions.FREEZE.getPermission());
        }
    }
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {

    }


    @EventHandler
    public void moveEvent(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        user = Optional.of(UserDataManager.UserSearch(e.getPlayer().getUniqueId()).get());

        if (user.get().getUserFrozen()) {
            e.setCancelled(true);
            player.sendTitle(Utils.Color("&f&lYou have been Frozen!"), Utils.Color("&c&lPlease wait for staff"));
        } else {
            e.setCancelled(false);
        }
    }

    @EventHandler
    public void dropEvent(PlayerDropItemEvent e) {
        Player player = e.getPlayer();
        user = Optional.of(UserDataManager.UserSearch(player.getUniqueId()).get());
        e.setCancelled(user.get().getUserFrozen());
    }


}
