package me.justplugins.ultimatestaff.Utils;

import me.justplugins.ultimatestaff.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Random;

import static org.bukkit.Bukkit.getServer;

public class Utils implements Listener {

    public Utils(Main main) {
        getServer().getPluginManager().registerEvents(this, main);
    }

    public static int idGen() {
        Random id = new Random( System.currentTimeMillis() );
        return 10000 + id.nextInt(50000);
    }

    public static String prefix() {
        return "&aUltimateStaff &7| ";
    }


    public static ItemStack getHead(OfflinePlayer player) {
        ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta skull = (SkullMeta) item.getItemMeta();
        skull.setDisplayName(player.getName());
        ArrayList<String> lore = new ArrayList<String>();
        skull.setLore(lore);
        skull.setOwner(player.getName());
        item.setItemMeta(skull);
        return item;
    }

    /**
     * @param text add the string you want to colorize
     * @return the text with colours
     */
    public static String Color(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }


    /**
     * @param usage add the usage
     * @return the usage within a nice message
     */
    public static String Usage(String usage) {
        return Color(prefix() + "&fUse " + usage + "");
    }

    /**
     * @param whatdoesntexist enter a subject that doesn't exist
     * @return the subject within a nice message
     */
    public static String DoesNotExist(String whatdoesntexist) {
        return Color(prefix()  + "&f" + whatdoesntexist + " does not exist.");
    }

    /**
     * @param forwhat Add the subject the player doesn't have a permission for
     * @return the permission within a nice message
     */
    public static String NoPermission(String forwhat) {
        return Color(prefix()  + "&fYou dont have permission to do " + forwhat);
    }


}
