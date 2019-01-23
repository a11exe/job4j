package ru.job4j.array;

import java.util.Arrays;

/**
 * Удаление дубликатов в массиве.
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 21.01.2019
 */
public class ArrayDuplicate {

    /**
     * Удаляет дубли в массиве.
     * @param array массив для удаления дублей.
     * @return массив без дублей.
     */
    public String[] remove(String[] array) {
        int unique = array.length;

        for (int out = 0; out < array.length; out++) {
            for (int in = 1 + out; in < unique; in++) {
                if (array[out].equals(array[in])) {
                    array[in] = array[unique - 1];
                    unique--;
                    in--;
                }
            }
        }

        return Arrays.copyOf(array, unique);
    }
}
