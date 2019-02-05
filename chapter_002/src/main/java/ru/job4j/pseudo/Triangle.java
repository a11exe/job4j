package ru.job4j.pseudo;

/**
 * Треугольник.
 *
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 05.02.2019
 */
public class Triangle implements Shape {
    @Override
    public String draw() {
        return new StringBuilder()
                .append("  X  \n")
                .append(" XXX \n")
                .append("XXXX\n")
                .toString();
    }
}
