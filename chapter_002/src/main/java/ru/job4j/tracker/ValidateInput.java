package ru.job4j.tracker;

import java.util.List;

/**
 * Проверка введенных данных на ошибки
 *
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 07.02.2019
 */
public class ValidateInput implements Input {

    private final Input input;

    public ValidateInput(final Input input) {
        this.input = input;
    }

    @Override
    public String ask(String question) {
        return null;
    }

    public int ask(String question, List<Integer> range) {
        boolean valid = false;
        int key = 0;
        while (!valid) {
            try {
                key = this.input.ask(question, range);
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Введены некорректные данные. Введите число:");
            } catch (MenuOutException e) {
                System.out.println("Введены некорректные данные. Введите существющий пункт меню:");
            }
        }
        return key;
    }
}
