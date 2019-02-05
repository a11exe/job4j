package ru.job4j.pseudo;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 05.02.2019
 */
public class TriangleTest {

    @Test
    public void draw() {
        assertThat(
                new Triangle().draw(),
                is(
                        new StringBuilder()
                                .append("  X  \n")
                                .append(" XXX \n")
                                .append("XXXX\n")
                                .toString()
                )
        );
    }
}