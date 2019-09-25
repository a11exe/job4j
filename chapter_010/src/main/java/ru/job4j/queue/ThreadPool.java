package ru.job4j.queue;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 25.09.2019
 */
public class ThreadPool {

    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks;

    public ThreadPool(SimpleBlockingQueue<Runnable> tasks) {
        this.tasks = tasks;
        int size = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < size; i++) {
            Thread worker = new Worker(tasks);
            threads.add(worker);
        }
    }

    public void runPool() {
        for (Thread thread: threads
             ) {
            thread.start();
        }
    }

    public void work(Runnable job) throws InterruptedException {
        tasks.offer(job);
    }

    public void shutdown() {
        for (Thread thread: threads
                ) {
            thread.interrupt();
        }
    }

    class Worker extends Thread {

        private final SimpleBlockingQueue<Runnable> tasks;

        private Worker(SimpleBlockingQueue<Runnable> tasks) {
            this.tasks = tasks;
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                Runnable job;
                try {
                    job = tasks.poll();
                    job.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

}
