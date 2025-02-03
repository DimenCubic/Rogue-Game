package ui.gui;

 
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

import javax.swing.JFrame;
 
import javax.swing.JPanel;

import model.Weapon;
import ui.RogueGame;

// Represents the frame for "Take Weapons" button.
public class Box extends JFrame {

    List<Weapon> weapons = new ArrayList<>();

    // REQUIRES: g is not null
    // EFFECTS: Genarate the frame of the "Take Weapons" button.
    public Box(RogueGame g) {
        super("weapon Box");

        this.setSize(800, 600);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new GridLayout(5, 3));

        weapons = g.getHome().getStoredWeapon();

        for (Weapon i : weapons) {
            JPanel weaponGUI = new WeaponGUI(i);

            weaponGUI.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {

                    g.getBag().addWeapon(i);
                    g.getHome().takeWeapon(i);

                    Box.this.remove(weaponGUI);
                    Box.this.revalidate();
                    Box.this.repaint();
                }
            });

            this.add(weaponGUI);
        }

        this.setVisible(true);
    }

}
