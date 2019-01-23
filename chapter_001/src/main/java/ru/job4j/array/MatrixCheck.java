package ru.job4j.array;

/**
 * Квадратный массив заполнен по диагонали одинаков true или false.
 *
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 21.01.2019
 */
public class MatrixCheck {

    /**
     * Квадратный массив заполнен по диагонали одинаков true или false.
     *
     * @param data массив для проверки.
     * @return по диагонали одинаковые значения true или false.
     */
    public boolean mono(boolean[][] data) {

        boolean result = true;

        if (data.length > 1) {

            boolean checkedLeft = data[0][0];
            boolean checkedRight = data[data.length - 1][0];

            for (int i = 1; i < data.length; i++) {
                // check from left to right
                if (checkedLeft != data[i][i]) {
                    result = false;
                    break;
                }
                // check from right to left
                if (checkedRight != data[data.length - 1 - i][i]) {
                    result = false;
                    break;
                }
            }
        }

        return result;
    }

}
