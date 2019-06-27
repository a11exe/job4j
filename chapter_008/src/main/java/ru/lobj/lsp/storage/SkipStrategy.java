package ru.lobj.lsp.storage;

import ru.lobj.lsp.food.Food;

import java.util.List;

/**
 * Strategy for skipping storage
 *
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 26.06.2019
 */
public class SkipStrategy implements StorageStrategy {

    @Override
    public boolean add(Food food, List<Food> foods) {
        return false;
    }
}
