package xyz.nutty1337.swap;

import org.bukkit.entity.Player;

public class GodThread extends Thread{
    public void run() {
        Main.INSTANCE.getServer().broadcastMessage("§6§lPlayers are invulnerable for " + String.valueOf(Main.INSTANCE.getConfig().getInt("prepTime")) + " minutes.");
        try {
            Thread.sleep(Main.INSTANCE.getConfig().getInt("prepTime") * 60000);
        } catch (InterruptedException e) {
            return;
        }
        for (Player p : Main.INSTANCE.getServer().getOnlinePlayers()) {
            p.setInvulnerable(false);
        }
        Main.INSTANCE.getServer().broadcastMessage("§6§lPlayers are no longer invulnerable. Good luck !");
    }
}
