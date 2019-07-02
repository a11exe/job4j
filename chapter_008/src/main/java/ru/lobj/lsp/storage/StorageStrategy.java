package ru.lobj.lsp.storage;


import ru.lobj.lsp.food.Food;

import java.util.List;

public interface StorageStrategy {

    boolean add(Food food, List<Food> foods);

}
