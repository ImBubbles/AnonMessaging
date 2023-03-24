package me.bubbles.events;

import me.bubbles.Client;
import me.bubbles.modal.MessageModalMaker;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;

import java.io.FileNotFoundException;

public class AdButton extends EventHandler {

    public AdButton() {
        super(ButtonInteractionEvent.class,true);
    }

    @Override
    public void onEvent(GenericEvent event) {

        ButtonInteractionEvent button = (ButtonInteractionEvent) event;

        if(button.getButton().getId().contains("ad")) {

            try {

                if(Client.fakeName.hasFakename(button.getUser().getId())) {
                    // adSPLIT2replySPLIT1name
                    String name = button.getButton().getId().split("SPLIT2")[1].split("SPLIT1")[0];
                    String autoReply = button.getButton().getId().split("SPLIT1")[1].split("SPLIT2")[0];

                    MessageModalMaker modal = new MessageModalMaker(name,autoReply);

                    button.replyModal(modal.getModal()).queue();

                }else{
                    button.reply("You must set a fake name first!").setEphemeral(true).queue();
                }

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

        }

    }

}
