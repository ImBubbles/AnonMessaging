package me.bubbles.modal;

import me.bubbles.Client;
import me.bubbles.user.UserHandler;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;

import java.io.FileNotFoundException;

public class AdModalMaker {

    private Modal modal;

    public AdModalMaker(String userID, String content) throws FileNotFoundException {

        TextInput message = TextInput.create("message","Ad Message", TextInputStyle.PARAGRAPH)
                .setPlaceholder("Message")
                .setValue(content)
                .setMinLength(10)
                .setMaxLength(1900)
                .setRequired(true)
                .build();

        TextInput replyAutoInput = TextInput.create("autoInput","Reply Modal Placeholder", TextInputStyle.SHORT)
                .setPlaceholder("Text")
                .setMinLength(3)
                .setMaxLength(100)
                .setRequired(true)
                .build();

        modal = Modal.create("ad-modal", Client.fakeName.getFakename(userID))
                .addActionRows(ActionRow.of(message),ActionRow.of(replyAutoInput))
                .build();

    }

    public AdModalMaker(String userID) throws FileNotFoundException {

        TextInput message = TextInput.create("message","Ad Message", TextInputStyle.PARAGRAPH)
                .setPlaceholder("Message")
                .setMinLength(10)
                .setMaxLength(1900)
                .setRequired(true)
                .build();

        TextInput replyAutoInput = TextInput.create("autoInput","Reply Modal Placeholder", TextInputStyle.SHORT)
                .setPlaceholder("Text")
                .setMinLength(3)
                .setMaxLength(100)
                .setRequired(true)
                .build();

        UserHandler user = new UserHandler(userID);

        modal = Modal.create("ad-modal",user.getFakename())
                .addActionRows(ActionRow.of(message),ActionRow.of(replyAutoInput))
                .build();

    }

    public Modal getModal() {
        return modal;
    }

}
