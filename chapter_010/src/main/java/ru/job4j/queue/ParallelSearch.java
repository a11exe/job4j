package ru.job4j.queue;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 24.09.2019
 */
public class ParallelSearch {
    public static void main(String[] args) {

        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(10);
        final Thread consumer = new Thread(
                () -> {
                    while (!Thread.currentThread().isInterrupted()) {
                        try {
                            System.out.println(queue.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        new Thread(
                () -> {
                    for (int index = 0; index != 3; index++) {
                        try {
                            queue.offer(index);
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    consumer.interrupt();
                }

        ).start();

    }
}
