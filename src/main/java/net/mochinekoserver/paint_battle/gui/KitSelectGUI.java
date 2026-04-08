package net.mochinekoserver.paint_battle.gui;

import net.mochinekoserver.paint_battle.library.BaseGUI;
import net.mochinekoserver.paint_battle.status.KitType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

public class KitSelectGUI extends BaseGUI {

    public static final String TITLE = ChatColor.GOLD + "キットを選択してください";

    public static void openGUI(Player player) {
        Inventory inv = Bukkit.createInventory(null, 9*5, TITLE);
        for (KitType kitType : KitType.values()) {
            inv.addItem(kitType.getKitItem());
        }
        player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 50, 1);
        player.openInventory(inv);
    }
}
