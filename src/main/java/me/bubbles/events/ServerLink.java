package me.bubbles.events;

import me.bubbles.user.UserHandler;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

public class ServerLink extends EventHandler {
    public ServerLink() {
        super(MessageReceivedEvent.class,true);
    }

    @Override
    public void onEvent(GenericEvent e) throws FileNotFoundException {

        MessageReceivedEvent event = (MessageReceivedEvent) e;

        UserHandler user = new UserHandler(event.getMember().getUser());

        if(event.getMessage().getContentDisplay().contains("discord.gg/")&&event.getMember().hasPermission(Permission.MESSAGE_MANAGE)) {
            if(user.hasFakename()) {
                event.getChannel().sendMessage(user.getMention()+" do not send discord server links!").queue();
                event.getMessage().delete().queue();
            }else{
                event.getMessage().delete().queue();
                event.getMember().ban(24, TimeUnit.HOURS).queue();
            }
        }

    }

}
