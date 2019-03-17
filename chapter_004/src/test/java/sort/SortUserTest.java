package sort;

import org.junit.Test;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class SortUserTest {

    @Test
    public void sort() {
        SortUser sortUser = new SortUser();
        List<User> users = List.of(
        new User(3, "Ivan"),
        new User(5, "Pavel"),
        new User(4, "Nikon"),
        new User(1, "Oleg"));
        Set<User> set = sortUser.sort(users);
        User[] usersArr = set.toArray(new User[3]);

        assertThat(usersArr[0].getName(), is("Oleg"));
        assertThat(usersArr[3].getName(), is("Pavel"));
    }

    @Test
    public void sortNameLength() {
        SortUser sortUser = new SortUser();
        List<User> users = List.of(
        new User(3, "Ivan"),
        new User(5, "Pavel"),
        new User(4, "Nikolai"),
        new User(1, "Bob"));
        List<User> list = sortUser.sortNameLength(users);

        assertThat(list.get(0).getName(), is("Bob"));
        assertThat(list.get(3).getName(), is("Nikolai"));
    }

    @Test
    public void sortByAllFields() {
        SortUser sortUser = new SortUser();
        User ivan1 = new User(25, "Иван");
        User ivan2 = new User(30, "Иван");
        User serg1 = new User(20, "Сергей");
        User serg2 = new User(25, "Сергей");
        List<User> users = List.of(
        serg2,
        ivan2,
        serg1,
        ivan1);
        List<User> list = sortUser.sortByAllFields(users);

        List<User> expected = List.of(
        ivan1,
        ivan2,
        serg1,
        serg2);

        assertThat(list, is(expected));
    }
}