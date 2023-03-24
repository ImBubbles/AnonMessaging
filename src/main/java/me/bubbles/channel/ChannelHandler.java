package me.bubbles.channel;

import me.bubbles.Client;
import me.bubbles.webhook.WebhookHandler;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.io.FileNotFoundException;

public class ChannelHandler {

    private TextChannel channel;
    private String channelID;
    private String ownerID;
    private boolean hasOwner=false;

    public ChannelHandler(String id) throws FileNotFoundException {
        this.channel=Client.getClient().getTextChannelById(id);
        this.channelID=id;
        this.hasOwner=Client.channelManager.hasOwner(id);
        setupOwner();
    }

    public ChannelHandler(TextChannel channel) throws FileNotFoundException {
        this.channel=channel;
        this.channelID=channel.getId();
        this.hasOwner=Client.channelManager.hasOwner(channel.getId());
        setupOwner();
    }

    public void sendMessage(String msg) {
        channel.sendMessage(msg).queue();
    }

    public void sendMessageAndButton(String msg,ButtonType buttonType, String value, String regex) {
        channel.sendMessage(msg).addActionRow(getButton(buttonType,value,regex)).queue();
    }

    private Button getButton(ButtonType buttonType, String value, String regex) {
        return Button.primary(buttonType.name+regex+value,"Reply");
    }

    private void setupOwner() throws FileNotFoundException {
        this.ownerID=Client.channelManager.getOwner(channelID);
    }

    public TextChannel getChannel() {
        return channel;
    }

    public enum ButtonType {
        REPLY("reply"),
        AD("ad");

        public String name;

        ButtonType(String name) {
            this.name=name;
        }

    }

}
