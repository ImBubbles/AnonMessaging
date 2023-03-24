package me.bubbles;

import me.bubbles.channel.ChannelManager;
import me.bubbles.commands.CommandManager;
import me.bubbles.events.listeners.EventManager;
import me.bubbles.fakename.FakeName;
import me.bubbles.files.FileHandler;
import me.bubbles.files.FileManager;
import me.bubbles.log.LogManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class Client {
    public static FileManager fileManager;
    public static LogManager logManager;
    public static ChannelManager channelManager;
    public static EventManager eventManager;
    public static CommandManager commandManager;
    public static FakeName fakeName;
    public static Guild guild;
    private static JDA client;

    public static void main(String[] args) throws IOException, InterruptedException {

        fileManager=new FileManager();
        logManager=new LogManager();

        fileManager.registerFile("server","server.txt");

        // Start Bot

        client = JDABuilder.createDefault(new FileHandler(fileManager.getFile("server")).getStringStringData(":").get("BotToken"))
                .setActivity(Activity.watching("you"))
                .enableIntents(GatewayIntent.MESSAGE_CONTENT,GatewayIntent.GUILD_PRESENCES,GatewayIntent.GUILD_MESSAGES,GatewayIntent.GUILD_MESSAGE_TYPING)
                .build().awaitReady();

        try {
            startupClasses();
        } catch(IOException e) {
            e.printStackTrace();
        }

        logManager.log(LogManager.Type.STARTUP,"Startup classes started");

        logManager.log(LogManager.Type.STARTUP,"Bot started");

        // Register Commands
        commandsSetup(client);

        logManager.log(LogManager.Type.STARTUP,"Commands started");

    }

    public static JDA getClient() {
        return client;
    }

    public static String getTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        return dtf.format(LocalDateTime.now());
    }

    private static void startupClasses() throws IOException {
        // Startup Files and Classes
        channelManager=new ChannelManager();
        fakeName=new FakeName();
        commandManager=new CommandManager();
        eventManager=new EventManager();
    }

    private static void commandsSetup(JDA client) throws IOException {
        // Commands List
        fileManager.registerFile("commands","commands.txt");
        FileHandler commandsHandler = new FileHandler("commands.txt");

        HashMap<String,String> commandSet = commandsHandler.getStringStringData(":");

        String id = new FileHandler("server.txt").getStringStringData(":").get("GuildID");

        guild = client.getGuildById(id);

        for(int i=0; i<commandSet.size(); i++) {

            assert guild != null;

            if(commandSet.keySet().toArray()[i].toString().equalsIgnoreCase("fakename")){
                guild.upsertCommand(commandSet.keySet().toArray()[i].toString().toLowerCase(),commandSet.values().toArray()[i].toString())
                        .addOption(OptionType.STRING,"name","Set fake name",false).queue();
            } else if(commandSet.keySet().toArray()[i].toString().equalsIgnoreCase("message")){
                guild.upsertCommand(commandSet.keySet().toArray()[i].toString().toLowerCase(),commandSet.values().toArray()[i].toString())
                        .addOption(OptionType.STRING,"name","Player to message",true)
                        .addOption(OptionType.STRING,"message","Message to send to user",true).queue();
            } else{
                guild.upsertCommand(commandSet.keySet().toArray()[i].toString().toLowerCase(),commandSet.values().toArray()[i].toString()).queue();
            }

            logManager.log(LogManager.Type.STARTUP,"Commands registered: /"+commandSet.keySet().toArray()[i].toString().toLowerCase());

        }

    }

}