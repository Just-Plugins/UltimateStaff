package me.justplugins.ultimatestaff.Modules;

import me.justplugins.ultimatestaff.Main;
import me.justplugins.ultimatestaff.Modules.Configs.UserData.UserDataManager;
import me.justplugins.ultimatestaff.Modules.Configs.UserData.userdata;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Optional;

import static org.bukkit.Bukkit.getServer;

public class SilentJoinModule implements Listener {
    final Main plugin;
    private Optional<userdata> user;

    public SilentJoinModule(Main plugin) {
        this.plugin = plugin;
        getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        user = UserDataManager.UserSearch(player.getUniqueId());
        if(!user.isPresent()){
            new UserDataManager(plugin).createUser(player,false,false, false);
            user = Optional.of(UserDataManager.UserSearch(player.getUniqueId()).get());
        }

        if (user.get().getSilentJoin()) {
            event.setJoinMessage("");
            return;
        }
    }
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        user = UserDataManager.UserSearch(player.getUniqueId());
        if(!user.isPresent()){
            new UserDataManager(plugin).createUser(player,false,false, false);
            user = Optional.of(UserDataManager.UserSearch(player.getUniqueId()).get());
        }

        if (user.get().getSilentJoin()) {
            event.setQuitMessage("");
            return;
        }
    }
}
