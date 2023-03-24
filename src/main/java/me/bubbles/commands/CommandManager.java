package me.bubbles.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;

public class CommandManager {

    private static ArrayList<Command> commandList = new ArrayList<>();
    private static ArrayList<Command> activeCommands = new ArrayList<>();

    public CommandManager() {
        registerCommands();
    }

    public static ArrayList<Command> getCommandList() {
        return commandList;
    }

    public static ArrayList<Command> getActiveCommands() {
        return activeCommands;
    }

    public void addCommand(Command... commands) {
        for(Command command : commands) {
            commandList.add(command);
            if(command.isEnabled()) {
                activeCommands.add(command);
            }
        }
    }

    public void registerCommands() {
        addCommand(new FixMe(), new List(), new SendAd(), new SendMessage(), new SetFakeName());
    }

    public void onSlashCommandInteractionEvent(SlashCommandInteractionEvent event) {
        for(Command command : activeCommands) {
            if(event.getInteraction().getName().equalsIgnoreCase(command.getCommand())) {
                command.run(event);
            }
        }
    }

}
