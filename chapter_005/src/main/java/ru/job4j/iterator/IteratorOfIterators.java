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
    private Iterator<Integer> actualIterator;

    public IteratorOfIterators() {
    }

    public IteratorOfIterators(Iterator<Iterator<Integer>> it) {
        this.it = it;
        try {
            this.actualIterator = it.next();
        } catch (NoSuchElementException e) {
            this.actualIterator = null;
        }
    }

    public Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new IteratorOfIterators(it);
    }

    @Override
    public boolean hasNext() {
        boolean hasNext = false;
        if (actualIterator != null) {
            hasNext = actualIterator.hasNext();
            if ((!hasNext) && (it.hasNext())) {
                    actualIterator = it.next();
                    hasNext = actualIterator.hasNext();
                }
        }

        return hasNext;
    }

    @Override
    public Integer next() {
        Integer next;
        if (actualIterator == null) {
            throw new NoSuchElementException("no iterators");
        }
        if (actualIterator.hasNext()) {
            next = actualIterator.next();
        } else {
            if (it.hasNext()) {
                actualIterator = it.next();
                next = actualIterator.next();
            } else {
                throw new NoSuchElementException("iterators finish");
            }
        }
        return next;
    }
}
