package ru.job4j.exam;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 08.02.2019
 */
public class MergeSortArraysTest {

    private int[] first;
    private int[] second;
    private int[] merged;

    @Test
    public void thenTwoSameLengthSameNumbersThenSorted() {
        first = new int[]{1, 2, 3};
        second = new int[]{1, 2, 3};
        merged = new int[]{1, 1, 2, 2, 3, 3};
        MergeSortArrays mergeSortArrays = new MergeSortArrays();
        assertThat(mergeSortArrays.mergeArraysAndSortLoop(first, second), is(merged));
    }

    @Test
    public void thenTwoSameLengthDiffNumbersThenSorted() {
        first = new int[]{1, 2, 3};
        second = new int[]{4, 5, 6};
        merged = new int[]{1, 2, 3, 4, 5, 6};
        MergeSortArrays mergeSortArrays = new MergeSortArrays();
        assertThat(mergeSortArrays.mergeArraysAndSortLoop(first, second), is(merged));
    }

    @Test
    public void thenTwoDifferentLengthSameNumbersThenSorted() {
        first = new int[]{1, 2};
        second = new int[]{2, 3, 4, 5};
        merged = new int[]{1, 2, 2, 3, 4, 5};
        MergeSortArrays mergeSortArrays = new MergeSortArrays();
        assertThat(mergeSortArrays.mergeArraysAndSortLoop(first, second), is(merged));
    }

    @Test
    public void thenTwoDifferentLengthDiffNumbersThenSorted() {
        first = new int[]{1, 2};
        second = new int[]{3, 4, 5, 6};
        merged = new int[]{1, 2, 3, 4, 5, 6};
        MergeSortArrays mergeSortArrays = new MergeSortArrays();
        assertThat(mergeSortArrays.mergeArraysAndSortLoop(first, second), is(merged));
    }

    @Test
    public void thenOneEmptyThenSorted() {
        first = new int[]{};
        second = new int[]{1, 2};
        merged = new int[]{1, 2};
        MergeSortArrays mergeSortArrays = new MergeSortArrays();
        assertThat(mergeSortArrays.mergeArraysAndSortLoop(first, second), is(merged));
    }

    @Test
    public void thenTwoBigThenCompareTime() {
        first = new int[Integer.MAX_VALUE / 500];
        for (int i = 0; i < first.length; i++) {
            first[i] = i;
        }
        second = new int[Integer.MAX_VALUE / 1000];
        for (int i = 0; i < second.length; i++) {
            second[i] = i;
        }
        merged = new int[first.length + second.length];
        int pos = 0;
        for (int i = 0; i < second.length; i++) {
            merged[pos++] = i;
            merged[pos++] = i;
        }
        for (int i = second.length; i < first.length; i++) {
            merged[pos++] = first[i];
        }
        long st = System.nanoTime();
        MergeSortArrays mergeSortArrays = new MergeSortArrays();
        assertThat(mergeSortArrays.mergeArraysAndSortLoop(first, second), is(merged));
        long en = System.nanoTime();
        System.out.println("MerSortArrays by loop time:" + String.format("%,12d", (en - st)) + " ns");

        st = System.nanoTime();
        assertThat(mergeSortArrays.mergeArraysAndSortQuicksort(first, second), is(merged));
        en = System.nanoTime();
        System.out.println("MerSortArrays by QuickSort time:" + String.format("%,12d", (en - st)) + " ns");

    }

}