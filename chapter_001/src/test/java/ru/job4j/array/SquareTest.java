package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author Alexandr Abramov (alllexe@mail.ru)
 * @since 20.01.2019
 * @version 1
 */
public class SquareTest {

    @Test
    public void whenBound3Then149() {
        int bound = 3;
        Square square = new Square();
        int[] rst = square.calculate(bound);
        int[] expect = new int[] {1, 4, 9};
        assertThat(rst, is(expect));
    }

    @Test
    public void whenBound1Then1() {
        int bound = 1;
        Square square = new Square();
        int[] rst = square.calculate(bound);
        int[] expect = new int[] {1};
        assertThat(rst, is(expect));
    }

    @Test
    public void whenBound5Then149() {
        int bound = 5;
        Square square = new Square();
        int[] rst = square.calculate(bound);
        int[] expect = new int[] {1, 4, 9, 16, 25};
        assertThat(rst, is(expect));
    }

}