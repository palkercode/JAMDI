package me.palkercode.jamdi.listeners;

import me.palkercode.jamdi.JAMDI;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class MessageListener extends ListenerAdapter implements Listener {
    private static final JDA jda = JDABuilder.createDefault(JAMDI.botToken)
            .enableIntents(GatewayIntent.MESSAGE_CONTENT)
            .build();

    public static void InitializeDML() throws InterruptedException {
        jda.awaitReady();

        jda.addEventListener(new MessageListener());
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot() || !event.getChannel().getId().equals(JAMDI.channelId)) return;
        Bukkit.broadcastMessage(JAMDI.discordToMinecraftFormat
                .replace("%discord-nickname%", event.getAuthor().getName())
                .replace("%discord-message%", event.getMessage().getContentRaw()));
    }

    @EventHandler
    private void onMinecraftMessage(AsyncPlayerChatEvent event) {
        TextChannel channel = jda.getTextChannelById(JAMDI.channelId);

        channel.sendMessage(JAMDI.minecraftToDiscordFormat
                .replace("%minecraft-nickname%", event.getPlayer().getName())
                .replace("%minecraft-message%", event.getMessage()))
                .queue();
    }
}
