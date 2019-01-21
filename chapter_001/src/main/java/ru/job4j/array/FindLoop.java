package ru.job4j.array;

/**
 * Поиск элемента в массиве.
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 21.01.2019
 */
public class FindLoop {

    /**
     * Ищем индекс элемента в массиве.
     * @param data массив в котором ищем
     * @param el элемент который ищем
     * @return если найден возвращаем индекс элемента, иначе -1
     */
    public int indexOf(int[] data, int el) {
        int rst = -1; // если элемента нет в массиве, то возвращаем -1.
        for (int index = 0; index < data.length; index++) {
            if (data[index] == el) {
                rst = index;
                break;
            }
        }
        return rst;
    }

}
