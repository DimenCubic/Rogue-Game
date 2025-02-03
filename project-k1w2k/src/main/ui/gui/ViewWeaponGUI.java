package ui.gui;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Weapon;
import ui.RogueGame;

//Represents the frame for "ViewMyWeapon" button.
public class ViewWeaponGUI extends JFrame {

    List<Weapon> weapons = new ArrayList<>();

    // REQUIRES: g is not null. 
    // EFFECTS: Generate the frame of "ViewMyweapon" button.
    public ViewWeaponGUI(RogueGame g) {
        super("Bag!!!");

        this.setSize(800, 500);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new GridLayout(3, 5));

        weapons = g.getBag().getWeaponList();

        for (Weapon i : weapons) {
            JPanel weaponGUI = new WeaponGUI(i);
            this.add(weaponGUI);
        }

        this.setVisible(true);

    }
}
