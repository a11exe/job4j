package ru.job4j.list;

/**
 * Очередь FIFO
 *
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 26.03.2019
 */
public class SimpleQueue<T> {

    private final SimpleStack<T> stack = new SimpleStack<>();
    private final SimpleStack<T> stackReverse = new SimpleStack<>();

    /**
     * Добавить элемент в очередь
     * @param value добавляемый элемент
     */
    public void push(T value) {
        stack.push(value);
    }

    /**
     * Взять элемент из очереди по FIFO
     * @return элемент из очереди
     */
    public T poll() {
        T result;
        int stackSize = stack.size();
        for (int i = 0; i < stackSize; i++) {
            stackReverse.push(stack.poll());
        }
        result = stackReverse.poll();
        int stackReverseSize = stackReverse.size();
        for (int i = 0; i < stackReverseSize; i++) {
            stack.push(stackReverse.poll());
        }
       return result;
    }

    /**
     * Возвращает размер очереди
     * @return размер очереди
     */
    public int size() {
        return stack.size();
    }

}
