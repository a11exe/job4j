package ru.job4j.pseudo;

/**
 * Квадрат.
 *
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 05.02.2019
 */
public class Square implements Shape {
    @Override
    public String draw() {
        return new StringBuilder()
                .append("XXXX\n")
                .append("XXXX\n")
                .append("XXXX\n")
                .append("XXXX\n")
                .toString();
    }
}
