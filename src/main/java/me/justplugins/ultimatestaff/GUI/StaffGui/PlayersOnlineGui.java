package me.justplugins.ultimatestaff.GUI.StaffGui;

import me.justplugins.ultimatestaff.Main;
import me.justplugins.ultimatestaff.Modules.InventoryModules;
import me.justplugins.ultimatestaff.Utils.Permissions;
import me.justplugins.ultimatestaff.Utils.Utils;
import me.nathans212.baseplugin.gui.GUIBuilder;
import me.nathans212.baseplugin.gui.Gui;
import me.nathans212.baseplugin.gui.GuiUtils;
import me.nathans212.baseplugin.gui.compatibility.CompatibleMaterial;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

import static me.justplugins.ultimatestaff.Utils.Utils.getHead;

public class PlayersOnlineGui extends GUIBuilder {
    final Main plugin;
    public PlayersOnlineGui(Main plugin, Player player) {
        super(plugin, player);
        this.plugin = plugin;
    }

    @Override
    public String Title() {
        return Utils.Color("&8StaffGui > Online Players");
    }

    @Override
    public int Rows() {
        return 6;
    }

    int p;

    @Override
    public void onOpen(Player player, Gui gui) {
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
                    Utils.Color("&9Server: &f") + pl.getServer().getServerName(),
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
            new MainStaffGui(plugin, player);
        });
        setButton(53, GuiUtils.createButtonItem(CompatibleMaterial.JACK_O_LANTERN, Utils.Color("&9&lAll Players"), Utils.Color("&7&oClick to only see Staff")), guiClickEvent -> {
            new StaffOnlineGui(plugin, player);
        });
    }
}
