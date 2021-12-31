package me.justplugins.ultimatestaff.GUI.PunishGUI;

import me.justplugins.ultimatestaff.Commands.PunishCommands.PunishTarget;
import me.justplugins.ultimatestaff.Main;

import me.justplugins.ultimatestaff.Modules.Configs.Config;
import me.justplugins.ultimatestaff.Modules.PunishModules;
import me.justplugins.ultimatestaff.Utils.Utils;
import me.nathans212.baseplugin.gui.GUIBuilder;
import me.nathans212.baseplugin.gui.Gui;
import me.nathans212.baseplugin.gui.GuiUtils;
import me.nathans212.baseplugin.gui.compatibility.CompatibleMaterial;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.ArrayList;

import static me.justplugins.ultimatestaff.GUI.PunishGUI.PlayerPunishGui.reasons;
import static me.justplugins.ultimatestaff.GUI.PunishGUI.Stage2.punishType;
import static me.justplugins.ultimatestaff.Utils.Utils.getHead;

public class PlayerPunishGui extends GUIBuilder {
    final Main plugin;
    int p;
    static final ArrayList<String> reasons = new ArrayList<>();

    //static String reason = "null";
    //private static ArrayList<Player> chat_tracking = new ArrayList<>();

    public PlayerPunishGui(Main plugin, Player player) {
        super(plugin, player);
        this.plugin = plugin;
    }

    @Override
    public String Title() {
        return Utils.Color("&8Punishing > " + PunishTarget.punishtarget().getName());
    }

    @Override
    public int Rows() {
        return 6;
    }

    @Override
    public void onOpen(Player player, Gui gui) {
        reasons.clear();
        for (String r : Config.Config.getStringList("Punishments.Reasons")) {
            if (p == 44) {
                break;
            }
            setButton(p, GuiUtils.createButtonItem(CompatibleMaterial.PAPER, Utils.Color("&l" + Utils.Color("&c&l" + Config.Config.getStringList("Punishments.Reasons").get(p)))), guiClickEvent -> {

                if (!reasons.contains(Config.Config.getStringList("Punishments.Reasons").get(guiClickEvent.slot))) {
                    reasons.add(Config.Config.getStringList("Punishments.Reasons").get(guiClickEvent.slot));
                    updateItem(guiClickEvent.slot,CompatibleMaterial.WRITTEN_BOOK, Utils.Color("&a&l" + Config.Config.getStringList("Punishments.Reasons").get(guiClickEvent.slot)));
                    updateItemLore(48,Utils.Color(
                            "&9Punishing: &f" + PunishTarget.punishtarget().getName() +" \n" +
                                    "&9Reasons: &f" + reasons.toString() + "\n" +
                                    "&9Punish Type: &f" + null + "\n" +
                                    "&9Duration: &f" + null + "\n" ));
                } else {
                    reasons.remove(Config.Config.getStringList("Punishments.Reasons").get(guiClickEvent.slot));
                    updateItem(guiClickEvent.slot,CompatibleMaterial.PAPER,Utils.Color("&c&l" + Config.Config.getStringList("Punishments.Reasons").get(guiClickEvent.slot)));
                    updateItemLore(48,Utils.Color(
                            "&9Punishing: &f" + PunishTarget.punishtarget().getName() +" \n" +
                                    "&9Reasons: &f" + reasons.toString() + "\n" +
                                    "&9Punish Type: &f" + null + "\n" +
                                    "&9Duration: &f" + null + "\n" ));
                }
            });
            p++;
        }


        setButton(48, GuiUtils.createButtonItem(getHead(PunishTarget.punishtarget()),
                Utils.Color("&l" + PunishTarget.punishtarget().getName()),
                Utils.Color("&9Punishing: &f" + PunishTarget.punishtarget().getName() +" \n" +
                        "&9Reasons: &f[]" + "\n" +
                        "&9Punish Type: &f" + null + "\n" +
                        "&9Duration: &f" + null + "\n")),
                guiClickEvent -> {});

        setButton(50, GuiUtils.createButtonItem(CompatibleMaterial.EMERALD_BLOCK, Utils.Color("&a&lDone!"), Utils.Color("&7&oClick when you are\n&7&odone selecting reasons.")), guiClickEvent -> {
            player.playSound(player.getLocation(), Sound.ENTITY_CHICKEN_EGG, 100,1);
            new Stage2(plugin,player);
        });
    }
}


// Stage 2 of the punishment action
class Stage2 extends GUIBuilder {
    static String punishType;
    final Main plugin;

    public Stage2(Main plugin, Player player) {
        super(plugin, player);
        this.plugin = plugin;
    }

    @Override
    public String Title() {
        return Utils.Color("&8Punishing > " + PunishTarget.punishtarget().getName());
    }

    @Override
    public int Rows() {
        return 4;
    }

    @Override
    public void onOpen(Player player, Gui gui) {

        setButton(10, GuiUtils.createButtonItem(CompatibleMaterial.OAK_DOOR, Utils.Color("&6&lKick " + PunishTarget.punishtarget().getName())), guiClickEvent -> {
            punishType = "kick";
            player.playSound(player.getLocation(), Sound.ENTITY_CHICKEN_EGG, 100,1);
            new Stage3(plugin,player);
        });

        setButton(12, GuiUtils.createButtonItem(CompatibleMaterial.IRON_AXE, Utils.Color("&6&lBan " + PunishTarget.punishtarget().getName())), guiClickEvent -> {
            punishType = "ban";
            player.playSound(player.getLocation(), Sound.ENTITY_CHICKEN_EGG, 100,1);
            new Stage3(plugin,player);
        });

        setButton(14, GuiUtils.createButtonItem(CompatibleMaterial.DIAMOND_AXE, Utils.Color("&6&lIP-Ban " + PunishTarget.punishtarget().getName())), guiClickEvent -> {
            punishType = "ipban";
            player.playSound(player.getLocation(), Sound.ENTITY_CHICKEN_EGG, 100,1);
            new Stage3(plugin,player);
        });

        setButton(16, GuiUtils.createButtonItem(CompatibleMaterial.NOTE_BLOCK, Utils.Color("&6&lMute " + PunishTarget.punishtarget().getName())), guiClickEvent -> {
            punishType = "mute";
            player.playSound(player.getLocation(), Sound.ENTITY_CHICKEN_EGG, 100,1);
            new Stage3(plugin,player);
        });


        setButton(31, GuiUtils.createButtonItem(getHead(PunishTarget.punishtarget()), Utils.Color("&l" + PunishTarget.punishtarget().getName()),Utils.Color(
                "&9Punishing: &f" + PunishTarget.punishtarget().getName() +" \n" +
                        "&9Reasons: &f" + reasons.toString() + "\n" +
                        "&9Punish Type: &f" + punishType + "\n" +
                        "&9Duration: &f" + null + "\n"
        )), guiClickEvent -> {});

    }
}

class Stage3 extends GUIBuilder {
    final Main plugin;
    public Stage3(Main plugin, Player player) {
        super(plugin, player);
        this.plugin = plugin;
    }

    @Override
    public String Title() {
        return Utils.Color("&8Punishing > " + PunishTarget.punishtarget().getName());
    }

    @Override
    public int Rows() {
        return 4;
    }

    @Override
    public void onOpen(Player player, Gui gui) {

        setButton(13, GuiUtils.createButtonItem(getHead(PunishTarget.punishtarget()), Utils.Color("&l" + PunishTarget.punishtarget().getName()),Utils.Color(
                "&9Punishing: &f" + PunishTarget.punishtarget().getName() +" \n" +
                        "&9Reasons: &f" + reasons + "\n" +
                        "&9Punish Type: &f" + punishType + "\n" +
                        "&9Duration: &f" + null + "\n"
        )), guiClickEvent -> {});

        setButton(31, GuiUtils.createButtonItem(CompatibleMaterial.EMERALD_BLOCK, Utils.Color("&a&lDone!"), Utils.Color("&7&oClick to punish the player.")), guiClickEvent -> {
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 100,1);
            gui.close();
            switch (punishType) {
                case "ban":
                    PunishModules.Ban(player,PunishTarget.punishtarget(),null,reasons.toString());
                    break;
                case "ipban":
                    PunishModules.IPBan(player,PunishTarget.punishtarget(),null,reasons.toString());
                    break;
                case "kick":
                    PunishModules.Kick(player,PunishTarget.punishtarget(),reasons.toString());
                    break;
                case "mute":
                    PunishModules.Mute(player,PunishTarget.punishtarget(),null,reasons.toString());
                    break;
            }

        });

    }
}






        /* Custom reason adder
    @EventHandler
    public void onChatEvent(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();

        if (chat_tracking.contains(player)) {
            e.setCancelled(true);
            reason = e.getMessage();
            new PlayerPunishGui(plugin,player);
        }
    }

    public static String getChatOutput() {
        return reason;
    }

    public static void setChatOutput(String set) {
        reason = set;
    }

         */





