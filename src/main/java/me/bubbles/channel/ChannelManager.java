package me.bubbles.channel;

import me.bubbles.Client;
import me.bubbles.files.FileHandler;
import me.bubbles.log.LogManager;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;

public class ChannelManager {

    private static final String FILE_NAME = "user-channels.txt";
    private static File channels_file;

    // FORMAT --- userID,channelID
    private static FileHandler fileHandler;

    public ChannelManager() throws IOException {
        channels_file = new File(FILE_NAME);
        channels_file.createNewFile();
        Client.fileManager.addFile("user-channels", channels_file);
        fileHandler=new FileHandler(channels_file);
    }

    public TextChannel getTextChannel(String userID) throws FileNotFoundException {
        return Client.getClient().getTextChannelById(getUserChannel(userID));
    }

    public void registerUser(String name, String id) throws IOException {

        TextChannel textChannel = Client.guild.createTextChannel(id,Client.guild.getCategoryById("1041053949823824003")).syncPermissionOverrides().addMemberPermissionOverride(Long.parseLong(id),Permission.VIEW_CHANNEL.getRawValue(),Permission.CREATE_INSTANT_INVITE.getRawValue()).complete();

        fileHandler.write(id+":"+textChannel.getId());

        ChannelHandler channelHandler = new ChannelHandler(textChannel);

        channelHandler.sendMessage("**New channel for user:**\n"+name+"\n\n**Under fake name:**\n"+Client.fakeName.getFakename(id));

        Client.logManager.log(LogManager.Type.CHANNEL,"New channel for "+id);

    }

    public boolean hasChannel(String id) throws FileNotFoundException {


        HashMap<String,String> userChannels=fileHandler.getStringStringData(":");

        return userChannels.containsKey(id);
    }

    public String getUserChannel(String id) throws FileNotFoundException {
        HashMap<String,String> userChannels=fileHandler.getStringStringData(":");
        return userChannels.get(id);
    }

    public String getFakenameChannel(String fakename) throws FileNotFoundException {
        HashMap<String,String> userChannels=fileHandler.getStringStringData(":");
        return userChannels.get(Client.fakeName.getRealname(fakename));
    }

    public String getOwner(String channel) throws FileNotFoundException {
        HashMap<String,String> userChannels=fileHandler.getStringStringData(":");
        for(int i=0; i<userChannels.size(); i++) {
            if(userChannels.values().toArray()[i].toString().equals(channel)) {
                return userChannels.keySet().toArray()[i].toString();
            }
        }

        return null;

    }

    public boolean hasOwner(String channel) throws FileNotFoundException {
        HashMap<String,String> userChannels=fileHandler.getStringStringData(":");
        for(int i=0; i<userChannels.size(); i++) {
            if(userChannels.values().toArray()[i].toString().equals(channel)) {
                return true;
            }
        }

        return false;

    }

}
