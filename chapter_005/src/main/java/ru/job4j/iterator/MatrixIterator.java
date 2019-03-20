package ru.job4j.iterator;

import java.util.Arrays;
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
    private long count = 0;
    private final long size;

    public MatrixIterator(int[][] array) {
        this.array = array;
        this.size = Arrays.stream(array).flatMapToInt(Arrays::stream).count();
    }

    @Override
    public boolean hasNext() {
        return count < this.size;
    }

    @Override
    public Integer next() {
        int next;
        if (x > this.array.length -1) {
            throw new NoSuchElementException("no next element");
        }
        next = this.array[this.x][this.y];

        if (this.array[this.x].length - 1 > this.y) {
            this.y++;
        } else {
            if (this.array.length - 1 > this.x) {
                this.x++;
                this.y = 0;
            }
        }
        count++;

        return next;
    }
}
