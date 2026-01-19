package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Square extends JButton implements MouseListener {

    int x;
    int y;
    int i;
    boolean isHeart;
    boolean hasFlag;
    boolean pressed;
    Image heart = new ImageIcon(getClass().getResource("/heart.png")).getImage();
    Image flag = new ImageIcon(getClass().getResource("/flag.png")).getImage();
    int number;

    public Square(int xIn, int yIn, int iIn, boolean isHeartIn) {
        x = xIn;
        y = yIn;
        i = iIn;
        isHeart = isHeartIn;
        hasFlag = false;
        pressed = false;

        this.addMouseListener(this);
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

            if (hasFlag) {
                gr.drawImage(flag, 0, 0, getWidth(), getHeight(), null);
            }
        }
        else {
            gr.setColor(Color.gray);
            gr.fillRect(0, 0, getWidth(), getHeight());
            gr.setColor(Color.black);
            gr.drawRect(0, 0, getWidth(), getHeight());

            if (isHeart) {
                gr.drawImage(heart, 0, 0, getWidth(), getHeight(), null);
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
                        if (!adjacentSquare.pressed) {
                            adjacentSquare.pressed = true;
                            adjacentSquare.repaint();
                            Main.covered++;
                        }
                    }
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            if (isHeart) {
                Main.endGame();
            }
            pressed = true;
            Main.covered++;
        }
        else if (SwingUtilities.isRightMouseButton(e)) {
            if (!hasFlag) {
                hasFlag = true;
            }
            else {
                hasFlag = false;
            }
        }
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}