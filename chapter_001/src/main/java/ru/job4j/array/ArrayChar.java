package ru.job4j.array;

/**
 * Проверяет что слово начинается с заданного значения.
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 21.01.2019
 */
public class ArrayChar {
    private char[] data;

    public ArrayChar(String line) {
        this.data = line.toCharArray();
    }

    /**
     * Проверяет. что слово начинается с префикса.
     * @param prefix префикс.
     * @return если слово начинается с префикса
     */
    public boolean startWith(String prefix) {
        boolean result = true;
        char[] value = prefix.toCharArray();
        // проверить. что массив data имеет первые элементы одинаковые с value
        if (value.length > data.length) {
            result = false;
        } else {
            for (int i = 0; i < value.length; i++) {
                if (value[i] != data[i]) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }
}
