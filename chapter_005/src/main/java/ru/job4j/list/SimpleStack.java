package ru.job4j.list;

/**
 * Стек LIFO последний пришел первым ушел
 * @param <T>
 */
public class SimpleStack<T> {

    private final LightLinkedList<T> lightLinkedList = new LightLinkedList<>();

    /**
     * Получаем из головы и удаляем элемент
     * @return значение первого элемента в списке
     */
    public T poll() {
        final LightLinkedList.Node<T> f = lightLinkedList.first;
        return (f == null) ? null : unlinkFirst(f);
    }

    /**
     * Убираем ссылки на первый элемент
     * Делаем следующий элемент первым
     * @param f первый элемент в списке
     * @return значение первого элемента в списке
     */
    private T unlinkFirst(LightLinkedList.Node<T> f) {
        // assert f == first && f != null;
        final T element = f.item;
        final LightLinkedList.Node<T> next = f.next;
        f.item = null;
        f.next = null; // help GC
        lightLinkedList.first = next;
        if (next == null) {
            lightLinkedList.last = null;
        } else {
            next.prev = null;
        }
        lightLinkedList.size--;
        lightLinkedList.modCount++;
        return element;
    }

    /**
     * Вставляем элемент в начало очереди
     * @param value значение элемента
     */
    public void push(T value) {
        final LightLinkedList.Node<T> f = lightLinkedList.first;
        final LightLinkedList.Node<T> newNode = new LightLinkedList.Node<>(null, value, f);
        lightLinkedList.first = newNode;
        if (f == null) {
            lightLinkedList.last = newNode;
        } else {
            f.prev = newNode;
        }
        lightLinkedList.size++;
        lightLinkedList.modCount++;
    }

    /**
     * Возвращает размер стека
     * @return размер стека
     */
    public int size() {
        return lightLinkedList.size;
    }

    /**
     * Возвращает элемент по индексу
     * @param index индекс элемента
     * @return елемент по этому индексу
     */
    public T get(int index) {
        return lightLinkedList.get(index);
    }
}
