package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Итератор двумерного массива
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 19.03.2019
 */
public class MatrixIterator implements Iterator<Integer> {

    private final int[][] array;
    private int x = 0;
    private int y = 0;

    public MatrixIterator(int[][] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        return (x < this.array.length);
    }

    @Override
    public Integer next() {
        int next;
        if (!hasNext()) {
            throw new NoSuchElementException("no next element");
        }
        next = this.array[x][y];
        moveCursor();
        return next;
    }

    private void moveCursor() {
        if (this.array[x].length - 1 > y) {
            y++;
        } else {
            x++;
            y = 0;
        }
    }
}
