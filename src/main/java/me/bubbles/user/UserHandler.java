package me.bubbles.user;

import me.bubbles.Client;
import me.bubbles.channel.ChannelHandler;
import me.bubbles.channel.ChannelManager;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

import java.io.FileNotFoundException;

public class UserHandler {
    private String id;
    private ChannelHandler channelHandler=null;
    private String fakename=null;
    private boolean hasFakename;
    private boolean hasChannel;
    private User user;

    public UserHandler(String id) throws FileNotFoundException {
        this(Client.getClient().retrieveUserById(id).complete());
    }

    public UserHandler(User user) throws FileNotFoundException {
        this.id=user.getId();
        this.hasFakename=Client.fakeName.hasFakename(user.getId());
        this.hasChannel=Client.channelManager.hasChannel(user.getId());
        this.fakename=hasFakename ? Client.fakeName.getFakename(user.getId()) : null;
        this.channelHandler=hasChannel ? new ChannelHandler(Client.channelManager.getTextChannel(user.getId())) : null;
        this.user=user;
    }

    public String getId() {
        return id;
    }

    public String getFakename() {
        return fakename;
    }
    public String getMention() {
        return user.getAsMention();
    }

    public void sendMessageToChannel(String message) {
        channelHandler.sendMessage(message);
    }

    public boolean hasFakename() {
        return hasFakename;
    }

    public TextChannel getTextChannel() {
        return channelHandler.getChannel();
    }

    public ChannelHandler getChannelHandler() {
        return channelHandler;
    }

    public boolean hasChannel() throws FileNotFoundException {
        return Client.channelManager.hasChannel(id);
    }

}
