package net.mochinekoserver.paint_battle;

import net.mochinekoserver.paint_battle.command.GameStartCommand;
import net.mochinekoserver.paint_battle.command.KitCommand;
import net.mochinekoserver.paint_battle.command.TeamCommand;
import net.mochinekoserver.paint_battle.factory.PluginItemFactory;
import net.mochinekoserver.paint_battle.listener.*;
import net.mochinekoserver.paint_battle.manager.GameManager;
import net.mochinekoserver.paint_battle.manager.JsonManager;
import net.mochinekoserver.paint_battle.status.FileType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.getInventory().addItem(PluginItemFactory.createKitSelector());
        }

        saveDefaultConfig();

        for (FileType fileType : FileType.values()) {
            new JsonManager(fileType).createJson();
        }
        GameManager.getInstance().resetGame();

        var plm = getServer().getPluginManager();
        plm.registerEvents(new PlayerInteractListener(), this);
        plm.registerEvents(new ProjectileHitListener(), this);
        plm.registerEvents(new BlockBreakListener(), this);
        plm.registerEvents(new PlayerChatListener(), this);
        plm.registerEvents(new InventoryClickListener(), this);
        plm.registerEvents(new PlayerMoveListener(), this);

        getCommand("game_start").setExecutor(new GameStartCommand());
        getCommand("game_stop").setExecutor(new GameStartCommand());
        getCommand("game_team").setExecutor(new TeamCommand());
        getCommand("kit_select").setExecutor(new KitCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        GameManager.getInstance().resetGame();
    }
}
