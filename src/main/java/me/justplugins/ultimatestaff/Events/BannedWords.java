package me.justplugins.ultimatestaff.Events;

import me.justplugins.ultimatestaff.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.List;

import static org.bukkit.Bukkit.getServer;

public class BannedWords implements Listener {
    final Main plugin;
    List<String> List = new ArrayList<>();
    public BannedWords(Main plugin) {
        this.plugin = plugin;
            if (plugin.getMainConfig().getBoolean("ChatSettings.BannedWords.Enabled")) {
                getServer().getPluginManager().registerEvents(this, plugin);
                List = plugin.getMainConfig().getStringList("ChatSettings.BannedWords.List");
            }
        }

    @EventHandler
    public void onChatEvent(AsyncPlayerChatEvent event) {
        String replacement = plugin.getMainConfig().getString("ChatSettings.BannedWords.Replacement");

        for (String word : List) {
            if (event.getMessage().contains(word)) {
                String msg = event.getMessage().replace(word, replacement);
                event.setMessage(msg);
            }
        }
    }
}
