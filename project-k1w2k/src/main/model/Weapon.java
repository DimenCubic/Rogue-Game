package model;

import org.json.JSONObject;

import persistence.Writable;

/*
 * Represents a weapon which has the name, attacking value and durability.
 */
public class Weapon implements Writable {
    private static final int max_durability = 100; // The maximum number of the durability of the weapon.

    private String name;
    private int av; // Attacking value of the weapon.
    private int durability; // Durability of the weapon.

    /*
     *   * REQUIRES: 0 < av <= 100 && 0 < durability <= 100
     *   * EFFECTS: Create a weapon which has name, attacking value and durability.
     *  
     */
    public Weapon(String name, int av, int durability) {
        this.name = name;
        this.av = av;
        this.durability = durability;
    }

    /*
     *   * REQUIRES: num != 0
     * MODIFIES: this
     *   * EFFECTS: Change the value of the Durability of the Weapon.
     *  
     */
    public void changeDurability(int num) {
        durability += num;
    }

    public String getName() {
        return name;
    }

    public int getAv() {
        return av;
    }

    public int getDurability() {
        return durability;
    }

    public int getMaxDurability() {
        return max_durability;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("av", av);
        json.put("durability", durability);
        return json;
    }
}

/*
 *  * REQUIRES:
 *  * MODIFIES:
 *  * EFFECTS:
 *  
 */

/*
 * REQUIRES:
 * MODIFIES:
 *   * EFFECTS:
 *  
 */