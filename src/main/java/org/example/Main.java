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
        int num;

        JFrame screen = new JFrame("heartsweeper <3");
        screen.setSize(600, 600);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel game = new JPanel();
        game.setLayout(new GridLayout(height, width));
        Square[] squares = new Square[height * width];
        Random rand = new Random();

        for (int i = 0; i < (height * width); i++) {
            squares[i] = new Square(xCurr, yCurr, (rand.nextFloat() < 0.2));

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

        screen.add(game);
        screen.setVisible(true);
    }

}