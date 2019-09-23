package ru.job4j.storage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 23.09.2019
 */
@ThreadSafe
public class UserStore {
    @GuardedBy("userStorage")
    private final Map<Integer, User> userStorage = new HashMap<>();

    public boolean add(User user) {
        synchronized (userStorage) {
            boolean added = false;
            if (userStorage.get(user.getId()) == null) {
                userStorage.put(user.getId(), user);
                added = true;
            }
            return added;
        }
    }

    public boolean update(User user) {
        synchronized (userStorage) {
            boolean updated = false;
            if (userStorage.get(user.getId()) != null) {
                userStorage.put(user.getId(), user);
                updated = true;
            }
            return updated;
        }
    }

    public boolean delete(User user) {
        synchronized (userStorage) {
            boolean removed = false;
            if (userStorage.get(user.getId()) != null) {
                userStorage.remove(user.getId());
                removed = true;
            }
            return removed;
        }
    }

    public boolean transfer(int fromId, int toId, int amount) {
        synchronized (userStorage) {
            boolean transfer = false;
            User userFrom = userStorage.get(fromId);
            User userTo = userStorage.get(toId);
            if (userFrom != null && userTo != null) {
                if (userFrom.getAmount() >= amount) {
                    userFrom.setAmount(userFrom.getAmount() - amount);
                    userTo.setAmount(userTo.getAmount() + amount);
                    transfer = true;
                }
            }
            return transfer;
        }
    }

}
