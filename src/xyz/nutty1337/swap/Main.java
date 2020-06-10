package xyz.nutty1337.swap;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public static Main INSTANCE;
    public static boolean GameState = false;
    public static Thread SwapThread = new GameThread();
    public static Thread InvulThread = new GodThread();

    @Override
    public void onEnable() {
        saveDefaultConfig();
        INSTANCE = this;
        getCommand("startswap").setExecutor(new Swap());
        getCommand("stopswap").setExecutor(new Swap());

        getServer().getPluginManager().registerEvents(new DeathEvent(), this);
    }
    @Override
    public void onDisable() {
        try {
            Main.INSTANCE.GameState = false;
            Main.INSTANCE.InvulThread.interrupt(); // stop thread
            Main.INSTANCE.SwapThread.interrupt(); // stop thread
            Main.INSTANCE.InvulThread = new GodThread(); // reset thread
            Main.INSTANCE.SwapThread = new GameThread(); // reset thread
        }
        catch (Exception ex) {

        }

    }
}
