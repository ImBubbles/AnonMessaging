package me.bubbles.commands;

import me.bubbles.Client;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

public class SetFakeName extends Command {

    public SetFakeName() {
        super("fakename",true);
    }

    @Override
    public void run(SlashCommandInteractionEvent event) {

        if(event.getGuildChannel().equals(Client.getClient().getGuildChannelById("1071552113634721803"))) {

            OptionMapping option = event.getOption("name");

            HashMap<String, String> list = null;

            if(option == null) {

                try {
                    list = Client.fakeName.getFileHandler().getStringStringData(":");
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }

                String id = Objects.requireNonNull(event.getInteraction().getMember()).getId();

                String response = list.keySet().contains(id) ? list.get(id) : "not set";

                event.reply("Your fake name is " + response + ".").setEphemeral(true).queue();

            } else {

                try {
                    list = Client.fakeName.getFileHandler().getStringStringData(":");
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }

                try {
                    if (list.keySet().contains(event.getInteraction().getMember().getId())) {
                        event.reply("You already have set your fake name, contact staff to change your fake name.").setEphemeral(true).queue();
                    } else if (option.getAsString().length() <= 16
                            && !(Client.fakeName.hasEntry(option.getAsString()))
                            && !(option.getAsString().contains(":"))) {
                        Client.fakeName.addEntry(event.getInteraction().getMember().getId(), option.getAsString());
                        Client.channelManager.registerUser(event.getUser().getName(), event.getUser().getId());
                        event.reply("Your fake name has been set.").setEphemeral(true).queue();
                    } else {
                        event.reply("Fake names requirements:\n- No more than 16 characters\n- Cannot already be in use\n- Cannot contain illegal characters \":, \"").setEphemeral(true).queue();
                    }
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        }else{
            event.reply("You can only run this command in #commands.").setEphemeral(true).queue();
        }

    }
}
