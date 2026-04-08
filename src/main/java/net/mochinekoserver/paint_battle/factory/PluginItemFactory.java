package net.mochinekoserver.paint_battle.factory;

import net.mochinekoserver.paint_battle.util.ItemUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class PluginItemFactory {

    public static ItemStack createKitSelector() {
        return new ItemUtil(Material.NETHER_STAR,
                ChatColor.GOLD + "キットを選択",
                Arrays.asList(ChatColor.GOLD + "右クリックでキットを選択してください"))
                .buildItemStack();
    }

}
