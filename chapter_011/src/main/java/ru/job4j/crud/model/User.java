package ru.job4j.crud.model;

import java.time.LocalDate;
import java.util.Objects;

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
    private Integer photoId;

    public User() {
    }

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

    public User(Integer id, String name, String login, String email, LocalDate createDate, Integer photoId) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = createDate;
        this.photoId = photoId;
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

    public Integer getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Integer photoId) {
        this.photoId = photoId;
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
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
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
