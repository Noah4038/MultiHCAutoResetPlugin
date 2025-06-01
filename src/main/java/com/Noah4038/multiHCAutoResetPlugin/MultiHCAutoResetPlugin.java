package com.Noah4038.multiHCAutoResetPlugin;

import org.bukkit.*;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class MultiHCAutoResetPlugin extends JavaPlugin implements Listener {

    private final Set<String> deadPlayersInEnd = new HashSet<>();
    private boolean restartScheduled = false;
    private final int RESTART_DELAY_SECONDS = 10;

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("HardcoreDeathReset enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("HardcoreDeathReset disabled.");
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player deadPlayer = event.getEntity();
        World world = deadPlayer.getWorld();

        if (restartScheduled) return;

        if (world.getEnvironment() == Environment.THE_END) {
            deadPlayersInEnd.add(deadPlayer.getName());
            long totalPlayers = Bukkit.getOnlinePlayers().stream()
                    .filter(p -> p.getWorld().getEnvironment() == Environment.THE_END)
                    .count();
            if (deadPlayersInEnd.size() >= totalPlayers) {
                scheduleRestart(deadPlayer.getName());
            }
        } else {
            scheduleRestart(deadPlayer.getName());
        }
    }

    private void scheduleRestart(String deadPlayerName) {
        restartScheduled = true;
        new BukkitRunnable() {
            int secondsLeft = RESTART_DELAY_SECONDS;

            @Override
            public void run() {

                if (secondsLeft ==10) {
                    Bukkit.broadcastMessage(ChatColor.RED + deadPlayerName + "が死亡しました。ワールドリセットを開始します" + ChatColor.YELLOW + " 2秒後にスペクテイターモードへ移行します。");
                }
                if (secondsLeft == 8) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.setGameMode(GameMode.SPECTATOR);
                    }
                }
                if (secondsLeft > 0) {
                    Bukkit.broadcastMessage("サーバー再起動まで残り" + secondsLeft + "秒");
                    secondsLeft--;
                } else {
                    Bukkit.broadcastMessage("サーバーを再起動します...");
                    createResetFlag();
                    Bukkit.shutdown();
                    cancel();
                }
            }
        }.runTaskTimer(this, 0L, 20L);
    }

    private void createResetFlag() {
        try {
            File flag = new File(getDataFolder().getParentFile().getParentFile(), "reset.flag");
            if (!flag.exists()) {
                flag.createNewFile();
            }
        } catch (Exception e) {
            getLogger().warning("Failed to create reset.flag: " + e.getMessage());
        }
    }
}