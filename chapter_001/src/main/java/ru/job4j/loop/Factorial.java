package ru.job4j.loop;
/**
 * Конвертор валюты.
 * @author Alexandr Abramov (alllexe@mail.ru)
 * @since 18.01.2019
 * @version 1
 */
public class Factorial {

    /**
     * Факториал
     * @param n положительное число для расчета факториала
     * @return значение факториала
     */
    public int calc(int n) {

        int rez = 1;

        if (n == 0) {
            return rez;
        }

        n = n < 0 ? -n : n;

        for (int i = 1; i <= n; i++) {
            rez *= i;
        }
        return rez;

    }

}
