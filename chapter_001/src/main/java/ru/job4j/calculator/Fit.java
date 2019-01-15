package ru.job4j.calculator;

/**
 * Программа расчета идеального веса.
 * @author Alexandr Abramov (alllexe@mail.ru)
 * @since 15.01.2019
 * @version 1
 */
public class Fit {

    /**
     * Идеальный вес для мужчины.
     * @param height Рост.
     * @return идеальный вес.
     */
    public double manWeight(double height) {
        return (height - 100) * 1.15;
    }

    /**
     * Идеальный вес для женщины.
     * @param height Рост.
     * @return идеальный вес.
     */
    public double womanWeight(double height) {
        return (height - 110) * 1.15;
    }
}
