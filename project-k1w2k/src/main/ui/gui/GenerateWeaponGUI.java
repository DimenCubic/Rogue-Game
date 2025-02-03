package ui.gui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Weapon;
import ui.RogueGame;
import ui.gui.picture.FormPicture;

// Generate a weapon and generate a weapon frame;
public class GenerateWeaponGUI extends JFrame {

    Weapon weapon;

    JLabel name;
    JLabel attack;
    JLabel durability;
    JLabel askQ;
    FormPicture image;

    JButton confirm;
    JButton giveUp;

    // REQUIRES: g is not null.
    // EFFECTS: Generate a frame for the weapon.
    public GenerateWeaponGUI(RogueGame g) {
        super("New Weapon!");

        this.setSize(800, 500);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel root = new JPanel();
        this.setContentPane(root);
        root.setLayout(new WeaponLayout());

        weapon = g.buildWeaponGUI();

        initLabel();

        initConfirm(g);

        initGiveUp();

        initImage();

        root.add(attack);
        root.add(name);
        root.add(durability);
        root.add(askQ);
        root.add(image);
        root.add(confirm);
        root.add(giveUp);

        this.setVisible(true);

    }

    // REQUIRES: g is not null.
    // MODIFIES: confirm
    // EFFECTS: Initialize confirm Button.
    private void initConfirm(RogueGame g) {
        confirm = new JButton("Yes");
        confirm.setPreferredSize(new Dimension(100, 50));
        confirm.setFont(new Font("Arial", Font.BOLD, 10));
        confirm.addActionListener((e) -> {
            g.getBag().addWeapon(weapon);
            dispose();
        });
    }

    
    // MODIFIES: giveUp
    // EFFECTS: Initialize giveUp button.
    private void initGiveUp() {
        giveUp = new JButton("No");
        giveUp.setPreferredSize(new Dimension(100, 50));
        giveUp.setFont(new Font("Arial", Font.BOLD, 10));
        giveUp.addActionListener((e) -> {
            dispose();
        });
    }

    
    // MODIFIES: image
    // EFFECTS: initialize image object.
    private void initImage() {
        if (weapon.getName().equals("Morning Star Wand")) {
            image = new FormPicture("./data/picture/weapons/MorningStayWand.jpeg");
        } else if (weapon.getName().equals("Black Hole Generator")) {
            image = new FormPicture("./data/picture/weapons/BlackHoleGenerator.png");
        } else if (weapon.getName().equals("Death Knight")) {
            image = new FormPicture("./data/picture/weapons/DeathKnight.png");
        } else if (weapon.getName().equals("Ru YI JIN GU BANG")) {
            image = new FormPicture("./data/picture/weapons/RuYi.png");
        } else if (weapon.getName().equals("Mobius Ring")) {
            image = new FormPicture("./data/picture/weapons/MobiusRing.png");
        }
        image.setPreferredSize(new Dimension(150, 150));
    }

 
    // MODIFIES: attack, name, durability, askQ
    // EFFECTS: Initialize label items.
    private void initLabel() {
        attack = new JLabel("Attacking: " + weapon.getAv());
        attack.setPreferredSize(new Dimension(200, 50));
        attack.setFont(new Font("Arial", Font.BOLD, 15));

        name = new JLabel(weapon.getName());
        name.setPreferredSize(new Dimension(400, 50));
        name.setFont(new Font("Arial", Font.BOLD, 20));

        durability = new JLabel("Durability: " + weapon.getDurability());
        durability.setPreferredSize(new Dimension(200, 50));
        durability.setFont(new Font("Arial", Font.BOLD, 15));

        askQ = new JLabel("Do you want to accept this weapon ?");
        askQ.setPreferredSize(new Dimension(400, 50));
        askQ.setFont(new Font("Arial", Font.BOLD, 15));
    }

    // MODIFIES: this
    // EFFECTS: The layout of the class GenerateWeaponGUI.
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

            name.setLocation(width / 2 - 50, 50);
            name.setSize(name.getPreferredSize());

            attack.setLocation(300, 100);
            attack.setSize(attack.getPreferredSize());

            durability.setLocation(300, 200);
            durability.setSize(durability.getPreferredSize());

            askQ.setLocation(width / 2 - 150, 300);
            askQ.setSize(askQ.getPreferredSize());

            image.setLocation(50, 100);
            image.setSize(image.getPreferredSize());

            confirm.setLocation(width / 2 - 100, 350);
            confirm.setSize(confirm.getPreferredSize());

            giveUp.setLocation(width / 2 + 50, 350);
            giveUp.setSize(giveUp.getPreferredSize());

        }

    }

}
