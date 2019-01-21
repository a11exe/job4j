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
     * @param data массив для проверки.
     * @return по диагонали одинаковые значения true или false.
     */
    public boolean mono(boolean[][] data) {
        boolean result = true;
        if (data.length > 1) {
            // check from left to right
            boolean checked = data[0][0];
            for (int i = 1; i < data.length; i++) {
                if (checked != data[i][i]) {
                    result = false;
                    break;
                }
            }
            // check from right to left
            if (result) {
                checked = data[0][data.length - 1];
                for (int i = 1; i < data.length; i++) {
                    if (checked != data[i][data.length - 1 - i]) {
                        result = false;
                        break;
                    }
                }
            }
        }
        return result;
    }

}
