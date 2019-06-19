package ru.lobj.lsp.storage;

import ru.lobj.lsp.food.Food;

import java.util.ArrayList;
import java.util.List;

/**
 * Storage
 * using storageStrategy for adding to storage
 *
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 19.06.2019
 */
public class Storage {

    private StorageStrategy storageStrategy;
    private List<Food> foods;

    public Storage(StorageStrategy storageStrategy) {
        this.storageStrategy = storageStrategy;
        foods = new ArrayList<>();
    }

    public List<Food> getFoods() {
        return foods;
    }

    /**
     * Add food to storage using strategy.
     * @param food - food.
     * @return - food added.
     */
    public boolean addFood(Food food) {
        return this.storageStrategy.add(food, this.foods);
    }

}
