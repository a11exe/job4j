package ru.job4j.email;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 26.09.2019
 */
public class User {

    private String username;
    private String email;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
