package model;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

/*
 * Represent the home for the character in the game world.
 * 
 * When Character at the home, he/she can fix the durability of the weapon by mataining it,
 * restore his/her own health by eating food
 * and restore his/her magic by touching the Magic Stone.
 * 
 * Character can store the weapons on the home and there is no capacity limit.
 * 
 * Character can take the weapons from the home and put them into bag.
 */
public class Home implements Writable {
    private ArrayList<Weapon> storeWeapon;
    private int numWeapon;

    /*
     *   * EFFECTS: Create Home in the game.
     * Create a new list for stored weapons.
     * Initial number of weapons as zero.
     *  
     */
    public Home() {
        storeWeapon = new ArrayList<Weapon>();
        numWeapon = 0;
    }

    /*
     * REQUIRES: Bag.numWeapon > 0
     * MODIFIES: storeWeaqpon, this
     *   * EFFECTS: Store a weapon from bag to the home.
     * Increase the number of weapons stored at home by one.
     *  
     */
    public void storeWeapon(Weapon w) {
        storeWeapon.add(w);
        numWeapon++;
    }

    /*
     * REQUIRES: numWeapon > 0 && Bag.numWeapon < Bag.capacity.
     * MODIFIES: storeWeapon, this
     *   * EFFECTS: Take a weapon from home to the bag
     * and decrease the number of stored weapons by one.
     *  
     */
    public void takeWeapon(Weapon w) {
        storeWeapon.remove(w);
        numWeapon--;
    }

    /*
     *   * REQUIRES: c.hv < 100 && c.hv > 0
     *   * MODIFIES: c
     *   * EFFECTS: Restore the health of character to the maximum
     *  
     */
    public void restoreHealth(Character c) {
        c.changeHv(c.getMaxhv() - c.getHv());
    }

    /*
     *   * REQUIRES: c.mv < 100 && c.mv >= 0;
     *   * MODIFIES: c
     *   * EFFECTS: Restore the magic of character to the maximum
     *  
     */
    public void restoreMagic(Character c) {
        c.changeMv(c.getMaxmv() - c.getMv());
    }

    /*
     *   * REQUIRES: w.durability < 100 && w.durability >= 0
     *   * MODIFIES: w
     *   * EFFECTS: Fix the durability of this weapon to the maximum
     *  
     */
    public void fixDurability(Weapon w) {
        w.changeDurability(w.getMaxDurability() - w.getDurability());
    }

    public ArrayList<Weapon> getStoredWeapon() {
        return storeWeapon;
    }

    public int getNumweapon() {
        return numWeapon;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("numWeapon", numWeapon);
        json.put("storeWeapon", weaponsToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray weaponsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Weapon t : storeWeapon) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
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