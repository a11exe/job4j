package ru.job4j.crud.dao;

import ru.job4j.crud.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 04.10.2019
 */
public class MemoryStore implements Store {

    private final static MemoryStore INSTANCE = new MemoryStore();
    private final ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<>();
    private final AtomicInteger id = new AtomicInteger(0);

    private MemoryStore() {
    }

    public static MemoryStore getInstance() {
        return INSTANCE;
    }

    private Integer getNextId() {
        return id.incrementAndGet();
    }

    @Override
    public User add(User user) {
        user.setId(getNextId());
        return users.put(user.getId(), user);
    }

    @Override
    public void update(User user) {
        users.put(user.getId(), user);
    }

    @Override
    public void delete(User user) {
        users.remove(user.getId());
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public User findById(int id) {
        return users.get(id);
    }

    @Override
    public User findByLogin(String login) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean updatePhoto(User user) {
        throw new UnsupportedOperationException();
    }
}
