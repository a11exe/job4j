package ru.job4j.loop;

import java.util.function.BiPredicate;

/**
 * Рисуем пирамиду.
 *
 * @author Alexandr Abramov (alllexe@mail.ru)
 * @version 1
 * @since 18.01.2019
 */
public class Paint {

    /**
     * Рисуем правую часть пирамиды.
     * @param height высота пирамиды.
     * @return строка с рисунком пирамиды.
     */
    public String rightTrl(int height) {
        return this.loopBy(
                height,
                height,
                (row, column) -> row >= column
        );
    }

    /**
     * Рисуем левую часть пирамиды.
     * @param height высота пирамиды.
     * @return строка с рисунком пирамиды.
     */
    public String leftTrl(int height) {
        return this.loopBy(
                height,
                height,
                (row, column) -> row >= height - column - 1
        );
    }

    /**
     * Рисуем пирамиду.
     *
     * @param height высота пирамиды.
     * @return строка с рисунком пирамиды.
     */
    public String piramid(int height) {
        return this.loopBy(
                height,
                2 * height - 1,
                (row, column) -> row >= height - column - 1 && row + height - 1 >= column
        );
    }

    private String loopBy(int height, int weight, BiPredicate<Integer, Integer> predict) {
        StringBuilder screen = new StringBuilder();
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != weight; column++) {
                if (predict.test(row, column)) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(System.lineSeparator());
        }
        return screen.toString();
    }
}
