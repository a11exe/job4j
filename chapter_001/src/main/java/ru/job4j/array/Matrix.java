package ru.job4j.array;

/**
 * Матрица умножения.
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 21.01.2019
 */
public class Matrix {

    /**
     * Матрица умножения.
     * @param size размер матрицы.
     * @return матрица содержащия произведения индексов сторон.
     */
    public int[][] multiple(int size) {
        int[][] table = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                table[i][j] = (i+1)*(j+1);
            }
        }
        return table;
    }

}