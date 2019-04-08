package ru.job4j.map;


import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 02.04.2019
 */
public class SimpleMap<K, V> implements Iterable<V> {

    private int size = 0;

    private static class Node<K, V> {
        final K key;
        final V value;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public final String toString() {
            return key + "=" + value;
        }
    }

    private Node<K, V>[] table;

    static int hash(Object key) {
        int h;
        if (key == null) {
            h = 0;
        } else {
            h = key.hashCode();
            h = h ^ (h >>> 16);
        }
        return h;
    }

    public SimpleMap() {
        //noinspection unchecked
        table = (Node<K, V>[]) new Node[16];
    }

    /**
     * Определяет индекс в массиве по хешу ключа
     * @param hash хеш ключа
     * @return индекс в масссиве
     */
    private int indexFor(int hash) {
        return hash & (table.length - 1);
    }

    /**
     * Увеличивает размер массив Node[]
     * @param size новый размер массива
     */
    private void resize(int size) {
        Node<K, V>[] oldTab = table;
        //noinspection unchecked
        table = (Node<K, V>[]) new Node[size];
        for (Node<K, V> node : oldTab) {
            if (node != null) {
                K key = node.key;
                V value = node.value;
                int hash = hash(key);
                int index = indexFor(hash);
                table[index] = new Node<>(key, value);
            }
        }
    }

    /**
     * Вставка значения.
     * По одинаковым ключам перезаписвает значение
     * @param key ключ
     * @param value значение
     * @return результат вставки
     */
    public boolean insert(K key, V value) {
        boolean result = false;
        if (size == table.length) {
            resize(size * 2);
        }
        int hash = hash(key);
        int index = indexFor(hash);
        Node<K, V> oldVal = table[index];
        if (oldVal == null) {
            table[index] = new Node<>(key, value);
            result = true;
            size++;
        } else if (oldVal.key.equals(key)) {
            table[index] = new Node<>(key, value);
            result = true;
        }
        return result;
    }

    /**
     * Получение значения по ключу
     * @param key ключ
     * @return значение по ключу
     */
    public V get(K key) {
        int hash = hash(key);
        int index = indexFor(hash);
        V value = null;
        Node<K, V> node = table[index];
        if (node != null) {
            if ((node.key == null && key == null) || (node.key != null && node.key.equals(key))) {
                value = node.value;
            }
        }
        return value;
    }

    /**
     * Удаление значения по ключу
     * @param key ключ
     * @return результат удаления
     */
    public boolean delete(K key) {
        boolean result = false;
        if (table.length > 0) {
            int hash = hash(key);
            int index = indexFor(hash);
            Node<K, V> node = table[index];
            if (node != null && key.equals(node.key)) {
                table[index] = null;
                result = true;
                size--;
            }
        }
        return result;
    }

    @Override
    public Iterator<V> iterator() {
        return Arrays.stream(table).filter(Objects::nonNull).map(s->s.value).collect(Collectors.toList()).iterator();
    }
}
