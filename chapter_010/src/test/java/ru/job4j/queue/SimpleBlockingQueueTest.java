package ru.job4j.queue;

import org.junit.Test;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 23.09.2019
 */
public class SimpleBlockingQueueTest {

    @Test
    public void simpleBlockingQueueWork() throws InterruptedException {
        SimpleBlockingQueue<Integer> simpleBlockingQueue = new SimpleBlockingQueue<>(10);

        Thread producer = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                simpleBlockingQueue.offer(i);
            }
        });

        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                simpleBlockingQueue.poll();
            }
        });

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();
    }

}