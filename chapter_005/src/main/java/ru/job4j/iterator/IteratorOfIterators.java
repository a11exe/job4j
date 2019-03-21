package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Итератор итераторов
 *
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 20.03.2019
 */
public class IteratorOfIterators implements Iterator<Integer> {

    private Iterator<Iterator<Integer>> it;
    private Iterator<Integer> actual;

    public IteratorOfIterators() {
    }

    public IteratorOfIterators(Iterator<Iterator<Integer>> it) {
        this.it = it;
        this.actual = it.hasNext() ? it.next() : null;
    }

    public Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new IteratorOfIterators(it);
    }

    @Override
    public boolean hasNext() {
        boolean hasNext = false;
        if (actual != null) {
            hasNext = actual.hasNext();
            while (!hasNext && it.hasNext()) {
                actual = it.next();
                hasNext = actual.hasNext();
            }
        }
        return hasNext;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException("no iterators");
        }
        if (!actual.hasNext()) {
            actual = it.next();
        }

        return actual.next();
    }
}
