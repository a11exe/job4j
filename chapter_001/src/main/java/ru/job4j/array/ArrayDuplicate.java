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
        int countDoubles = sortArray(array);
        return  Arrays.copyOf(array, array.length - countDoubles);
    }

    /**
     * Соритрует массив строк перенося дубли в конец.
     * @param array массив для сортировки.
     * @return количество дублей.
     */
    private int sortArray(String[] array) {

        boolean hasDoubles;
        int countDoubles = 0;

        if (array.length > 1) {
            for (int i = 0; i < array.length - countDoubles; i++) {
                String check = array[i];
                do {
                    hasDoubles = false;
                    for (int j = i + 1; j < array.length - countDoubles; j++) {
                        if (check.equals(array[j])) {
                            shiftArray(array, j);
                            countDoubles++;
                            hasDoubles = true;
                        }
                    }
                } while (hasDoubles);
            }

        }

        return countDoubles;
    }

    /**
     * Элемент j переносит в конец массива сдвигая массив влево на 1
     * @param array массив
     * @param j позиция которая переносится в конец
     */
    private void shiftArray(String[] array, int j) {
        String str = array[j];
        System.arraycopy(array, j + 1, array, j, array.length - 1 - j);
//        for (int i = j; i < array.length - 1; i++) {
//            array[i] = array[i + 1];
//        }
        array[array.length - 1] = str;
    }

}
