package ru.job4j.exam;

import java.util.Arrays;

/**
 * Слияние двух отсортированных массивов в один отсортированный
 *
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 08.02.2019
 */
public class MergeSortArrays {

    /**
     * Слияние двух массивов с сортировкой через цикл.     *
     * @param first первый массив.
     * @param second второй массив.
     * @return объединенный отсортированный массив.
     */
   public int[] mergeArraysAndSortLoop(int[] first, int[] second) {
        int[] rez = new int[first.length + second.length];
        int rezPos = 0;
        int firstPos = 0;
        int secondPos = 0;
        int max = Math.max(first.length, second.length);
        for (int k = 0; k < max; k++) {
            if ((firstPos == first.length) || (secondPos == second.length)) {
                break;
            }
            if (first[firstPos] == second[secondPos]) {
                rez[rezPos++] = first[firstPos++];
                rez[rezPos++] = second[secondPos++];
                k++;
            } else if (first[firstPos] < second[secondPos]) {
                rez[rezPos++] = first[firstPos++];
            } else if (first[firstPos] > second[secondPos]) {
                rez[rezPos++] = second[secondPos++];
            }
        }
        for (int k = firstPos; k < first.length; k++) {
            rez[rezPos++] = first[k];
        }
        for (int k = secondPos; k < second.length; k++) {
            rez[rezPos++] = second[k];
        }
        return rez;
    }

    /**
     * Слияние двух массивов с сортировкой через arrays.sort.
     * @param first первый массив.
     * @param second второй массив.
     * @return объединенный отсортированный массив.
     *
     */
    public int[] mergeArraysAndSortQuicksort(int[] first, int[] second) {
        int[] rez = new int[first.length + second.length];
        System.arraycopy(first, 0, rez, 0, first.length);
        System.arraycopy(second, 0, rez, first.length, second.length);
        Arrays.sort(rez);
        return rez;
    }

}
