package persistence;

import model.Bag;
import model.Character;
import model.Home;
import model.Weapon;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file.
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads source file as String and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: raeds Bag from file and returns it.
    // throws IOException if an error occurs reading data form file.
    public Bag readBag() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseBag(jsonObject);
    }

    // EFFECTS: pares Bag from JSON object and returns it
    private Bag parseBag(JSONObject jsonObject) {
        int cap = jsonObject.getInt("capacity");
        Bag bag = new Bag(cap);
        addWeaponsBag(bag, jsonObject);
        return bag;
    }

    // MODIFIES: bag
    // EFFECTS: parses Weapon from JSON object and adds them to bag.
    private void addWeaponBag(Bag bag, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int av = jsonObject.getInt("av");
        int durability = jsonObject.getInt("durability");
        Weapon w1 = new Weapon(name, av, durability);
        bag.addWeapon(w1);
    }

    // MODIFIES: bag
    // EFFECTS: parses Weapon from JSON object and adds them to bag.
    private void addWeaponsBag(Bag bag, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("weaponList");
        for (Object json : jsonArray) {
            JSONObject nextWeapon = (JSONObject) json;
            addWeaponBag(bag, nextWeapon);
        }
    }

    // EFFECTS: raeds Character from file and returns it.
    // throws IOException if an error occurs reading data form file.
    public Character readCharacter() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCharacter(jsonObject);
    }

    // EFFECTS: pares Character from JSON object and returns it
    private Character parseCharacter(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int av = jsonObject.getInt("av");
        int hv = jsonObject.getInt("hv");
        int oriav = jsonObject.getInt("oriav");
        int dv = jsonObject.getInt("dv");
        int mv = jsonObject.getInt("mv");
        int maxhv = jsonObject.getInt("maxhv");
        int maxmv = jsonObject.getInt("maxmv");
        boolean isarm = jsonObject.getBoolean("isarm");
        int experience = jsonObject.getInt("experience");
        int level = jsonObject.getInt("level");
        Character character = new Character(name, hv, oriav, av, dv, mv, maxhv, maxmv, isarm, experience, level);
        if (isarm) {
            addWeaponCharacter(character, jsonObject);
        }

        return character;
    }

    // MODIFIES: character
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addWeaponCharacter(Character character, JSONObject jsonObject) {

        String name = jsonObject.getJSONObject("weapon").getString("name");
        int av = jsonObject.getJSONObject("weapon").getInt("av");
        int durability = jsonObject.getJSONObject("weapon").getInt("durability");
        Weapon weapon = new Weapon(name, av, durability);
        character.equipWeapon(weapon);

    }

    // EFFECTS: raeds Home from file and returns it.
    // throws IOException if an error occurs reading data form file.
    public Home readHome() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseHome(jsonObject);
    }

    // EFFECTS: pares Home from JSON object and returns it
    private Home parseHome(JSONObject jsonObject) {
        Home home = new Home();
        addWeaponsHome(home, jsonObject);
        return home;
    }

    // MODIFIES: home
    // EFFECTS: parses Weapon from JSON object and adds them to home.
    private void addWeaponHome(Home home, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int av = jsonObject.getInt("av");
        int durability = jsonObject.getInt("durability");
        Weapon w1 = new Weapon(name, av, durability);
        home.storeWeapon(w1);
    }

    // MODIFIES: home
    // EFFECTS: parses Weapon from JSON object and adds them to home.
    private void addWeaponsHome(Home home, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("storeWeapon");
        for (Object json : jsonArray) {
            JSONObject nextWeapon = (JSONObject) json;
            addWeaponHome(home, nextWeapon);
        }
    }
}
