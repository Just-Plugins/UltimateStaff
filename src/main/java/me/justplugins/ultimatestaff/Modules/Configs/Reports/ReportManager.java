package me.justplugins.ultimatestaff.Modules.Configs.Reports;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.songoda.core.SongodaCore;
import com.songoda.core.SongodaPlugin;
import com.songoda.core.chat.ChatMessage;
import com.songoda.core.chat.ColorCode;
import com.songoda.core.locale.Message;
import me.justplugins.ultimatestaff.Main;
import me.justplugins.ultimatestaff.Utils.Permissions;
import me.justplugins.ultimatestaff.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class ReportManager {
    static Main plugin;
    public ReportManager(Main plugin) {
        ReportManager.plugin = plugin;
        LoadReports();
    }

    private static ArrayList<Reports> ReportsList = new ArrayList<>();

    public static Reports createReport(Player player, int id, String reason, String proof){
        Optional<Reports> u = ReportSearch(id);
        u.ifPresent(reports -> ReportsList.remove(reports));
        Reports reports = new Reports(player, id, reason, proof);
        ReportsList.add(reports);
        try {
            SaveReport();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reports;
    }

    public static void SaveReport() throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        File file = new File(plugin.getDataFolder().getAbsolutePath() + "/Reports.json");
        Writer writer = new FileWriter(file, false);
        gson.toJson(ReportsList, writer);
        writer.flush();
        writer.close();
    }

    public static ArrayList<Reports> getReports() {
        return ReportsList;
    }

    public static void CloseReport(int id) {
        Optional<Reports> u = ReportSearch(id);
        u.ifPresent(reports -> ReportsList.remove(reports));
        try {
            SaveReport();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static long getOpenReports() {
        return ReportsList.size();
    }

    public static Optional<Reports> ReportSearch(int id) {
        return ReportsList.stream().filter(Reports -> Reports.getID() == id).findAny();
    }

    public static List<String> getPlayers() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        File file = new File(plugin.getDataFolder().getAbsolutePath() + "/Reports.json");
        Reader reader = null;
        try {
            reader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Reports[] reports = gson.fromJson(reader, Reports[].class);
        List<Reports> reportsList = Arrays.asList(reports);

        return reportsList.stream().map(Reports::getUser).collect(Collectors.toList());
    }

    public static List<UUID> getUUIDs() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        File file = new File(plugin.getDataFolder().getAbsolutePath() + "/Reports.json");
        Reader reader = null;
        try {
            reader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Reports[] reports = gson.fromJson(reader, Reports[].class);
        List<Reports> reportsList = Arrays.asList(reports);

        List<UUID> uuids = reportsList.stream().map(Reports::getPlayerUUID).collect(Collectors.toList());
        return uuids;
    }

    public static List<Integer> getIDs() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        File file = new File(plugin.getDataFolder().getAbsolutePath() + "/Reports.json");
        Reader reader = null;
        try {
            reader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Reports[] reports = gson.fromJson(reader, Reports[].class);
        List<Reports> reportsList = Arrays.asList(reports);

        List<Integer> ids = reportsList.stream().map(Reports::getID).collect(Collectors.toList());
        return ids;
    }

    public static List<String> getProofs() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        File file = new File(plugin.getDataFolder().getAbsolutePath() + "/Reports.json");
        Reader reader = null;
        try {
            reader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Reports[] reports = gson.fromJson(reader, Reports[].class);
        List<Reports> reportsList = Arrays.asList(reports);

        List<String> proofs = reportsList.stream().map(Reports::getProof).collect(Collectors.toList());
        return proofs;
    }

    public static List<String> getReasons() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        File file = new File(plugin.getDataFolder().getAbsolutePath() + "/Reports.json");
        Reader reader = null;
        try {
            reader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Reports[] reports = gson.fromJson(reader, Reports[].class);
        List<Reports> reportsList = Arrays.asList(reports);

        List<String> reason = reportsList.stream().map(Reports::getReason).collect(Collectors.toList());
        return reason;
    }

    public static List<String> getDates() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        File file = new File(plugin.getDataFolder().getAbsolutePath() + "/Reports.json");
        Reader reader = null;
        try {
            reader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Reports[] reports = gson.fromJson(reader, Reports[].class);
        List<Reports> reportsList = Arrays.asList(reports);

        List<String> dates = reportsList.stream().map(Reports::getDate).collect(Collectors.toList());
        return dates;
    }

    public void LoadReports() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        File file = new File(plugin.getDataFolder().getAbsolutePath() + "/Reports.json");
        if (file.exists()) {
            Reader reader = null;
            try {
                reader = new FileReader(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Reports[] n = gson.fromJson(reader, Reports[].class);
            ReportsList = new ArrayList<>(Arrays.asList(n));
        }
    }


}