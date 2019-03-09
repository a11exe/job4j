package user;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class UserConvertTest {

    @Test
    public void process() {
        User user1 = new User(1, "Ivan", "Moscow");
        User user2 = new User(2, "Vovan", "Moscow");
        User user3 = new User(3, "Tolyan", "Moscow");
        List<User> users = Arrays.asList(user1, user2, user3);
        Map<Integer, User> actual = new UserConvert().process(users);
        Map<Integer, User> expetced = new HashMap<>();
        expetced.put(1, user1);
        expetced.put(2, user2);
        expetced.put(3, user3);
        assertThat(actual, is(expetced));
    }
}