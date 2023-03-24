package me.bubbles.events;

import me.bubbles.modal.MessageModalMaker;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;

public class ReplyButton extends EventHandler {

    public ReplyButton() {
        super(ButtonInteractionEvent.class,true);
    }

    @Override
    public void onEvent(GenericEvent event) {

        ButtonInteractionEvent button = (ButtonInteractionEvent) event;

        if(button.getButton().getId().contains("reply:")) {

            MessageModalMaker modal = new MessageModalMaker(button.getButton().getId());

            button.replyModal(modal.getModal()).queue();

        }

    }

}
