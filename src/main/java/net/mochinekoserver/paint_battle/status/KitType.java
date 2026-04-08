package net.mochinekoserver.paint_battle.status;

import net.mochinekoserver.paint_battle.kit.TestKit;
import net.mochinekoserver.paint_battle.library.KitBase;
import net.mochinekoserver.paint_battle.util.ItemUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.UUID;

public enum KitType {
    TEST_KIT("テストキット",
            TestKit.class,
            new ItemUtil(
                    Material.BEDROCK,
                    ChatColor.GOLD + "テスト武器",
                    Arrays.asList(ChatColor.GOLD + "これはテスト武器です")
            )
                    .setProperty(ItemStackProperty.KIT_ITEM)
                    .buildItemStack()
    );

    private final String name;
    private final Class<? extends KitBase> kit_class;
    private final ItemStack itemStack;

    KitType(String name, Class<? extends KitBase> kit_class, ItemStack itemStack) {
        this.name = name;
        this.kit_class = kit_class;
        this.itemStack = itemStack;
    }

    public String getName() {return name;}

    public Class<? extends KitBase> getKitClass() {
        return kit_class;
    }

    public ItemStack getKitItem() {
        return itemStack;
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
