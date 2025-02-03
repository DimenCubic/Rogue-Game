package ui.gui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Weapon;
import ui.gui.picture.FormPicture;

// Represents the JPanel weapon.
public class WeaponGUI extends JPanel {

    JLabel name;
    JLabel attack;
    JLabel durability;

    FormPicture image;

    // REQUIRES: w is not null.
    // MODIFIES: name, this, attack,durability.
    // EFFECTS: Generate the panel for a weapon.
    public WeaponGUI(Weapon w) {

        this.setSize(200, 100);
        this.setLayout(new WeaponLayout());

        name = new JLabel(w.getName());
        name.setPreferredSize(new Dimension(100, 10));
        name.setFont(new Font("Arial", Font.BOLD, 10));

        attack = new JLabel("Attacking: " + w.getAv());
        attack.setPreferredSize(new Dimension(100, 10));
        attack.setFont(new Font("Arial", Font.BOLD, 10));

        durability = new JLabel("Durability: " + w.getDurability());
        durability.setPreferredSize(new Dimension(100, 10));
        durability.setFont(new Font("Arial", Font.BOLD, 10));

        initImage(w);

        this.add(name);
        this.add(attack);
        this.add(durability);
        this.add(image);

    }

    // REQUIRES: w is not null.
    // MODIFIES: w, image.
    // EFFECTS: Initialize the image.
    private void initImage(Weapon w) {
        if (w.getName().equals("Morning Star Wand")) {
            image = new FormPicture("./data/picture/weapons/MorningStayWand.jpeg");
        } else if (w.getName().equals("Black Hole Generator")) {
            image = new FormPicture("./data/picture/weapons/BlackHoleGenerator.png");
        } else if (w.getName().equals("Death Knight")) {
            image = new FormPicture("./data/picture/weapons/DeathKnight.png");
        } else if (w.getName().equals("Ru YI JIN GU BANG")) {
            image = new FormPicture("./data/picture/weapons/RuYi.png");
        } else if (w.getName().equals("Mobius Ring")) {
            image = new FormPicture("./data/picture/weapons/MobiusRing.png");
        }
        image.setPreferredSize(new Dimension(50, 50));
    }

    
    // MODIFIES: this
    // EFFECTS: Layout for Weapon panel.
    private class WeaponLayout extends LayoutAdapter {

        @Override
        public void addLayoutComponent(Component comp, Object constraints) {

        }

        @Override
        public void removeLayoutComponent(Component comp) {

        }

        @Override
        public void layoutContainer(Container parent) {
            int width = parent.getWidth();
            int height = parent.getHeight();

            name.setLocation(width / 2 - 60, 10);
            name.setSize(name.getPreferredSize());

            attack.setLocation(width / 2 - 60, 30);
            attack.setSize(attack.getPreferredSize());

            durability.setLocation(width / 2 - 60, 50);
            durability.setSize(durability.getPreferredSize());

            image.setLocation(20, 20);
            image.setSize(image.getPreferredSize());
        }

    }
}
