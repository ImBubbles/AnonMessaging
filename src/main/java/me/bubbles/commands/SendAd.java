package me.bubbles.commands;

import me.bubbles.Client;
import me.bubbles.modal.AdModalMaker;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.io.FileNotFoundException;

public class SendAd extends Command {

    public SendAd() {
        super("ad",true);
    }

    @Override
    public void run(SlashCommandInteractionEvent event) {

        if(event.getInteraction().getName().equals("ad")) {
            if(event.getGuildChannel().equals(Client.getClient().getGuildChannelById("1071583636693983373"))) {

                try {
                    event.replyModal(new AdModalMaker(event.getUser().getId()).getModal()).queue();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }

            }else{
                event.reply("You can only use the command in #post-here.").setEphemeral(true).queue();
            }

        }

    }

}
