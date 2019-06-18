package ru.job4j.calculator;

/**
 * Stub
 *
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 05.02.2019
 */
public class StubInput implements Input {

    private final String[] value;

    private int position;

    public StubInput(final String[] value) {
        this.value = value;
    }

    @Override
    public String ask(String question) {
        return this.value[this.position++];
    }

}
