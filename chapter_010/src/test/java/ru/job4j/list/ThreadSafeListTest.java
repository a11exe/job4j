package ru.job4j.list;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 23.09.2019
 */
public class ThreadSafeListTest {

    @Test
    public void whenIterateInThreadsThenSameResult() throws InterruptedException {
        final AtomicInteger count1 = new AtomicInteger(0);
        final AtomicInteger count2 = new AtomicInteger(0);
        final AtomicInteger count3 = new AtomicInteger(0);

        ThreadSafeList<Integer> threadSafeList = new ThreadSafeList<>();
        for (int i = 0; i < 100; i++) {
            threadSafeList.add(i);
        }

        Thread thread1 = new Thread(() -> {
            for (Integer aThreadSafeList : threadSafeList) {
                count1.getAndAdd(aThreadSafeList);
                System.out.println("thread 1");
            }
            System.out.println("thread 1 finish");
        });

        Thread thread2 = new Thread(() -> {
            for (Integer aThreadSafeList : threadSafeList) {
                count2.getAndAdd(aThreadSafeList);
                System.out.println("thread 2");
            }
            System.out.println("thread 2 finish");
        });

        Thread thread3 = new Thread(() -> {
            for (Integer aThreadSafeList : threadSafeList) {
                count3.getAndAdd(aThreadSafeList);
                System.out.println("thread 3");
            }
            System.out.println("thread 3 finish");
        });

        System.out.println("before join");

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println("after join");

        assertThat(count1.get(), is(4950));
        assertThat(count2.get(), is(4950));
        assertThat(count3.get(), is(4950));

    }

}