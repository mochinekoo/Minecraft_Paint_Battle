package net.mochinekoserver.paint_battle.listener;

import net.mochinekoserver.paint_battle.json.BlockGuardJson;
import net.mochinekoserver.paint_battle.manager.JsonManager;
import net.mochinekoserver.paint_battle.status.FileType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Map;

public class BlockBreakListener implements Listener {

    private static final BlockGuardJson json = (BlockGuardJson) new JsonManager(FileType.CONFIG).getDeserializedJson();
    
    @EventHandler
    public void onGuard(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Map<String, BlockGuardJson.GuardData> guardData = json.getGuardData();
        for (BlockGuardJson.GuardData guard : guardData.values()) {
            if (guard.isAABB(event.getBlock().getLocation())) {
                event.setCancelled(true);
            }
        }
    }
    
}
