package ru.job4j.list;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Iterator;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 23.09.2019
 */
@ThreadSafe
public class ThreadSafeList<E> implements Iterable<E> {

    @GuardedBy("simpleArrayList")
    private final SimpleArrayList<E> simpleArrayList = new SimpleArrayList<>();

    @Override
    public Iterator<E> iterator() {
        synchronized (this.simpleArrayList) {
            return copyList(this.simpleArrayList).iterator();
        }
    }

    private SimpleArrayList<E> copyList(SimpleArrayList<E> simpleArrayList) {
        synchronized (this.simpleArrayList) {
            SimpleArrayList<E> simpleArrayListCopy = new SimpleArrayList<>();
            for (E e : this.simpleArrayList) {
                simpleArrayListCopy.add(e);
            }
            return simpleArrayListCopy;
        }
    }

    /**
     * Добавляем объект в коллекцию
     * @param value объект для добавления
     */
    public void add(E value) {
        synchronized (this.simpleArrayList) {
            this.simpleArrayList.add(value);
        }
    }

    /**
     * Получаем объект из колелкции
     * @param index индекс объекта
     * @return объект по заданному индексу
     */
    public E get(int index) {
        synchronized (this.simpleArrayList) {
            return this.simpleArrayList.get(index);
        }
    }
}
