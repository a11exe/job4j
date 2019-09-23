package ru.job4j.storage;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 23.09.2019
 */
public class User {

    private final int id;
    private int amount;

    public User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
