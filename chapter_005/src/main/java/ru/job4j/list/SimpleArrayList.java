package ru.job4j.list;

import java.util.*;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 25.03.2019
 */
public class SimpleArrayList<E> implements Iterable<E> {

    private int size;
    private Object[] container;
    private int modCount;
    private static final int DEFAULT_CAPACITY = 10;

    public SimpleArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public SimpleArrayList(int initialCapacity) {
        this.container = new Object[initialCapacity];
        modCount = 0;
    }

    /**
     * Вычисляет новый размер массива
     */
    private int newCapacity() {
        int oldCapacity = container.length;
        return oldCapacity + (oldCapacity >> 1);
    }

    /**
     * Увеличиваем массив
     * @return новый увеличенный массив
     */
    private Object[] grow() {
        container = Arrays.copyOf(container, newCapacity());
        return container;
    }

    /**
     * Добавляет объект в коллекцию
     * @param e объект для добавления.
     * @param elementData коллекция в которую будет добавлен объект
     * @param s текущий размер коллекции
     */
    private void add(E e, Object[] elementData, int s) {
        if (s == elementData.length) {
            elementData = grow();
        }
        elementData[s] = e;
        size = s + 1;
    }

    /**
     * Добавляем объект в коллекцию
     * @param value объект для добавления
     */
    public void add(E value) {
        modCount++;
        add(value, container, size);
    }

    /**
     * Получаем объект из колелкции
     * @param index индекс объекта
     * @return объект по заданному индексу
     */
    public E get(int index) {
        Objects.checkIndex(index, size);
        //noinspection unchecked
        return (E) container[index];
    }

    /**
     * Итератор коллекции с проверкой модификации коллекции при обходе.
     */
    private class Itr implements Iterator<E> {
        int cursor;       // index of next element to return
        final int expectedModCount = modCount;

        // prevent creating a synthetic constructor
        Itr() {
        }

        public boolean hasNext() {
            return cursor != size;
        }

        @SuppressWarnings("unchecked")
        public E next() {
            checkForComodification();
            int i = cursor;
            if (i >= size) {
                throw new NoSuchElementException();
            }
            Object[] elementData = SimpleArrayList.this.container;
            if (i >= elementData.length) {
                throw new ConcurrentModificationException();
            }
            cursor = i + 1;
            return (E) elementData[i];
        }

        final void checkForComodification() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }
    }

    /**
     * Возвращает итератор для коллекции
     * @return итератор для коллекции
     */
    public Iterator<E> iterator() {
        //noinspection unchecked
        return new SimpleArrayList.Itr();
    }
}
