package net.mochinekoserver.paint_battle.listener;

import net.mochinekoserver.paint_battle.json.BlockGuardJson;
import net.mochinekoserver.paint_battle.manager.JsonManager;
import net.mochinekoserver.paint_battle.status.FileType;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

import java.util.Map;

public class ProjectileHitListener implements Listener {

    private static final BlockGuardJson json = (BlockGuardJson) new JsonManager(FileType.CONFIG).getDeserializedJson();

    @EventHandler
    public void onHit(ProjectileHitEvent event){
        var entity = event.getHitEntity();
        var hitBlock = event.getHitBlock();
        if (hitBlock == null) return;

        Map<String, BlockGuardJson.GuardData> guardData = json.getGuardData();
        var guard = guardData.get("game_area");
        if (guard.isAABB(hitBlock.getLocation())) {
            hitBlock.setType(Material.WHITE_WOOL);
        }
    }
}
