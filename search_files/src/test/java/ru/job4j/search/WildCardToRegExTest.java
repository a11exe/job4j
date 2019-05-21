package ru.job4j.search;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 20.05.2019
 */
public class WildCardToRegExTest {

    @Test
    public void whenAsteriskShouldBeAllSymbols() {
        String mask = "*.txt";
        List<String> test = List.of("file1.txt", "file2.txt.", "file5.text", "file5.xml", "file6.txt", "ddsd", "file7.txt");

        String expectedRegexp = "^.*\\.txt$";
        List<String> expected = List.of("file1.txt", "file6.txt", "file7.txt");

        assertThat(WildCardToRegEx.convert(mask), is(expectedRegexp));
        Pattern pattern = Pattern.compile(WildCardToRegEx.convert(mask));
        List<String> actual = test.stream()
                .filter(s -> pattern.matcher(s).find())
                .collect(Collectors.toList());

        assertThat(actual, is(expected));
    }

    @Test
    public void whenQuestionShouldBeOneSymbols() {
        String mask = "file?.txt";
        List<String> test = List.of("file1.txt", "file22.txt", "file5.text", "file5.xml", "file6.txt", "ddsd", "file7.txt");

        String expectedRegexp = "^file.\\.txt$";
        List<String> expected = List.of("file1.txt", "file6.txt", "file7.txt");

        assertThat(WildCardToRegEx.convert(mask), is(expectedRegexp));
        Pattern pattern = Pattern.compile(WildCardToRegEx.convert(mask));
        List<String> actual = test.stream()
                .filter(s -> pattern.matcher(s).find())
                .collect(Collectors.toList());

        assertThat(actual, is(expected));
    }

    @Test
    public void whenQuestionAndAsteriskShouldBeBoth() {
        String mask = "file?.*t";
        List<String> test = List.of("file1.txt", "file22.txt", "file5.text", "file5.xml", "file6.txt", "ddsd", "file7.txt");

        String expectedRegexp = "^file.\\..*t$";
        List<String> expected = List.of("file1.txt", "file5.text", "file6.txt", "file7.txt");

        assertThat(WildCardToRegEx.convert(mask), is(expectedRegexp));
        Pattern pattern = Pattern.compile(WildCardToRegEx.convert(mask));
        List<String> actual = test.stream()
                .filter(s -> pattern.matcher(s).find())
                .collect(Collectors.toList());

        assertThat(actual, is(expected));
    }
}