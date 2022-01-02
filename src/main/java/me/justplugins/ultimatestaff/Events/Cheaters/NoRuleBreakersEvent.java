package me.justplugins.ultimatestaff.Events.Cheaters;

import me.justplugins.ultimatestaff.Main;
import me.justplugins.ultimatestaff.Utils.Permissions;
import me.justplugins.ultimatestaff.Utils.Utils;
import me.justplugins.ultimatestaff.Modules.featch.NoRuleBreakersCloud.Cloud.CloudRuleBreakers;
import org.bukkit.Bukkit;
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
        CloudRuleBreakers rulebreaker;
        if (plugin.getRuleBreakerip(event.getPlayer().getAddress().getAddress().getAddress().toString()).isPresent()) {
            rulebreaker = plugin.getRuleBreaker(event.getPlayer().getAddress().getAddress().getAddress().toString()).get();
            if (plugin.getMainConfig().getString("NoRuleBreakers.Actions.Low").equals("Warn")) {
                Bukkit.broadcast(event.getPlayer().getDisplayName() + " Has been global banned for " + rulebreaker.getReason(), Permissions.NO_RULE_BREAKERS_WARN.getPermission());
            }
            if (plugin.getMainConfig().getString("NoRuleBreakers.Actions.Low").equals("Kick")) {
                event.getPlayer().kickPlayer(Utils.Color(Utils.prefix() + "&1You have been kicked for\n" + rulebreaker.getReason() + "\n" + rulebreaker.getBanID()));
            }
            if (plugin.getRuleBreaker(event.getPlayer().getUniqueId()).isPresent()) {
                rulebreaker = plugin.getRuleBreaker(event.getPlayer().getUniqueId()).get();

            }
            CloudRuleBreakers play = plugin.getRuleBreaker(event.getPlayer().getName()).get();

        }
    }
}