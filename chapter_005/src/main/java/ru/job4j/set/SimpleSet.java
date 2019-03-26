package ru.job4j.set;

import ru.job4j.list.SimpleArrayList;
import java.util.Iterator;

public class SimpleSet<E> implements Iterable<E> {

    private final SimpleArrayList<E> list;

    public SimpleSet() {
        list = new SimpleArrayList<>();
    }

    /**
     * Добавляем элемент в коллекцию если его нет
     * @param e элемент для добавления в коллекцию
     */
    public void add(E e) {
        boolean isNew = true;
        for (E item : list) {
            if (item.equals(e)) {
                isNew = false;
                break;
            }
        }
        if (isNew) {
            list.add(e);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }
}
