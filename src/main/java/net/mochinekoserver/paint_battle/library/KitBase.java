package net.mochinekoserver.paint_battle.library;

import net.mochinekoserver.paint_battle.status.KitType;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public abstract class KitBase {

    private KitType kitType;
    protected OfflinePlayer offlineplayer;
    protected float health;
    protected boolean canUse;

    public KitBase(KitType kitType) {
        this.kitType = kitType;
    }

    /**
     * キットの種類
     */
    public KitType getType() {
        return kitType;
    }

    /**
     * 武器の現在の体力
     * @return float型で返す
     */
    public float getHealth() {
        return health;
    }

    /**
     * 武器が使用可能か
     */
    public boolean canUse() {
        return canUse;
    }

    /**
     * この武器を持っているプレイヤー（オフライン）
     */
    public OfflinePlayer getOfflinePlayer() {
        return offlineplayer;
    }

    /**
     * この武器を持っているプレイヤー（オンライン）
     */
    public Player getPlayer() {
        return Bukkit.getPlayer(offlineplayer.getUniqueId());
    }

    /**
     * キットを発動させる関数（例：雪玉を設置する等）
     * @apiNote {@link PlayerInteractEvent} などでこの関数を呼び出す
     */
    public abstract void fireKit();

    /**
     * ブロックを塗る関数
     * @apiNote {@link ProjectileHitEvent} などでこの関数を呼び出す
     */
    public abstract void setBlock(Location centerLoc);
}
