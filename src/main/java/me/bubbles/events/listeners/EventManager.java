package me.bubbles.events.listeners;

import me.bubbles.Client;
import me.bubbles.events.*;
import me.bubbles.log.LogManager;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.EventListener;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class EventManager implements EventListener {

    private static ArrayList<EventHandler> eventHandlers = new ArrayList<>();
    private static ArrayList<EventHandler> activeEventHandlers = new ArrayList<>();

    public EventManager() throws IOException {
        registerEvents(new AdButton(), new AdModal(), new MsgModal(), new ReplyButton(), new ServerLink());
        Client.logManager.log(LogManager.Type.STARTUP,"Events started");
    }

    public static ArrayList<EventHandler> getEventHandlerList() {
        return eventHandlers;
    }

    public static ArrayList<EventHandler> getActiveEventHandlers() {
        return activeEventHandlers;
    }

    public void addEventHandler(EventHandler... events) {
        for(EventHandler event : events) {
            eventHandlers.add(event);
            if(event.isEnabled()) {
                activeEventHandlers.add(event);
            }
        }
    }

    private void registerEvents(EventHandler... object) {
        Client.getClient().addEventListener(new Listeners());
       addEventHandler(object);
    }

    @Override
    public void onEvent(GenericEvent genericEvent) {
        for(EventHandler event : activeEventHandlers) {
            if(genericEvent.getClass().equals(event.getEventClass())) {
                try {
                    event.onEvent(genericEvent);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
