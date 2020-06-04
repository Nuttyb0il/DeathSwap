package xyz.nutty1337.swap;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class Swap implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
        if (lbl.equalsIgnoreCase("startswap")) {
            if(Main.INSTANCE.GameState) {
                sender.sendMessage("§c[!]§a The game has already started.");
            }
            else {
                Main.INSTANCE.GameState = true;
                if(Main.INSTANCE.getConfig().getBoolean("randomTp")) {
                    sender.getServer().broadcastMessage("§6§lTeleporting players randomly..");
                    Random r = new Random();
                    for (Player p : sender.getServer().getOnlinePlayers()) {
                        double x = r.nextInt(500000);
                        double y = 250;
                        double z = r.nextInt(500000);
                        p.teleport(new Location(p.getWorld(), x,y,z));
                    }
                }
                sender.getServer().getWorld("world").setTime(0);
                for (Player p : sender.getServer().getOnlinePlayers()) {
                    p.setFoodLevel(20);
                    p.setSaturation(20);
                    p.setHealth(20);
                    p.setExp(0);
                    if(Main.INSTANCE.getConfig().getBoolean("prepGod")) {
                        p.setInvulnerable(true);
                    }
                    if(Main.INSTANCE.getConfig().getBoolean("prepSpeed")) {
                        p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Main.INSTANCE.getConfig().getInt("speedTime"), 5));
                    }
                }
                Main.INSTANCE.SwapThread.start();
                if(Main.INSTANCE.getConfig().getBoolean("prepGod")) {
                    Main.INSTANCE.InvulThread.start();
                }
                sender.getServer().broadcastMessage("§6§lDeathSwap has started. (" + sender.getName() + ")");
            }
        }
        else if(lbl.equalsIgnoreCase("stopswap")) {
            Main.INSTANCE.GameState = false;
            Main.INSTANCE.InvulThread.interrupt(); // stop thread
            Main.INSTANCE.SwapThread.interrupt(); // stop thread
            Main.INSTANCE.InvulThread = new GodThread(); // reset thread
            Main.INSTANCE.SwapThread = new GameThread(); // reset thread

            for (Player p : sender.getServer().getOnlinePlayers()) {
                p.setInvulnerable(false);
            }
            sender.getServer().broadcastMessage("§c§lDeathSwap has been stopped. (" + sender.getName() + ")");
        }
        return false;
    }
}
