package net.mochinekoserver.paint_battle.listener;

import net.mochinekoserver.paint_battle.Main;
import net.mochinekoserver.paint_battle.manager.ConfigManager;
import net.mochinekoserver.paint_battle.manager.GameManager;
import net.mochinekoserver.paint_battle.manager.TeamManager;
import net.mochinekoserver.paint_battle.status.GameStatus;
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
        var teamManager = TeamManager.getInstance();
        var configManager = ConfigManager.getInstance();

        Bukkit.getScheduler().runTaskLater(Main.getPlugin(Main.class), () -> {
            var bossbar = gameManager.getGameBossBar();
            bossbar.addPlayer(player);
        }, 20L);

        if (gameManager.getStatus() == GameStatus.RUNNING) {
            var joinTeam = teamManager.getJoinGameTeam(player);
            if (joinTeam == null) return;
            var teamSpawn = configManager.getTeamSpawnLocation(joinTeam);
            player.teleport(teamSpawn);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        var player = event.getPlayer();
        var gameManager = GameManager.getInstance();
        var bossbar = gameManager.getGameBossBar();
        bossbar.removePlayer(player);
    }

}
