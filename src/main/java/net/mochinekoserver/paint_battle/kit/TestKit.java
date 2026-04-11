package net.mochinekoserver.paint_battle.kit;

import net.mochinekoserver.paint_battle.library.KitBase;
import net.mochinekoserver.paint_battle.manager.TeamManager;
import net.mochinekoserver.paint_battle.status.KitType;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.util.Vector;

public class TestKit extends KitBase {

    public TestKit(OfflinePlayer offlinePlayer) {
        super(KitType.TEST_KIT);
        this.offlineplayer = offlinePlayer;
        this.health = 100;
        this.canUse = true;
    }

    @Override
    public void fireKit() {
        Player player = getPlayer();
        Location loc = player.getEyeLocation().clone();
        Vector direction = loc.getDirection().normalize().clone();
        var amount = 5;
        var totalAngle = Math.toRadians(180);
        var duration = totalAngle / amount;
        var start = -totalAngle / 2;
        for (int i = 0; i < amount; i++) {
            double angle = start + (duration * i);
            Vector newVec = direction.clone().rotateAroundY(angle).multiply(0.5);
            Snowball snowBall = player.getWorld().spawn(loc, Snowball.class);
            snowBall.setCustomName(player.getName());
            snowBall.setVelocity(newVec);
        }

    }

    @Override
    public void setBlock(Location centerLoc) {
        var playerTeam = TeamManager.getInstance().getJoinGameTeam(getPlayer());
        centerLoc.getBlock().setType(playerTeam.getTeamBlock(), true);
    }

}
