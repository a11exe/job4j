package ru.lobj.lsp;

import ru.lobj.lsp.food.Food;
import ru.lobj.lsp.storage.Storage;

import java.util.ArrayList;
import java.util.List;

/**
 * ControllQuality
 * adding food to storage according storage strategy
 *
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 19.06.2019
 */
public class ControllQuality {

    private final List<Storage> storages;

    public ControllQuality(List<Storage> storages) {
        this.storages = storages;
    }

    /**
     * add food to storage
     * one from storages
     * @param food - food.
     * @return - food added.
     */
    public boolean addToStorage(Food food) {
        boolean added = false;
        for (Storage storage : storages) {
            if (storage.addFood(food)) {
                added = true;
                break;
            }
        }
        return added;
    }

    /**
     * Extract all foods from storages and redistribut again
     */
    public void resort() {
        List<Food> redistribuitFood = new ArrayList<>();
        storages.forEach(s-> {
            redistribuitFood.addAll(s.getFoods());
            s.clear();
        });
        redistribuitFood.forEach(this::addToStorage);
    }
}
