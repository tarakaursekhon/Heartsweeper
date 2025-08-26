package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Square extends JButton implements ActionListener {

    int x;
    int y;
    int i;
    boolean isHeart;
    boolean pressed;
    Image heart = new ImageIcon(getClass().getResource("/heart.png")).getImage();
    int number;

    public Square(int xIn, int yIn, int iIn, boolean isHeartIn) {
        x = xIn;
        y = yIn;
        i = iIn;
        isHeart = isHeartIn;
        pressed = false;

        this.addActionListener(this);
    }

    public void setNumber(int num) {
        number = num;
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
                Main.endGame();
            }
            else if (number > 0) {
                Color[] numColours = {Color.blue, Color.green, Color.yellow, Color.pink,
                        Color.red, Color.magenta, Color.orange, Color.darkGray};
                gr.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
                gr.setColor(numColours[number - 1]);
                gr.drawString(Integer.toString(number), 7, 20);
            }
            else {
                Square[] adjacentSquares = Main.getAdjacentSquares(this);

                for (Square adjacentSquare : adjacentSquares) {
                    if (!adjacentSquare.isHeart) {
                        adjacentSquare.pressed = true;
                        adjacentSquare.repaint();
                    }
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        pressed = true;
        repaint();
    }

}