package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestBag {
    private Bag bag;
    private Weapon w1;
    private Weapon w2;

    @BeforeEach
    void runBefore() {
        bag = new Bag(10);
        w1 = new Weapon("w1", 1, 1);
        w2 = new Weapon("w2", 2, 2);
    }

    @Test 
    void testConstructor() {
        assertEquals(10, bag.getCapacity());
        assertEquals(0, bag.getNumWeapon());
        assertEquals(0, bag.getWeaponList().size()); 
    }

    @Test
    void testAddWeapon() {
        bag.addWeapon(w1);
        assertEquals(1, bag.getNumWeapon());
        assertEquals(1, bag.getWeaponList().size()); 
        assertEquals(w1, bag.getWeaponList().get(0));
    }

    @Test
    void testMultipleAddWeapon() {
        bag.addWeapon(w1);
        assertEquals(1, bag.getNumWeapon());
        assertEquals(1, bag.getWeaponList().size()); 
        assertEquals(w1,bag.getWeaponList().get(0));
        bag.addWeapon(w2);
        assertEquals(2, bag.getNumWeapon());
        assertEquals(2, bag.getWeaponList().size());
        assertEquals(w1,bag.getWeaponList().get(0));
        assertEquals(w2,bag.getWeaponList().get(1));
       
    }

    @Test
    void testRemoveWeapon() {
        bag.addWeapon(w1);
        bag.addWeapon(w2); 
        bag.removeWeapon(w2);
        assertEquals(1, bag.getNumWeapon());
        assertEquals(1, bag.getWeaponList().size()); 
        assertEquals(w1, bag.getWeaponList().get(0));
    }

    @Test
    void testMultipleRemoveWeapon() {
        bag.addWeapon(w1);
        bag.addWeapon(w2); 
        bag.removeWeapon(w2);
        assertEquals(1, bag.getNumWeapon());
        assertEquals(1, bag.getWeaponList().size()); 
        assertEquals(w1, bag.getWeaponList().get(0));
        bag.removeWeapon(w1);
        assertEquals(0, bag.getNumWeapon());
        assertEquals(0, bag.getWeaponList().size()); 
        ArrayList<Weapon> testWeapon = new ArrayList<Weapon>();
        assertEquals(testWeapon, bag.getWeaponList()); 
    }

    
}
