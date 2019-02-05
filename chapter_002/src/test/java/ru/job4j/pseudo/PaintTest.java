package ru.job4j.pseudo;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 05.02.2019
 */
public class PaintTest {

    @Test
    public void drawTriangle() {
        PrintStream stdout = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        new Paint().draw(new Triangle());
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("  X  \n")
                                .append(" XXX \n")
                                .append("XXXX\n")
                                .append("\r\n")
                                .toString()
                )
        );
        System.setOut(stdout);
    }

    @Test
    public void drawSquare() {
        PrintStream stdout = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        new Paint().draw(new Square());
        assertThat(
                new String(out.toByteArray()),
                is(
                        new StringBuilder()
                                .append("XXXX\n")
                                .append("XXXX\n")
                                .append("XXXX\n")
                                .append("XXXX\n")
                                .append("\r\n")
                                .toString()
                )
        );
        System.setOut(stdout);
    }

}