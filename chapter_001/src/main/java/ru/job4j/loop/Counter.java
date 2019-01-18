package ru.job4j.loop;

/**
 * Конвертор валюты.
 * @author Alexandr Abramov (alllexe@mail.ru)
 * @since 18.01.2019
 * @version 1
 */
public class Counter {

    /**
     * Возвращает сумму четных чисел в диапазоне включительно.
     * @param start начало диапазона
     * @param finish конец диапазона
     * @return сумма четных чисел
     */
    public int add(int start, int finish) {

        int sum = 0;
        start = start % 2 == 0 ? start : start - 1;

        while (start <= finish) {
            sum += start;
            start += 2;
        }

        return sum;
    }
}
