package ru.job4j.array;

/**
 * Переворачивает массив задом наперед.
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 21.01.2019
 */
public class Turn {

    /**
     * Переворачивает массив задом наперед.
     * @param array массив который необходимо перевернуть.
     * @return перевернутый массив.
     */
    public int[] back(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int a = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = a;
        }
        return array;
    }

}
