package me.justplugins.ultimatestaff.Modules;

import me.justplugins.ultimatestaff.Main;
import me.justplugins.ultimatestaff.Modules.Configs.UserData.UserDataManager;
import me.justplugins.ultimatestaff.Modules.Configs.UserData.userdata;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Arrays;
import java.util.Optional;

import static org.bukkit.Bukkit.getPlayer;
import static org.bukkit.Bukkit.getServer;

public class StaffPing implements Listener {
    final Main plugin;
    public Player p;
    public String[] message;
    private Optional<userdata> user;

    public StaffPing(Main plugin) {
        this.plugin = plugin;
        getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onChatEvent(AsyncPlayerChatEvent ce) {


        message = ce.getMessage().split(" ");


        Bukkit.getOnlinePlayers().forEach(player -> {

            if (ce.getMessage().contains(player.getName())) {

                p = getPlayer(StaffMessagePingingFilter(message, player).get());

                user = UserDataManager.UserSearch(p.getUniqueId());

                if (user.get().getNameNotify()) {
                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BELL, SoundCategory.MASTER, 534647, 0);
                    String lastColor = ChatColor.getLastColors(ce.getMessage());
                    ce.setMessage(ce.getMessage().replaceAll(player.getName(), ChatColor.GREEN + "" + ChatColor.BOLD + player.getName() + (lastColor.isEmpty() ? ChatColor.RESET : lastColor)));
                }
            }
        });
    }

    public Optional<String> StaffMessagePingingFilter(String[] user,Player player) {
        return Arrays.stream(user).filter(userdata1 -> userdata1.equals(player.getDisplayName())).findFirst();
    }
}
