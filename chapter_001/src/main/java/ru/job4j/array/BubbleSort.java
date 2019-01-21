package ru.job4j.array;

/**
 * Сортировка пузырьком.
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 21.01.2019
 */
public class BubbleSort {

    /**
     * Сортировка пузырьком.
     * @param array входящий массив для сортировки.
     * @return отсортированный массив.
     */
    public int[] sort(int[] array) {
        boolean alreadySorted;
        for (int i = 0; i < array.length; i++) {
            alreadySorted = true;
            for (int j = 0; j < array.length - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int b = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = b;
                    alreadySorted = false;
                }
            }
            if (alreadySorted) {
                break;
            }
        }
        return array;
    }

}
