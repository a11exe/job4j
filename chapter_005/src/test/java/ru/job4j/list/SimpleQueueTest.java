package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 26.03.2019
 */
public class SimpleQueueTest {

    private SimpleQueue<Integer> queue;

    @Before
    public void before() {
        queue = new SimpleQueue<>();
        queue.push(1);
        queue.push(2);
        queue.push(3);
    }

    @Test
    public void whenAdd3ShouldSize3() {
        assertThat(queue.size(), is(3));
    }

    @Test
    public void whenPollShouldFIFO() {
        int[] actual = new int[3];
        actual[0] = queue.poll();
        actual[1] = queue.poll();
        actual[2] = queue.poll();
        assertThat(actual, is(new int[]{1, 2, 3}));
    }

    @Test
    public void whenFIFOShouldFIFO() {
        assertThat(queue.poll(), is(1));
        assertThat(queue.poll(), is(2));
        queue.push(4);
        queue.push(5);
        assertThat(queue.size(), is(3));
        assertThat(queue.poll(), is(3));
        assertThat(queue.poll(), is(4));
        assertThat(queue.poll(), is(5));
        queue.push(6);
        queue.push(7);
        assertThat(queue.poll(), is(6));
        assertThat(queue.poll(), is(7));
    }

    @Test
    public void whenPollEmptyQueueShouldNull() {
        queue = new SimpleQueue<>();
        assertNull(queue.poll());
    }

}