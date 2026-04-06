package net.mochinekoserver.paint_battle.kit;

import net.mochinekoserver.paint_battle.library.KitBase;
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

    public void fireKit() {
        Player player = getPlayer();
        Location loc = player.getLocation();
        Vector direction = loc.getDirection();
        Vector newVec = direction.multiply(2);
        Snowball snowBall = player.getWorld().spawn(loc, Snowball.class);
        snowBall.setCustomName(player.getName());
        snowBall.setVelocity(newVec);
    }

}
