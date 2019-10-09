package ru.job4j.crud.dao;

import org.junit.Test;
import ru.job4j.crud.model.User;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 09.10.2019
 */
public class DBStoreTest {

    @Test
    public void whenAddUserWhenShouldAdded() {
        User user = new User(null, "test", "test", "test@mail.ru");
        Store store = DBStore.getInstance();
        User added = store.add(user);
        user.setId(added.getId());
        assertThat(user, is(added));
    }

}