package ui.gui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import model.Event;
import model.EventLog;
import ui.RogueGame;
import ui.gui.picture.FormPicture;

// Represents the frame of the home.
public class Home extends JFrame {

    JLabel title = new JLabel("HOME");

    JButton loadGame = new JButton("Load the game");
    JButton saveGame = new JButton("Save the game");
    JButton buildWeapon = new JButton("Build Weapon");
    JButton takeWeapon = new JButton("Take the Weapon");
    JButton storeWeapon = new JButton("Store the Weapon");
    JButton viewMyWeapon = new JButton("View my weapon");
    JButton sortWeapon = new JButton("Reorder weapons (Attacking)");
    FormPicture fireplace = new FormPicture("./data/picture/fireplace.jpg");

    // REQUIRES: g is not null.
    // MODIFIES: all fields.
    // EFFECTS: Generate the frame of the home.
    public Home(RogueGame g) {
        super("Home");

        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        closeWindow(this, g);

        JPanel root = new JPanel();
        this.setContentPane(root);
        root.setLayout(new HomeLayout());

        initGame(g);

        initWeapon(g);

        sortWeapon.setFont(new Font("Arial", Font.BOLD, 10));
        sortWeapon.setPreferredSize(new Dimension(200, 50));
        sortWeapon.addActionListener((e) -> {
            g.sortWeaponGUI();
        });

        viewMyWeapon.setFont(new Font("Arial", Font.BOLD, 10));
        viewMyWeapon.setPreferredSize(new Dimension(200, 50));
        viewMyWeapon.addActionListener((e) -> {
            ViewWeaponGUI view = new ViewWeaponGUI(g);
            view.setVisible(true);
        });

        fireplace.setPreferredSize(new Dimension(200, 200));

        addItems(root);

        this.setVisible(true);
    }

    // REQUIRES: root is not null.
    // MODIFIES: root.
    // EFFECTS: Add items to root.
    private void addItems(JPanel root) {
        root.add(title);
        root.add(loadGame);
        root.add(saveGame);
        root.add(buildWeapon);
        root.add(takeWeapon);
        root.add(storeWeapon);
        root.add(viewMyWeapon);
        root.add(fireplace);
        root.add(sortWeapon);
    }

    // REQUIRES: g is not null.
    // MODIFIES: buildWeapon, takeWeapon, storeweapon.
    // EFFECTS: Initialize the weapon items.
    private void initWeapon(RogueGame g) {
        buildWeapon.setFont(new Font("Arial", Font.BOLD, 10));
        buildWeapon.setPreferredSize(new Dimension(200, 50));
        buildWeapon.addActionListener((e) -> {
            GenerateWeaponGUI geneWea = new GenerateWeaponGUI(g);
            geneWea.setVisible(true);

        });

        takeWeapon.setFont(new Font("Arial", Font.BOLD, 10));
        takeWeapon.setPreferredSize(new Dimension(200, 50));
        takeWeapon.addActionListener((e) -> {
            Box box = new Box(g);
            box.setVisible(true);
        });

        storeWeapon.setFont(new Font("Arial", Font.BOLD, 10));
        storeWeapon.setPreferredSize(new Dimension(200, 50));
        storeWeapon.addActionListener((e) -> {
            Bag bag = new Bag(g);
            bag.setVisible(true);
        });

    }

    // REQUIRES: g is not null.
    // MODIFIES: title, loadGame, saveGame.
    // EFFECTS: Initialize the items of the game.
    private void initGame(RogueGame g) {
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setPreferredSize(new Dimension(100, 100));

        loadGame.setFont(new Font("Arial", Font.BOLD, 10));
        loadGame.setPreferredSize(new Dimension(200, 50));
        loadGame.addActionListener((e) -> {
            g.loadGameGUI();
        });

        saveGame.setFont(new Font("Arial", Font.BOLD, 10));
        saveGame.setPreferredSize(new Dimension(200, 50));

        saveGame.addActionListener((e) -> {
            g.saveGameGUI();
        });
    }

    // REQUIRES: frame is not null.
    // MODIFIERS: frame
    // EFFECTS: when we try to close current frame, a window will jump out.
    public void closeWindow(JFrame frame, RogueGame g) {
        this.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {

                int choice = JOptionPane.showConfirmDialog(
                        frame,
                        "Do you want to save this game ?",
                        "Warning",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.WARNING_MESSAGE);

                if (choice == JOptionPane.YES_OPTION) {
                    g.saveGameGUI();
                    printLog();
                    System.exit(0);
                } else if (choice == JOptionPane.NO_OPTION) {
                    printLog();
                    System.exit(0);
                }
            }
        });
    }

    // EFFECTS: Print out the logged events during the running of the application.
    private void printLog() {
        EventLog eventLog = EventLog.getInstance();
        System.out.println("Logged Events:");
        for (Event event : eventLog) {
            System.out.println(event.getDescription());
        }
    }

    // MODIFIES: this
    // EFFECTS: Layout for the Home frame.
    private class HomeLayout extends LayoutAdapter {

        @Override
        public void addLayoutComponent(Component comp, Object constrains) {

        }

        @Override
        public void removeLayoutComponent(Component comp) {

        }

        @Override
        public void layoutContainer(Container parent) {
            int width = parent.getWidth();
            int height = parent.getHeight();

            title.setLocation(width / 2 - 50, 50);
            title.setSize(title.getPreferredSize());

            loadGame.setLocation(30, height - 200);
            loadGame.setSize(loadGame.getPreferredSize());

            saveGame.setLocation(250, height - 200);
            saveGame.setSize(saveGame.getPreferredSize());

            buildWeapon.setLocation(470, height - 200);
            buildWeapon.setSize(buildWeapon.getPreferredSize());

            takeWeapon.setLocation(30, height - 100);
            takeWeapon.setSize(takeWeapon.getPreferredSize());

            storeWeapon.setLocation(250, height - 100);
            storeWeapon.setSize(storeWeapon.getPreferredSize());

            viewMyWeapon.setLocation(470, height - 100);
            viewMyWeapon.setSize(viewMyWeapon.getPreferredSize());

            fireplace.setLocation(width / 2 - 120, 150);
            fireplace.setSize(fireplace.getPreferredSize());

            sortWeapon.setLocation(width / 2 + 100, 210);
            sortWeapon.setSize(sortWeapon.getPreferredSize());
        }

    }

}
