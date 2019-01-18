package ru.job4j.loop;

import org.junit.Test;

import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 18.01.2019
 */
public class PaintTest {

    @Test
    public void whenPaintPiramidWithHeigtThreeThenStringWithThreeRows() {
        Paint paint = new Paint();
        assertThat(paint.piramid(3), is(new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add("  ^  ")
                .add(" ^^^ ")
                .add("^^^^^")
                .toString()));
    }

    @Test
    public void whenPaintPiramidWithHeigtTwoThenStringWithTwoRows() {
        Paint paint = new Paint();
        assertThat(paint.piramid(2), is(new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add(" ^ ")
                .add("^^^")
                .toString()));
    }

    @Test
    public void whenPyramid4Left() {
        Paint paint = new Paint();
        String rst = paint.leftTrl(4);
        System.out.println(rst);
        assertThat(rst,
                is(
                        new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                                .add("   ^")
                                .add("  ^^")
                                .add(" ^^^")
                                .add("^^^^")
                                .toString()
                )
        );
    }

    @Test
    public void whenPyramid4Right() {
        Paint paint = new Paint();
        String rst = paint.rightTrl(4);
        System.out.println(rst);
        assertThat(rst,
                is(
                        new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                                .add("^   ")
                                .add("^^  ")
                                .add("^^^ ")
                                .add("^^^^")
                                .toString()
                )
        );
    }
}