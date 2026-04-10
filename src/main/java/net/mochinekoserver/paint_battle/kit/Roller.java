package net.mochinekoserver.paint_battle.kit;

import net.mochinekoserver.paint_battle.library.KitBase;
import net.mochinekoserver.paint_battle.manager.TeamManager;
import net.mochinekoserver.paint_battle.status.KitType;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;

public class Roller extends KitBase {

    public Roller(OfflinePlayer offlinePlayer) {
        super(KitType.ROLLER);
        this.offlineplayer = offlinePlayer;
        this.health = 100;
        this.canUse = true;
    }

    @Override
    public void fireKit() {

    }

    @Override
    public void setBlock(Location centerLoc) {
        //中心から-1ブロック～+1ブロックまでを塗る
        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                Location loc = new Location(centerLoc.getWorld(), centerLoc.getBlockX() + x, centerLoc.getBlockY(), centerLoc.getBlockZ() + z);
                Block block = loc.getBlock();
                var playerTeam = TeamManager.getInstance().getJoinGameTeam(getPlayer());
                if (block.getType() != Material.AIR) {
                    block.setType(playerTeam.getTeamBlock(), true);
                }
            }
        }
    }
}
