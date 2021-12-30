package me.justplugins.ultimatestaff.Events;

import me.justplugins.ultimatestaff.Main;
import me.justplugins.ultimatestaff.Utils.Permissions;
import me.justplugins.ultimatestaff.Utils.Utils;
import me.justplugins.ultimatestaff.Modules.featch.domains.cloud.CloudDataFetcher;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.bukkit.Bukkit.getServer;

public class AntiAdEvent implements Listener {
    final Main plugin;
    List<String> wList = new ArrayList<>();

    public AntiAdEvent(Main plugin) {
        this.plugin = plugin;
        if (plugin.getMainConfig().getBoolean("ChatSettings.AntiAdvertisement.Enabled")) {
            getServer().getPluginManager().registerEvents(this, plugin);
            wList = plugin.getMainConfig().getStringList("ChatSettings.AntiAdvertisement.WhiteList");
        }
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        if (wList.contains(event.getMessage())) {
            return;
        }
        if (event.getPlayer().hasPermission(Permissions.DOMAINWHITELIST_BYPASS.getPermission())) {
            return;
        }
        if (event.getMessage().contains(".")) {
            String[] Message = event.getMessage().split("\\.");
            for (String m : Message) {
                try {
                    if (event.getPlayer().hasPermission(Permissions.DOMAINWHITELIST_BYPASS_STRING.getPermission() + m)) {
                        return;
                    }
                    if (CloudDataFetcher.domains().entrySet().stream().anyMatch(s -> s.getValue().getdomains().contains(m))) {
                        event.setCancelled(true);
                        event.getPlayer().sendMessage(Utils.Color(Utils.prefix() + "&cYou can't send that link"));
                        return;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}