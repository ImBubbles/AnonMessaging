package me.bubbles.webhook;

import me.bubbles.json.JsonManager;

import java.io.IOException;
import java.util.HashMap;

public class WebhookHandler {

    private String webhookURL;

    public WebhookHandler(String webhookURL) {
        this.webhookURL=webhookURL;
    }

    public void sendMessage(String sender, String header, String message) throws IOException {

        HashMap<String, String> msg = new HashMap<>();
        msg.put("Message", message);

        JsonManager hook = new JsonManager(this.webhookURL, sender, msg);

        hook.execute();

    }

}
