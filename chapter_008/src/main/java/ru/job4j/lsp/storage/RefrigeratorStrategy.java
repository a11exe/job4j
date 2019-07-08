package ru.job4j.lsp.storage;


import ru.job4j.lsp.food.Food;

import java.util.List;

/**
 * Strategy for adding to refrigerator storage
 *
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 19.06.2019
 */
public class RefrigeratorStrategy implements StorageStrategy {

    private final StorageStrategy storageStrategy;

    public RefrigeratorStrategy(StorageStrategy storageStrategy) {
        this.storageStrategy = storageStrategy;
    }

    @Override
    public boolean add(Food food, List<Food> foods) {
        boolean added = false;
        if (food.isVegetable()) {
            added = storageStrategy.add(food, foods);
        }
        return added;
    }
}
