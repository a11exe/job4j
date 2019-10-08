package ru.job4j.crud.model;

import java.time.LocalDate;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 04.10.2019
 */
public class User {

    private Integer id;
    private String name;
    private String login;
    private String email;
    private LocalDate createDate;


    public User(Integer id, String name, String login) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.createDate = LocalDate.now();
    }

    public User(Integer id, String name, String login, String email) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = LocalDate.now();
    }

    public User(Integer id, String name, String login, String email, LocalDate createDate) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = createDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", login='" + login + '\''
                + ", email='" + email + '\''
                + ", createDate=" + createDate
                + '}';
    }
}
