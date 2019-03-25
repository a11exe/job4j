package ru.job4j.generic;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class UserStoreTest {

    @Test
    public void thenAdd1Get1ShouldBeSame() {
        UserStore userStore = new UserStore(5);
        User user = new User("dd");
        userStore.add(user);
        assertThat(userStore.findById("dd"), is(user));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenAdd3ThenSizeStore2ShouldException() {
        UserStore userStore = new UserStore(2);
        User user1 = new User("dd1");
        User user2 = new User("dd2");
        User user3 = new User("dd3");
        userStore.add(user1);
        userStore.add(user2);
        userStore.add(user3);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenAdd3DeleteIndex2GetSecondShouldException() {
        UserStore userStore = new UserStore(2);
        User user1 = new User("dd1");
        User user2 = new User("dd2");
        User user3 = new User("dd3");
        userStore.add(user1);
        userStore.add(user2);
        userStore.add(user3);
        assertTrue(userStore.delete("dd2"));
        userStore.findById("dd2");
    }

    @Test
    public void whenDeleteNotAddedShouldFalse() {
        UserStore userStore = new UserStore(2);
        User user1 = new User("dd1");
        User user3 = new User("dd3");
        userStore.add(user1);
        userStore.add(user3);
        assertFalse(userStore.delete("dd2"));
    }

    @Test
    public void whenDeleteEmptyArrayShouldFalse() {
        UserStore userStore = new UserStore(0);
        assertFalse(userStore.delete("dd2"));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenFindByIdNotPresentShouldException() {
        UserStore userStore = new UserStore(2);
        User user2 = new User("dd2");
        User user3 = new User("dd3");
        userStore.add(user2);
        userStore.add(user3);
        userStore.findById("dd4");
    }

    @Test(expected = NoSuchElementException.class)
    public void whenReplaceAndGetReplacedShouldException() {
        UserStore userStore = new UserStore(3);
        User user1 = new User("dd1");
        User user2 = new User("dd2");
        User user3 = new User("dd3");
        User user4 = new User("dd4");
        userStore.add(user1);
        userStore.add(user2);
        userStore.add(user3);
        assertTrue(userStore.replace("dd3", user4));
        assertThat(userStore.findById("dd4"), is(user4));
        userStore.findById("dd3");
    }

    @Test
    public void whenReplaceIdNotPresentShouldFalse() {
        UserStore userStore = new UserStore(3);
        User user1 = new User("dd1");
        User user4 = new User("dd4");
        userStore.add(user1);
        assertFalse(userStore.replace("dd3", user4));
    }


}