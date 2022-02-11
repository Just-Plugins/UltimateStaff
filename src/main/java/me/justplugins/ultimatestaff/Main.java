package me.justplugins.ultimatestaff;

import com.songoda.core.SongodaCore;
import com.songoda.core.SongodaPlugin;
import com.songoda.core.commands.AbstractCommand;
import com.songoda.core.commands.CommandManager;
import com.songoda.core.core.SongodaCoreCommand;
import com.songoda.core.gui.GuiManager;
import me.justplugins.ultimatestaff.Commands.*;
import me.justplugins.ultimatestaff.Commands.PunishCommands.*;
import me.justplugins.ultimatestaff.Events.BannedWords;
import me.justplugins.ultimatestaff.Events.AntiAdEvent;
import me.justplugins.ultimatestaff.Events.Cheaters.Cheaters;
import me.justplugins.ultimatestaff.Events.Cheaters.NoRuleBreakersEvent;
import me.justplugins.ultimatestaff.Modules.*;
import me.justplugins.ultimatestaff.Modules.Configs.*;
import me.justplugins.ultimatestaff.Modules.Configs.Reports.ReportManager;
import me.justplugins.ultimatestaff.Modules.featch.NoRuleBreakersCloud.Cloud.CloudRuleBreakers;
import me.justplugins.ultimatestaff.Modules.featch.NoRuleBreakersCloud.RuleBreakersManager;
import me.justplugins.ultimatestaff.Modules.Configs.UserData.UserDataManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;

public final class Main extends JavaPlugin {

    //ArrayList
    public static final ArrayList<Player> vanishlist = new ArrayList<>();
    public static final ArrayList<Player> flyinglist = new ArrayList<>();

    public RuleBreakersManager RuleBreakers;

    @Override
    public void onEnable() {

        long start = System.currentTimeMillis();

        getLogger().log(Level.INFO,""); //setColor

        getLogger().log(Level.INFO, "UltimateStaff || Loading...");

        getLogger().log(Level.INFO, "UltimateStaff || Loading Configs...");

        //Configs
        new UserDataManager(this);
        new ReportManager(this);
        new Config(this); // The main config for the plugin

        getLogger().log(Level.INFO, "UltimateStaff || Configs Done!");

        getLogger().log(Level.INFO, "UltimateStaff || Fetching data from the cloud...");

        // Cloud
        this.RuleBreakers = new RuleBreakersManager(this);
        //new MySQLConnector(this,"",4535,"","","",true).connect(Connection::commit);

        getLogger().log(Level.INFO, "UltimateStaff || Fetching Done!");

        getLogger().log(Level.INFO, "UltimateStaff || Loading Commands...");

        // Load Commands
        CommandManager commandManager = new CommandManager(this);
        commandManager.setNoConsoleMessage("UltimateStaff || You can't run this in the console!");
        commandManager.addCommands(
            new MainCommand(this),
            new StaffChat(this),
            new StaffGui(this),
            new InvenSee(this),
            new PunishTarget(this),

            new Report(this),
            new Reports(this),

            new Kick(this),
            new Ban(this),
            new IpBan(this),
            new Freeze(this),
            new Mute(this),

            new UnBan(this),
            new UnIpBan(this),
            new UnFreeze(this),
            new UnMute(this)
        );

        getLogger().log(Level.INFO, "UltimateStaff || Commands Done!");

        getLogger().log(Level.INFO, "UltimateStaff || Loading Events...");

        ////Events
        new AntiAdEvent(this);
        new NoRuleBreakersEvent(this);
        new Cheaters(this);
        new SilentJoinModule(this);
        new StaffPing(this);
        new BannedWords(this);
        new FrozenModule(this);
        new PunishModules(this);

        getLogger().log(Level.INFO, "UltimateStaff || Events Done!");

        getLogger().log(Level.INFO, "UltimateStaff || Successfully loaded in " + (System.currentTimeMillis() - start) + "ms");
        getLogger().log(Level.INFO, "UltimateStaff || All Rights Reserved");

    }


    //public void onLoad() {
    //    ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();
    //}


    public FileConfiguration getMainConfig() {
        return Config.Config;
    }
    public RuleBreakersManager getRuleBreakers() {
        return RuleBreakers;
    }
    public Optional<CloudRuleBreakers> getRuleBreaker(String name){
        return getRuleBreakers().getCloudAddons().stream().filter(user -> user.getName().equalsIgnoreCase(name)).findFirst();
    }
    public Optional<CloudRuleBreakers> getRuleBreaker(UUID uuid){
        return getRuleBreakers().getCloudAddons().stream().filter(user -> user.getName().equals(uuid)).findFirst();
    }
    public Optional<CloudRuleBreakers> getRuleBreakerip(String ip){
        return getRuleBreakers().getCloudAddons().stream().filter(user -> user.getName().equals(ip)).findFirst();
    }

}

//Todo: Cannot invoke "me.justplugins.ultimatestaff.Main.getDataFolder()" because "this.plugin" is null
//Todo: All onlineplayers tabs need more pages when more players "also for the reports page"
//Todo: PunishModules add duration feature
//Todo: Everything form the config should be configurable in the gui
//Todo: Mysql && Bungeecord support
//Todo: Utils staffmessage send also to all bungee servers
//Todo: Fix the staff settings

//misc - desing
//Todo: add sound effects.

