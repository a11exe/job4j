package ru.job4j.array;

import java.util.Arrays;

/**
 * Сортировка пузырьком.
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 21.01.2019
 */
public class BubbleSort {

    private int count = 0;

    /**
     * Сортировка пузырьком.
     * @param array входящий массив для сортировки.
     * @return отсортированный массив.
     */
    public int[] sort(int[] array) {
        boolean sorted;
        printArray(array);
        for (int i = array.length - 1; i > 0; i--) {
            sorted = true;
            for (int j = 0; j < i; j++) {
                count++;
                if (array[j] > array[j + 1]) {
                    toSwap(array, j + 1, j);
                    sorted = false;
                }
            }
            if (sorted) {
                break;
            }
            printArray(array);
        }
        System.out.println("Count: " + count);
        return array;
    }

    private void toSwap(int[] array, int i, int j) {
        int b = array[i];
        array[i] = array[j];
        array[j] = b;
    }

    public void printArray(int[] array) {
        Arrays.stream(array).forEach(System.out::print);
        System.out.println();
    }
}
