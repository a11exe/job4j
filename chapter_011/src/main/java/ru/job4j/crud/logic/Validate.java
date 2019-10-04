package ru.job4j.crud.logic;

import ru.job4j.crud.model.User;

import java.util.List;

public interface Validate {
    boolean add(User user);

    boolean update(User user);

    boolean delete(User user);

    List<User> findAll();

    User findById(int id);
}
