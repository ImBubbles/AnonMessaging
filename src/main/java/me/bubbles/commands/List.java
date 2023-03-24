package me.bubbles.commands;

import me.bubbles.Client;
import me.bubbles.files.FileHandler;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.utils.FileUpload;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public class List extends Command {

    public List() {
        super("list",true);
    }

    @Override
    public void run(SlashCommandInteractionEvent event) {

        if(event.getInteraction().getName().equalsIgnoreCase("list")) {

            if (event.getGuildChannel().equals(Client.getClient().getGuildChannelById("1071552113634721803"))) {

                HashMap<String, String> data;

                try {
                    data = Client.fakeName.getFileHandler().getStringStringData(":");
                    File file = new File("list.txt");
                    if (file.exists()) {
                        file.delete();
                    }
                    file.createNewFile();
                    FileHandler fH = new FileHandler(file);
                    for (int i = 0; i < data.values().size(); i++) {
                        fH.write((i + 1) + ". " + data.values().toArray()[i].toString());
                    }
                    FileUpload fileUpload = FileUpload.fromData(file);
                    event.replyFiles(fileUpload).queue();
                    fileUpload.close();
                } catch (FileNotFoundException e) {
                    event.reply("An error has occurred, please try again or contact staff.").queue();
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    event.reply("An error has occurred, please try again or contact staff.").queue();
                    throw new RuntimeException(e);
                }

            }else{
                event.reply("You can only run this command in #commands!").setEphemeral(true).queue();
            }

        }

    }

}
