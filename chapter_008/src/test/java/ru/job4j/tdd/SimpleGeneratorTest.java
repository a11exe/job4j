package ru.job4j.tdd;

import org.junit.Test;

import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 03.07.2019
 */
public class SimpleGeneratorTest {

    @Test
    public void whenTwoKeysShouldRightPhrase() {
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> values = Map.of("name", "Petr", "subject", "you");
        String expected = "I am a Petr, Who are you?";
        assertThat(SimpleGenerator.formatString(template, values), is(expected));
    }

    @Test
    public void whenOneKeyThreeTimesShouldRightPhrase() {
        String template = "Help, ${sos}, ${sos}, ${sos}";
        Map<String, String> values = Map.of("sos", "Aaa");
        String expected = "Help, Aaa, Aaa, Aaa";
        assertThat(SimpleGenerator.formatString(template, values), is(expected));
    }

    @Test(expected = RuntimeException.class)
    public void whenLessKeysShouldExceptions() {
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> values = Map.of("name", "Petr");
        String expected = "I am Petr, Who are you?";
        assertThat(SimpleGenerator.formatString(template, values), is(expected));
    }

    @Test(expected = RuntimeException.class)
    public void whenMoreKeysShouldExceptions() {
        String template = "I am a ${name}, Who are you?";
        Map<String, String> values = Map.of("name", "Petr", "subject", "you");
        String expected = "I am Petr, Who are you?";
        assertThat(SimpleGenerator.formatString(template, values), is(expected));
    }


}