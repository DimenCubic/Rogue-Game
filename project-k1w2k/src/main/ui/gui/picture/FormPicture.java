package ui.gui.picture;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class FormPicture extends JPanel {
    BufferedImage image;

    public FormPicture(String url) {

        try {
            File file = new File(url);
            // System.out.println(file);
            this.image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);

        g.drawImage(this.image, 0, 0, width, height, null);

    }
}
