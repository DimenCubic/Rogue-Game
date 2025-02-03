package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestCharacter {
    private Character cha;
    private Bag bag;
    private Weapon w1;
    private Weapon w2;

    @BeforeEach
    void runBefore() {
        cha = new Character("1");
        bag = new Bag(5);
        w1 = new Weapon("w1", 50, 50);
        w2 = new Weapon("w2", 50, 50);
        bag.addWeapon(w1);
        bag.addWeapon(w2);
    }


    @Test 
    void testConstructor() {
        assertEquals(cha.getAv(), 6);
        assertEquals(cha.getDv(), 3);
        assertEquals(cha.getExperience(), 0);
        assertEquals(cha.getHv(), 100);
        assertEquals(cha.getLevel(),0);
        assertEquals(cha.getMaxhv(), 100);
        assertEquals(cha.getMaxmv(), 100);
        assertEquals(cha.getMv(), 100);
        assertEquals(cha.getOriav(), 5);
        assertEquals(cha.getIsarm(), true);
        assertEquals(cha.getName(), "1");
        assertEquals(cha.getUpgradeExp(), 100);
        assertEquals(cha.getWeapon().getName(),"Wood Stick");
    }

    @Test 
    void testEquipWeapon() {
        cha.equipWeapon(w1);
        assertEquals(cha.getAv(), 56);
    }

    
    
    @Test 
    void testRemoveWeapon() {
        cha.equipWeapon(w1);
        cha.removeWeapon();
        assertEquals(bag.getWeaponList().size(),2);
    }

    @Test 
    void testAddExp() {
        cha.addExp(30);
        assertEquals(cha.getExperience(), 30);
    }
    
    @Test 
    void testMultipleAddExp() {
        cha.addExp(10);
        cha.addExp(20);
        assertEquals(cha.getExperience(), 30);
    }

    @Test
    void testAddLevel() {
        cha.addLevel();
        assertEquals(cha.getLevel(),1);
    }

    @Test
    void testMultipleAddLevel() {
        cha.addLevel();
        cha.addLevel();
        assertEquals(cha.getLevel(), 2);
    }

    @Test 
    void testUpgrade() {
        cha.addExp(120);
        cha.upgrade();
        assertEquals(cha.getExperience(), 0);
        assertEquals(cha.getLevel(), 1);
        assertEquals(cha.getOriav(), 10);
        assertEquals(cha.getDv(), 6);
        assertEquals(cha.getMaxhv(), 110);
        assertEquals(cha.getMaxmv(), 110);
        assertEquals(cha.getAv(), 11);
        cha.removeWeapon();
        cha.addExp(120);
        cha.upgrade();
        assertEquals(cha.getAv(), 15);
    }

    @Test
    void testChangeHv() {
        cha.changeHv(-30);
        assertEquals(cha.getHv(), 70);
    }
    
    @Test
    void testMultipleChangeHv() {
        cha.changeHv(-30);
        cha.changeHv(-10);
        assertEquals(cha.getHv(), 60);
    }

    @Test
    void testChangeMv() {
        cha.changeMv(-10);
        assertEquals(cha.getMv(), 90);
    }
    
    @Test
    void testMultipleChangeMv() {
        cha.changeMv(-10);
        cha.changeMv(-20);
        assertEquals(cha.getMv(), 70);
    }
}
