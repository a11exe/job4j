package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 21.01.2019
 */
public class BubbleSortTest {

    @Test
    public void sort() {
        BubbleSort bubbleSort = new BubbleSort();
        assertThat(bubbleSort.sort(new int[]{5, 1, 2, 7, 3}), is(new int[]{1, 2, 3, 5, 7}));
    }

    @Test
    public void whenSortArrayWithTenElementsThenSortedArray() {
        //напишите здесь тест, проверяющий сортировку массива из 10 элементов методом пузырька, например {1, 5, 4, 2, 3, 1, 7, 8, 0, 5}.
        BubbleSort bubbleSort = new BubbleSort();
        assertThat(bubbleSort.sort(new int[]{1, 5, 4, 2, 3, 1, 7, 8, 0, 5}), is(new int[]{0, 1, 1, 2, 3, 4, 5, 5, 7, 8}));
    }

    @Test
    public void  whenSortArrayWithTwoElementsThenSortedArray() {
        BubbleSort bubbleSort = new BubbleSort();
        assertThat(bubbleSort.sort(new int[]{2, 1}), is(new int[]{1, 2}));
    }

    @Test
    public void  whenSortArrayWithOneElementsThenSortedArray() {
        BubbleSort bubbleSort = new BubbleSort();
        assertThat(bubbleSort.sort(new int[]{1}), is(new int[]{1}));
    }

}