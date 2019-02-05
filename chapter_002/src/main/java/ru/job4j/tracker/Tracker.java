package ru.job4j.tracker;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * Трекер
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 01.02.2019
 */
public class Tracker {

    /**
     * Массив для хранение заявок.
     */
    private final Item[] items = new Item[100];

    /**
     * Указатель ячейки для новой заявки.
     */
    private int position = 0;

    /**
     * Метод реализаущий добавление заявки в хранилище
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items[this.position++] = item;
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
        for (int i = 0; i < this.position; i++) {
            if (this.items[i].getId().equals(id)) {
                this.items[i] = item;
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
        for (int i = 0; i < this.position; i++) {
            if (this.items[i].getId().equals(id)) {
                System.arraycopy(this.items, i + 1, this.items, i, position - 1 - i);
                this.items[position - 1] = null;
                this.position--;
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
    public Item[] findAll() {
        return Arrays.copyOf(items, position);
    }

    /**
     * Получение списка по имени
     * @param key имя заявки
     * @return массив заявок.
     */
    public Item[] findByName(String key) {
        Item[] found = new Item[this.position];
        int position = 0;
        for (int i = 0; i < this.position; i++) {
            if (this.items[i].getName().equals(key)) {
                found[position++] = this.items[i];
            }
        }
        return Arrays.copyOf(found, position);
    }

    /**
     * Поулчение заявки по id
     * @param id номер заявки.
     * @return найденная заявка.
     */
    public Item findById(String id) {
        Item  found = null;
        for (int i = 0; i < this.position; i++) {
            if (this.items[i].getId().equals(id)) {
                found = this.items[i];
                break;
            }
        }
        return found;
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
