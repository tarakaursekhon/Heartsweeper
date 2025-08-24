package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Square extends JButton implements ActionListener {

    int x;
    int y;
    boolean pressed;

    public Square(int xIn, int yIn) {
        x = xIn;
        y = yIn;
        pressed = false;

        this.addActionListener(this);
    }

    protected void paintComponent(Graphics gr) {
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
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        pressed = true;
    }

}