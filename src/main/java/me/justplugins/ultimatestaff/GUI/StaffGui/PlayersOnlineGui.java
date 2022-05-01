package me.justplugins.ultimatestaff.GUI.StaffGui;

import com.songoda.core.compatibility.CompatibleMaterial;
import com.songoda.core.compatibility.CompatibleSound;
import com.songoda.core.gui.Gui;
import com.songoda.core.gui.GuiManager;
import com.songoda.core.gui.GuiUtils;
import me.justplugins.ultimatestaff.Main;
import me.justplugins.ultimatestaff.Modules.InventoryModules;
import me.justplugins.ultimatestaff.Utils.Permissions;
import me.justplugins.ultimatestaff.Utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

import static me.justplugins.ultimatestaff.Utils.Utils.getHead;

public class PlayersOnlineGui extends Gui {
    final Main plugin;
    int p;
    Player player;
    public PlayersOnlineGui(Main plugin, Player player, Page page) {
        this.plugin = plugin;
        this.player = player;
        setTitle(Utils.Color("&8StaffGui > Online Players"));
        setRows(6);
        setDefaultItem(CompatibleMaterial.AIR.getItem());
        setDefaultSound(CompatibleSound.BLOCK_NOTE_BLOCK_BIT);
        p = 0;

        if (page == Page.PLAYERS) {
            Players();
        }

        if (page == Page.STAFF) {
            Staff();
        }
    }

    private void Players() {
        for (Player pl : Bukkit.getOnlinePlayers()) {
            if (p == 49) {
                break;
            }
            setButton(p, GuiUtils.createButtonItem(getHead(pl), Utils.Color("&l" + pl.getDisplayName())), ClickType.RIGHT, guiClickEvent -> player.teleport(pl));
            setAction(p, ClickType.LEFT, guiClickEvent -> InventoryModules.seeInventory(player, pl));

            updateItemLore(p,
                    Utils.Color("&9Rank: &f" + pl.getPlayer().getPlayerListName()),
                    Utils.Color("&9Gamemode: &f" + pl.getPlayer().getGameMode().name()),
                    Utils.Color("&9World: &f") + pl.getLocation().getWorld().getName(),
                    Utils.Color("&9Server: &fN/A"),
                    Utils.Color(""),
                    Utils.Color("&9Ping: &fN/A"),
                    Utils.Color("&9Health: &f" + pl.getHealthScale()),
                    Utils.Color(""),
                    Utils.Color("&cIsStaff: &f" + pl.hasPermission(Permissions.STAFF_MEMBER.getPermission())),
                    Utils.Color("&cStaffMode: &ffalse"),
                    Utils.Color(""),
                    Utils.Color("&7&oRight Click to teleport"),
                    Utils.Color("&7&oLeft Click to look in their inventory.")
            );
            p++;
        }

        setButton(49, GuiUtils.createButtonItem(CompatibleMaterial.ARROW, Utils.Color("&f&lGo Back"),Utils.Color("&7Click to go Back")), (event) -> {
            new GuiManager(plugin).showGUI(player,new MainStaffGui(plugin, player));
        });
        setButton(53, GuiUtils.createButtonItem(CompatibleMaterial.JACK_O_LANTERN, Utils.Color("&9&lAll Players"), Utils.Color("&7&oClick to only see Staff")), guiClickEvent -> {
            reset();
            Staff();
        });
    }
    private void Staff() {
        for (Player pl : Bukkit.getOnlinePlayers()) {
            if (p == 49) {
                break;
            }
            if (pl.hasPermission(Permissions.STAFF_MEMBER.getPermission())) {
                setButton(p, GuiUtils.createButtonItem(getHead(pl), Utils.Color("&l" + pl.getDisplayName())), ClickType.RIGHT, guiClickEvent -> player.teleport(pl));
                setAction(p, ClickType.LEFT, guiClickEvent -> InventoryModules.seeInventory(player, pl));

                updateItemLore(p,
                        Utils.Color("&9Rank: &f" + pl.getPlayer().getPlayerListName()),
                        Utils.Color("&9Gamemode: &f" + pl.getPlayer().getGameMode().name()),
                        Utils.Color("&9World: &f") + pl.getLocation().getWorld().getName(),
                        Utils.Color("&9Server: &f") + pl.getServer().getName(),
                        Utils.Color(""),
                        Utils.Color("&9Ping: &f69ms"),
                        Utils.Color("&9Health: &f" + pl.getHealthScale()),
                        Utils.Color(""),
                        Utils.Color("&cIsStaff: &f" + pl.hasPermission(Permissions.STAFF_MEMBER.getPermission())),
                        Utils.Color("&cStaffMode: &ffalse"),
                        Utils.Color(""),
                        Utils.Color("&7&oRight Click to teleport"),
                        Utils.Color("&7&oLeft Click to look in their inventory.")
                );
                p++;
            }

            setButton(49, GuiUtils.createButtonItem(CompatibleMaterial.ARROW, Utils.Color("&f&lGo Back"),Utils.Color("&7Click to go Back")), (event) -> {
                new GuiManager(plugin).showGUI(player,new MainStaffGui(plugin, player));
            });
            setButton(53, GuiUtils.createButtonItem(CompatibleMaterial.PUMPKIN, Utils.Color("&c&lStaff Only"), Utils.Color("&7&oClick to see everyone")), guiClickEvent -> {
                reset();
                Staff();
            });
        }
    }

    protected enum Page {
        STAFF,
        PLAYERS
    }
}
