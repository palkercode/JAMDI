package me.palkercode.jamdi;

import me.palkercode.jamdi.listeners.MessageListener;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public class JAMDI extends JavaPlugin {
    public static String botToken;
    public static String channelId;

    public static String discordToMinecraftFormat;
    public static String minecraftToDiscordFormat;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getConfig().addDefault("bot-token", "YOUR DISCORD BOT TOKEN HERE");
        getConfig().addDefault("channel-id", "1234567890");

        botToken = getConfig().getString("bot-token");
        channelId = getConfig().getString("channel-id");

        discordToMinecraftFormat = getConfig().getString("discord-to-minecraft-message-format");
        minecraftToDiscordFormat = getConfig().getString("minecraft-to-discord-message-format");

        try {
            MessageListener.InitializeDML();
        } catch (InterruptedException e) {
            System.out.println("!!!Your discord token is invalid!!!");
            throw new RuntimeException(e);
        }

        getServer().getPluginManager().registerEvents(new MessageListener(), this);
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll(this);
    }
}
