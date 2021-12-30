package me.justplugins.ultimatestaff.Modules.featch.NoRuleBreakersCloud.Cloud;


import com.google.gson.annotations.Expose;

import java.util.UUID;

public class CloudRuleBreakers {

    @Expose
    private String Name, PlayerIP, Reason, BanID,Actions;
    @Expose
    private UUID PlayerUUID;
    @Expose
    private Boolean Status;
    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public UUID getUUID() {
        return PlayerUUID;
    }
    public void setUUID(String uuid) {
        UUID name = UUID.fromString(uuid);
        this.PlayerUUID = name;
    }

    public String getIP() {
        return PlayerIP;
    }
    public void setIP(String playerIP) {
        this.PlayerIP = playerIP;
    }

    public String getReason() {
        return Reason;
    }
    public void setReason(String Reason) {
        this.Reason = Reason;
    }

    public String getBanID() {
        return BanID;
    }
    public void setBanID(String BanID) {
        this.BanID = BanID;
    }

    public String getActions() {
        return Actions;
    }
    public void setActions(String Actions) {
        this.Actions = Actions;
    }

    public Boolean getStatus() {
        return Status;
    }
    public void setStatus(Boolean Status) {
        this.Status = Status;
    }

}
