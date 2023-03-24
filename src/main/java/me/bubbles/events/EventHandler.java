package me.bubbles.events;

import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.events.GenericEvent;

import java.io.FileNotFoundException;

public class EventHandler {
    private Class event;
    private boolean enabled;

    public EventHandler(Class event, boolean enabled) {
        this.event=event;
        this.enabled=enabled;
    }

    public Class getEventClass() {
        return event;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void toggleEnabled() {
        this.enabled=!this.enabled;
    }

    public void onEnable(Object listener) {
    }

    public void onDisable() {

    }

    public void onEvent(GenericEvent event) throws FileNotFoundException {

    }

}
