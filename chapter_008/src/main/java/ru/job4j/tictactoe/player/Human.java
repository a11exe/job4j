package ru.job4j.tictactoe.player;

import ru.job4j.tictactoe.*;
import ru.job4j.tictactoe.logic.Mark;
import ru.job4j.tictactoe.logic.Point;
import ru.job4j.tictactoe.logic.TicTacToe;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 15.07.2019
 */
public class Human implements Player {

    private final TicTacToe ticTacToe;
    private final Scanner scanner;
    private final PrintStream ps;
    private final String name;
    private final String playerMoveMsg = "Ваш ход. Введите координаты ячейки в формате (xy)";
    private final String inputPointMsg = "Введит 2 числа";
    private Mark mark;

    public Human(TicTacToe ticTacToe, String name, InputStream is, PrintStream ps) {
        this.ticTacToe = ticTacToe;
        this.scanner = new Scanner(is);
        this.ps = ps;
        this.name = name;
    }

    public Mark getMark() {
        return mark;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }

    /**
     * Make move on field using random algoritm
     * @return point of move
     */
    @Override
    public Point move() {

        Point point = null;
        boolean moved = false;
        while (!moved) {
            point = getPoint();
            try {
                ticTacToe.makeMove(point, getMark());
                moved = true;
            } catch (WrongMoveException e) {
                printMessage(e.getMessage());
            }
        }

        return point;
    }

    /**
     * Parse input xy
     * @return xy
     */
    private Point getPoint() {
        Point point = null;
        printMessage(playerMoveMsg);
        String input = scanner.next();
        if (input.length() != 2) {
            printMessage(inputPointMsg);
        } else {
            try {
                int x = Integer.parseInt(input.substring(0, 1));
                int y = Integer.parseInt(input.substring(1, 2));
                point = new Point(x, y);
            } catch (NumberFormatException e) {
                printMessage(inputPointMsg);
            }
        }
        return point;
    }

    private void printMessage(String pattern, String ... vars) {
        ps.println(String.format(pattern, vars));
    }

    @Override
    public String getMoveMessage() {
        return name + " сделал ход: %s%s";
    }
}
