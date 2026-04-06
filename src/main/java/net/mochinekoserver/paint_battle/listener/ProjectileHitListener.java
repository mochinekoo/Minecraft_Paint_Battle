package net.mochinekoserver.paint_battle.listener;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class ProjectileHitListener implements Listener {

    @EventHandler
    public void onHit(ProjectileHitEvent event){
        var entity = event.getHitEntity();
        var hitBlock = event.getHitBlock();
        if (hitBlock == null) return;

        hitBlock.setType(Material.WHITE_WOOL);
    }
}
