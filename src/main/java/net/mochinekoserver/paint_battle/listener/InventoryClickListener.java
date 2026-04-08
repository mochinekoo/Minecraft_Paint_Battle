package net.mochinekoserver.paint_battle.listener;

import net.mochinekoserver.paint_battle.gui.KitSelectGUI;
import net.mochinekoserver.paint_battle.manager.KitManager;
import net.mochinekoserver.paint_battle.status.KitType;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        HumanEntity player = event.getWhoClicked();
        Inventory inv = event.getClickedInventory();
        String title = event.getView().getTitle();
        ItemStack clickItem = event.getCurrentItem();
        KitManager kitManager = KitManager.getInstance();

        if (clickItem == null) return;
        if (clickItem.getType() == Material.AIR) return;
        if (title.equalsIgnoreCase(KitSelectGUI.TITLE)) {
            List<KitType> kitTypeList = Arrays.stream(KitType.values()).toList();
            kitManager.setKit(player.getUniqueId(), kitTypeList.get(event.getRawSlot()));
        }

        player.closeInventory();
    }

}
