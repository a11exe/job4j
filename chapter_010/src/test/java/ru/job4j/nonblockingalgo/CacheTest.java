package ru.job4j.nonblockingalgo;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.CoreMatchers.is;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 24.09.2019
 */
public class CacheTest {

    @Test
    public void whenThrowException() throws InterruptedException {
        Cache cache = new Cache();
        Base model = new Base(1, 1);
        cache.add(model);
        AtomicReference<Exception> ex = new AtomicReference<>();
        Thread thread1 = new Thread(
                () -> {
                    try {
                        cache.update(model);
                    } catch (Exception e) {
                        ex.set(e);
                    }
                }
        );
        Thread thread2 = new Thread(
                () -> {
                    try {
                        cache.update(new Base(1, 1));
                    } catch (Exception e) {
                        ex.set(e);
                    }
                }
        );

        thread1.start();
        thread1.join();
        thread2.start();
        thread2.join();
        Assert.assertThat(ex.get().getMessage(), is("version changed"));
    }
}