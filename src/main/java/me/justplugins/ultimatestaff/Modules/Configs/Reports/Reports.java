package me.justplugins.ultimatestaff.Modules.Configs.Reports;

import java.util.UUID;

public class Reports {
    private final UUID playerUUID;
    private final String PlayerName, reason, proof, date;
    private final int ID;


    public Reports(String PlayerName, UUID playerUUID, int ID, String reason, String proof, String date) {
        this.PlayerName = PlayerName;
        this.playerUUID = playerUUID;
        this.reason = reason;
        this.proof = proof;
        this.ID = ID;
        this.date = date;
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
