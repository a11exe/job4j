package ru.job4j.generic;

import java.util.NoSuchElementException;

public abstract class AbstractStore<T extends Base> implements Store<T> {

    private final SimpleArray<T> simpleArray;

    private int getIndex(String id) throws NoSuchElementException {
        int index = 0;
        boolean exist = false;
        for (T model : simpleArray) {
            if (model.getId().equals(id)) {
                exist = true;
                break;
            }
            index++;
        }
        if (!exist) {
            index = -1;
        }
        return index;
    }


    public AbstractStore(int size) {
        this.simpleArray = new SimpleArray<>(size);
    }

    @Override
    public void add(T model) {
        this.simpleArray.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        boolean result = false;
        int index = getIndex(id);
        if (index != -1) {
            this.simpleArray.set(index, model);
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        int index = getIndex(id);
        if (index != -1) {
            this.simpleArray.remove(index);
            result = true;
        }
        return result;
    }

    @Override
    public T findById(String id) throws NoSuchElementException {
        int index = getIndex(id);
        if (index == -1) {
            throw new NoSuchElementException("model not found d by id: " + id);
        }
        return this.simpleArray.get(index);
    }
}
