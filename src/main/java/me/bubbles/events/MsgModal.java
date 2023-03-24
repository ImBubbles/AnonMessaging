package me.bubbles.events;

import me.bubbles.Client;
import me.bubbles.channel.ChannelHandler;
import me.bubbles.user.UserHandler;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

public class MsgModal extends EventHandler {

    // DISABLED

    public MsgModal() {
        super(ModalInteractionEvent.class, true);
    }

    @Override
    public void onEvent(GenericEvent ev) {

        ModalInteractionEvent event = (ModalInteractionEvent) ev;

        if(event.getModalId().equals("msg-modal")) {

            String fakename = Objects.requireNonNull(event.getValue("name")).getAsString();
            String senderName = null;
            try {
                senderName = Client.fakeName.getFakename(event.getUser().getId());
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            String msg = Objects.requireNonNull(event.getValue("message")).getAsString();

            try {
                if(Client.fakeName.hasEntry(fakename)&&(!fakename.equals(senderName))) {

                    ChannelHandler recipient = new ChannelHandler(Client.channelManager.getFakenameChannel(fakename));
                    UserHandler userRecipient = new UserHandler(Client.fakeName.getRealname(fakename));
                    ChannelHandler sender = new ChannelHandler(Client.channelManager.getUserChannel(event.getUser().getId()));
                    recipient.sendMessageAndButton("**"+senderName+"**\n\n"+msg+"\n\n"+userRecipient.getMention(),ChannelHandler.ButtonType.REPLY,Client.fakeName.getFakename(event.getUser().getId()),":");
                    sender.sendMessage("**Sent To "+fakename+"**\n\n"+msg);
                    event.reply("Message sent.").setEphemeral(true).queue();

                }else{

                    event.reply("A problem has occurred, invalid fake name used.").setEphemeral(true).queue();

                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

    }
}
