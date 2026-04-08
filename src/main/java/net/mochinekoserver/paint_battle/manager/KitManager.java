package net.mochinekoserver.paint_battle.manager;

import net.mochinekoserver.paint_battle.library.KitBase;
import net.mochinekoserver.paint_battle.status.KitType;
import net.mochinekoserver.paint_battle.util.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class KitManager {

    private static KitManager instance;
    private static Map<UUID, KitBase> kit_map = new HashMap<>();

    private KitManager() {}

    public static KitManager getInstance() {
        if (instance == null) instance = new KitManager();
        return instance;
    }

    public void setKit(UUID uuid, KitBase kit) {
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(uuid);
        if (offlinePlayer.isOnline()) {
            offlinePlayer.getPlayer().getInventory().addItem(kit.getType().getKitItem());
        }
        kit_map.put(uuid, kit);
    }

    public void setKit(UUID uuid, KitType type) {
        OfflinePlayer player = Bukkit.getOfflinePlayer(uuid);
        setKit(uuid, type.newInstance(player));
        ChatUtil.sendInfoMessage(player.getPlayer(), type.getName() + "が選ばれました。");
    }

    public KitBase getKit(UUID uuid) {
        return kit_map.get(uuid);
    }
}
