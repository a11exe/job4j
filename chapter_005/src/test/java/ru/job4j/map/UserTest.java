package ru.job4j.map;

import org.junit.Test;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 28.03.2019
 */
public class UserTest {

    @Test
    public void whenAdd2UsersSameItemsInMapShouldMapSize2() {

        Map<User, Object> users = new HashMap();
        User user1 = new User("Alex", 3, new GregorianCalendar(1982, 31, 9));
        User user2 = new User("Alex", 3, new GregorianCalendar(1982, 31, 9));
        users.put(user1, new Object());
        users.put(user2, new Object());
        System.out.println(users);
        assertThat(users.size(), is(2));
    }

}