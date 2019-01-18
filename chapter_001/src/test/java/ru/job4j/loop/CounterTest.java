package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Сумма чисел тест.
 * @author Alexandr Abramov (alllexe@mail.ru)
 * @since 18.01.2019
 * @version 1
 */
public class CounterTest {

    @Test
    public void whenSumEvenNumbersFromOneToTenThenThirty() {
        Counter counter = new Counter();
        assertThat(counter.add(1, 10), is(30));
    }
}