package ru.job4j.list;

/**
 * Очередь FIFO
 *
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 26.03.2019
 */
public class SimpleQueue<T> extends SimpleStack<T> {

    @Override
    public T poll() {
        final Node<T> f = last;
        return (f == null) ? null : unlinkLast(f);
    }

    private T unlinkLast(Node<T> f) {
        final T element = f.item;
        final Node<T> prev = f.prev;
        f.item = null;
        f.prev = null; // help GC
        last = prev;
        if (last == null) {
            first = null;
        } else {
            prev.next = null;
        }
        size--;
        modCount++;
        return element;
    }
}
