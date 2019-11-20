package ru.job4j.crud.logic;

import org.apache.commons.fileupload.FileItem;
import ru.job4j.crud.dao.DBStore;
import ru.job4j.crud.dao.Store;
import ru.job4j.crud.model.City;
import ru.job4j.crud.model.User;

import java.util.List;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 04.10.2019
 */
public class ValidateService implements Validate {

    private final Store logic = DBStore.getInstance();
    private final PhotoService photoService = PhotoServiceImpl.getInstance();
    private final static ValidateService INSTANCE = new ValidateService();

    private ValidateService() {
    }

    public static ValidateService getInstance() {
        return INSTANCE;
    }

    public boolean add(User user, FileItem fileItem, String uploadPath) {
        User added = logic.add(user);
        updateFile(added, fileItem, uploadPath);
        return (added.getId() != null);
    }

    public boolean update(User user, FileItem fileItem, String uploadPath) {
        boolean result = false;
        if (user.getId() != null && logic.findById(user.getId()) != null) {
            logic.update(user);
            updateFile(user, fileItem, uploadPath);
            result = true;
        }
        return result;
    }

    public boolean delete(User user, String uploadPath) {
        boolean result = false;
        if (user.getId() != null && logic.findById(user.getId()) != null) {
            logic.delete(user);
            deleteFile(user, uploadPath);
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

    private void updateFile(User user, FileItem fileItem, String uploadPath) {
        if (fileItem != null && fileItem.getSize() > 0) {
            user.setPhotoId(user.getId());
            if (photoService.loadFile(fileItem, uploadPath, user.getPhotoId())) {
                logic.updatePhoto(user);
            }
        }
    }

    private void deleteFile(User user, String uploadPath) {
        if (photoService.isFileExist(uploadPath, user.getPhotoId())) {
            photoService.deleteFile(uploadPath, user.getPhotoId());
        }
    }

    @Override
    public User findByLogin(String login) {
        return logic.findByLogin(login);
    }

    @Override
    public List<City> findAllCities() {
        return logic.findAllCities();
    }
}