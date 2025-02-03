package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestWeapon {
    private Weapon w1;

    @BeforeEach
    void runBefore() {
        w1 = new Weapon("w1", 10, 80);
    }

    @Test
    void testConstructor() {
        assertEquals("w1", w1.getName());
        assertEquals(w1.getAv(),10);
        assertEquals(w1.getDurability(),80);
    } 

    @Test
    void testChangeDurability() {
        w1.changeDurability(-10);
        assertEquals(70, w1.getDurability());
    }

    @Test 
    void testMultipleDurability() {
        w1.changeDurability(-10);
        w1.changeDurability(-20);
        assertEquals(w1.getDurability(),50);
    }

    

}
