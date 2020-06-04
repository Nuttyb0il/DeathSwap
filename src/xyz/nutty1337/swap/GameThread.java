package xyz.nutty1337.swap;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Random;

public class GameThread extends Thread {
    public void run() {

        while (Main.INSTANCE.GameState == true) {
            try {
                int sleeptime = RNG() * 1000;
                Thread.sleep(sleeptime);
                int secs = 10;
                for (int i = 0; i < 10; i++) {
                    Main.INSTANCE.getServer().broadcastMessage("§6Swapping in " + String.valueOf(secs) + "s");
                    secs -= 1;
                    Thread.sleep(1000);
                }

                ArrayList<Player> a = new ArrayList();
                for (Player p : Main.INSTANCE.getServer().getOnlinePlayers()) { // garbage
                    a.add(p);
                }

                Location p1 = a.get(0).getLocation();
                Location p2 = a.get(1).getLocation();
                a.get(0).teleport(p2);
                a.get(1).teleport(p1);
                Main.INSTANCE.getServer().broadcastMessage("§aSwapped !");

            } catch (InterruptedException e) {
                return;
            }
        }

    }
    public static int RNG() {
        Random random = new Random();
        return random.nextInt(Main.INSTANCE.getConfig().getInt("maxSeconds")) + Main.INSTANCE.getConfig().getInt("minSeconds");
    }
}