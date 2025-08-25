package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int height = 20;
        int width = 20;
        int xCurr = 0;
        int yCurr = 0;

        JFrame screen = new JFrame("heartsweeper <3");
        screen.setSize(600, 600);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel game = new JPanel();
        game.setLayout(new GridLayout(height, width));
        Square[] squares = new Square[height * width];
        Random rand = new Random();

        for (int i = 0; i < (height * width); i++) {
            squares[i] = new Square(xCurr, yCurr, (rand.nextFloat() < 0.2));
            game.add(squares[i]);

            if (xCurr == (width - 1)) {
                xCurr = 0;
                yCurr++;
            }
            else {
                xCurr++;
            }
        }

        screen.add(game);
        screen.setVisible(true);
    }

}