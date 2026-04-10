package net.mochinekoserver.paint_battle.listener;

import net.mochinekoserver.paint_battle.manager.KitManager;
import net.mochinekoserver.paint_battle.status.KitType;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        var player = event.getPlayer();
        var playerinv = player.getInventory();
        var playerLoc = player.getLocation();
        var kitManager = KitManager.getInstance();
        var kit = kitManager.getKit(player.getUniqueId());
        var from = event.getFrom();
        var to = event.getTo();
        if (kit == null) return;
        if (!playerinv.getItemInMainHand().isSimilar(kit.getType().getKitItem())) return; //持っているメインハンドがキットじゃない場合は飛ばす
        if (!player.isOnGround()) return; //地面にいないなら飛ばす

        if (kit.getType() == KitType.ROLLER) {
            var underLoc = playerLoc.clone().subtract(0, 1, 0);
            if (underLoc.getBlock().getType() == Material.AIR) return;
            kit.setBlock(underLoc);
        }
    }

}
