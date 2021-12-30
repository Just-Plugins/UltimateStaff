package me.justplugins.ultimatestaff.Modules;

import me.justplugins.ultimatestaff.Main;
import me.justplugins.ultimatestaff.Modules.Configs.Config;
import me.justplugins.ultimatestaff.Modules.Configs.UserData.UserDataManager;
import me.justplugins.ultimatestaff.Modules.Configs.UserData.userdata;
import me.justplugins.ultimatestaff.Utils.Utils;
import org.apache.commons.lang.StringUtils;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Date;
import java.util.Optional;

public class PunishModules implements Listener {
    final Main plugin;
    public PunishModules(Main plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this,plugin);
    }

    private static Optional<userdata> user;

    public static String KickMessage(String typeBan) {
        return Utils.Color(String.join("\n", Config.Config.getStringList("Message."+typeBan)));
    }

    //Banning Module
    public static void Ban(Player player, Player target, Date duration, String reason) {
        if (Bukkit.getBanList(BanList.Type.NAME).isBanned(target.getName())) {
            //Player is already banned
            player.sendMessage(Utils.Color(Utils.prefix() + "&fThat player is already Banned!"));
        } else {
            //Ban Player
            Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(),reason,duration,player.getDisplayName());
            //.replace("{DURATION}",duration.toString())
            target.kickPlayer(KickMessage("BanMessage").replace("{PUNISH-TYPE}","Ban").replace("{REASON}",reason));
            player.sendMessage(Utils.Color(Utils.prefix() + " &fBanned &l" + target.getName()));
        }
    }

    //UnBanning Module
    public static void unBan(Player player, OfflinePlayer target) {
        if (!Bukkit.getBanList(BanList.Type.NAME).isBanned(target.getName())) {
            //Player isn't banned
            player.sendMessage(Utils.Color(Utils.prefix() + "&fThat player is not Banned!"));
        } else {
            //Unban Player
            Bukkit.getBanList(BanList.Type.NAME).pardon(target.getName());
            player.sendMessage(Utils.Color(Utils.prefix() + "&fUnbanned &l" + target.getName()));
        }
    }

    //IP-Ban Module
    public static void IPBan(Player player, Player target, Date duration, String reason) {
        if (Bukkit.getBanList(BanList.Type.IP).isBanned(target.getName())) {
            //Player is already banned
            player.sendMessage(Utils.Color(Utils.prefix() + "&fThat player is already IP-Banned!"));
        } else {
            //Find all matching players and kick
            String ip = target.getAddress().getAddress().getHostAddress();

            Bukkit.getBanList(BanList.Type.IP).addBan(ip,reason,duration,player.getDisplayName());

            for (Player pl : Bukkit.getOnlinePlayers()) {
                if (pl.getAddress().getAddress().getHostAddress().equals(ip)) {
                    //.replace("{DURATION}",duration.toString())
                    pl.kickPlayer(KickMessage("IPBanMessage").replace("{PUNISH-TYPE}","IpBan").replace("{REASON}",reason));
                }
            }

            //.replace("{DURATION}",duration.toString())
            target.kickPlayer(KickMessage("IPBanMessage").replace("{PUNISH-TYPE}","IpBan").replace("{REASON}",reason));
            player.sendMessage(Utils.Color(Utils.prefix() + "&fIP-Banned &l" + target.getName()));
        }
    }

    //IP-UnBanning Module
    public static void unIpBan(Player player, OfflinePlayer target) {
        if (!Bukkit.getBanList(BanList.Type.IP).isBanned(target.getName())) {
            //Player isn't banned
            player.sendMessage(Utils.Color(Utils.prefix() + "&fThat player is not IP-Banned!"));
        } else {
            //Unban all ip banned Players

            String addresses = Bukkit.getBanList(BanList.Type.IP).getBanEntry(target.toString()).getTarget();

            Bukkit.getBanList(BanList.Type.IP).pardon(addresses);
            player.sendMessage(Utils.Color(Utils.prefix() + "&fIP-Unbanned &l" + target.getName()));
        }
    }

    //Kicking Module
    public static void Kick(Player player, Player target, String reason) {
        if (!target.isOnline()) {
            //Player is not online
            player.sendMessage(Utils.Color(Utils.prefix() + "&fThat player is not online!"));
        } else {
            //Kick Player
            target.kickPlayer(KickMessage("KickMessage").replace("{PUNISH-TYPE}","Kick").replace("{REASON}",reason).replace("{DURATION}","&f"));
            player.sendMessage(Utils.Color(Utils.prefix() + "&fKicked &l" + target.getDisplayName()));
        }

    }
    //Muting Module
    public static void Mute(Player player, Player target, String duration, String reason) {
        user = Optional.of(UserDataManager.UserSearch(target.getUniqueId()).get());

        if (user.get().getMuted()) {
            player.sendMessage(Utils.Color(Utils.prefix() + "&cThat player is already muted!"));
        } else {
            user.get().setMuted(true);
            user.get().setUserMutedReason(reason);
            player.sendMessage(Utils.Color(Utils.prefix() + "&aMuted &f&l" + target.getDisplayName() + "&a!"));
        }
    }

    //UnMuting Module
    public static void unMute(Player player, Player target) {
        user = Optional.of(UserDataManager.UserSearch(target.getUniqueId()).get());

        if (!user.get().getMuted()) {
            player.sendMessage(Utils.Color(Utils.prefix() + "&cThat player is not muted!"));
        } else {
            user.get().setMuted(false);
            user.get().setUserMutedReason("Null");
            player.sendMessage(Utils.Color(Utils.prefix() + "&aUnMuted &f&l" + target.getDisplayName() + "&a!"));
        }
    }


    //Freezing Module
    public static void Freeze(Player player, Player target, String reason) {

        user = Optional.of(UserDataManager.UserSearch(target.getPlayer().getUniqueId()).get());

        if (user.get().getUserFrozen()) {
            //Player is already frozen
            player.sendMessage(Utils.Color(Utils.prefix() + "&fThat player is already Frozen!"));
        } else {
            //Freeze Player
            user.get().setUserFrozenReason(reason);
            user.get().setUserFrozen(true);

            target.setAllowFlight(true);
            target.setInvulnerable(true);
            if (target.isInsideVehicle()) target.getVehicle().removePassenger(target);
            target.sendTitle(Utils.Color("&f&lYou have been Frozen!"), Utils.Color("&c&lDO NOT DISCONNECT!"),0,999999999,1);
            target.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 999999, 60));
            target.playSound(target.getLocation(), Sound.MUSIC_DRAGON, 500, 1);
            target.sendMessage(StringUtils.repeat(" \n", 100));
            target.sendMessage(Utils.Color(
                    "&7|----------------------------------|\n" +
                    "&cYou have been frozen by staff \n" +
                    "&cPlease wait until staff arrives! \n" + " \n" +
                    "&9Reason: &f" + user.get().getUserFrozenReason() + "\n" +
                    "&7|----------------------------------|"));
            player.sendMessage(Utils.Color(Utils.prefix() + "&fFrozen &l" + target.getDisplayName()));
        }
    }

    //UnFreezing Module
    public static void unFreeze(Player player, Player target) {
        user = Optional.of(UserDataManager.UserSearch(target.getPlayer().getUniqueId()).get());

        if (user.get().getUserFrozen()) {
            if (!target.isOnline()) {
                //Player is not online
                player.sendMessage(Utils.Color(Utils.prefix() + "&fThat player is not online!"));
            } else {
                //unFreeze Player
                user.get().setUserFrozen(false);
                user.get().setUserFrozenReason("No Reason was added.");
                target.setInvulnerable(false);
                player.sendMessage(Utils.Color(Utils.prefix() + "&fUnFrozen &l" + target.getDisplayName()));
                target.setAllowFlight(false);
                target.removePotionEffect(PotionEffectType.BLINDNESS);
                target.sendTitle("","You have been UnFrozen!",0,30,5);
                target.stopSound(Sound.MUSIC_DRAGON);
            }
        } else {
            player.sendMessage(Utils.Color(Utils.prefix() + "&fThat player is not Frozen!"));
        }
    }


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        //Check if player is IP-banned when joining kicks player
        if (Bukkit.getIPBans().contains(player.toString())) {
            String reason = Bukkit.getBanList(BanList.Type.IP).getBanEntry(player.toString()).getReason();
            Date duration = Bukkit.getBanList(BanList.Type.IP).getBanEntry(player.toString()).getExpiration();
            player.kickPlayer(KickMessage("IPBanMessage").replace("{PUNISH-TYPE}","IpBan").replace("{REASON}",reason).replace("{DURATION}",duration.toString()));
        }

        //Check if player is banned when joining kicks player
        if (Bukkit.getBannedPlayers().contains(player)) {
            String reason = Bukkit.getBanList(BanList.Type.NAME).getBanEntry(player.toString()).getReason();
            Date duration = Bukkit.getBanList(BanList.Type.NAME).getBanEntry(player.toString()).getExpiration();
            player.kickPlayer(KickMessage("BanMessage").replace("{PUNISH-TYPE}","Ban").replace("{REASON}",reason).replace("{DURATION}",duration.toString()));
        }
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        user = Optional.of(UserDataManager.UserSearch(player.getUniqueId()).get());

        if (user.get().getMuted()) {
            event.setCancelled(true);
            player.sendMessage(Utils.Color("&cYou are muted!"));
        } else {
            event.setCancelled(false);
        }
    }

}