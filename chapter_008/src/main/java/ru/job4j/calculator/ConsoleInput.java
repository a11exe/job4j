package ru.job4j.calculator;

import java.util.Scanner;

/**
 * Console input
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 04.02.2019
 */
public class ConsoleInput implements Input {

    private final Scanner scanner = new Scanner(System.in);

    /**
     * Print question to console and return user answer.
     *
     * @param question - text of question printing to console.
     * @return user input from console.
     */
    @Override
    public String ask(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }

}
