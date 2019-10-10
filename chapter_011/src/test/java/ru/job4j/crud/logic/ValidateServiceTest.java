package ru.job4j.crud.logic;

import org.junit.Test;
import ru.job4j.crud.model.User;

import static org.junit.Assert.*;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 09.10.2019
 */
public class ValidateServiceTest {

    @Test
    public void whenAddUserWhenShouldAdded() {
        User user = new User(null, "test", "test", "test@mail.ru");
        assertTrue(ValidateService.getInstance().add(user));
    }

    @Test
    public void whenFindAllUserWhenShouldLeastOne() {
        assertTrue(ValidateService.getInstance().findAll().size() > 0);
    }
}