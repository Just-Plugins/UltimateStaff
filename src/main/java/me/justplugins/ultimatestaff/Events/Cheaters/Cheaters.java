package me.justplugins.ultimatestaff.Events.Cheaters;

import me.justplugins.ultimatestaff.Main;
import me.justplugins.ultimatestaff.Modules.PostRequest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import static org.bukkit.Bukkit.getServer;

public class Cheaters implements Listener {
    final Main plugin;
    public Cheaters(Main plugin){
        this.plugin = plugin;
        getServer().getPluginManager().registerEvents(this, plugin);

    }
    //@EventHandler
    //public void check(PlayerCheatEvent event ){
    //    new PostRequest().logs(event.getPlayer(), event.getCheat().getName()+" "+event.getCheat().getReliabilityAlert());
    //}
}
