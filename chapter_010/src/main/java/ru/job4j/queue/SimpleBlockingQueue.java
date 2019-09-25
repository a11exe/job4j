package ru.job4j.queue;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;
/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 24.09.2019
 */
@ThreadSafe
public class SimpleBlockingQueue<T> {
    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();
    private final int size;

    public SimpleBlockingQueue(int size) {
        this.size = size;
    }

    public synchronized void offer(T value) throws InterruptedException {
        while (queue.size() == this.size) {
            wait();
        }
        queue.offer(value);
        System.out.println(String.format("Producer set: %s actual size: %s", value, queue.size()));
        notify();

    }

    public synchronized T poll() throws InterruptedException {
        T t;
        while (queue.size() == 0) {
            wait();
        }
        t = queue.poll();
        System.out.println(String.format("Consumer get: %s actual size: %s", t, queue.size()));
        notify();

        return t;
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}