package ru.job4j.tracker;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Трекер
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 01.02.2019
 */
public class Tracker implements ITracker {

    /**
     * Массив для хранение заявок.
     */
    private final List<Item> items = new ArrayList<>();

    /**
     * Метод реализаущий добавление заявки в хранилище
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items.add(item);
        return item;
    }

    /**
     * Редактирование заявок.
     * @param id номер заявки.
     * @param item отредактированная заявка.
     * @return true - флаг успешного редактирования
     */
    public boolean replace(String id, Item item) {
        boolean replaced = false;
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).getId().equals(id)) {
                items.set(i, item);
                replaced = true;
                break;
            }
        }
        return replaced;
    }

    /**
     * Удаление заявок.
     * @param id номер заявки.
     * @return флаг успешного удаления.
     */
    public boolean delete(String id) {
        boolean deleted = false;
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).getId().equals(id)) {
                this.items.remove(i);
                deleted = true;
                break;
            }
        }
        return deleted;
    }

    /**
     * Получение списка всех заявок.
     * @return массив заявок.
     */
    public List<Item> findAll() {
        return this.items;
    }

    /**
     * Получение списка по имени
     * @param key имя заявки
     * @return массив заявок.
     */
    public List<Item> findByName(String key) {
        return items.stream().filter(x->x.getName().equals(key)).collect(Collectors.toList());
    }

    /**
     * Поулчение заявки по id
     * @param id номер заявки.
     * @return найденная заявка.
     */
    public Item findById(String id) {
        return items.stream().filter(x->x.getId().equals(id)).findFirst().orElse(null);
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
