package persistence;

import model.Weapon;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkWeapon(String name, int av, int durability, Weapon weapon) {
        assertEquals(weapon.getAv(), av);
        assertEquals(weapon.getDurability(), durability);
        assertEquals(weapon.getName(), name);
    }
}
