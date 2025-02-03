package model;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

// Represent a bag which has weapons on it. This bag also has capacity.
public class Bag implements Writable {
    private int capacity; // The Bag Capacity
    private int numWeapon; // The number of weapons
    private ArrayList<Weapon> weaponList; // The list of weapons.

    /*
     *  * REQUIRES: capacity > 0
     *  * EFFECTS: Create an instance of the Bag;
     *  *          Create a new weaponList;
     *  *         Assign the capacity of the bag;
     *  *          The number of weapons set to zero.
     *  
     */
    public Bag(int capacity) {
        weaponList = new ArrayList<Weapon>();
        this.capacity = capacity;
        numWeapon = 0;
    }

    /*
     *   * REQUIRES numWeapon < capacity
     *   * MODIFIES: weaponList, this
     *   * EFFECTS: Add a weapon into the bag increase the number of weapon by one.
     *  
     */
    public void addWeapon(Weapon w) {
        weaponList.add(w);
        numWeapon++;
        EventLog.getInstance().logEvent(new Event("Add " + w.getName() + " to the bag."));
    }

    /*
     *   * REQUIRES: index >= 0 && index <= weaponList.size() - 1 && numWeapon >= 1
     *   * MODIFIES: weaponList, this
     *   * EFFECTS: Remove a weapon from the bag. Decrease the number of weapons by
     * one.
     *  
     */
    public void removeWeapon(Weapon w) {
        weaponList.remove(w);
        numWeapon--;
        EventLog.getInstance().logEvent(new Event("Store " + w.getName() + " to Box from Bag."));
    }

    public ArrayList<Weapon> getWeaponList() {
        return weaponList;
    }

    public int getNumWeapon() {
        return numWeapon;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("capacity", capacity);
        json.put("numWeapon", numWeapon);
        json.put("weaponList", weaponsToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray weaponsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Weapon t : weaponList) {
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
