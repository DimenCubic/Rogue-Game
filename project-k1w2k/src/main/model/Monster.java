package model;

 

/*
 * Represent amonster whcih has the health value, attacking value and 
 *   the harm of monster caused to weapon.
 * 
 * Monster will generate a weapon when it is defeated by character.
 */
public class Monster {
    private String name; //The name of the Monster.
    private int hv; // The health value of the monster.
    private int av; // The attacking value of the monster.
    private int harmw; // The number of the damage would cause from monster to weapon
                        // for each time it's being hit.
   
   /*
    * REQUIRES: 70 <= hv <= 100 && 5 <= av <= 10 && 3 <= harm_w <= 5
    * EFFECTS: Create a monster which has the name, health value, attackinf value and harm to weapon.
    */
    public Monster(String name,int hv, int av, int harmw) {
        this.name = name;
        this.hv = hv;
        this.av = av;
        this.harmw = harmw;
    }

   

   /*
    * REQUIRES: hv <= 0
    * EFFECTS: Generate the experence form 10 to 50 when monster is defeated by character.
    */
    public int generateE() {
        int e = (int)(Math.random() * 40) + 10;
        return e;
    }


   /*
    * REQUIRES: num != 0
    * MODIFIES: this
    * EFFECTS: Chaneg the value of health of the monster.
    */
    public void changeHv(int num) {
        hv += num;
    }

    public int getHv() {
        return hv;
    }

    public int getAv() {
        return av;
    }

    public int getHarmw() {
        return harmw;
    }

    public String getName() {
        return name;
    }

}



/*
 * REQUIRES: 
 * MODIFIES:
 * EFFECTS:
 */


 /* 
    * REQUIRES:  
    * MODIFIES:  
    * EFFECTS:            
    */