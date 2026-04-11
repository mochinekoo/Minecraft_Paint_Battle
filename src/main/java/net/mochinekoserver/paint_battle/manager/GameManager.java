package net.mochinekoserver.paint_battle.manager;

import net.mochinekoserver.paint_battle.Main;
import net.mochinekoserver.paint_battle.json.BlockGuardJson;
import net.mochinekoserver.paint_battle.library.GameBase;
import net.mochinekoserver.paint_battle.status.FileType;
import net.mochinekoserver.paint_battle.status.GameStatus;
import net.mochinekoserver.paint_battle.status.GameTeam;
import net.mochinekoserver.paint_battle.util.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameManager extends GameBase {

    private static GameManager instance;
    private static final BlockGuardJson json = (BlockGuardJson) new JsonManager(FileType.CONFIG).getDeserializedJson();
    private BossBar bossBar;
    private List<Location> allCount = new ArrayList<>();

    private GameManager() {
        this.bossBar = Bukkit.createBossBar("ゲーム開始待機中...", BarColor.BLUE, BarStyle.SEGMENTED_20);
        this.bossBar.setVisible(true);
    }

    public static GameManager getInstance() {
        if (instance == null) instance = new GameManager();
        return instance;
    }

    @Override
    public int startGame() {
        if (isGameActive()) return -1;
        if (getStatus() != GameStatus.WAITING) return -1;

        var teamManager = TeamManager.getInstance();
        var configManager = ConfigManager.getInstance();
        BukkitTask task = new BukkitRunnable() {
            int countTime = 10;
            @Override
            public void run() {
                if (getStatus() == GameStatus.WAITING || getStatus() == GameStatus.COUNTTING) {
                    if (countTime <= 0) {
                        ChatUtil.sendGlobalInfoMessage("ゲーム開始!");
                        setStatus(GameStatus.RUNNING);

                        teamManager.assignTeam();
                        bossBar.show();
                        for (Player online : Bukkit.getOnlinePlayers()) {
                            var gameTeam = teamManager.getJoinGameTeam(online);
                            bossBar.addPlayer(online);
                            var teamSpawn = configManager.getTeamSpawnLocation(gameTeam);
                            online.teleport(teamSpawn);
                        }
                    }
                    else {
                        String message = String.format("ゲームを開始まであと%d秒", countTime);
                        ChatUtil.sendGlobalInfoMessage(message);
                        countTime--;
                        setStatus(GameStatus.COUNTTING);
                    }
                }
                else if (getStatus() == GameStatus.RUNNING) {
                    String format = "%,.1f%% : %,.1f%%";
                    bossBar.setTitle(format.formatted(getArea(GameTeam.RED) * 100, getArea(GameTeam.BLUE) * 100));

                    subtractTime(1);
                }
            }
        }.runTaskTimer(Main.getPlugin(Main.class), 0L, 20L);
        setTask(task);
        return 0;
    }

    @Override
    public void resetGame() {
        ConfigManager configManager = ConfigManager.getInstance();
        Map<String, BlockGuardJson.GuardData> guardData = json.getGuardData();
        var guard = guardData.get("game_area");
        var start = guard.getStartLocation();
        var end = guard.getEndLocation();
        Location min = new Location(configManager.getGameWorld(),
                Math.min(start.getBlockX(), end.getBlockX()),
                Math.min(start.getBlockY(), end.getBlockY()),
                Math.min(start.getBlockZ(), end.getBlockZ()));
        Location max = new Location(configManager.getGameWorld(),
                Math.max(start.getBlockX(), end.getBlockX()),
                Math.max(start.getBlockY(), end.getBlockY()),
                Math.max(start.getBlockZ(), end.getBlockZ()));
        for (int x = min.getBlockX(); x < max.getBlockX(); ++x) {
            for (int y = min.getBlockY(); y < max.getBlockY(); ++y) {
                for (int z = min.getBlockZ(); z < max.getBlockZ(); ++z) {
                    Location loc = new Location(configManager.getGameWorld(), x, y, z);
                    var block = loc.getBlock();
                    if (block.getType() != Material.AIR) {
                        allCount.add(block.getLocation());
                    }
                }
            }
        }
    }

    private float getArea(GameTeam team) {
        int count = 0;
        for (Location loc : allCount) {
            Block block = loc.getBlock();
            if (block.getType() == team.getTeamBlock()) {
                count++;
            }
        }
        return (float) count / allCount.size();
    }

    public BossBar getGameBossBar() {
        return bossBar;
    }

}
