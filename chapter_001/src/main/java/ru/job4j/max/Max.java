package ru.job4j.max;

/**
 * Максимум из двух чисел x и y.
 * @author Alexandr Abramov (alllexe@mail.ru)
 * @since 16.01.2019
 * @version 1
 */
public class Max {

    /**
     * Максимум из двух чисел.
     * @param first первое число.
     * @param second второе число.
     * @return максимум из first и second
     */
    public int max(int first, int second) {
        return  first > second ? first : second;
    }

    /**
     * Максимум из трех чисел.
     * @param first первое число.
     * @param second второе число.
     * @param third третье число.
     * @return максимум из first, second, third.
     */
    public int max(int first, int second, int third) {
        return max(first, max(second, third));
    }
}
