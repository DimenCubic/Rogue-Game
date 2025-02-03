package model;

import org.json.JSONObject;

import persistence.Writable;

/* 
 * Represents the Character that user can control, which has the health value,
 *   attacking value, defence value, magic value and experience. 
 * Character can equip weapon to increase its attacking value.
 * Character can upgrade.
 */
public class Character implements Writable {
    private static final int UPGRADE_EXPERIENCE = 100; // The amount of experience needed for character to upgrade.

    private String name;
    private int hv; // The health value of the character.
    private int oriav; // The original attack value of the character
    private int av; // The attack value of the character.
    private int dv; // The defence value of the character
    private int mv; // The magic value of the character.
    private int maxhv; // The maximum value of the health.
    private int maxmv; // The maximum value of the magic.

    private boolean isarm; // Whether the character armed or not.
    private Weapon weapon; // The weapon hold by the character.

    private int experience; // The experience character has.
    private int level;

    /*
     *   * EFFECTS: Create a Character.
     * Set initial value of maximum value of health as 100;
     * Set initial value of maximum value of magic as 100;
     * Set initial value of health as max health value;
     * Set initial value of original attacking as 5;
     * Set initial value of attacking as original attacking;
     * Set initial value of defence as 3;
     * Set initial value of magic as max magic value;
     * Set initial value of experience as 0;
     * Set initial value of level as 0;
     * Set initial valur of is_arm as flase.
     * Set initial name as the given name;
     *  
     */
    public Character(String name) {
        this.name = name;
        maxhv = 100;
        maxmv = 100;
        hv = maxhv;
        oriav = 5;
        dv = 3;
        mv = maxmv;
        experience = 0;
        level = 0;
        isarm = true;
        weapon = new Weapon("Wood Stick", 1, 100);
        av = 6;
    }

    // EFFECTS: The constructor for persistent storage.
    public Character(String name, int hv, int oriav, int av, int dv, int mv, int maxhv, int maxmv, boolean isarm,
            int experience, int level) {
        this.name = name;
        this.hv = hv;
        this.oriav = oriav;
        this.av = av;
        this.dv = dv;
        this.mv = mv;
        this.maxhv = maxhv;
        this.maxmv = maxmv;
        this.isarm = isarm;
        this.experience = experience;
        this.level = level;
    }

    /*
     *   * REQUIRES: index >= 0 and Bag.capacity > 0 && is_arm == false
     *   * MODIFIES: this
     *   * EFFECTS: Character equips a weapon from the bag, which increases his/her
     * attacking number;
     * Character shows armed;
     * Record what weapon the character holds.
     *  
     */
    public void equipWeapon(Weapon w) {
        av += w.getAv();
        isarm = true;
        this.weapon = w;
    }

    /*
     *   * REQUIRES: av > ori_av && is_arm == true
     *   * MODIFIES: this
     * EFFECTS: Remove the current weapon from the character.
     * Decrease the attacking value of the Character.
     * Put it on the bag.(Do it on RogueGame class)
     * Character shows not armed;
     */
    public void removeWeapon() {
        av -= weapon.getAv();
        weapon = null;
        isarm = false;
    }

    /*
     *   * REQUIRES: experience >= UPGRADE_EXPERIENCE
     *   * MODIFIES: this
     *   * EFFECTS: Character use experience to upgrade, which increases the value
     * of maximum of health by 10,
     * maximum of magic by 10,
     * original attacking number by 5 and defence by 3. However, all experience will
     * be consumed.
     *  
     */
    public void upgrade() {
        maxhv += 10;
        maxmv += 10;
        oriav += 5;
        dv += 3;
        experience = 0;
        if (isarm) {
            av = oriav + weapon.getAv();
        } else {
            av = oriav;
        }
        addLevel();
    }

    /*
     *   * REQUIRES: num > 0
     *   * MODIFIES: this
     *   * EFFECTS: Increased experience by num.
     *  
     */
    public void addExp(int num) {
        experience += num;
    }

    /*
     *   * MODIFIES: this
     *   * EFFECTS: Increased level by one.
     *  
     */
    public void addLevel() {
        level++;
    }

    /*
     * REQUIRES: num != 0
     *   * MODIFIES: this
     *   * EFFECTS: Change the value of the health.
     *  
     */
    public void changeHv(int num) {
        hv += num;
    }

    /*
     * REQUIRES: num != 0
     *   * MODIFIES: this
     *   * EFFECTS: Change the value of the Magic.
     *  
     */
    public void changeMv(int num) {
        mv += num;
    }

    public int getHv() {
        return hv;
    }

    public int getAv() {
        return av;
    }

    public int getDv() {
        return dv;
    }

    public int getMv() {
        return mv;
    }

    public int getOriav() {
        return oriav;
    }

    public int getMaxhv() {
        return maxhv;
    }

    public int getMaxmv() {
        return maxmv;
    }

    public int getExperience() {
        return experience;
    }

    public int getLevel() {
        return level;
    }

    public boolean getIsarm() {
        return isarm;
    }

    public int getUpgradeExp() {
        return UPGRADE_EXPERIENCE;
    }

    public String getName() {
        return name;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("hv", hv);
        json.put("oriav", oriav);
        json.put("av", av);
        json.put("dv", dv);
        json.put("mv", mv);
        json.put("maxhv", maxhv);
        json.put("maxmv", maxmv);
        json.put("maxhv", maxhv);
        json.put("isarm", isarm);
        if (isarm) {
            json.put("weapon", weapon.toJson());
        }
        json.put("experience", experience);
        json.put("level", level);

        return json;
    }
}

/*
 *  * REQUIRES:
 *  * MODIFIES:
 *  * EFFECTS:
 *  
 */