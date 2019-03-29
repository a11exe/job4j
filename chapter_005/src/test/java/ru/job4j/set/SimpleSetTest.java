package ru.job4j.set;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SimpleSetTest {

    @Test
    public void whenAdd3ElementsShouldGetSame3() {
        List<String> expected = List.of("ss", "dd", "bb");
        SimpleSet<String> set = new SimpleSet<>();
        expected.forEach(set::add);
        List<String> actual = new ArrayList<>();
        for (String str: set) {
            actual.add(str);
        }
        assertThat(actual, is(expected));
    }

    @Test
    public void whenAdd2DiffElements4TimesShouldGetOnly2Diff() {
        List<String> expected = List.of("ss", "dd");
        SimpleSet<String> set = new SimpleSet<>();
        expected.forEach(set::add);
        expected.forEach(set::add);
        expected.forEach(set::add);
        expected.forEach(set::add);
        List<String> actual = new ArrayList<>();
        for (String str: set) {
            actual.add(str);
        }
        assertThat(actual, is(expected));
    }

    @Test
    public void whenAddOneNullFiveShouldGetOneNullFive() {
        List<Integer> expected = Arrays.asList(1, null, 5);
        SimpleSet<Integer> set = new SimpleSet<>();
        expected.forEach(set::add);
        List<Integer> actual = new ArrayList<>();
        for (Integer value: set) {
            actual.add(value);
        }
        assertThat(actual, is(expected));
    }

    @Test
    public void whenAdd2SameElementsShouldGetOnly1() {
        List<String> expected = List.of("ss", "ss");
        SimpleSet<String> set = new SimpleSet<>();
        expected.forEach(set::add);
        List<String> actual = new ArrayList<>();
        for (String str: set) {
            actual.add(str);
        }
        assertThat(actual.size(), is(1));
        assertThat(actual.get(0), is("ss"));
    }

}