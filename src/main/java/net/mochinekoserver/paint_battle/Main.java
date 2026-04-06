package net.mochinekoserver.paint_battle;

import net.mochinekoserver.paint_battle.command.GameStartCommand;
import net.mochinekoserver.paint_battle.command.KitCommand;
import net.mochinekoserver.paint_battle.command.TeamCommand;
import net.mochinekoserver.paint_battle.listener.PlayerInteractListener;
import net.mochinekoserver.paint_battle.listener.ProjectileHitListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        var plm = getServer().getPluginManager();
        plm.registerEvents(new PlayerInteractListener(), this);
        plm.registerEvents(new ProjectileHitListener(), this);

        getCommand("game_start").setExecutor(new GameStartCommand());
        getCommand("game_stop").setExecutor(new GameStartCommand());
        getCommand("game_team").setExecutor(new TeamCommand());
        getCommand("kit_select").setExecutor(new KitCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
