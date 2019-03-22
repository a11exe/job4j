package ru.jobj.generic;

import java.util.Arrays;
import java.util.Iterator;

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
        this.array[index] = model;
    }

    public void remove(int index) {
        if (index < this.array.length) {
            System.arraycopy(this.array, index + 1, this.array, index, cursor - index);
            this.array[cursor] = null;
            cursor--;
        }
    }

    public T get(int index) {
        //noinspection unchecked
        return (index < this.array.length) ? (T) this.array[index] : null;
    }

    @Override
    public Iterator<T> iterator() {
        //noinspection unchecked
        return (Iterator<T>) Arrays.stream(this.array).iterator();
    }
}
