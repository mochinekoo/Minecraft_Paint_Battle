package net.mochinekoserver.paint_battle.listener;

import net.mochinekoserver.paint_battle.manager.KitManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        var action = event.getAction();
        var kitManager = KitManager.getInstance();
        var kit = kitManager.getKit(player.getUniqueId());
        if (kit == null) return;
        if (action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK ||
            action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
            kit.fireKit();
        }
    }

}
