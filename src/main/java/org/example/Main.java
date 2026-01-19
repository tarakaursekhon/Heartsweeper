package org.example;

import javax.swing.*;
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

    static String name = "?";
    static short covered = 0;

    public static void main(String[] args) {
        screen.setSize(614, 637);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setLayout(new GridLayout(height, width));

        JTextField enterName = new JTextField("enter name: ");
        enterName.setFont(new Font("Baskerville Old Face", Font.PLAIN, 20));
        enterName.setBackground(new Color(255, 255, 255));
        enterName.setOpaque(true);
        enterName.setBorder(new EmptyBorder(0, 40, 0, 40));
        enterName.setBounds(200, 250, 200, 100);

        name = JOptionPane.showInputDialog(screen, enterName);
        if (name == null || name.trim().isEmpty()) {
            name = "?";
        }

        for (int i = 0; i < (height * width); i++) {
            squares[i] = new Square(xCurr, yCurr, i, (rand.nextFloat() < 0.15));

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

            if (!squares[i].isHeart) {
                Square[] adjacentSquares = getAdjacentSquares(squares[i]);

                for (Square adjacentSquare : adjacentSquares) {
                    if (adjacentSquare.isHeart) {
                        num++;
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

    public static Square[] getAdjacentSquares(Square sqr) {
        Square[] adjSqrs;

        if (sqr.x == 0 && sqr.y == 0) {
            adjSqrs = new Square[3];
            adjSqrs[0] = squares[sqr.i + 1];
            adjSqrs[1] = squares[sqr.i + width];
            adjSqrs[2] = squares[sqr.i + width + 1];
        }
        else if (sqr.x == 0 && sqr.y == 19) {
            adjSqrs = new Square[3];
            adjSqrs[0] = squares[sqr.i - width];
            adjSqrs[1] = squares[sqr.i - width + 1];
            adjSqrs[2] = squares[sqr.i + 1];
        }
        else if (sqr.x == 19 && sqr.y == 0) {
            adjSqrs = new Square[3];
            adjSqrs[0] = squares[sqr.i - 1];
            adjSqrs[1] = squares[sqr.i + width - 1];
            adjSqrs[2] = squares[sqr.i + width];
        }
        else if (sqr.x == 19 && sqr.y == 19) {
            adjSqrs = new Square[3];
            adjSqrs[0] = squares[sqr.i - width - 1];
            adjSqrs[1] = squares[sqr.i - width];
            adjSqrs[2] = squares[sqr.i - 1];
        }
        else if (sqr.x == 0) {
            adjSqrs = new Square[5];
            adjSqrs[0] = squares[sqr.i - width];
            adjSqrs[1] = squares[sqr.i - width + 1];
            adjSqrs[2] = squares[sqr.i + 1];
            adjSqrs[3] = squares[sqr.i + width];
            adjSqrs[4] = squares[sqr.i + width + 1];
        }
        else if (sqr.x == 19) {
            adjSqrs = new Square[5];
            adjSqrs[0] = squares[sqr.i - width - 1];
            adjSqrs[1] = squares[sqr.i - width];
            adjSqrs[2] = squares[sqr.i - 1];
            adjSqrs[3] = squares[sqr.i + width - 1];
            adjSqrs[4] = squares[sqr.i + width];
        }
        else if (sqr.y == 0) {
            adjSqrs = new Square[5];
            adjSqrs[0] = squares[sqr.i - 1];
            adjSqrs[1] = squares[sqr.i + 1];
            adjSqrs[2] = squares[sqr.i + width - 1];
            adjSqrs[3] = squares[sqr.i + width];
            adjSqrs[4] = squares[sqr.i + width + 1];
        }
        else if (sqr.y == 19) {
            adjSqrs = new Square[5];
            adjSqrs[0] = squares[sqr.i - width - 1];
            adjSqrs[1] = squares[sqr.i - width];
            adjSqrs[2] = squares[sqr.i - width + 1];
            adjSqrs[3] = squares[sqr.i - 1];
            adjSqrs[4] = squares[sqr.i + 1];
        }
        else {
            adjSqrs = new Square[8];
            adjSqrs[0] = squares[sqr.i - width - 1];
            adjSqrs[1] = squares[sqr.i - width];
            adjSqrs[2] = squares[sqr.i - width + 1];
            adjSqrs[3] = squares[sqr.i - 1];
            adjSqrs[4] = squares[sqr.i + 1];
            adjSqrs[5] = squares[sqr.i + width - 1];
            adjSqrs[6] = squares[sqr.i + width];
            adjSqrs[7] = squares[sqr.i + width + 1];
        }

        return adjSqrs;
    }

    public static void endGame() {
        SaveScores save = new SaveScores();
        save.SaveScores_CVM_main(name, Short.valueOf(covered));

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