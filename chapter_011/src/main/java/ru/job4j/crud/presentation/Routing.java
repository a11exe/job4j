package ru.job4j.crud.presentation;

import ru.job4j.crud.logic.Validate;
import ru.job4j.crud.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 04.10.2019
 */
public class Routing {

    private final Map<String, Function<User, Boolean>> dispatch = new HashMap<>();
    private final Validate validate;

    public Routing(Validate validate) {
        this.validate = validate;
    }

    public Function<User, Boolean> add() {
        return validate::add;
    }

    public Function<User, Boolean> update() {
        return validate::update;
    }

    public Function<User, Boolean> delete() {
        return validate::delete;
    }

    public Routing init() {
        this.load("add", this.add());
        this.load("update", this.update());
        this.load("delete", this.delete());
        this.load("findAll", this.delete());
        return this;
    }

    public void load(String action, Function<User, Boolean> handle) {
        this.dispatch.put(action, handle);
    }

    public boolean sent(final String action, User user) {
        return this.dispatch.get(
                action
        ).apply(user);
    }

}
