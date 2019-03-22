package ru.jobj.generic;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Обертка над массивом
 *
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 22.03.2019
 */
public class SimpleArray<T> implements Iterable<T> {

    private final Object[] array;
    private int cursor;

    public SimpleArray(int size) {
        this.array = new Object[size];
    }

    public void add(T model) throws ArrayIndexOutOfBoundsException {
        this.array[cursor++] = model;
    }

    public void set(int index, T model) throws ArrayIndexOutOfBoundsException {
        checkSize(index);
        this.array[index] = model;
    }

    public void remove(int index) throws ArrayIndexOutOfBoundsException {
        checkSize(index);
        System.arraycopy(this.array, index + 1, this.array, index, cursor - index);
        this.array[cursor] = null;
        cursor--;
    }

    public T get(int index) {
        //noinspection unchecked
        return (index < this.array.length) ? (T) this.array[index] : null;
    }

    @Override
    public Iterator<T> iterator() {
        //noinspection unchecked
        return new SimpleArrayIterator(cursor, (Iterator<T>) Arrays.stream(this.array).iterator());
    }

    private void checkSize(int index) throws ArrayIndexOutOfBoundsException {
        if (index > cursor || cursor == 0) {
            throw new ArrayIndexOutOfBoundsException("empty or full");
        }
    }

    private class SimpleArrayIterator implements Iterator<T> {

        private final int size;
        private int cursor;
        private final Iterator<T> arrayIterator;

        public SimpleArrayIterator(int size, Iterator<T> arrayIterator) {
            this.size = size;
            this.arrayIterator = arrayIterator;
            cursor = 0;
        }

        @Override
        public boolean hasNext() {
            return (cursor <= size - 1) && arrayIterator.hasNext();
        }

        @Override
        public T next() {
            if (cursor > size - 1) {
                throw new NoSuchElementException("cursor lower than size");
            }
            cursor++;
            return arrayIterator.next();
        }
    }
}
