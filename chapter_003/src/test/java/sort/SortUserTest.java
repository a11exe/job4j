package sort;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class SortUserTest {

    @Test
    public void sort() {
        SortUser sortUser = new SortUser();
        List<User> users = new ArrayList<>();
        users.add(new User(3, "Ivan"));
        users.add(new User(5, "Pavel"));
        users.add(new User(4, "Nikon"));
        users.add(new User(1, "Oleg"));
        Set<User> set = sortUser.sort(users);
        User[] usersArr = set.toArray(new User[3]);

        assertThat(usersArr[0].getName(), is("Oleg"));
        assertThat(usersArr[3].getName(), is("Pavel"));
    }

    @Test
    public void sortNameLength() {
        SortUser sortUser = new SortUser();
        List<User> users = new ArrayList<>();
        users.add(new User(3, "Ivan"));
        users.add(new User(5, "Pavel"));
        users.add(new User(4, "Nikolai"));
        users.add(new User(1, "Bob"));
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
        List<User> users = new ArrayList<>();
        users.add(serg2);
        users.add(ivan2);
        users.add(serg1);
        users.add(ivan1);
        List<User> list = sortUser.sortByAllFields(users);

        List<User> expected = new ArrayList<>();
        expected.add(ivan1);
        expected.add(ivan2);
        expected.add(serg1);
        expected.add(serg2);

        assertThat(list, is(expected));
    }
}