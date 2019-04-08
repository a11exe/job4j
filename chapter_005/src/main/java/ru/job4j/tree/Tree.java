package ru.job4j.tree;

import java.util.*;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 08.04.2019
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E> {

    private final Node<E> root;

    public Tree(E rootValue) {
        this.root = new Node<>(rootValue);
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el != null) {
                if (el.eqValue(value)) {
                    rsl = Optional.of(el);
                    break;
                }
                for (Node<E> child : el.leaves()) {
                    data.offer(child);
                }
            }
        }
        return rsl;
    }

    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        if (findBy(child).isEmpty()) {
            Optional<Node<E>> node = findBy(parent);
            if (node.isPresent()) {
                node.get().add(new Node<>(child));
                result = true;
            }
        }
        return result;
    }

    private class TreeIterator<T extends Comparable<T>> implements Iterator<T> {

        private final Queue<Node<T>> data = new LinkedList<>();

        public TreeIterator() {
            //noinspection unchecked
            data.offer((Node<T>) root);
        }

        @Override
        public boolean hasNext() {
            return !data.isEmpty();
        }

        @Override
        public T next() {
            Node<T> val = data.poll();
            for (Node<T> child : Objects.requireNonNull(val).leaves()) {
                data.offer(child);
            }
            return val.getValue();
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new TreeIterator<>();
    }

    /**
     * Определяет бинарное дерево
     * Если дочерних элементов <=2
     * дерево бинароное
     * @return признак бинарного дерева
     */
    public boolean isBinary() {
        boolean isBinary = true;
        final Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (Objects.requireNonNull(el).leaves().size() > 2) {
                isBinary = false;
                break;
            }
            for (Node<E> child : Objects.requireNonNull(el).leaves()) {
                data.offer(child);
            }
        }

        return isBinary;
    }

}
