package ru.job4j.iterator;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 20.03.2019
 */
public class EvenNumbersIteratorTest {

    @Test
    public void whenTwoEvenNumbersIn4ArrayShouldTwoNumbers() {
        EvenNumbersIterator it = new EvenNumbersIterator(new int[] {4, 2, 1, 1});
        Integer[] expected = new Integer[]{4, 2};
        Integer[] actual = new Integer[2];
        actual[0] =  it.next();
        actual[1] =  it.next();
        assertThat(actual, is(expected));
    }

    @Test
    public void whenTwoEvenNumbersIn4ArrayShouldHasNextFalseAfterTwoNext() {
        EvenNumbersIterator it = new EvenNumbersIterator(new int[] {3, 4, 2, 1, 1, 7});
        it.next();
        it.next();
        it.hasNext();
        it.hasNext();
        assertFalse(it.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenTwoEvenNumbersIn4ArrayShouldExceptionAfter3Next() {
        EvenNumbersIterator it = new EvenNumbersIterator(new int[] {3, 4, 2, 1, 1, 7});
        it.next();
        it.next();
        it.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenEmptyArrayShouldExceptionAfterNext() {
        EvenNumbersIterator it = new EvenNumbersIterator(new int[] {});
        it.next();
    }

    @Test
    public void whenEmptyArrayShouldFalseAfterHasNext() {
        EvenNumbersIterator it = new EvenNumbersIterator(new int[] {});
        it.hasNext();
        assertFalse(it.hasNext());
    }

}