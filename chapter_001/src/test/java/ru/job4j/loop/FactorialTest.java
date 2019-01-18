package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Конвертор валюты.
 * @author Alexandr Abramov (alllexe@mail.ru)
 * @since 18.01.2019
 * @version 1
 */
public class FactorialTest {

    @Test
    public void whenCalculateFactorialForFiveThenOneHundredTwenty() {
        Factorial factorial = new Factorial();
        assertThat(factorial.calc(5), is(120));
    }

    @Test
    public void whenCalculateFactorialForZeroThenOne() {
        Factorial factorial = new Factorial();
        assertThat(factorial.calc(0), is(1));
    }
}