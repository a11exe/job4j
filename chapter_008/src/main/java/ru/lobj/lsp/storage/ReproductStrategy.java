package ru.lobj.lsp.storage;

import ru.lobj.lsp.food.Food;

import java.util.List;

/**
 * Strategy for adding to reproduct storage
 *
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 27.06.2019
 */
public class ReproductStrategy implements StorageStrategy {

    private final StorageStrategy storageStrategy;

    public ReproductStrategy(StorageStrategy storageStrategy) {
        this.storageStrategy = storageStrategy;
    }

    @Override
    public boolean add(Food food, List<Food> foods) {
        boolean added = false;
        if (food.isCanReproduct()) {
            added = storageStrategy.add(food, foods);
        }
        return added;
    }
}