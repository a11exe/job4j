package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SimpleStackTest {

    private SimpleStack<Integer> stack;

    @Before
    public void before() {
        stack = new SimpleStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
    }

    @Test
    public void whenPollShouldLIFO() {
        int[] actual = new int[3];
        actual[0] = stack.poll();
        actual[1] = stack.poll();
        actual[2] = stack.poll();
        assertThat(actual, is(new int[]{3, 2, 1}));
    }

    @Test
    public void whenPollEmptyStackShouldNull() {
        stack = new SimpleStack<>();
        assertNull(stack.poll());
    }

    @Test
    public void whenPush3ElementsShouldSize3() {
        assertThat(stack.size(), is(3));
    }

    @Test
    public void whenEmptyShouldSize0() {
        stack = new SimpleStack<>();
        assertThat(stack.size(), is(0));
    }

    @Test
    public void whenAdd3ElementsShouldGet2ElementByIndexIsOne() {
        assertThat(stack.get(1), is(2));
    }

    @Test
    public void whenAdd3ElementsShouldSize3() {
        assertThat(stack.size(), is(3));
    }


}