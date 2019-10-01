package ru.job4j.bomberman;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 30.09.2019
 */
public class Cell {
    private final int x;
    private final int y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Cell{"
                + "x="
                + x
                + ", y="
                + y
                + '}';
    }
}
