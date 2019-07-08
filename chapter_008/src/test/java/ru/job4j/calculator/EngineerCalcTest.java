package ru.job4j.calculator;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.function.Consumer;

import static org.junit.Assert.assertThat;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 26.06.2019
 */
public class EngineerCalcTest {

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final Consumer<String> output = new Consumer<>() {
        private final PrintStream stdout = new PrintStream(out);

        @Override
        public void accept(String s) {
            stdout.println(s);
        }
    };

    /**
     * Test sin method
     */
    @Test
    public void whenAddOnePlusOneThenTwo()  {
        InteractCalc calc = new EngineerCalculator(new StubInput(new String[]{"5", "sin", "+", "2", "="}), output);
        calc.next();
        calc.next();
        calc.next();
        calc.next();
        calc.next();
        assertThat(out.toString(), CoreMatchers.is("-0.9589242746631385" + System.lineSeparator() + "1.0410757253368614" + System.lineSeparator()));
    }

    /**
     * Test cos method
     */
    @Test
    public void whenDiv4On2Then2() {
        InteractCalc calc = new EngineerCalculator(new StubInput(new String[]{"5", "cos", "+", "2", "="}), output);
        calc.next();
        calc.next();
        calc.next();
        calc.next();
        calc.next();
        assertThat(out.toString(), CoreMatchers.is("0.28366218546322625" + System.lineSeparator() + "2.283662185463226" + System.lineSeparator()));
    }


}
