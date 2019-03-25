package ru.job4j.list;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 25.03.2019
 */
public class SimpleLinkedList<E> {

    private int size;
    private Node<E> first;

    /**
     * Метод вставляет в начало списка данные.
     */
    public void add(E date) {
        Node<E> newLink = new Node<>(date);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
    }

    /**
     * Реализовать метод удаления первого элемент в списке.
     */
    public E delete() {
        E deleted = null;
        if (size > 0) {
            deleted = this.first.date;
            this.first = this.first.next;
            size--;
        }
        return deleted;
    }

    /**
     * Метод получения элемента по индексу.
     */
    public E get(int index) {
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.date;
    }

    /**
     * Метод получения размера коллекции.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Класс предназначен для хранения данных.
     */
    private static class Node<E> {

        final E date;
        Node<E> next;

        Node(E date) {
            this.date = date;
        }
    }
}
