package ru.job4j.iterator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 19.03.2019
 */
public class MatrixIteratorTest {

    @Test
    public void when2on2ArrayNext4TimesShouldArray4Elements() {
        MatrixIterator matrixIterator = new MatrixIterator(new int[][]{{1, 2}, {3, 4}});
        Integer[] expected = new Integer[]{1, 2, 3, 4};
        List<Integer> list = new ArrayList<>();
        list.add(matrixIterator.next());
        list.add(matrixIterator.next());
        list.add(matrixIterator.next());
        list.add(matrixIterator.next());
        Integer[] actual = list.toArray(new Integer[0]);

        assertThat(actual, is(expected));
    }

}