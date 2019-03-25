package ru.job4j.list;

/**
 * Стек LIFO последний пришел первым ушел
 * @param <T>
 */
public class SimpleStack<T> extends LightLinkedList<T> {

    /**
     * Получаем из головы и удаляем элемент
     * @return значение первого элемента в списке
     */
    public T poll() {
        final Node<T> f = first;
        return (f == null) ? null : unlinkFirst(f);
    }

    /**
     * Убираем ссылки на первый элемент
     * Делаем следующий элемент первым
     * @param f первый элемент в списке
     * @return значение первого элемента в списке
     */
    private T unlinkFirst(Node<T> f) {
        // assert f == first && f != null;
        final T element = f.item;
        final Node<T> next = f.next;
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

    /**
     * Вставляем элемент в начало очереди
     * @param value значение элемента
     */
    public void push(T value) {
        final Node<T> f = first;
        final Node<T> newNode = new Node<>(null, value, f);
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
