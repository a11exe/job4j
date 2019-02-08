package ru.job4j.tracker;

/**
 * Базовый класс для действий
 *
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 08.02.2019
 */
public abstract class BaseAction implements UserAction {

    private final int key;
    private final String name;

    public BaseAction(int key, String name) {
        this.key = key;
        this.name = name;
    }

    @Override
    public abstract void execute(Input input, Tracker tracker);

    @Override
    public int key() {
        return this.key;
    }

    @Override
    public String info() {
        return key() + ". " + this.name;
    }
}
