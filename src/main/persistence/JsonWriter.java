package persistence;

import model.Bag;
import model.Character;
import model.Home;

import org.json.JSONObject;

import java.io.*;

// Represents a writer that writes JSON representation of bag, character and home to file.
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file.
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file
    // cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of Bag to file.
    public void writeBag(Bag bag) {
        JSONObject json = bag.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of Character to file.
    public void writeCharacter(Character character) {
        JSONObject json = character.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of Home to file.
    public void writeHome(Home home) {
        JSONObject json = home.toJson();
        saveToFile(json.toString(TAB));
    }
}
