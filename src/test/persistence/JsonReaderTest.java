package persistence;

import model.Bag;
import model.Character;
import model.Home;
import model.Weapon;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFileForBag() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Bag bag = reader.readBag();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderNonExistentFileForCharacter() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Character character = reader.readCharacter();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderNonExistentFileForHome() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Home home = reader.readHome();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyBag() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyBag.json");
        try {
            Bag bag = reader.readBag();
            assertEquals(0, bag.getNumWeapon());
            assertEquals(10, bag.getCapacity());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralBag() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralBag.json");
        try {
            Bag bag = reader.readBag();
            List<Weapon> weaponList = bag.getWeaponList();
            assertEquals(2, weaponList.size());
            assertEquals(5, bag.getCapacity());
            checkWeapon("Dark Soul", 5, 10, weaponList.get(0));
            checkWeapon("Elden Ring", 10, 20, weaponList.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testRaederEmptyHome() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyHome.json");
        try {
            Home home = reader.readHome();
            assertEquals(0, home.getNumweapon());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralHome() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralHome.json");
        try {
            Home home = reader.readHome();
            List<Weapon> storeWeapon = home.getStoredWeapon();
            assertEquals(2, storeWeapon.size());
            checkWeapon("weapon 1", 5, 10, storeWeapon.get(0));
            checkWeapon("weapon 2", 10, 20, storeWeapon.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderCharacter() {
        JsonReader reader = new JsonReader("./data/testReaderCharacter.json");
        try {
            Character character = reader.readCharacter();
            assertEquals(character.getAv(), 4);
            assertEquals(character.getDv(), 4);
            assertEquals(character.getExperience(), 77);
            assertEquals(character.getHv(), 10);
            assertEquals(character.getIsarm(), true);
            assertEquals(character.getLevel(), 2);
            assertEquals(character.getMaxhv(), 30);
            assertEquals(character.getMaxmv(), 45);
            assertEquals(character.getMv(), 10);
            assertEquals(character.getName(), "CHEN");
            assertEquals(character.getOriav(), 20);
            checkWeapon("Wood", 1, 4, character.getWeapon());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }

    }

    @Test
    void testReaderCharacterWithoutWeapon() {
        JsonReader reader = new JsonReader("./data/testReaderCharacterWithoutWeapon.json");
        try {
            Character character = reader.readCharacter();
            assertEquals(character.getAv(), 3);
            assertEquals(character.getDv(), 4);
            assertEquals(character.getExperience(), 77);
            assertEquals(character.getHv(), 10);
            assertEquals(character.getIsarm(), false);
            assertEquals(character.getLevel(), 2);
            assertEquals(character.getMaxhv(), 30);
            assertEquals(character.getMaxmv(), 45);
            assertEquals(character.getMv(), 10);
            assertEquals(character.getName(), "CHEN");
            assertEquals(character.getOriav(), 20);
            

        } catch (IOException e) {
            fail("Couldn't read from file");
        }

    }
}
