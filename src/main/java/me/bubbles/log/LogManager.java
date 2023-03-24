package me.bubbles.log;

import me.bubbles.Client;
import me.bubbles.files.FileHandler;

import java.io.*;

public class LogManager {

    private static String FILE_NAME="logs\\log-"+Client.getTime()+".txt";
    private static FileHandler fileHandler;


    public LogManager() throws IOException {
        Client.fileManager.registerFile("log", FILE_NAME);
        fileHandler=new FileHandler(Client.fileManager.getFile("log"));
    }

    public void log(String str) throws IOException {
        fileHandler.write("["+Client.getTime()+"] "+Type.DEFAULT.name+" | "+str);
    }

    public void log(Type type, String str) throws IOException {
        fileHandler.write("["+Client.getTime()+"] "+type.name+" | "+str);
    }

    public enum Type {
        STARTUP("Startup"),
        FILE("File"),
        EVENT("Event"),
        MODERATOR("Moderator"),
        IOException("IOException"),
        RuntimeException("RuntimeException"),
        WEBHOOK("Webhook"),
        CHANNEL("Channel"),
        DEFAULT("Bot");

        public String name;

        Type(String name) {
            this.name=name;
        }

    }

}
