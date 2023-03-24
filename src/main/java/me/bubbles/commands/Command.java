package me.bubbles.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class Command {
    private String name;
    private String command;
    private boolean enabled;

    public Command(String name, boolean enabled) {
        this.name=name;
        this.command=name.toLowerCase();
        this.enabled=enabled;
    }

    public String getName() {
        return name;
    }

    public String getCommand() {
        return command;
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

    public void run(SlashCommandInteractionEvent event) {

    }

}
