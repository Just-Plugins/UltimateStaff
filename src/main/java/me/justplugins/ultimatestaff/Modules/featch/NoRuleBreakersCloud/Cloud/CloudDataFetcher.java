package me.justplugins.ultimatestaff.Modules.featch.NoRuleBreakersCloud.Cloud;

import com.google.common.reflect.TypeToken;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import me.justplugins.ultimatestaff.Main;
import me.justplugins.ultimatestaff.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import sun.misc.IOUtils;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class CloudDataFetcher extends Thread {
    private static final String FETCH_URL = "http://api.ultimatestaff.xyz/RuleBreakers.php";

    private LinkedTreeMap<String, CloudRuleBreakers> current;
    private boolean running;
    private boolean online;
    final Main plugin;

    public CloudDataFetcher(Main plugin) {
        this.plugin = plugin;
        this.current = new LinkedTreeMap<>();
        this.running = true;
        this.online = false;
        start();
    }

    @Override
    public void run() {
        while (running) {
            fetch();
            try {
                sleep(plugin.getMainConfig().getInt("NoRuleBreakers.Update.rate"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void onDisable() {
        running = false;
    }

    private void fetch() {
        try {
            current = new GsonBuilder()
                    .excludeFieldsWithoutExposeAnnotation()
                    .create().fromJson(new URL(FETCH_URL).toString(), new TypeToken<Map<String, CloudRuleBreakers>>() {
                    }.getType());
            online = true;
            PlayerCheck();

        } catch (Exception e) {
            online = false;
        }
    }

    public boolean isOnline() {
        return online;
    }

    public List<CloudRuleBreakers> getCurrent() {
        return new ArrayList<>(current.values());
    }

    public void PlayerCheck() {
        Bukkit.getScheduler().runTask(plugin, new Runnable() {
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (plugin.getRuleBreakerip(p.getAddress().getAddress().getAddress().toString()).isPresent() || plugin.getRuleBreaker(p.getUniqueId()).isPresent()) {
                        CloudRuleBreakers play = plugin.getRuleBreaker(p.getName()).get();
                        p.kickPlayer(Utils.Color( "&7[&aUltimate Staff&7] &9You have been kicked for\n" + play.getReason()+"\n"+ play.getBanID()));
                    }
                }
            }
        });
    }
}
