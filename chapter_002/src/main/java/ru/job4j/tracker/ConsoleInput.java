package ru.job4j.tracker;

import java.util.Scanner;

/**
 * Консольный ввод
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 04.02.2019
 */
public class ConsoleInput implements Input {

    private final Scanner scanner = new Scanner(System.in);

    /**
     * Выводит текст вопроса и возвращает ввод пользователя.
     * @param question текст вопроса выводится в консоль.
     * @return введенный данные из консоли.
     */
    @Override
    public String ask(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }

    /**
     * Номер пункта меню
     * @param question Текст выводимого вопроса
     * @param range массив из номеров пунктов меню
     * @return выбранный пункт меню
     */
    @Override
    public int ask(String question, int[] range) {
        int key = Integer.parseInt(ask(question));
        boolean exist = false;
        for (int x: range
             ) {
            if (x == key) {
                exist = true;
                break;
            }
        }
        // if-else-throw
        if (!exist) {
            throw new MenuOutException("Нет такого пункта меню");
        }
        return key;
    }
}
