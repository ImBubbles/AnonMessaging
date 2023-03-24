package me.bubbles.commands;

import me.bubbles.Client;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.FileNotFoundException;

public class FixMe extends Command {

    public FixMe() {
        super("FixMe",true);
    }

    @Override
    public void run(SlashCommandInteractionEvent event) {

        try {
            if(Client.fakeName.hasFakename(event.getUser().getId())) {

                Client.channelManager.getTextChannel(event.getUser().getId()).getManager().putMemberPermissionOverride(Long.parseLong(event.getUser().getId()), Permission.VIEW_CHANNEL.getRawValue(),Permission.CREATE_INSTANT_INVITE.getRawValue()).complete();

                event.reply("Your permissions have been restored.").queue();

            }else{
                event.reply("You are not eligible to be fixed.").queue();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

}
