package ru.job4j.lsp.storage;

import ru.job4j.lsp.food.Food;

import java.util.List;

/**
 * Strategy for adding to shop storage
 *
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 19.06.2019
 */
public class ShopStrategy implements StorageStrategy {

    @Override
    public boolean add(Food food, List<Food> foods) {
        boolean added = false;

        if (food.getExpaireRate() >= 25 && food.getExpaireRate() <= 75) {
            added = foods.add(food);
        } else if (food.getExpaireRate() > 75 && food.getExpaireRate() < 100) {
            food.setDiscount(5);
            added = foods.add(food);
        }
        return added;
    }
}
