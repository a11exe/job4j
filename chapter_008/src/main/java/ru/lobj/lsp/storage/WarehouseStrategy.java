package ru.lobj.lsp.storage;


import ru.lobj.lsp.food.Food;

import java.util.List;

/**
 * Strategy for adding to shop storage
 *
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 19.06.2019
 */
public class WarehouseStrategy implements StorageStrategy {

    @Override
    public boolean add(Food food, List<Food> foods) {
        boolean added = false;
        if (food.getExpaireRate() < 25) {
            added = foods.add(food);
        }
        return added;
    }
}
