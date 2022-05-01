package me.justplugins.ultimatestaff;

import com.songoda.core.SongodaCore;
import com.songoda.core.commands.CommandManager;
import me.justplugins.ultimatestaff.Commands.*;
import me.justplugins.ultimatestaff.Commands.PunishCommands.*;
import me.justplugins.ultimatestaff.Events.BannedWords;
import me.justplugins.ultimatestaff.Modules.Configs.Config;
import me.justplugins.ultimatestaff.Modules.Configs.Reports.ReportManager;
import me.justplugins.ultimatestaff.Modules.Configs.UserData.UserDataManager;
import me.justplugins.ultimatestaff.Modules.FrozenModule;
import me.justplugins.ultimatestaff.Modules.PunishModules;
import me.justplugins.ultimatestaff.Modules.SilentJoinModule;
import me.justplugins.ultimatestaff.Modules.StaffPing;
import me.justplugins.ultimatestaff.Utils.ConsoleColors;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.logging.Level;

public final class Main extends JavaPlugin {


    @Override
    public void onEnable() {

        long start = System.currentTimeMillis();

        getLogger().log(Level.INFO,""); //setColor

        getLogger().log(Level.INFO, ConsoleColors.BLUE_BOLD + "UltimateStaff || Loading..." + ConsoleColors.RESET);

        getLogger().log(Level.INFO, ConsoleColors.BLUE_BOLD + "UltimateStaff || Loading Configs..." + ConsoleColors.RESET);

        //Configs
        new UserDataManager(this);
        new ReportManager(this);
        new Config(this); // The main config for the plugin

        getLogger().log(Level.INFO, ConsoleColors.BLUE_BOLD + "UltimateStaff || Configs Done!" + ConsoleColors.RESET);

        getLogger().log(Level.INFO, ConsoleColors.BLUE_BOLD + "UltimateStaff || Fetching data from the cloud..." + ConsoleColors.RESET);

        // Cloud

        getLogger().log(Level.INFO, ConsoleColors.BLUE_BOLD + "UltimateStaff || Fetching Done!" + ConsoleColors.RESET);

        getLogger().log(Level.INFO, ConsoleColors.BLUE_BOLD + "UltimateStaff || Loading Commands..." + ConsoleColors.RESET);

        // Load Commands
        CommandManager commandManager = new CommandManager(this);
        commandManager.setNoPermsMessage("&cYou do not have permission to use this command!");
        commandManager.setSyntaxErrorMessage(Arrays.asList(
                ChatColor.RED + "Invalid Syntax!",
                ChatColor.GRAY + "The valid syntax is: " + ChatColor.GREEN + "%syntax%" + ChatColor.GRAY + "."
        ));
        commandManager.setNoCommandMessage(ChatColor.RED + "That command does not exist!");
        commandManager.registerCommandDynamically(new MainCommand(this));
        commandManager.registerCommandDynamically(new StaffChat(this));
        commandManager.registerCommandDynamically(new StaffGui(this));
        commandManager.registerCommandDynamically(new InvenSee(this));

        commandManager.registerCommandDynamically(new PunishTarget(this));
        commandManager.registerCommandDynamically(new Report(this));
        commandManager.registerCommandDynamically(new Reports(this));

        commandManager.registerCommandDynamically(new Kick(this));
        commandManager.registerCommandDynamically(new Ban(this));
        commandManager.registerCommandDynamically(new IpBan(this));
        commandManager.registerCommandDynamically(new Freeze(this));
        commandManager.registerCommandDynamically(new Mute(this));

        commandManager.registerCommandDynamically(new UnBan(this));
        commandManager.registerCommandDynamically(new UnIpBan(this));
        commandManager.registerCommandDynamically(new UnFreeze(this));
        commandManager.registerCommandDynamically(new UnMute(this));

        getLogger().log(Level.INFO, ConsoleColors.BLUE_BOLD + "UltimateStaff || Commands Done!" + ConsoleColors.RESET);

        getLogger().log(Level.INFO, ConsoleColors.BLUE_BOLD + "UltimateStaff || Loading Events..." + ConsoleColors.RESET);

        ////Events
        new SilentJoinModule(this);
        new StaffPing(this);
        new BannedWords(this);
        new FrozenModule(this);
        new PunishModules(this);

        getLogger().log(Level.INFO, ConsoleColors.BLUE_BOLD + "UltimateStaff || Events Done!" + ConsoleColors.RESET);

        getLogger().log(Level.INFO, ConsoleColors.BLUE_BOLD + "UltimateStaff || Successfully loaded in " + (System.currentTimeMillis() - start) + "ms" + ConsoleColors.RESET);
        getLogger().log(Level.INFO, ConsoleColors.BLUE_BOLD + "UltimateStaff || All Rights Reserved" + ConsoleColors.RESET);

    }



    public FileConfiguration getMainConfig() {
        return Config.Config;
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

