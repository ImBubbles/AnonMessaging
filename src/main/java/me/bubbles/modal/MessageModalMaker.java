package me.bubbles.modal;

import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;

public class MessageModalMaker {
    private Modal modal;

    public MessageModalMaker(String strName, String strMessage) {

        TextInput name = TextInput.create("name","Send To", TextInputStyle.SHORT)
                .setPlaceholder("Fake Name")
                .setValue(strName)
                .setMinLength(1)
                .setMaxLength(16)
                .setRequired(true)
                .build();

        TextInput message = TextInput.create("message","Message", TextInputStyle.PARAGRAPH)
                .setPlaceholder("Text")
                .setValue(strMessage)
                .setMinLength(1)
                .setMaxLength(500)
                .setRequired(true)
                .build();

        modal = Modal.create("msg-modal","Message")
                .addActionRows(ActionRow.of(name),ActionRow.of(message))
                .build();

    }

    public MessageModalMaker(String msg) {

        String[] split = msg.split(":");

        TextInput name = TextInput.create("name","Send To", TextInputStyle.SHORT)
                .setPlaceholder("Fake Name")
                .setValue(split[1])
                .setMinLength(1)
                .setMaxLength(16)
                .setRequired(true)
                .build();

        TextInput message = TextInput.create("message","Message", TextInputStyle.PARAGRAPH)
                .setPlaceholder("Text")
                .setMinLength(3)
                .setMaxLength(500)
                .setRequired(true)
                .build();

        modal = Modal.create("msg-modal","Message")
                .addActionRows(ActionRow.of(name),ActionRow.of(message))
                .build();

    }

    public MessageModalMaker() {

        TextInput name = TextInput.create("name","Send To", TextInputStyle.SHORT)
                .setPlaceholder("Fake Name")
                .setRequired(true)
                .build();

        TextInput message = TextInput.create("message","Message", TextInputStyle.PARAGRAPH)
                .setPlaceholder("Text")
                .setMinLength(3)
                .setMaxLength(500)
                .setRequired(true)
                .build();

        modal = Modal.create("msg-modal","Message")
                .addActionRows(ActionRow.of(name),ActionRow.of(message))
                .build();


    }

    public Modal getModal() {
        return modal;
    }

}
