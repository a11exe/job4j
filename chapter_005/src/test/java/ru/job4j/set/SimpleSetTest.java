package ru.job4j.set;

import org.junit.Test;

import java.util.ArrayList;
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

}