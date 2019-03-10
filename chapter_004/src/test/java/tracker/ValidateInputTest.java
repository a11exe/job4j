package tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 08.02.2019
 */
public class ValidateInputTest {
    private final ByteArrayOutputStream mem = new ByteArrayOutputStream();
    private final PrintStream out = System.out;

    @Before
    public void loadMem() {
        System.setOut(new PrintStream(this.mem));
    }

    @After
    public void loadSys() {
        System.setOut(this.out);
    }

    @Test
    public void whenInvalidInputChar() {
        ValidateInput input = new ValidateInput(
                new StubInput(new String[] {"invalid", "1"})
        );
        input.ask("Enter", Collections.singletonList(1));
        assertThat(this.mem.toString(),
                is(
                        String.format("Введены некорректные данные. Введите число:%n")
        ));
    }

    @Test
    public void whenInvalidInputNumber() {
        ValidateInput input = new ValidateInput(
                new StubInput(new String[] {"9", "1"})
        );
        input.ask("Enter", Collections.singletonList(1));
        assertThat(this.mem.toString(),
                is(
                        String.format("Введены некорректные данные. Введите существющий пункт меню:%n")
                ));
    }


}
