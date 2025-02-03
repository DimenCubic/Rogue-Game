package ui.gui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JTextField;

import ui.RogueGame;
import ui.gui.picture.FormPicture;

// Represent GUI for Login.
// User needs to create character by typing name in this interface.
public class Login extends JFrame {
    RogueGame rogue;

    JButton confirm = new JButton("Confirm");
    JLabel title = new JLabel("ROGUE GAME");
    JLabel instruction = new JLabel("Please enter your name.");
    JTextField name = new JTextField(10);
    FormPicture icon = new FormPicture("./data/picture/icon.png");

 
    
    // EFFECTS: Genarate the frame of Login.
    public Login() {

        super("Login");

        try {
            rogue = new RogueGame();
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }

        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel root = new JPanel();
        this.setContentPane(root);
        root.setLayout(new LoginLayout());

        initLogin();

        icon.setPreferredSize(new Dimension(300, 300));

        root.add(confirm);
        root.add(title);
        root.add(instruction);
        root.add(name);
        root.add(icon);

        this.setVisible(true);
    }

     
    // MODIFIES: title, instruction, name, confirm
    // EFFECTS: Initialize the items belonging to Login frame.
    private void initLogin() {
        title.setFont(new Font("Arial", Font.BOLD, 14));
        title.setPreferredSize(new Dimension(150, 30));

        instruction.setFont(new Font("Arial", Font.BOLD, 10));
        instruction.setPreferredSize(new Dimension(150, 30));

        name.setPreferredSize(new Dimension(60, 30));

        confirm.setPreferredSize(new Dimension(150, 30));
        confirm.addActionListener((e) -> {
            rogue.initGUI(name.getText());
            Home home = new Home(rogue);
            home.setVisible(true);

            dispose();
        });
    }

    
    // MODIFIES: this
    // EFFECTS: Layout for Login frame.
    private class LoginLayout extends LayoutAdapter {

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

            title.setLocation(width / 2 + 50, height / 2 - 50 - 50);
            title.setSize(title.getPreferredSize());

            instruction.setLocation(width / 2 + 50, height / 2 - 50);
            instruction.setSize(instruction.getPreferredSize());

            name.setLocation(width / 2 + 50, height / 2);
            name.setSize(name.getPreferredSize());

            confirm.setLocation(width / 2 + 50, height / 2 + 100 - 50);
            confirm.setSize(confirm.getPreferredSize());

            icon.setLocation(100, 100);
            icon.setSize(icon.getPreferredSize());

        }

    }

}
