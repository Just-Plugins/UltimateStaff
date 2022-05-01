package me.justplugins.ultimatestaff.Modules.Configs.Punishments;

import me.justplugins.ultimatestaff.Utils.PunishTypes;
import org.bukkit.entity.Player;

import java.util.Date;
import java.util.UUID;

public class Punishments {
    private final UUID playerUUID;
    private final String PlayerName, reason;
    private final PunishTypes punishType;
    private final int ID;
    private final Date endDate;


    public Punishments(Player PlayerName, int ID, String reason, PunishTypes punishType, Date endDate) {
        this.PlayerName = PlayerName.getName();
        this.playerUUID = PlayerName.getUniqueId();
        this.reason = reason;
        this.ID = ID;
        this.punishType = punishType;
        this.endDate = endDate;
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

    public PunishTypes getPunishType() {
        return punishType;
    }

    public int getID() {
        return ID;
    }

    public Date getDate() {
        return endDate;
    }

}
