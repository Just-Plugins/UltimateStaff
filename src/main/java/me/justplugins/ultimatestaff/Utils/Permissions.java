package me.justplugins.ultimatestaff.Utils;

public enum Permissions {

    NO_RULE_BREAKERS_WARN("norulebreakers.warn"),

    DOMAINWHITELIST_BYPASS("domainwhitelist.bypass"),
    DOMAINWHITELIST_BYPASS_STRING("domainwhitelist."),
    REPORT_PLAYER("report"),

    MAIN_COMMAND("maincommand"),

    //Gamemode changing permissions
    GAMEMODE_CREATIVE("gamemode.creative"),
    GAMEMODE_SURVIVAL("gamemode.survival"),
    GAMEMODE_ADVENTURE("gamemode.adventure"),
    GAMEMODE_SPECTATOR("gamemode.spectator"),


    //Staff Permissions
    STAFFMODE("staffmode"),
    STAFFGUI("staffmenu"),
    STAFF_MEMBER("staffmember"),
    STAFF_CHAT("staffchat"),
    VANSIH("vanish"),
    FLY("fly"),
    INVENTORY_SEE("invensee"),
    FREEZE("freeze"),
    PUNISHMENTS("punishments"),
    PUNISH_PLAYER("punish"),
    REPORT("reports"),

    //Admin Permissions
    EDIT_PUNISH_TEMPLATES("edit.punishments"),



    END("end");



    private final String perm;

    Permissions(String perm) {
        this.perm = perm;
    }

    public final String getPermission() {
        return "ultimatestaff." + this.perm;
    }
}

