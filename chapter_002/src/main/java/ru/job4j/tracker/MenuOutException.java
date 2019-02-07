package ru.job4j.tracker;

/**
 * Выбранный пункт не содержится в текущем меню
 *
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 07.02.2019
 */
public class MenuOutException extends RuntimeException {
    public MenuOutException(String message) {
        super(message);
    }
}
