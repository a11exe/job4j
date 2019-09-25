package ru.job4j.queue;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 25.09.2019
 */
public class ThreadPoolTest {

    @Test
    public void checkThreadPool() throws InterruptedException {

        SimpleBlockingQueue<Runnable> queue = new SimpleBlockingQueue<>(5);

        List<Runnable> jobs = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            jobs.add(() -> System.out.println(String.format("%s starting job %s", Thread.currentThread().getName(), finalI)));
        }

        // init pool
        final ThreadPool threadPool = new ThreadPool(queue);

        // adding jobs to queue
        Thread producer = new Thread(
                () -> {
                    for (Runnable job: jobs) {
                        try {
                            threadPool.work(job);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );

        // get job from queue and run in thread from thread pool
        Thread consumer = new Thread(
                threadPool::runPool
        );
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();

        System.out.println(consumer.getState());

        System.out.println("finish");

    }

    @Test
    public void checkShutdownThreadPool() throws InterruptedException {

        SimpleBlockingQueue<Runnable> queue = new SimpleBlockingQueue<>(5);

        List<Runnable> jobs = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            jobs.add(() -> System.out.println(String.format("%s starting job %s", Thread.currentThread().getName(), finalI)));
        }

        // init pool
        final ThreadPool threadPool = new ThreadPool(queue);

        // adding jobs to queue
        Thread producer = new Thread(
                () -> {
                    for (Runnable job: jobs) {
                        try {
                            threadPool.work(job);
                            Thread.currentThread().wait(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );

        // get job from queue and run in thread from thread pool
        Thread consumer = new Thread(
                threadPool::runPool
        );
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();

        threadPool.shutdown();

        System.out.println(consumer.getState());

        System.out.println("finish");

    }
}