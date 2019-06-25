package ru.job4j.calculator;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.function.Consumer;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.*;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 18.06.2019
 */
public class InteractCalcTest {

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final Consumer<String> output = new Consumer<>() {
        private final PrintStream stdout = new PrintStream(out);

        @Override
        public void accept(String s) {
            stdout.println(s);
        }
    };

    @Test
    public void whenSum5And5Should10() {
        InteractCalc interactCalc = new InteractCalc(new StubInput(new String[]{"5", "+", "5", "="}), output);
        interactCalc.next();
        interactCalc.next();
        interactCalc.next();
        interactCalc.next();
        assertThat(out.toString(), is("10.0" + System.lineSeparator()));
    }

    @Test
    public void whenSubtract10And2Should8() {
        InteractCalc interactCalc = new InteractCalc(new StubInput(new String[]{"10", "-", "2", "="}), output);
        interactCalc.next();
        interactCalc.next();
        interactCalc.next();
        interactCalc.next();
        assertThat(out.toString(), is("8.0" + System.lineSeparator()));
    }

    @Test
    public void whenMultiple4And3Should12() {
        InteractCalc interactCalc = new InteractCalc(new StubInput(new String[]{"4", "*", "3", "="}), output);
        interactCalc.next();
        interactCalc.next();
        interactCalc.next();
        interactCalc.next();
        assertThat(out.toString(), is("12.0" + System.lineSeparator()));
    }

    @Test
    public void whenDivide15And3Should15() {
        InteractCalc interactCalc = new InteractCalc(new StubInput(new String[]{"15", "/", "3", "="}), output);
        interactCalc.next();
        interactCalc.next();
        interactCalc.next();
        interactCalc.next();
        assertThat(out.toString(), is("5.0" + System.lineSeparator()));
    }

    @Test
    public void whenSum5And5Subtract2Should8() {
        InteractCalc interactCalc = new InteractCalc(new StubInput(new String[]{"5", "+", "5", "-", "2", "="}), output);
        interactCalc.next();
        interactCalc.next();
        interactCalc.next();
        interactCalc.next();
        interactCalc.next();
        interactCalc.next();
        assertThat(out.toString(), is("8.0" + System.lineSeparator()));
    }

    @Test
    public void whenSum2And2ResetAnd3Sum3Should6() {
        InteractCalc interactCalc = new InteractCalc(new StubInput(new String[]{"2", "+", "2", "reset", "3", "+", "3", "="}), output);
        interactCalc.next();
        interactCalc.next();
        interactCalc.next();
        interactCalc.next();
        interactCalc.next();
        interactCalc.next();
        interactCalc.next();
        interactCalc.next();
        assertThat(out.toString(), is("6.0" + System.lineSeparator()));
    }

    @Test
    public void whenWrongNumberShouldErrorMsg() {
        InteractCalc interactCalc = new InteractCalc(new StubInput(new String[]{"2", "+", "-", "exit"}), output);
        interactCalc.next();
        interactCalc.next();
        interactCalc.next();
        assertThat(out.toString(), is("wrong data. enter the number" + System.lineSeparator()));
    }

    @Test
    public void whenWrongOperationShouldErrorMsg() {
        InteractCalc interactCalc = new InteractCalc(new StubInput(new String[]{"2", "2", "exit"}), output);
        interactCalc.next();
        interactCalc.next();
        assertThat(out.toString(), startsWith("wrong data. enter calc operation one from: " + System.lineSeparator()));
    }

}