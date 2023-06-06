package me.palkercode.jamdi;

import me.palkercode.jamdi.listeners.MessageListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class JAMDI extends JavaPlugin {
    public static String botToken;
    public static String channelId;
    @Override
    public void onEnable() {
        saveDefaultConfig();
        getConfig().addDefault("bot-token", "YOUR DISCORD TOKEN HERE");
        getConfig().addDefault("channel-id", "1234567890");

        botToken = getConfig().getString("bot-token");
        channelId = getConfig().getString("channel-id");

        try {
            MessageListener.InitializeDML();
        } catch (InterruptedException e) {
            System.out.println("!!!Your discord token in invalid!!!");
            throw new RuntimeException(e);
        }

        getServer().getPluginManager().registerEvents(new MessageListener(), this);
    }

    @Override
    public void onDisable() {

    }
}
