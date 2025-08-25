package org.example;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Random;

public class Main {

    static int height = 20;
    static int width = 20;
    static int xCurr = 0;
    static int yCurr = 0;
    static int num;

    static JFrame screen = new JFrame("heartsweeper <3");
    static JLayeredPane layers = new JLayeredPane();
    static JPanel game = new JPanel();
    static Square[] squares = new Square[height * width];
    static Random rand = new Random();

    public static void main(String[] args) {
        screen.setSize(614, 637);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setLayout(new GridLayout(height, width));

        for (int i = 0; i < (height * width); i++) {
            squares[i] = new Square(xCurr, yCurr, (rand.nextFloat() < 0.1));

            if (xCurr == (width - 1)) {
                xCurr = 0;
                yCurr++;
            }
            else {
                xCurr++;
            }
        }

        for (int i = 0; i < (height * width); i++) {
            num = 0;
            int[] toCheck = {
                    i - width - 1,
                    i - width,
                    i - width + 1,
                    i - 1,
                    i + 1,
                    i + width - 1,
                    i + width,
                    i + width + 1};

            if (!squares[i].isHeart) {
                for (int j = 0; j < 8; j++) {
                    if (toCheck[j] >= 0 && toCheck[j] < (height * width)) {
                        if (squares[toCheck[j]].isHeart) {
                            num++;
                        }
                    }
                }
            }

            squares[i].setNumber(num);
            game.add(squares[i]);
        }

        game.setBounds(0, 0, 600, 600);
        layers.add(game, JLayeredPane.DEFAULT_LAYER);
        screen.add(layers);
        screen.setVisible(true);
    }

    public static void endGame() {
        for (int i = 0; i < (height * width); i++) {
            squares[i].pressed = true;
            squares[i].repaint();
        }

        JLabel endText = new JLabel("game over </3");
        endText.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
        endText.setBackground(new Color(255, 255, 255, 200));
        endText.setOpaque(true);
        endText.setBorder(new EmptyBorder(0, 40, 0, 40));
        endText.setBounds(200, 250, 200, 100);
        layers.add(endText, JLayeredPane.POPUP_LAYER);

        screen.repaint();
    }

}