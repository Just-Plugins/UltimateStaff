package me.justplugins.ultimatestaff.Modules.Configs.UserData;

import java.util.ArrayList;
import java.util.UUID;

public class userdata {
    private String PlayerName, UserFrozenReason, UserMutedReason;
    private ArrayList<String> pastPunishments; //todo PastPunishments
    private UUID playerUUID;
    private Boolean NameNotify, SilentJoin, OnDuty, UserFrozen, Muted,StaffAFK;

    public userdata(String PlayerName, UUID playerUUID , Boolean NameNotify , Boolean SilentJoin, Boolean OnDuty, Boolean Muted, String UserMutedReason, Boolean UserFrozen, String UserFrozenReason,Boolean StaffAFK)
    {
        this.PlayerName = PlayerName;
        this.playerUUID = playerUUID;
        this.NameNotify = NameNotify;
        this.SilentJoin = SilentJoin;
        this.StaffAFK = StaffAFK;
        this.OnDuty = OnDuty;
        this.UserFrozen = UserFrozen;
        this.UserFrozenReason = UserFrozenReason;
        this.Muted = Muted;
        this.UserMutedReason = UserMutedReason;
    }

    public String getPlayerName()
    {
        return PlayerName;
    }
    public void setPlayerName(String PlayerName)
    {
        this.PlayerName = PlayerName;
    }

    public UUID getplayerUUID()
    {
        return playerUUID;
    }
    public void setplayerUUID(UUID playerUUID){
        this.playerUUID = playerUUID;
    }

    public Boolean getNameNotify()
    {
        return NameNotify;
    }
    public void setNameNotify(Boolean NameNotify){
        this.NameNotify = NameNotify;
    }

    public Boolean getSilentJoin()
    {
        return SilentJoin;
    }
    public void setSilentJoin(Boolean SilentJoin){
        this.SilentJoin = SilentJoin;
    }

    public Boolean getOnDuty() {
        return this.OnDuty;
    }
    public void setOnDuty(Boolean onDuty) {
        OnDuty = onDuty;
    }

    public Boolean getUserFrozen() {
        return this.UserFrozen;
    }
    public void setUserFrozen(Boolean UserFrozen) {
        this.UserFrozen = UserFrozen;
    }

    public String getUserFrozenReason() {
        return this.UserFrozenReason;
    }
    public void setUserFrozenReason(String UserFrozenReason) {
        this.UserFrozenReason = UserFrozenReason;
    }

    public Boolean getMuted() {
        return this.Muted;
    }
    public void setMuted(Boolean Muted) {
        this.Muted = Muted;
    }

    public String getUserMutedReason() {
        return this.UserMutedReason;
    }
    public void setUserMutedReason(String userMutedReason) {
        this.UserMutedReason = userMutedReason;
    }

    public Boolean getStaffAFK() {
        return this.StaffAFK;
    }

    public void setStaffAFK(Boolean staffAFK) {
        StaffAFK = staffAFK;
    }
}
