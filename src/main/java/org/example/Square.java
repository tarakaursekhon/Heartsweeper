package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Square extends JButton implements ActionListener {

    int x;
    int y;
    boolean isHeart;
    boolean pressed;
    Image heart = new ImageIcon(getClass().getResource("/heart.png")).getImage();

    public Square(int xIn, int yIn, boolean isHeartIn) {
        x = xIn;
        y = yIn;
        isHeart = isHeartIn;
        pressed = false;

        this.addActionListener(this);
    }

    protected void paintComponent(Graphics gr) {
        super.paintComponent(gr);

        if (!pressed) {
            gr.setColor(new Color(255, 153, 194));
            gr.fillRect(0, 0, getWidth(), getHeight());
            gr.setColor(Color.black);
            gr.drawRect(0, 0, getWidth(), getHeight());
        }
        else {
            gr.setColor(Color.gray);
            gr.fillRect(0, 0, getWidth(), getHeight());
            gr.setColor(Color.black);
            gr.drawRect(0, 0, getWidth(), getHeight());

            if (isHeart) {
                gr.drawImage(heart, 0, 0, getWidth(), getHeight(), null);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        pressed = true;
        repaint();
    }

}