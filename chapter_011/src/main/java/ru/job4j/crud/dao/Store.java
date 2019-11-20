package ru.job4j.crud.dao;

import ru.job4j.crud.model.City;
import ru.job4j.crud.model.User;

import java.util.List;

public interface Store {

    User add(User user);

    void update(User user);

    void delete(User user);

    List<User> findAll();

    User findById(int id);

    User findByLogin(String login);

    boolean updatePhoto(User user);

    List<City> findAllCities();
}
