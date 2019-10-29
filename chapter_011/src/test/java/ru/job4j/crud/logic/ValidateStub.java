package ru.job4j.crud.logic;

import org.apache.commons.fileupload.FileItem;
import ru.job4j.crud.dao.MemoryStore;
import ru.job4j.crud.dao.Store;
import ru.job4j.crud.model.User;

import java.util.List;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 15.10.2019
 */
public class ValidateStub implements Validate {

    private final Store logic = MemoryStore.getInstance();

    @Override
    public boolean add(User user, FileItem fileItem, String uploadPath) {
        User added = logic.add(user);
        return (added.getId() != null);
    }

    @Override
    public boolean update(User user, FileItem fileItem, String uploadPath) {
        boolean result = false;
        if (user.getId() != null && logic.findById(user.getId()) != null) {
            logic.update(user);
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(User user, String uploadPath) {
        boolean result = false;
        if (user.getId() != null && logic.findById(user.getId()) != null) {
            logic.delete(user);
            result = true;
        }
        return result;
    }

    @Override
    public List<User> findAll() {
        return logic.findAll();
    }

    @Override
    public User findById(int id) {
        return logic.findById(id);
    }

    @Override
    public User findByLogin(String login) {
        return logic.findByLogin(login);
    }
}
