package ru.job4j.tracker;

import java.time.LocalDateTime;
import java.util.List;

/**
 * enum. Eager loading.
 *
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 11.02.2019
 */
public enum TrackerEnum {

    INSTANCE;

    private static final Tracker TRACKER = new Tracker();

    /**
     * Метод реализаущий добавление заявки в хранилище
     * @param item новая заявка
     */
    public Item add(Item item) {
        return TRACKER.add(item);
    }

    /**
     * Редактирование заявок.
     * @param id номер заявки.
     * @param item отредактированная заявка.
     * @return true - флаг успешного редактирования
     */
    public boolean replace(String id, Item item) {
        return TRACKER.replace(id, item);
    }

    /**
     * Удаление заявок.
     * @param id номер заявки.
     * @return флаг успешного удаления.
     */
    public boolean delete(String id) {
        return TRACKER.delete(id);
    }

    /**
     * Получение списка всех заявок.
     * @return массив заявок.
     */
    public List<Item> findAll() {
        return TRACKER.findAll();
    }

    /**
     * Получение списка по имени
     * @param key имя заявки
     * @return массив заявок.
     */
    public List<Item> findByName(String key) {
        return TRACKER.findByName(key);
    }

    /**
     * Поулчение заявки по id
     * @param id номер заявки.
     * @return найденная заявка.
     */
    public Item findById(String id) {
        return TRACKER.findById(id);
    }

    /**
     * Метод генерирует уникальный ключ для заявки.
     * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
     * @return Уникальный ключ.
     */
    private String generateId() {
        return "" + LocalDateTime.now() + Math.random();
    }
}
