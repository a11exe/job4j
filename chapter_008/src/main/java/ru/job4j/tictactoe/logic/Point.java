package ru.job4j.tictactoe.logic;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 09.07.2019
 */
public class Point {

    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
