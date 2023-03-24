package me.bubbles.events.listeners;

import me.bubbles.Client;
import me.bubbles.log.LogManager;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageDeleteEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Listeners extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        try {
            Client.logManager.log(LogManager.Type.EVENT,"SlashCommandInteractionEvent - "+event.getCommandString()+":"+event.getInteraction().getUser().getId());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Client.commandManager.onSlashCommandInteractionEvent(event);
        Client.eventManager.onEvent(event);
    }

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        try {
            Client.logManager.log(LogManager.Type.EVENT,"ButtonInteractionEvent - "+event.getButton().getId()+":"+event.getInteraction().getUser().getId());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Client.eventManager.onEvent(event);
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        try {
            Client.logManager.log(LogManager.Type.EVENT,"MessageReceivedEvent - "+event.getGuildChannel().getId()+":"+event.getAuthor().getId()+":"+event.getMessage().getContentDisplay());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        Client.eventManager.onEvent(event);
    }

    @Override
    public void onModalInteraction(ModalInteractionEvent event) {
        try {
            Client.logManager.log(LogManager.Type.EVENT, "ModalInteractionEvent - "+event.getModalId()+":"+event.getUser().getId());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Client.eventManager.onEvent(event);
    }

}
