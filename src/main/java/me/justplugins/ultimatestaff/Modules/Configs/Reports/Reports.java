package me.justplugins.ultimatestaff.Modules.Configs.Reports;

import org.bukkit.entity.Player;

import java.util.Date;
import java.util.UUID;

public class Reports {
    private final UUID playerUUID;
    private final String PlayerName, reason, proof, date;
    private final int ID;


    public Reports(Player PlayerName, int ID, String reason, String proof) {
        this.PlayerName = PlayerName.getName();
        this.playerUUID = PlayerName.getUniqueId();
        this.reason = reason;
        this.proof = proof;
        this.ID = ID;
        this.date = new Date().toString();
    }

    public String getUser() {
        return this.PlayerName;
    }

    public String getReason() {
        return reason;
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }

    public String getProof() {
        return proof;
    }

    public int getID() {
        return ID;
    }

    public String getDate() {
        return date;
    }

}
