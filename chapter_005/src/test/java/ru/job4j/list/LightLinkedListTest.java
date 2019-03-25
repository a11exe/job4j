package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 25.03.2019
 */
public class LightLinkedListTest {

    private LightLinkedList<Integer> list;

    @Before
    public void beforeTest() {
        list = new LightLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    public void whenAddGetOneShouldFirst() {
        assertThat(list.get(0), is(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetEmptyShouldException() {
        LightLinkedList<Integer> list = new LightLinkedList<>();
        assertNull(list.get(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetNotAddedShouldException() {
        assertNull(list.get(4));
    }

    @Test
    public void whenAddedMoreInitCapacityShouldGrow() {
        IntStream.range(4, 15).forEach(n->list.add(n));
        assertThat(list.get(13), is(14));
    }

    @Test
    public void whenAdd3ElementsShouldGetSame3ElementsUsingIterator() {
        Iterator<Integer> iterator = this.list.iterator();
        int[] actual = new int[3];
        actual[0] = iterator.next();
        actual[1] = iterator.next();
        actual[2] = iterator.next();
        assertThat(actual, is(new int[]{1, 2, 3}));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenAdd3ElementsShouldExceptionThenNext4TimesUsingIterator() {
        Iterator<Integer> iterator = this.list.iterator();
        IntStream.range(0, 4).forEach(n->iterator.next());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenEmptyIteratorShouldException() {
        LightLinkedList<String> list = new LightLinkedList<>();
        Iterator<String> iterator = list.iterator();
        iterator.next();
    }

    @Test
    public void whenAdd3ElementsHasNext4ShouldFalse() {
        Iterator<Integer> iterator = this.list.iterator();
        assertTrue(iterator.hasNext());
        IntStream.range(0, 3).forEach(n->iterator.next());
        iterator.hasNext();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void whenEmptyHasNextShouldFalse() {
        LightLinkedList<Integer> list = new LightLinkedList<>();
        Iterator<Integer> iterator = list.iterator();
        iterator.hasNext();
        assertFalse(iterator.hasNext());
    }

}