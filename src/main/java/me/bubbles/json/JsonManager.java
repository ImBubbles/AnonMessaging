package me.bubbles.json;

import me.bubbles.Client;
import me.bubbles.log.LogManager;

import java.awt.*;
import java.io.IOException;
import java.util.HashMap;

public class JsonManager {

    private String jsonAddress;

    // FORMAT --- Key,Value
    private HashMap<String,String> content;

    private Json.EmbedObject embObject = new Json.EmbedObject();

    public JsonManager(String jsonAddress, String title, HashMap<String,String> content) {
        this.jsonAddress=jsonAddress;
        this.content=content;
        embObject.setTitle(title);

        for(int i=0; i<content.size(); i++) {
            embObject.addField(content.keySet().toArray()[i].toString(),content.values().toArray()[i].toString(),false);
        }
        embObject.setColor(Color.cyan);
    }

    public void execute() throws IOException {
        Json jHook = new Json(this.jsonAddress);
        jHook.setUsername("BubbleBot");
        jHook.addEmbed(this.embObject);
        jHook.execute();

        Client.logManager.log(LogManager.Type.WEBHOOK,this.jsonAddress+":"+content);
    }

}
