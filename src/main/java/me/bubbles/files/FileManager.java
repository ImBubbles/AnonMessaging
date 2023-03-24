package me.bubbles.files;

import me.bubbles.Client;
import me.bubbles.log.LogManager;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class FileManager {

    // FORMAT --- Name,File
    private static java.util.HashMap<String, java.io.File> filesList = new HashMap<>();

    public FileManager() {
        
    }

    public File getFile(String key) {
        return filesList.get(key);
    }

    public void registerFile(String key, String file) throws IOException {
        try {
            File newFile = new File(file);
            newFile.createNewFile();
            filesList.put(key, newFile);

        } catch (IOException ioException) {
            Client.logManager.log(LogManager.Type.IOException,ioException.getMessage());
        }

        FileHandler logHandler = new FileHandler(filesList.get("log"));

        logHandler.write("["+Client.getTime()+"] "+LogManager.Type.FILE.name+" | "+"New file - "+key+":"+file);

    }

    public void addFile(String name, File file) throws IOException {

        filesList.put(name, file);

        FileHandler logHandler = new FileHandler(filesList.get("log"));

        logHandler.write("["+Client.getTime()+"] "+LogManager.Type.FILE.name+" | "+"Loaded file - "+name+":"+file);

    }


}
