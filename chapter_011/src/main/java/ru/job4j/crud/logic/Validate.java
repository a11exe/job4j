package ru.job4j.crud.logic;

import org.apache.commons.fileupload.FileItem;
import ru.job4j.crud.model.User;

import java.util.List;

public interface Validate {
    boolean add(User user, FileItem fileItem, String uploadPath);

    boolean update(User user, FileItem fileItem, String uploadPath);

    boolean delete(User user, String uploadPath);

    List<User> findAll();

    User findById(int id);

    User findByLogin(String login);
}
