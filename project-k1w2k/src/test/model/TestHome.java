package model;

 
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestHome {
    private Home home;
    private Weapon w1;
    private Weapon w2;
    private Character cha;

    @BeforeEach
    void runBefore() {
        home = new Home();
        cha = new Character("1");
        w1 = new Weapon("w1",1,1);
        w2 = new Weapon("w2",2,2);
      
    }

    @Test
    void testConstructor() {
        assertEquals(home.getNumweapon(), 0);
        assertEquals(home.getStoredWeapon().size(),0);
    }


    @Test
    void testStoreWeapon() {
        home.storeWeapon(w1);
       
        assertEquals(home.getNumweapon(),1);
        assertEquals(home.getStoredWeapon().get(0),w1);

    }
    
    @Test 
    void testMultipleStoreWeapon() {
        home.storeWeapon(w1);
        home.storeWeapon(w2);
       
        assertEquals(home.getNumweapon(),2);
        assertEquals(home.getStoredWeapon().get(0), w1);
        assertEquals(home.getStoredWeapon().get(1), w2);
    }

    @Test
    void testTakeWeapon() {
        home.storeWeapon(w1);
        home.storeWeapon(w2);
        home.takeWeapon(w1);
        assertEquals(home.getNumweapon(), 1);
        assertEquals(home.getStoredWeapon().get(0), w2);
    }

    @Test 
    void testTakeMultipleWeapon() {
        home.storeWeapon(w1);
        home.storeWeapon(w2);
        home.takeWeapon(w1);
        home.takeWeapon(w2);
        assertEquals(home.getNumweapon(), 0);
        ArrayList<Weapon> teste = new ArrayList<Weapon>();
        assertEquals(home.getStoredWeapon(), teste);
    }

    @Test
    void testRestoreHealth() {
        cha.changeHv(-20);
        home.restoreHealth(cha);
        assertEquals(cha.getHv(), 100);
    }

    @Test
    void testRestoreMagic() {
        cha.changeMv(-50);
        home.restoreMagic(cha);
        assertEquals(cha.getMv(), 100);
    }

    @Test
    void testFixDurability() {
        w1.changeDurability(-50);
        home.fixDurability(w1);
        assertEquals(100, w1.getDurability());
    }


}
