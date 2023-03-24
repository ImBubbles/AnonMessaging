package me.bubbles.commands;

import me.bubbles.Client;
import me.bubbles.channel.ChannelHandler;
import me.bubbles.modal.MessageModalMaker;
import me.bubbles.user.UserHandler;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

import java.io.FileNotFoundException;
import java.io.IOException;

public class SendMessage extends Command {

    public SendMessage() {
        super("message",true);
    }

    @Override
    public void run(SlashCommandInteractionEvent event) {

        if(event.getInteraction().getName().equalsIgnoreCase("message")) {

            try {

                if(Client.fakeName.hasFakename(event.getUser().getId())) {
                    OptionMapping name = event.getOption("name");
                    OptionMapping message = event.getOption("message");

                    if(Client.guild.getCategoryById("1041053949823824003").getChannels().contains(event.getGuildChannel())) {

                        /*if (option == null) {

                            MessageModalMaker modal = new MessageModalMaker();

                            event.replyModal(modal.getModal()).complete();

                        } else {

                            if (Client.fakeName.hasEntry(option.getAsString())) {

                                MessageModalMaker modal = new MessageModalMaker("reply:" + option.getAsString());

                                event.replyModal(modal.getModal()).complete();

                            } else {

                                event.reply("No user found with fake name: " + option.getAsString() + ".").setEphemeral(true).queue();

                            }

                        }*/

                        if(Client.fakeName.hasEntry(name.getAsString())&&(!name.getAsString().equals(Client.fakeName.getFakename(event.getUser().getId())))) {

                            ChannelHandler recipient = new ChannelHandler(Client.channelManager.getFakenameChannel(name.getAsString()));

                            UserHandler userRecipient = new UserHandler(Client.fakeName.getRealname(name.getAsString()));

                            ChannelHandler sender = new ChannelHandler(Client.channelManager.getUserChannel(event.getUser().getId()));

                            recipient.sendMessageAndButton("**"+Client.fakeName.getFakename(event.getUser().getId())+"**\n\n"+message.getAsString()+"\n\n"+userRecipient.getMention(),ChannelHandler.ButtonType.REPLY,Client.fakeName.getFakename(event.getUser().getId()),":");

                            event.reply("**Sent To "+name.getAsString()+"**\n\n"+message.getAsString()).queue();

                        }else{

                            event.reply("A problem has occurred, invalid fake name used.").setEphemeral(true).queue();

                        }

                    }else{
                        event.reply("You can only use this command in your message channel.").setEphemeral(true).queue();
                    }

                }else{

                    event.reply("You must have a fake name set first!").setEphemeral(true).queue();

                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

    }

}
