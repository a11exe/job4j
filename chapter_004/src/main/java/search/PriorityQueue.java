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
        Task taskFinded = tasks.stream().filter(s -> s.getPriority() >= task.getPriority()).findFirst().orElse(null);
        int i = taskFinded == null ? 0 : tasks.indexOf(taskFinded);
        tasks.add(i, task);
    }

    public Task take() {
        return this.tasks.poll();
    }
}
