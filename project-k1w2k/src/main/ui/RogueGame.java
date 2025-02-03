
package ui;

import model.Character;
import model.Monster;
import model.Bag;
import model.Home;
import model.Weapon;
import java.util.*;

import persistence.JsonReader;
import persistence.JsonWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

// The Rogue-like Game.
public class RogueGame {
    private Character cha;
    private Home home;
    private Bag bag;
    private Scanner input;
    private boolean atHome;
    private ArrayList<String> nameMonster;
    private ArrayList<String> nameWeapon;

    private static final String JSON_STORE_BAG = "./data/Bag.json";
    private static final String JSON_STORE_HOME = "./data/Home.json";
    private static final String JSON_STORE_CHARACTER = "./data/Character.json";
    private JsonWriter jsonWriterBag;
    private JsonReader jsonReaderBag;
    private JsonWriter jsonWriterHome;
    private JsonReader jsonReaderHome;
    private JsonWriter jsonWriterCharacter;
    private JsonReader jsonReaderCharacter;

    // EFFECTS: runs the Rogue-like Game.
    public RogueGame() throws FileNotFoundException {
        // runRogueGame();
    }

    // MODIFIES: this.
    // EFFECTS: Process user input.
    @SuppressWarnings("methodlength")
    private void runRogueGame() {
        init();

        while (true) {
            if (atHome) {
                commandsAtHome();
                System.out.println();
                System.out.println("What you want to do next:");
                String command = input.next();
                if (command.equals("q")) {
                    System.out.println();
                    System.out.println("Do you want to save your progress? (Yes or No)");
                    String answer = input.next();
                    if (answer.equals("Yes")) {
                        saveRogueGame();
                        System.out.println();
                        System.out.println("Your progress has already been saved!");
                        System.out.println();
                    }
                    System.out.println("Thanks for your playing!");
                    break;
                } else {
                    actionAtHome(command);
                }
            } else {
                commandToFollow();
                String choice = input.next();
                if (choice.equals("q")) {
                    atHome = true;
                } else {
                    actionAtWild(choice);
                }
            }
        }
    }

    // EFFECTS: Process the command when character is on the wild.
    private void commandToFollow() {
        commandsAtWild();
        System.out.println();
        System.out.println("What do you want to do next ?");
    }

    // EFFECTS: Process everyaction in the wild.
    private void actionAtWild(String command) {
        if (command.equals("a")) {
            if (bag.getNumWeapon() == 0) {
                System.out.println("You don't have any weapon in the bag.");
            } else {
                actionaAtWild();
            }
        } else if (command.equals("b")) {
            actionbAtWild();
        } else if (command.equals("c")) {
            actioncAtWild();
        } else if (command.equals("d")) {
            actiondAtWild();
        } else if (command.equals("e")) {
            actioneAtWild();
        } else if (command.equals("f")) {
            showStatueC();
        } else {
            showWeaponBag();
        }
    }

    // EFFECTS: Print all weapons on the bag. Print no weapon message when bag is
    // empty.
    private void showWeaponBag() {
        if (bag.getNumWeapon() == 0) {
            System.out.println("There is no weapon in the bag.");
        } else {
            for (int i = 0; i < bag.getNumWeapon(); i++) {
                System.out.println("Name: " + bag.getWeaponList().get(i).getName()
                        + "\nAttacking Value: " + bag.getWeaponList().get(i).getAv()
                        + "\nDurability: " + bag.getWeaponList().get(i).getDurability() + "\n");
            }

        }
    }

    // EFFECTS: Process Command e at wild.
    private void actioneAtWild() {
        boolean success;
        success = sevenFivePercent();
        if (success) {
            System.out.println("You find a Monster!!!");
            System.out.println();
            Monster mon = generateM();
            showStatueM(mon);
            System.out.println("Do you want to fight with this monster ? (Yes or No) "
                    + "\nWARNING: If you die, you will lose ALL DATA!!!");
            System.out.println();
            String answer = input.next();
            if (answer.equals("Yes")) {
                fightM(mon);
            } else {
                return;
            }
        } else {
            System.out.println("You can't find a monster.");
        }
    }

    // EFFECTS: The major procedure of the fight.
    @SuppressWarnings("methodlength")
    private void fightM(Monster monster) {

        System.out.println("The fight Starts!");
        while (true) {
            System.out.println("Monster:");
            showStatueM(monster);
            fightProPrint();
            String action = input.next();
            if (action.equals("q")) {
                boolean success = fiftyPercent();
                if (success) {
                    System.out.println("You escape successfully!");
                    break;
                } else {
                    System.out.println("You fail to escape");
                }
            } else {
                processFight(action, monster);
            }

            checkDie();

            if (monster.getHv() <= 0) {
                beatReward(monster);
                break;

            }

            monsterAttack(monster);

        }
    }

    // EFFECTS: Check if the Character is died. If is, end the game.
    private void checkDie() {
        if (cha.getHv() <= 0) {
            System.out.println("Unfortunately, You DIE!");
            System.exit(0);
        }
    }

    // EFFECTS: Print information of the monster and character.
    private void fightProPrint() {
        System.out.println();
        System.out.println("Character:");
        showStatueC();
        System.out.println();
        commandAtFight();
        System.out.println();
        System.out.println("What will you do next?");
    }

    // MODIFIES: cha
    // EFFECTS: Monster hits character, which will cause you lose the health and
    // durability of the weapon.
    private void monsterAttack(Monster monster) {
        System.out.println(monster.getName() + " try to hit you!");
        boolean hit = fiftyPercent();
        if (hit) {
            System.out.println("You are hit by Monster!");
            cha.changeHv((-1) * monster.getAv());
        } else {
            System.out.println("You miss the monster's hit!");
        }
        System.out.println();
    }

    // MODIFIES: cha
    // EFFECTS: Process the reward after beating the monster.
    private void beatReward(Monster monster) {
        System.out.println("You beat the Monster!");
        System.out.println();
        System.out.println("Monster generates a weapon!");
        findWeapon();

        int experience = monster.generateE();
        System.out.println("You get " + experience + " experience!");
        cha.addExp(experience);
        if (cha.getExperience() >= cha.getUpgradeExp()) {
            System.out.println("Congratulations! You upgrade!");
            cha.upgrade();
        }
    }

    // MODIFIES: cha, monster
    // EFFECTS: Detailed information of the fight between character and monster.
    @SuppressWarnings("methodlength")
    private void processFight(String act, Monster monster) {
        if (act.equals("a")) {
            boolean hit = sevenFivePercent();
            if (hit) {
                System.out.println();
                System.out.println("You hit the Monster!");
                monster.changeHv((-1) * cha.getAv());
                if (cha.getIsarm()) {
                    cha.getWeapon().changeDurability((-1) * monster.getHarmw());

                    if (cha.getWeapon().getDurability() <= 0) {
                        cha.getWeapon().changeDurability((-1) * cha.getWeapon().getDurability());
                        System.out.println();
                        if (bag.getNumWeapon() == 0) {
                            System.out.println("Your weapon is damaged! Now you need to fight with your fist!");
                            actionbAtWild();
                        } else {
                            System.out.println("Your weapon is damaged! Please equip another one:");
                            actionaAtWild();
                        }

                    }
                }
            } else {
                System.out.println();
                System.out.println("Monster dodges your hit.");
            }
        } else if (act.equals("b")) {
            System.out.println();
            if (cha.getMv() < 20) {
                System.out.println("You don't have enough magic");
            } else {
                System.out.println("Your wind bullet hit the monster!");
                monster.changeHv((-1) * cha.getAv() - 10);
                cha.changeMv(-20);
            }

        } else if (act.equals("c")) {
            System.out.println();
            if (cha.getMv() < 50) {
                System.out.println("You don't have enough magic!");
            } else {
                System.out.println("Your wind bullet hit the monster!");
                monster.changeHv((-1) * cha.getAv() - 20);
                cha.changeMv(-50);
            }

        } else {
            if (bag.getNumWeapon() == 0) {
                System.out.println("You don't have any weapon in your bag!");
            } else {
                actionaAtWild();
            }
        }
    }

    // EFFECTS: Fifty percent probability generator.
    private boolean fiftyPercent() {
        int num = (int) (Math.random() * 2);
        if (num == 0) {
            return true;
        } else {
            return false;
        }
    }

    // EFFECTS: Commands when character on the Fight.
    private void commandAtFight() {
        System.out.println();
        System.out.println("a:\tGive monster a hit (75% success)");
        System.out.println("b:\tWind Bullet (Comsume 20 magic) ");
        System.out.println("c:\tDeath Bullet (Consume 50 magic)");
        System.out.println("d:\tEquip Weapon");
        System.out.println("q:\tEscape (50% success)");

    }

    // EFFECTS: Show the status of Character.
    private void showStatueC() {
        System.out.println("Name: " + cha.getName() + "\n" + "Health Number: "
                + cha.getHv() + "\nAttacking Number: " + cha.getAv()
                + "\nMagic Number: " + cha.getMv() + "\nDefence Number: " + cha.getDv()
                + "\nLevel: " + cha.getLevel() + "\nExperience: " + cha.getExperience());
    }

    // EFFECTS: Show the statue of Monster.
    private void showStatueM(Monster monster) {
        System.out.println("Name: " + monster.getName() + "\n" + "Health Number: "
                + monster.getHv() + "\nAttacking Number: " + monster.getAv()
                + "\nDamage to the weapon: " + monster.getHarmw());
    }

    // EFFECTS: Generate a random monster.
    private Monster generateM() {
        int name = (int) (Math.random() * 5);
        int hv = (int) (Math.random() * 31) + 70;
        int av = (int) (Math.random() * 6) + 5;
        int harmw = (int) (Math.random() * 3) + 3;
        Monster monster = new Monster(nameMonster.get(name), hv, av, harmw);
        return monster;
    }

    // EFFECTS: 75% probability generator.
    private boolean sevenFivePercent() {
        int num = (int) (Math.random() * 100) + 1;
        if (num >= 1 && num <= 75) {
            return true;
        } else {
            return false;
        }
    }

    // EFFECTS: Process command d when character on the wild.
    private void actiondAtWild() {
        boolean success = fivePercent();
        if (success) {
            System.out.println("You find a weapon!");
            findWeapon();
        } else {
            System.out.println("You can't find anything");
        }
    }

    // MODIFIES: bag
    // EFFECTS: Stimulate the situation when character finds a weapon/
    private void findWeapon() {
        Weapon wea = generateWeapon();
        System.out.println("Name: " + wea.getName() + "\nAttacking Value: "
                + wea.getAv() + "\nDurability: " + wea.getDurability());
        System.out.println("Do you want to pick up this weapon ? (Yes or No)");
        String answer = input.next();
        if (answer.equals("No")) {
            return;
        } else {
            if (bag.getNumWeapon() == bag.getCapacity()) {
                System.out.println("Your bag is full! "
                        + "Do you want drop one of your weapons on the bag to pick new one ? (Yes or No)");
                String ans = input.next();
                if (ans.equals("Yes")) {
                    actioncAtWild();
                    bag.addWeapon(wea);
                } else {
                    return;
                }
            } else {
                bag.addWeapon(wea);
            }
        }
    }

    // EFFECTS: Generate a Weapon.
    private Weapon generateWeapon() {
        int numName = (int) (Math.random() * 5);
        int numAv = (int) (Math.random() * 10) + 1;
        int numDu = (int) (Math.random() * 100) + 1;
        Weapon weap = new Weapon(nameWeapon.get(numName), numAv, numDu);
        return weap;
    }

    // EFFECTS: Five Percent Probability generator.
    private boolean fivePercent() {
        int num = (int) (Math.random() * 99) + 1;
        if (num >= 1 && num <= 5) {
            return true;
        } else {
            return false;
        }
    }

    // MODIFIES: bag
    // EFFECTS: Process command c when character in the wild.
    private void actioncAtWild() {
        if (bag.getNumWeapon() == 0) {
            System.out.println("Your bag is empty!");
        } else {
            for (int i = 0; i < bag.getNumWeapon(); i++) {
                System.out.println((i + 1) + ": " + bag.getWeaponList().get(i).getName() + "\tAttacking: "
                        + bag.getWeaponList().get(i).getAv()
                        + "Durability: " + bag.getWeaponList().get(i).getDurability());
            }

            System.out.println("Which weapon do you want to drop off ?");
            String choice = input.next();
            bag.removeWeapon(bag.getWeaponList().get(Integer.valueOf(choice) - 1));
        }
    }

    // MODIFIES: bag, cha
    // EFFECTS: Process Command b when character in the wild.
    private void actionbAtWild() {
        if (bag.getNumWeapon() == bag.getCapacity()) {
            System.out.println("Your bag is full!");
        } else {
            bag.addWeapon(cha.getWeapon());
            cha.removeWeapon();
            System.out.println("You have already remove the weapon!");
        }
    }

    // MODIFIES: cha, bag
    // EFFECTS: Process Command a when character in the wild.
    private void actionaAtWild() {
        for (int i = 0; i < bag.getNumWeapon(); i++) {
            System.out.println((i + 1) + ": " + bag.getWeaponList().get(i).getName() + "\tAttacking: "
                    + bag.getWeaponList().get(i).getAv()
                    + "\tDurability: " + bag.getWeaponList().get(i).getDurability());
        }

        System.out.println("Which weapon do you want to choose ?");
        String choice = input.next();
        if (!cha.getIsarm()) {
            cha.equipWeapon(bag.getWeaponList().get(Integer.valueOf(choice) - 1));
            bag.removeWeapon(bag.getWeaponList().get(Integer.valueOf(choice) - 1));
        } else {
            bag.addWeapon(cha.getWeapon());
            cha.equipWeapon(bag.getWeaponList().get(Integer.valueOf(choice) - 1));
            bag.removeWeapon(bag.getWeaponList().get(Integer.valueOf(choice) - 1));
        }
    }

    // EFFECTS: Print Commands character can do when he/she is in the wild.
    private void commandsAtWild() {
        System.out.println();
        System.out.println("a:\tEquip a weapon");
        System.out.println("b:\tEquip off weapon");
        System.out.println("c:\tDrop a weapon form the bag");
        System.out.println("d:\tTry to find treasure(5% Success)");
        System.out.println("e:\tSearch for Monster(75% Success)");
        System.out.println("f:\tShow the status");
        System.out.println("g:\tShow the weapons in my bag");
        System.out.println("q:\tGo back to home");
    }

    // EFFECTS: The Frame of the procedure of the action fllowing the command.
    @SuppressWarnings("methodlength")
    private void actionAtHome(String command) {
        if (command.equals("a")) {
            atHome = false;
            System.out.println();
            System.out.println("Now you are in the wild.");
        } else if (command.equals("b")) {
            actionbAtHome();
        } else if (command.equals("c")) {
            actioncAtHome();
        } else if (command.equals("d")) {
            actiondAtHome();
        } else if (command.equals("e")) {
            actionProcessE();
        } else if (command.equals("s")) {
            saveRogueGame();
        } else if (command.equals("l")) {
            loadRogueGame();
        } else {
            if (bag.getNumWeapon() == bag.getCapacity()) {
                System.out.println("Your Bag is full !");
            } else if (home.getNumweapon() == 0) {
                System.out.println("You don't have any weapon in the home now.");
            } else {
                actionfAtHome();
            }

        }
    }

    // EFFECTS: Save the Rogur Game to file.
    @SuppressWarnings("methodlength")
    private void saveRogueGame() {
        try {
            jsonWriterBag.open();
            jsonWriterBag.writeBag(bag);
            jsonWriterBag.close();
            System.out.println("Saved " + "Bag" + " to " + JSON_STORE_BAG);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_BAG);
        }

        try {
            jsonWriterCharacter.open();
            jsonWriterCharacter.writeCharacter(cha);
            jsonWriterCharacter.close();
            System.out.println("Saved " + "Character" + " to " + JSON_STORE_CHARACTER);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_CHARACTER);
        }

        try {
            jsonWriterHome.open();
            jsonWriterHome.writeHome(home);
            jsonWriterHome.close();
            System.out.println("Saved " + "Home" + " to " + JSON_STORE_HOME);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_HOME);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads game from file
    private void loadRogueGame() {
        try {
            bag = jsonReaderBag.readBag();
            System.out.println("Loaded " + "Bag" + " from " + JSON_STORE_BAG);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_BAG);
        }

        try {
            cha = jsonReaderCharacter.readCharacter();
            System.out.println("Loaded " + "Character" + " from " + JSON_STORE_CHARACTER);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_CHARACTER);
        }

        try {
            home = jsonReaderHome.readHome();
            System.out.println("Loaded " + "Home" + " from " + JSON_STORE_HOME);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_HOME);
        }
    }

    // EFFECTS: SubPart of the process of E at home.
    private void actionProcessE() {
        if (bag.getNumWeapon() == 0) {
            System.out.println("There is no weapon in the bag");
        } else {
            actioneAtHome();
        }
    }

    // REQUIRES: bag.numWeapon < bag.capacity and home.numWeapon != 0;
    // MODIFIES: bag, home
    // EFFECTS: Take the weapon from home to bag.
    private void actionfAtHome() {
        while (true) {

            for (int i = 0; i < home.getNumweapon(); i++) {
                System.out.println((i + 1) + ": " + home.getStoredWeapon().get(i).getName());
            }

            System.out.println(
                    "Which weapon do you want to take from the home ? (Enter the number and enter 'No' to quit)");
            String choice = input.next();
            if (choice.equals("No")) {
                break;
            } else {
                bag.addWeapon(home.getStoredWeapon().get(Integer.valueOf(choice) - 1));
                home.takeWeapon(home.getStoredWeapon().get(Integer.valueOf(choice) - 1));
            }
        }
    }

    // REQUIRES: bag.numWeapon > 0
    // MODIFIES: bag,home
    // EFFECTS: Store the weapon form the bag to home.
    private void actioneAtHome() {
        while (true) {

            for (int i = 0; i < bag.getNumWeapon(); i++) {
                System.out.println((i + 1) + ": " + bag.getWeaponList().get(i).getName());
            }
            System.out.println(
                    "Which weapon do you want to store into the home ? (Enter the number and enter 'No' to quit)");
            String choice = input.next();
            if (choice.equals("No")) {
                break;
            } else {
                home.storeWeapon(bag.getWeaponList().get(Integer.valueOf(choice) - 1));
                bag.removeWeapon(bag.getWeaponList().get(Integer.valueOf(choice) - 1));
            }

        }
    }

    // MODIFIES: weaponList, wen
    // EFFECTS: Process the command d at home.
    private void actiondAtHome() {
        while (true) {
            ArrayList<Weapon> weaponList = new ArrayList<Weapon>();
            weaponList = home.getStoredWeapon();
            for (int i = 1; i <= home.getNumweapon(); i++) {
                System.out.println(i + ": " + weaponList.get(i - 1).getName()
                        + "\tDurability: " + weaponList.get(i - 1).getDurability());
            }

            System.out.println(
                    "Which weapon do you want to fix ? (Type number before the Weapon to fix. Type 'No' to exit)");
            String choice = input.next();

            if (choice.equals("No")) {
                break;
            }

            Weapon wen = weaponList.get(Integer.valueOf(choice) - 1);
            wen.changeDurability(wen.getMaxDurability() - wen.getDurability());

        }
    }

    // MODIFIES: cha
    // EFFECTS: Process the command b at home
    private void actionbAtHome() {
        if (cha.getHv() == cha.getMaxhv()) {
            System.out.println("You are already very healthy! Enjoy your food and try to survive.");
        } else {
            cha.changeHv(cha.getMaxhv() - cha.getHv());
            System.out.println("Your injuries are all recovered!");
        }
    }

    // MODIFIES: cha
    // EFFECTS: Process command c at home
    private void actioncAtHome() {
        if (cha.getMv() == cha.getMaxmv()) {
            System.out.println("The magic stone doesn't have any react. It seems your magic is not need to supply.");
        } else {
            cha.changeMv(cha.getMaxmv() - cha.getMv());
            System.out.println("A soft blue beam of light envelops you. You fill your magic is restore.");
        }
    }

    // EFFECTS: Show all the commands you can execute at home.
    private void commandsAtHome() {
        System.out.println();
        System.out.println("a:\tGo to fight the monster");
        System.out.println("b:\tEating Food (Restore health)");
        System.out.println("c:\tTouching the Magic Stone (Restore Magic)");
        System.out.println("d:\tFix the weapon");
        System.out.println("e:\tStore the Weapon from the bag to home");
        System.out.println("f:\tTake the Weapon from the home to bag");
        System.out.println("s:\tSave the game to file");
        System.out.println("l:\tLoad the game from file");
        System.out.println("q:\tQuit the Game");
    }

    // MODIFIES: nameMonster, nameWeapon
    // EFFECTS: Initialization
    private void init() {
        input = new Scanner(System.in);
        createCharacter();
        bag = new Bag(10);
        home = new Home();
        nameMonster = new ArrayList<String>();
        nameWeapon = new ArrayList<String>();
        generateNameM();
        generateNameW();
        atHome = true;

        jsonWriterBag = new JsonWriter(JSON_STORE_BAG);
        jsonReaderBag = new JsonReader(JSON_STORE_BAG);
        jsonWriterHome = new JsonWriter(JSON_STORE_HOME);
        jsonReaderHome = new JsonReader(JSON_STORE_HOME);
        jsonWriterCharacter = new JsonWriter(JSON_STORE_CHARACTER);
        jsonReaderCharacter = new JsonReader(JSON_STORE_CHARACTER);
    }

    // MODIFIES: cha
    // EFFECTS: Craete a character
    private void createCharacter() {
        System.out.println();
        System.out.println("Please enter the name of your character:");
        String name;
        name = input.next();
        cha = new Character(name);
    }

    // MODIFIES: nameMonster
    // EFFECTS: Create the set of Monster name.
    private void generateNameM() {
        nameMonster.add("Black Dragon");
        nameMonster.add("Goblin King");
        nameMonster.add("Slime Queen");
        nameMonster.add("Immortal Zoombie");
        nameMonster.add("The Death of the Wing");
    }

    // MODIFIES: nameWeapon
    // EFFECTS: Create the set of Weapon name.
    private void generateNameW() {
        nameWeapon.add("Morning Star Wand");
        nameWeapon.add("Death Knight");
        nameWeapon.add("Ru YI JIN GU BANG");
        nameWeapon.add("Mobius Ring");
        nameWeapon.add("Black Hole Generator");
    }

    // MODIFIES: nameMonster, nameWeapon
    // EFFECTS: Initialization by using GUI
    public void initGUI(String name) {

        cha = new Character(name);

        bag = new Bag(10);
        home = new Home();
        nameMonster = new ArrayList<String>();
        nameWeapon = new ArrayList<String>();
        generateNameM();
        generateNameW();
        atHome = true;

        jsonWriterBag = new JsonWriter(JSON_STORE_BAG);
        jsonReaderBag = new JsonReader(JSON_STORE_BAG);
        jsonWriterHome = new JsonWriter(JSON_STORE_HOME);
        jsonReaderHome = new JsonReader(JSON_STORE_HOME);
        jsonWriterCharacter = new JsonWriter(JSON_STORE_CHARACTER);
        jsonReaderCharacter = new JsonReader(JSON_STORE_CHARACTER);
    }

    // MODIFIES: bag, cha, home.
    // EFFECTS: Specific method for GUI to load game.
    public void loadGameGUI() {
        try {
            bag = jsonReaderBag.readBag();
        } catch (IOException e) {
            // nothing
        }

        try {
            cha = jsonReaderCharacter.readCharacter();
        } catch (IOException e) {
            // nothing
        }

        try {
            home = jsonReaderHome.readHome();
        } catch (IOException e) {
            // nothing
        }
    }

    // MODIFIES: jsonWriterBag, jsonWriterCharacter, jsonWriterHome.
    // EFFECTS: Specific method for GUI to save game.
    public void saveGameGUI() {
        try {
            jsonWriterBag.open();
            jsonWriterBag.writeBag(bag);
            jsonWriterBag.close();

        } catch (FileNotFoundException e) {
            // nothing
        }

        try {
            jsonWriterCharacter.open();
            jsonWriterCharacter.writeCharacter(cha);
            jsonWriterCharacter.close();

        } catch (FileNotFoundException e) {
            // nothing
        }

        try {
            jsonWriterHome.open();
            jsonWriterHome.writeHome(home);
            jsonWriterHome.close();

        } catch (FileNotFoundException e) {
            // nothing
        }
    }

    // EFFECTS: Specific method for GUI to build weapon.
    public Weapon buildWeaponGUI() {
        int numName = (int) (Math.random() * 5);
        int numAv = (int) (Math.random() * 10) + 1;
        int numDu = (int) (Math.random() * 100) + 1;
        Weapon weap = new Weapon(nameWeapon.get(numName), numAv, numDu);

        return weap;
    }

    public Bag getBag() {
        return bag;
    }

    public Home getHome() {
        return home;
    }

    // MODIFIES: bag
    // EFFECTS: Sort the weapons in the bag based on attacking number.
    public void sortWeaponGUI() {
        List<Weapon> weapons = new ArrayList<>();
        weapons = bag.getWeaponList();

        for (int i = 0; i < weapons.size(); i++) {
            int index = i;
            int value = weapons.get(i).getAv();
            for (int j = i + 1; j < weapons.size(); j++) {
                if (weapons.get(j).getAv() > value) {
                    index = j;
                    value = weapons.get(j).getAv();
                }
            }

            Weapon tem = weapons.get(i);
            weapons.set(i, weapons.get(index));
            weapons.set(index, tem);
        }
    }

}
