package ru.jobj4.generic;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 22.03.2019
 */
public class SimpleArrayTest {

    @Test
    public void whenAddTwoElementsShouldGetTwoElements() {
        SimpleArray<String> simpleArray = new SimpleArray<>(5);
        simpleArray.add("ss");
        simpleArray.add("bb");
        String[] expected = new String[]{"ss", "bb"};
        String[] actual = new String[2];
        actual[0] = simpleArray.get(0);
        actual[1] = simpleArray.get(1);
        assertThat(actual, is(expected));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenAddFreeElementsInArrayTwoShouldException() {
        SimpleArray<String> simpleArray = new SimpleArray<>(2);
        simpleArray.add("ss");
        simpleArray.add("bb");
        simpleArray.add("sss");
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenAddElementsInEmptyArrayShouldException() {
        SimpleArray<String> simpleArray = new SimpleArray<>(0);
        simpleArray.add("ss");
        simpleArray.add("bb");
        simpleArray.add("sss");
    }

    @Test
    public void whenSetByIndexShouldGetSameElement() {
        SimpleArray<String> simpleArray = new SimpleArray<>(5);
        String expected = "ss";
        simpleArray.add(expected);
        simpleArray.set(0, expected);
        String actual = simpleArray.get(0);
        assertThat(actual, is(expected));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenSetWithoutAddShouldException() {
        SimpleArray<String> simpleArray = new SimpleArray<>(5);
        String expected = "ss";
        simpleArray.set(0, expected);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenSetByIndexGreaterSizeShouldException() {
        SimpleArray<String> simpleArray = new SimpleArray<>(5);
        simpleArray.set(5, "ss");
    }

    @Test
    public void whenGetByIndexGreaterThenSizeShouldNull() {
        SimpleArray<String> simpleArray = new SimpleArray<>(2);
        simpleArray.add("ss");
        simpleArray.add("bb");
        assertNull(simpleArray.get(4));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenRemoveWithoutAddShouldException() {
        SimpleArray<String> simpleArray = new SimpleArray<>(2);
        simpleArray.remove(0);
        assertNull(simpleArray.get(0));
    }

    @Test
    public void whenAddAndRemoveOneShouldGetNull() {
        SimpleArray<String> simpleArray = new SimpleArray<>(2);
        simpleArray.add("ss");
        simpleArray.remove(0);
        assertNull(simpleArray.get(0));
    }

    @Test
    public void whenAdd3AndRemoveOneInMiddleAddOneShouldLastAddedHasIndexThree() {
        SimpleArray<String> simpleArray = new SimpleArray<>(5);
        simpleArray.add("ss");
        simpleArray.add("aa");
        simpleArray.add("ff");
        simpleArray.remove(1);
        String expected = "rr";
        simpleArray.add(expected);
        assertThat(simpleArray.get(2), is(expected));
    }

    @Test
    public void whenAddTwoElementsShouldGetTwoElementsUsingIterator() {
        SimpleArray<String> simpleArray = new SimpleArray<>(5);
        simpleArray.add("ss");
        simpleArray.add("bb");
        String[] expected = new String[]{"ss", "bb"};
        String[] actual = new String[2];
        Iterator<String> iterator = simpleArray.iterator();
        actual[0] = iterator.next();
        actual[1] = iterator.next();
        assertThat(actual, is(expected));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenAddTwoElementsShouldExceptionThenNext3TimesUsingIterator() {
        SimpleArray<String> simpleArray = new SimpleArray<>(2);
        simpleArray.add("ss");
        simpleArray.add("bb");
        Iterator<String> iterator = simpleArray.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenEmptyIteratorShouldException() {
        SimpleArray<String> simpleArray = new SimpleArray<>(2);
        Iterator<String> iterator = simpleArray.iterator();
        iterator.next();
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenFullAddShouldException() {
        SimpleArray<String> simpleArray = new SimpleArray<>(2);
        simpleArray.add("ss");
        simpleArray.add("bb");
        simpleArray.add("zz");
    }


}