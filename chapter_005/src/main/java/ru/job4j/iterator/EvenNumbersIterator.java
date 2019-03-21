package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Итератор четных чисел
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 20.03.2019
 */
public class EvenNumbersIterator implements Iterator<Integer> {

    private final int[] array;
    private Integer cursor;

    public EvenNumbersIterator(int[] array) {
        this.array = array;
        this.cursor = 0;
    }

    @Override
    public boolean hasNext() {
        boolean hasNext = false;
        if (cursor < array.length) {
            hasNext = isEven(cursor) || moveCursor();
        }
        return hasNext;
    }

    @Override
    public Integer next() {
        Integer next;
        if (!hasNext()) {
            throw new NoSuchElementException("even number not found");
        }
        next = this.array[cursor];
        moveCursor();
        return next;
    }

    private boolean isEven(int i) {
        return this.array[i] % 2 == 0;
    }

    private boolean moveCursor() {
        boolean hasNextEven = false;
        cursor++;
        for (int i = cursor; i < this.array.length; i++) {
            if (isEven(i)) {
                cursor = i;
                hasNextEven = true;
                break;
            }
        }
        if (!hasNextEven) {
            cursor = array.length;
        }
        return hasNextEven;
    }
}
