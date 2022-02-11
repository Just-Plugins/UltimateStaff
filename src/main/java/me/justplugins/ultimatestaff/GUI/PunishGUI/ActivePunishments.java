package me.justplugins.ultimatestaff.GUI.PunishGUI;

import com.songoda.core.compatibility.CompatibleMaterial;
import com.songoda.core.compatibility.CompatibleSound;
import com.songoda.core.gui.Gui;
import com.songoda.core.gui.GuiManager;
import com.songoda.core.gui.GuiUtils;
import me.justplugins.ultimatestaff.GUI.MainGui.MainGui;
import me.justplugins.ultimatestaff.GUI.StaffGui.MainStaffGui;
import me.justplugins.ultimatestaff.Main;
import me.justplugins.ultimatestaff.Modules.Configs.UserData.UserDataManager;
import me.justplugins.ultimatestaff.Modules.Configs.UserData.userdata;
import me.justplugins.ultimatestaff.Modules.PunishModules;
import me.justplugins.ultimatestaff.Utils.Utils;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

import java.util.Optional;

public class ActivePunishments extends Gui {
    final Main plugin;
    static int state;
    private static Optional<userdata> user;

    public static int getState() {
        return state;
    }

    public static void setState(int state) {
        ActivePunishments.state = state;
    }

    public ActivePunishments(Main plugin, Player player) {
        this.plugin = plugin;
        setTitle(Utils.Color("&8StaffGui > Active Punishments"));
        setRows(6);
        setDefaultItem(CompatibleMaterial.AIR.getItem());
        setDefaultSound(CompatibleSound.BLOCK_NOTE_BLOCK_BIT);
        int p = 0;
        String stateName;


        switch(state) {
            case 0:
                stateName = "&c&lBans Only";
                for (OfflinePlayer pl2 : Bukkit.getBannedPlayers()) {
                    if (p == 49) {
                        break;
                    }

                    setButton(p, GuiUtils.createButtonItem(Utils.getHead(pl2), Utils.Color("&l" + pl2.getName()),
                                    Utils.Color("&9Punish Type: &fBan"),
                                    Utils.Color("&9Reason: &f" + Bukkit.getBanList(BanList.Type.NAME).getBanEntry(pl2.getName()).getReason()),
                                    Utils.Color("&9Duration: &f" + Bukkit.getBanList(BanList.Type.NAME).getBanEntry(pl2.getName()).getExpiration()),
                                    Utils.Color(""),
                                    Utils.Color("&cSource: &f" + Bukkit.getBanList(BanList.Type.NAME).getBanEntry(pl2.getName()).getSource()),
                                    Utils.Color("&cCreated: &f" + Bukkit.getBanList(BanList.Type.NAME).getBanEntry(pl2.getName()).getCreated()),
                                    Utils.Color(""),
                                    Utils.Color("&7&oMiddle Click to remove punishment"),
                                    Utils.Color("&7&oRight Click to edit the punishment.")), ClickType.MIDDLE,
                            guiClickEvent -> {
                                PunishModules.unBan(player,pl2);
                                new GuiManager(plugin).showGUI(player,new ActivePunishments(plugin, player));
                                setState(0);
                            });
                    p++;
                }
                setButton(53, GuiUtils.createButtonItem(CompatibleMaterial.IRON_AXE, Utils.Color(stateName), Utils.Color("&7&oClick")), guiClickEvent -> {
                    setState(1);
                    new GuiManager(plugin).showGUI(player,new ActivePunishments(plugin, player));
                });
                break;
            case 1:
                stateName = "&c&lIp-Bans Only";
                for (String pl2 : Bukkit.getIPBans()) {
                    OfflinePlayer pl = Bukkit.getOfflinePlayer(pl2);
                    if (p == 49) {
                        break;
                    }
                    setButton(p, GuiUtils.createButtonItem(Utils.getHead(pl), Utils.Color("&l" + pl.getName()),
                                    Utils.Color("&9Punish Type: &fIpBan"),
                                    Utils.Color("&9Reason: &f" + Bukkit.getBanList(BanList.Type.IP).getBanEntry(pl.getName()).getReason()),
                                    Utils.Color("&9Duration: &f" + Bukkit.getBanList(BanList.Type.IP).getBanEntry(pl.getName()).getExpiration()),
                                    Utils.Color(""),
                                    Utils.Color("&cSource: &f" + Bukkit.getBanList(BanList.Type.IP).getBanEntry(pl.getName()).getSource()),
                                    Utils.Color("&cCreated: &f" + Bukkit.getBanList(BanList.Type.IP).getBanEntry(pl.getName()).getCreated()),
                                    Utils.Color(""),
                                    Utils.Color("&7&oMiddle Click to remove punishment"),
                                    Utils.Color("&7&oRight Click to edit the punishment.")
                            ), ClickType.MIDDLE,
                            guiClickEvent -> {
                                PunishModules.unIpBan(player,pl);
                                setState(1);
                                new GuiManager(plugin).showGUI(player,new ActivePunishments(plugin, player));
                            });
                    p++;
                }
                setButton(53, GuiUtils.createButtonItem(CompatibleMaterial.DIAMOND_AXE, Utils.Color(stateName), Utils.Color("&7&oClick")), guiClickEvent -> {
                    setState(2);
                    new GuiManager(plugin).showGUI(player,new ActivePunishments(plugin, player));
                });
                break;
            case 2:
                stateName = "&c&lMutes Only";
                for (String pl2 : UserDataManager.getMutedList()) {
                    Player pl = Bukkit.getPlayer(pl2);
                    if (p == 49 || p == 0) break;

                    setButton(p, GuiUtils.createButtonItem(Utils.getHead(pl), Utils.Color("&l" + pl.getName()),
                                    Utils.Color("&9Punish Type: &fMute"),
                                    Utils.Color("&9Reason: &f" ),
                                    Utils.Color("&9Duration: &f"),
                                    Utils.Color(""),
                                    Utils.Color("&cSource: &f"),
                                    Utils.Color("&cCreated: &f"),
                                    Utils.Color(""),
                                    Utils.Color("&7&oMiddle Click to remove punishment"),
                                    Utils.Color("&7&oRight Click to edit the punishment.")
                            ), ClickType.MIDDLE,
                            guiClickEvent -> {
                                PunishModules.unMute(player,pl);
                                setState(2);
                                new GuiManager(plugin).showGUI(player,new ActivePunishments(plugin, player));
                            });
                    p++;
                }
                setButton(53, GuiUtils.createButtonItem(CompatibleMaterial.NOTE_BLOCK, Utils.Color(stateName), Utils.Color("&7&oClick")), guiClickEvent -> {
                    setState(3);
                    new GuiManager(plugin).showGUI(player,new ActivePunishments(plugin, player));
                });
                break;
            case 3:
                stateName = "&c&lAll Punishments";
                setButton(53, GuiUtils.createButtonItem(CompatibleMaterial.CHEST, Utils.Color(stateName), Utils.Color("&7&oClick")), guiClickEvent -> {
                    setState(0);
                    new GuiManager(plugin).showGUI(player,new ActivePunishments(plugin, player));
                });
                break;
        }

        //Go Back Button
        setButton(49, GuiUtils.createButtonItem(CompatibleMaterial.ARROW, Utils.Color("&f&lGo Back"),Utils.Color("&7Click to go Back")), (event) -> {
            new GuiManager(plugin).showGUI(player,new MainStaffGui(plugin, player));
        });
    }
}


