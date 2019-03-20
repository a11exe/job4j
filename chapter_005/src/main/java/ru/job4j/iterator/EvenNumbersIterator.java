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
    private int index;

    public EvenNumbersIterator(int[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        boolean hasNext = false;
        for (int i = index; i < this.array.length; i++) {
            if (this.array[i] % 2 == 0) {
                hasNext = true;
                break;
            }
        }
        return hasNext;
    }

    @Override
    public Integer next() {
        Integer next = null;
        for (int i = index; i < this.array.length; i++) {
            if (this.array[i] % 2 == 0) {
                next = this.array[i];
                index = ++i;
                break;
            }
        }

        if (next == null) {
            throw new NoSuchElementException("even number not found");
        }

        return next;
    }
}
