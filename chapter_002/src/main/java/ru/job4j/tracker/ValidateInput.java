package ru.job4j.tracker;

/**
 * Проверка введенных данных на ошибки
 *
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 07.02.2019
 */
public class ValidateInput extends ConsoleInput {

    @Override
    public int ask(String question, int[] range) {
        boolean valid = false;
        int key = 0;
        while (!valid) {
            try {
                key = super.ask(question, range);
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
