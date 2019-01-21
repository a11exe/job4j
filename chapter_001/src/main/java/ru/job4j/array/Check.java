package ru.job4j.array;

/**
 * Проверяет массив заполнен true и false.
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 21.01.2019
 */
public class Check {

    /**
     * Проверяет массив заполнен true и false.
     * @param data массив для проверки.
     * @return все элементы true или все элементы false.
     */
    public boolean mono(boolean[] data) {

        if (data.length > 0) {
            boolean checked = data[0];
            for (int i = 1; i < data.length; i++) {
                if (checked != data[i]) {
                    return false;
                }
            }
        }

        return true;
    }

}
