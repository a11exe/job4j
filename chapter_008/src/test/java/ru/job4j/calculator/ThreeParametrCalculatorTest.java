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
 * @since 02.07.2019
 */
public class ThreeParametrCalculatorTest {

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final Consumer<String> output = new Consumer<>() {
        private final PrintStream stdout = new PrintStream(out);

        @Override
        public void accept(String s) {
            stdout.println(s);
        }
    };

    @Test
    public void whenThreeParamTestShouldSumOf3Parametrs() {
        InteractCalc calc = new ThreeParametrCalculator(new StubInput(new String[]{"3", "sum3", "4", "5", "6", "="}), output);
        calc.next();
        calc.next();
        assertThat(out.toString(), CoreMatchers.is("15.0" + System.lineSeparator()));
    }


}
