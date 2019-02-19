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
}