package me.bubbles.events;

import me.bubbles.Client;
import me.bubbles.channel.ChannelHandler;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;

import java.io.IOException;
import java.util.Objects;

public class AdModal extends EventHandler {

    public AdModal() {
        super(ModalInteractionEvent.class,true);
    }

    @Override
    public void onEvent(GenericEvent ev) {

        ModalInteractionEvent event = (ModalInteractionEvent) ev;

        if(event.getModalId().equals("ad-modal")){

            ChannelHandler marketplace = null;

            if(!(event.getValue("message").getAsString().contains("discord.gg/"))) {
                try {
                    marketplace = new ChannelHandler(Objects.requireNonNull(Client.getClient().getTextChannelById("1071583374889734234")));
                    marketplace.sendMessageAndButton("**"+Client.fakeName.getFakename(event.getUser().getId())+"**\n\n"+event.getValue("message").getAsString(),ChannelHandler.ButtonType.AD,Client.fakeName.getFakename(event.getUser().getId())+"SPLIT1"+event.getValue("autoInput").getAsString(),"SPLIT2");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                event.reply("Ad sent successfully.").setEphemeral(true).queue();
            } else {
                event.reply("You cannot send discord server links!").setEphemeral(true).queue();
            }

        }
    }

}
