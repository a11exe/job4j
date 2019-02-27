package bank;

import java.util.Objects;

/**
 * Пользователь.
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 27.02.2019
 */
public class User {

    private String name;
    private final String passport;

    public User(String name, String passport) {
        this.name = name;
        this.passport = passport;
    }

    public User(String passport) {
        this.passport = passport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(passport, user.passport);
    }

    @Override
    public int hashCode() {

        return Objects.hash(passport);
    }

    @Override
    public String toString() {
        return "User{" + "name='" + name + '\'' + ", passport='" + passport + '\'' + '}';
    }
}
