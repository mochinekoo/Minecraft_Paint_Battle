package net.mochinekoserver.paint_battle.library;

import net.mochinekoserver.paint_battle.status.KitType;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

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
        return offlineplayer.getPlayer();
    }

    public abstract void fireKit();
}
