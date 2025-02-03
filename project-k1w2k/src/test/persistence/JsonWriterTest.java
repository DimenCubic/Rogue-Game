package persistence;

import model.Bag;
import model.Character;
import model.Home;
import model.Weapon;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFileForBag() {
        try {
            Bag bag = new Bag(10);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterInvalidFileForCharacter() {
        try {
            Character character = new Character("Max");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterInvalidFileForHome() {
        try {
            Home character = new Home();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyBag() {
        try {
            Bag bag = new Bag(10);
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyBag.json");
            writer.open();
            writer.writeBag(bag);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyBag.json");
            bag = reader.readBag();
            assertEquals(bag.getCapacity(), 10);
            assertEquals(bag.getNumWeapon(), 0);
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }

    }

    @Test
    void testWriterGeneralBag() {
        try {
            Bag bag = new Bag(10);
            Weapon w1 = new Weapon("w1", 1, 1);
            Weapon w2 = new Weapon("w2", 2, 2);

            bag.addWeapon(w1);
            bag.addWeapon(w2);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralBag.json");
            writer.open();
            writer.writeBag(bag);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralBag.json");
            bag = reader.readBag();
            assertEquals(bag.getCapacity(), 10);
            assertEquals(bag.getNumWeapon(), 2);
            List<Weapon> weaponList = bag.getWeaponList();
            assertEquals(2, weaponList.size());
            checkWeapon("w1", 1, 1, weaponList.get(0));
            checkWeapon("w2", 2, 2, weaponList.get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterEmptyHome() {
        try {
            Home home = new Home();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyHome.json");
            writer.open();
            writer.writeHome(home);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyHome.json");
            home = reader.readHome();
            assertEquals(home.getNumweapon(), 0);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralHome() {
        try {
            Home home = new Home();
            Weapon w1 = new Weapon("w1", 1, 1);
            Weapon w2 = new Weapon("w2", 2, 2);

            home.storeWeapon(w1);
            home.storeWeapon(w2);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralHome.json");
            writer.open();
            writer.writeHome(home);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralHome.json");
            home = reader.readHome();
            assertEquals(home.getNumweapon(), 2);
            List<Weapon> storeWeapon = home.getStoredWeapon();
            assertEquals(2, storeWeapon.size());
            checkWeapon("w1", 1, 1, storeWeapon.get(0));
            checkWeapon("w2", 2, 2, storeWeapon.get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    @SuppressWarnings("methodlength")
    void testWriterCharacter() {
        try {
            Character character = new Character("c1");

            JsonWriter writer = new JsonWriter("./data/testWriterCharacter.json");
            writer.open();
            writer.writeCharacter(character);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterCharacter.json");
            character = reader.readCharacter();
            assertEquals(character.getAv(), 7);
            assertEquals(character.getDv(), 3);
            assertEquals(character.getExperience(), 0);
            assertEquals(character.getHv(), 100);
            assertEquals(character.getIsarm(), true);
            assertEquals(character.getLevel(), 0);
            assertEquals(character.getMaxhv(), 100);
            assertEquals(character.getMaxmv(), 100);
            assertEquals(character.getMv(), 100);
            assertEquals(character.getName(), "c1");
            assertEquals(character.getOriav(), 5);
            assertEquals(character.getUpgradeExp(), 100);
            checkWeapon("Wood Stick", 1, 100, character.getWeapon());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }

    }

    @Test
    @SuppressWarnings("methodlength")
    void testWriterCharacterWithoutWeapon() {
        try {
            Character character = new Character("c1");
            character.removeWeapon();
            JsonWriter writer = new JsonWriter("./data/testWriterCharacterWithoutWeapon.json");
            writer.open();
            writer.writeCharacter(character);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterCharacterWithoutWeapon.json");
            character = reader.readCharacter();
            assertEquals(character.getAv(), 5);
            assertEquals(character.getDv(), 3);
            assertEquals(character.getExperience(), 0);
            assertEquals(character.getHv(), 100);
            assertEquals(character.getIsarm(), false);
            assertEquals(character.getLevel(), 0);
            assertEquals(character.getMaxhv(), 100);
            assertEquals(character.getMaxmv(), 100);
            assertEquals(character.getMv(), 100);
            assertEquals(character.getName(), "c1");
            assertEquals(character.getOriav(), 5);
            assertEquals(character.getUpgradeExp(), 100);
             
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }

    }
}
