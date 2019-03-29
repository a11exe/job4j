package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 25.03.2019
 */
public class LightLinkedList<E> implements Iterable<E> {

    private Node<E> last;
    private Node<E> first;
    private int size = 0;
    private int modCount = 0;

    public void add(E value) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, value, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        modCount++;
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index)) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    private Node<E> node(int index) {
        // assert isElementIndex(index);

        if (index < (size >> 1)) {
            Node<E> x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            return x;
        } else {
            Node<E> x = last;
            for (int i = size - 1; i > index; i--) {
                x = x.prev;
            }
            return x;
        }
    }

    public E get(int index) {
        checkElementIndex(index);
        return node(index).item;
    }

    /**
     * Итератор коллекции с проверкой модификации коллекции при обходе.
     */
    private class Itr implements Iterator<E> {
        Node<E> actual = first;
        final int expectedModCount = modCount;

        // prevent creating a synthetic constructor
        Itr() {
        }

        public boolean hasNext() {
            return actual != null;
        }

        @SuppressWarnings("unchecked")
        public E next() {
            if (actual == null) {
                throw new NoSuchElementException();
            }
            E next = actual.item;
            checkForComodification();
            actual = actual.next;
            return next;
        }

        final void checkForComodification() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }
    }

    /** @noinspection unchecked*/
    @Override
    public Iterator<E> iterator() {
        return new LightLinkedList.Itr();
    }

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    public int size() {
        return size;
    }

    /**
     * Убираем ссылки на первый элемент
     * Делаем следующий элемент первым
     * @param f первый элемент в списке
     * @return значение первого элемента в списке
     */
    private E unlinkFirst(Node<E> f) {
        // assert f == first && f != null;
        final E element = f.item;
        final Node<E> next = f.next;
        f.item = null;
        f.next = null; // help GC
        first = next;
        if (next == null) {
            last = null;
        } else {
            next.prev = null;
        }
        size--;
        modCount++;
        return element;
    }

    public E removeFirst() {
        final Node<E> f = first;
       E removed = null;
        if (f != null) {
            removed = unlinkFirst(f);
        }
        return removed;
    }

    public void addFirst(E e) {
        final Node<E> f = first;
        final Node<E> newNode = new Node<>(null, e, f);
        first = newNode;
        if (f == null) {
            last = newNode;
        } else {
            f.prev = newNode;
        }
        size++;
        modCount++;
    }
}
