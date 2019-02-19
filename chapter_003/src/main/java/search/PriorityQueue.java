package search;

import java.util.LinkedList;

/**
 * Список задач с приоритетами
 *
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 18.02.2019
 */
public class PriorityQueue {
    private final LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Метод должен вставлять в нужную позицию элемент.
     * Позиция определять по полю приоритет.
     * Для вставик использовать add(int index, E value)
     * @param task задача
     */
    public void put(Task task) {
        int i = 0;
        for (; i < tasks.size(); i++) {
            if (tasks.get(i).getPriority() >= task.getPriority()) {
                break;
            }
        }
        tasks.add(i, task);
    }

    public Task take() {
        return this.tasks.poll();
    }
}
