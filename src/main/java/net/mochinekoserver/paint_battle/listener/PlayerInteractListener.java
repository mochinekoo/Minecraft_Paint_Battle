package net.mochinekoserver.paint_battle.listener;

import net.mochinekoserver.paint_battle.factory.PluginItemFactory;
import net.mochinekoserver.paint_battle.gui.KitSelectGUI;
import net.mochinekoserver.paint_battle.manager.KitManager;
import net.mochinekoserver.paint_battle.status.ItemStackProperty;
import net.mochinekoserver.paint_battle.util.ItemUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onKitClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        var inv = player.getInventory();
        var mainHand = inv.getItemInMainHand();
        var action = event.getAction();
        var kitManager = KitManager.getInstance();
        var kit = kitManager.getKit(player.getUniqueId());

        if (kit == null) return;
        if (!ItemUtil.containsProperty(mainHand, ItemStackProperty.KIT_ITEM)) return; //キットアイテム以外
        if (action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK ||
            action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
            kit.fireKit();
        }
    }

    @EventHandler
    public void onItemClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        var inv = player.getInventory();
        var mainHand = inv.getItemInMainHand();
        var action = event.getAction();

        if (action == Action.PHYSICAL) return;
        if (mainHand.isSimilar(PluginItemFactory.createKitSelector())) {
            KitSelectGUI.openGUI(player);
        }
    }

}
