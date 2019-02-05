package ru.job4j.pseudo;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 05.02.2019
 */
public class SquareTest {

    @Test
    public void draw() {
        assertThat(
                new Square().draw(),
                is(
                new StringBuilder()
                        .append("XXXX\n")
                        .append("XXXX\n")
                        .append("XXXX\n")
                        .append("XXXX\n")
                        .toString()
                )
        );
    }

}