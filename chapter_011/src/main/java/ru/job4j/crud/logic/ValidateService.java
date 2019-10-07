package ru.job4j.crud.logic;

import ru.job4j.crud.dao.MemoryStore;
import ru.job4j.crud.dao.Store;
import ru.job4j.crud.model.User;

import java.util.List;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 04.10.2019
 */
public class ValidateService implements Validate {

    private final Store logic = MemoryStore.getInstance();
    private final static ValidateService INSTANCE = new ValidateService();

    private ValidateService() {
    }

    public static ValidateService getInstance() {
        return INSTANCE;
    }

    public boolean add(User user) {
        logic.add(user);
        return true;
    }

    public boolean update(User user) {
        boolean result = false;
        if (user.getId() != null && logic.findById(user.getId()) != null) {
            logic.update(user);
            result = true;
        }
        return result;
    }

    public boolean delete(User user) {
        boolean result = false;
        if (user.getId() != null && logic.findById(user.getId()) != null) {
            logic.delete(user);
            result = true;
        }
        return result;
    }

    public List<User> findAll() {
        return logic.findAll();
    }


    public User findById(int id) {
        return logic.findById(id);
    }
}