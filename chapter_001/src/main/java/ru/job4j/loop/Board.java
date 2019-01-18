package ru.job4j.loop;

/**
 * Конвертор валюты.
 * @author Alexandr Abramov (alllexe@mail.ru)
 * @since 18.01.2019
 * @version 1
 */
public class Board {

    /**
     * Рисует шахматную доску из символов x и пробелов.
     * @param width ширина шахматной доски.
     * @param height высота шахаматной доски.
     * @return строка содержащая рисунок шахматной доски
     */
    public String paint(int width, int height) {
        StringBuilder screen = new StringBuilder();
        String ln = System.lineSeparator();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if ((i + j) % 2 == 0) {
                    screen.append("x");
                } else {
                    screen.append(" ");
                }
            }
            // добавляем перевод на новую строку.
            screen.append(ln);
        }
        return screen.toString();
    }

}
