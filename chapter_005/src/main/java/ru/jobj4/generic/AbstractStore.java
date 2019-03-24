package ru.jobj4.generic;

import java.util.NoSuchElementException;

public abstract class AbstractStore<T extends Base> implements Store<T> {

    private final SimpleArray<T> simpleArray;

    private int getIndex(SimpleArray<T> simpleArray, String id) throws NoSuchElementException {
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
            throw new NoSuchElementException("model not found by id: " + id);
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
        try {
            this.simpleArray.set(getIndex(simpleArray, id), model);
            result = true;
        } catch (ArrayIndexOutOfBoundsException | NoSuchElementException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        try {
            this.simpleArray.remove(getIndex(simpleArray, id));
            result = true;
        } catch (ArrayIndexOutOfBoundsException | NoSuchElementException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public T findById(String id) throws NoSuchElementException {
        return this.simpleArray.get(getIndex(simpleArray, id));
    }
}
