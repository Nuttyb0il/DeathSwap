package xyz.nutty1337.swap;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathEvent implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        if(Main.INSTANCE.GameState == true) {
            Main.INSTANCE.GameState = false;
            Player p = e.getEntity();
            p.getServer().broadcastMessage("§c§l" + p.getName() + " has lost the game.");
            p.getServer().dispatchCommand(Bukkit.getConsoleSender(), "stopswap"); // Stop swap game
            if(Main.INSTANCE.getConfig().getBoolean("deathSound")) {
                for (Player t : p.getServer().getOnlinePlayers()) {
                    t.playSound(t.getLocation(), Sound.ENTITY_WITHER_DEATH, 1.0F, 1.0F);
                }
            }
        }
    }
}
