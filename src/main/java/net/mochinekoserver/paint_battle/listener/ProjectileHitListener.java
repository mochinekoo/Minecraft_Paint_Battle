package net.mochinekoserver.paint_battle.listener;

import net.mochinekoserver.paint_battle.json.BlockGuardJson;
import net.mochinekoserver.paint_battle.manager.JsonManager;
import net.mochinekoserver.paint_battle.manager.KitManager;
import net.mochinekoserver.paint_battle.manager.TeamManager;
import net.mochinekoserver.paint_battle.status.FileType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

import java.util.Map;
import java.util.UUID;

public class ProjectileHitListener implements Listener {

    private static final BlockGuardJson json = (BlockGuardJson) new JsonManager(FileType.CONFIG).getDeserializedJson();

    @EventHandler
    public void onHit(ProjectileHitEvent event){
        var entity = event.getEntity(); //エンティティ
        var hitBlock = event.getHitBlock(); //当たったブロック
        var teamManager = TeamManager.getInstance();
        var player = Bukkit.getOfflinePlayer(entity.getName()); //プレイヤー（エンティティのカスタムネームから割り出す）
        var playerTeam = teamManager.getJoinGameTeam(player); //プレイヤーが参加しているチーム
        var kit = KitManager.getInstance().getKit(player.getUniqueId());
        if (hitBlock == null) return; //当たったブロックがない場合は飛ばす

        Map<String, BlockGuardJson.GuardData> guardData = json.getGuardData();
        var guard = guardData.get("game_area");
        if (guard.isAABB(hitBlock.getLocation())) { //ゲームエリア内である
            if (playerTeam == null) {
                //
            }
            else {
                kit.setBlock(hitBlock.getLocation());
            }
        }
    }
}
