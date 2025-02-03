package model;

 
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestMonster {
    private Monster monster;

    @BeforeEach
    void runBefore() {
        monster = new Monster("m1",80,10,5);
    }

    @Test
    void testConstructor() {
        assertEquals(monster.getAv(),10);
        assertEquals(monster.getHv(),80);
        assertEquals(monster.getHarmw(),5);
        assertEquals(monster.getName(),"m1");
    }

 
    

    @Test
    void testGenerateE() {
        int e = monster.generateE();
        assertEquals(e >= 10 && e <= 50, true);
    }

    @Test
    void testChangeHv() {
        monster.changeHv(30);
        assertEquals(monster.getHv(), 110);
    }

    @Test 
    void testMultipleChangeHv() {
        monster.changeHv(30);
        monster.changeHv(-10);
        assertEquals(monster.getHv(), 100);
    }

}
