package ru.job4j.map;

import java.util.Calendar;
import java.util.Objects;

/**
 * Пользователь
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 28.03.2019
 */
public class User {

    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, children, birthday);
    }
}
