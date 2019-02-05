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
}
