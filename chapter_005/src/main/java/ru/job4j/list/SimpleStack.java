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
        return lightLinkedList.removeFirst();
    }

    /**
     * Вставляем элемент в начало очереди
     * @param value значение элемента
     */
    public void push(T value) {
        lightLinkedList.addFirst(value);
    }

    /**
     * Возвращает размер стека
     * @return размер стека
     */
    public int size() {
        return lightLinkedList.size();
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
