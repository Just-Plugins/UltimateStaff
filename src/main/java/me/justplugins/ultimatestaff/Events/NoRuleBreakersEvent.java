package me.justplugins.ultimatestaff.Events;

import me.justplugins.ultimatestaff.Main;
import me.justplugins.ultimatestaff.Modules.featch.NoRuleBreakersCloud.Cloud.CloudRuleBreakers;
import me.justplugins.ultimatestaff.Utils.Utils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static org.bukkit.Bukkit.getServer;

public class NoRuleBreakersEvent implements Listener {
    final Main plugin;

    public NoRuleBreakersEvent(Main plugin) {
        this.plugin = plugin;
        getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        System.out.println(plugin.getRuleBreakers().getCloudAddons().size());

        if (plugin.getRuleBreaker(event.getPlayer().getName()).isPresent() || plugin.getRuleBreaker(event.getPlayer().getUniqueId()).isPresent()) {

            CloudRuleBreakers play = plugin.getRuleBreaker(event.getPlayer().getName()).get();
            event.getPlayer().kickPlayer(Utils.Color(Utils.prefix() + "&9You have been kicked for\n" + play.getReason() + "\n" + play.getBanID()));
            return;

        }
    }
}