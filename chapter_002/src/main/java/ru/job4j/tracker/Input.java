package ru.job4j.tracker;

/**
 * Интерфейс ввода. Выводит текст вопроса и возвращает ввод пользователя
 */
public interface Input {
    String ask(String question);
    int ask(String question, int[] range);
}
