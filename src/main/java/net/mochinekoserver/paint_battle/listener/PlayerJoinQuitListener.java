package net.mochinekoserver.paint_battle.listener;

import net.mochinekoserver.paint_battle.Main;
import net.mochinekoserver.paint_battle.manager.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoinQuitListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        var player = event.getPlayer();
        var gameManager = GameManager.getInstance();

        Bukkit.getScheduler().runTaskLater(Main.getPlugin(Main.class), () -> {
            var bossbar = gameManager.getGameBossBar();
            bossbar.addPlayer(player);
        }, 20L);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        var player = event.getPlayer();
        var gameManager = GameManager.getInstance();
        var bossbar = gameManager.getGameBossBar();
        bossbar.removePlayer(player);
    }

}
