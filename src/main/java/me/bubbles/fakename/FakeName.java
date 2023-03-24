package me.bubbles.fakename;

import me.bubbles.Client;
import me.bubbles.files.FileHandler;
import me.bubbles.log.LogManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public class FakeName {

    private static final String FILE_NAME = "fakenames.txt";
    private static final FileHandler fileHandler = new FileHandler(FILE_NAME);

    public FakeName() throws IOException {
        startFile();
    }

    public FileHandler getFileHandler() {
        return fileHandler;
    }

    public void addEntry(String name, String nick) throws IOException {
        fileHandler.write(name+":"+nick);
        Client.logManager.log(LogManager.Type.FILE,"New fake name entry - "+name+":"+nick);
    }

    public String getFakename(String id) throws FileNotFoundException {
        return fileHandler.getStringStringData(":").get(id);
    }

    public boolean hasFakename(String id) throws FileNotFoundException {
        return fileHandler.getStringStringData(":").containsKey(id);
    }

    public String getRealname(String fakename) throws FileNotFoundException {

        HashMap<String, String> data =
                fileHandler.getStringStringData(":");

        for(int i=0; i<data.size(); i++) {
            if(data.values().toArray()[i].toString().equalsIgnoreCase(fakename)) {
                return data.keySet().toArray()[i].toString();
            }
        }

        return null;

    }

    public boolean hasEntry(String fakename) throws FileNotFoundException {

        HashMap<String, String> data =
                fileHandler.getStringStringData(":");

        for(String str : data.values()) {
            if(str.equalsIgnoreCase(fakename)) {
                return true;
            }
        }

        return false;

    }

    private void startFile() throws IOException {
        Client.fileManager.registerFile("fakenames",FILE_NAME);
    }

}
