package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 21.01.2019
 */
public class MatrixCheckTest {

    @Test
    public void whenDataMonoByTrueThenTrue() {
        MatrixCheck check = new MatrixCheck();
        boolean[][] input = new boolean[][]{
                {true, true, true},
                {false, true, true},
                {true, false, true}
        };
        boolean result = check.mono(input);
        assertThat(result, is(true));
    }

    @Test
    public void whenDataNotMonoByTrueThenFalse() {
        MatrixCheck check = new MatrixCheck();
        boolean[][] input = new boolean[][]{
                {true, true, false},
                {false, false, true},
                {true, false, true}
        };
        boolean result = check.mono(input);
        assertThat(result, is(false));
    }

    @Test
    public void whenDataNotMonoByTrueThenFalseReverse() {
        MatrixCheck check = new MatrixCheck();
        boolean[][] input = new boolean[][]{
                {true, true, true},
                {false, true, true},
                {false, false, true}
        };
        boolean result = check.mono(input);
        assertThat(result, is(false));
    }

}