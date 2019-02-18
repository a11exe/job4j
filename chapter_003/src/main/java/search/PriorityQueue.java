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
        int index = 0;
        for (Task t: tasks
                ) {
            if (t.getPriority() >= task.getPriority()) {
                index = tasks.lastIndexOf(t);
                break;
            }
        }
        tasks.add(index, task);
    }

    public Task take() {
        return this.tasks.poll();
    }
}
