package net.mochinekoserver.paint_battle.status;

import net.mochinekoserver.paint_battle.kit.TestKit;
import net.mochinekoserver.paint_battle.library.KitBase;
import org.bukkit.OfflinePlayer;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

public enum KitType {
    TEST_KIT(TestKit.class);

    private final Class<? extends KitBase> kit_class;

    KitType(Class<? extends KitBase> kit_class) {
        this.kit_class = kit_class;
    }

    public KitBase newInstance(OfflinePlayer player) {
        try {
            var constructor = kit_class.getConstructor(OfflinePlayer.class);
            return constructor.newInstance(player);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

}
