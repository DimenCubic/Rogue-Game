package ui.gui;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Weapon;
import ui.RogueGame;

// Represents the frame for "StoreWeapon" button.
public class Bag extends JFrame {

    List<Weapon> weapons = new ArrayList<>();

    // REQUIRES: g is not null;
    // EFFECTS: Construct the frame for bag.
    public Bag(RogueGame g) {
        super("Bag");

        this.setSize(800, 600);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(new GridLayout(3, 5));

        weapons = g.getBag().getWeaponList();

        for (Weapon i : weapons) {
            JPanel weaponGUI = new WeaponGUI(i);

            weaponGUI.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {

                    g.getBag().removeWeapon(i);
                    g.getHome().storeWeapon(i);

                    Bag.this.remove(weaponGUI);
                    Bag.this.revalidate();
                    Bag.this.repaint();
                }
            });

            this.add(weaponGUI);
        }

        this.setVisible(true);
    }
}
