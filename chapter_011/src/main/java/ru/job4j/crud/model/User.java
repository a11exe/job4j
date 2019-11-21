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
    private Role role;
    private String password;

    public User() {
    }

    public User(final Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.login = builder.login;
        this.email = builder.email;
        this.createDate = builder.createDate;
        this.photoId = builder.photoId;
        this.role = builder.role;
        this.password = builder.password;
    }

    public static class Builder {
        private Integer id;
        private String name;
        private String login;
        private String email;
        private LocalDate createDate;
        private Integer photoId;
        private Role role;
        private String password;

        public User build() {
            return new User(this);
        }

        public Builder withId(final Integer id) {
            this.id = id;
            return this;
        }

        public Builder withName(final String name) {
            this.name = name;
            return this;
        }

        public Builder withLogin(final String login) {
            this.login = login;
            return this;
        }

        public Builder withEmail(final String email) {
            this.email = email;
            return this;
        }

        public Builder withCreateDate(final LocalDate createDate) {
            this.createDate = createDate;
            return this;
        }

        public Builder withPhotoId(final Integer photoId) {
            this.photoId = photoId;
            return this;
        }

        public Builder withRole(final Role role) {
            this.role = role;
            return this;
        }

        public Builder withPassword(final String password) {
            this.password = password;
            return this;
        }
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

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return Role.ADMIN.equals(this.role);
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
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
                + ", photoId=" + photoId
                + ", role=" + role
                + '}';
    }
}
