package me.justplugins.ultimatestaff.Modules.Configs.UserData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.justplugins.ultimatestaff.Main;
import org.bukkit.entity.Player;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class UserDataManager {
    static Main plugin;
    public UserDataManager(Main plugin) {
        UserDataManager.plugin = plugin;
        try {
            LoadUserdata();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<userdata> userdata = new ArrayList<>();

    public void createUser(Player Player){
        Optional<userdata> u = UserSearch(Player.getUniqueId());
        u.ifPresent(value -> userdata.remove(value));
        userdata users = new userdata(Player.getDisplayName(), Player.getUniqueId(), false,false,false,false,"None",false,"None",false);
        userdata.add(users);
        try {
            SaveUserdata();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void SaveUserdata() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        File file = new File(plugin.getDataFolder().getAbsolutePath() + "/Userdata.json" );
        Writer writer = new FileWriter(file,false);
        gson.toJson(userdata,writer);
        writer.flush();
        writer.close();
    }


    public void LoadUserdata() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        File file = new File(plugin.getDataFolder().getAbsolutePath() + "/Userdata.json" );
        if(file.exists()){
            Reader reader = null;
            try {
                reader = new FileReader(file);
            } catch (FileNotFoundException e) {
                file.createNewFile();
            }
            userdata[] n = gson.fromJson(reader, userdata[].class);
            userdata = new ArrayList<>(Arrays.asList(n));
        }
    }

    public static List<String> getMutedList() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        File file = new File(plugin.getDataFolder().getAbsolutePath() + "/Userdata.json");
        Reader reader = null;
        try {
            reader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        userdata[] muted = gson.fromJson(reader, userdata[].class);
        List<userdata> reportsList = Arrays.asList(muted);

        List<String> reason = reportsList.stream().map(me.justplugins.ultimatestaff.Modules.Configs.UserData.userdata::getPlayerName).collect(Collectors.toList());
        return reason;
    }

    public static void setMuted(Player player, Boolean setMuted, String reason) {
        for (userdata user : userdata) {
            if (user.getplayerUUID() == player.getUniqueId()) {
                user.setMuted(setMuted);
                user.setUserMutedReason(reason);
            }
        }
    }

    public static void setFrozen(Player player, Boolean setFrozen, String reason) {
        for (userdata user : userdata) {
            if (user.getplayerUUID() == player.getUniqueId()) {
                user.setUserFrozen(setFrozen);
                user.setUserFrozenReason(reason);
            }
        }
    }

    public static void setAFK(Player player, Boolean setAFK) {
        for (userdata user : userdata) {
            if (user.getplayerUUID() == player.getUniqueId()) user.setStaffAFK(setAFK);
        }
    }

    public static void setNameNotify(Player player,Boolean setNameNotify) {
        for (userdata user : userdata) {
            if (user.getplayerUUID() == player.getUniqueId()) user.setNameNotify(setNameNotify);
        }
    }

    public static void setOnDuty(Player player, Boolean setOnDuty) {
        for (userdata user : userdata) {
            if (user.getplayerUUID() == player.getUniqueId()) user.setOnDuty(setOnDuty);
        }
    }

    public static void setSilentJoin(Player player,Boolean setSilentJoin) {
        for (userdata user : userdata) {
            if (user.getplayerUUID() == player.getUniqueId()) user.setSilentJoin(setSilentJoin);
        }
    }

    public static Optional<userdata> UserSearch(UUID user) {
        return userdata.stream().filter(userdata1 -> userdata1.getplayerUUID().equals(user)).findFirst();
    }
}
