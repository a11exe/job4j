package ru.job4j.lsp.storage;


import ru.job4j.lsp.food.Food;

import java.util.List;

public interface StorageStrategy {

    boolean add(Food food, List<Food> foods);

}
