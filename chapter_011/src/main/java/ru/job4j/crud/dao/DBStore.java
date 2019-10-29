package ru.job4j.crud.dao;

import org.apache.commons.dbcp2.BasicDataSource;
import ru.job4j.crud.model.Role;
import ru.job4j.crud.model.User;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 09.10.2019
 */
public class DBStore implements Store {

    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final DBStore INSTANCE = new DBStore();
    private static final String SQL_ADD_USER = "INSERT INTO USERS (name, login, email, password, createDate, role) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE_PHOTO = "UPDATE USERS SET photoId = ? WHERE id = ?";
    private static final String SQL_UPDATE_USER = "UPDATE USERS SET name = ?, login = ?, email = ?, password = ?, role = ? WHERE id = ?";
    private static final String SQL_DELETE_USER = "DELETE FROM USERS WHERE id=?";
    private static final String SQL_FIND_ALL_USERS = "SELECT id, name, login, email, photoId FROM USERS";
    private static final String SQL_FIND_USER_BY_ID = "SELECT id, name, login, email, password, photoId, createDate, role FROM USERS WHERE id=?";
    private static final String SQL_FIND_USER_BY_LOGIN = "SELECT id, name, login, email, password, photoId, createDate, role FROM USERS WHERE login=?";

    private DBStore() {

        Properties properties = new Properties();
        try (InputStream in = DBStore.class.getClassLoader().getResourceAsStream("app.properties")) {
            properties.load(in);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        SOURCE.setDriverClassName(properties.getProperty("driver"));
        SOURCE.setUrl(properties.getProperty("url"));
        SOURCE.setUsername(properties.getProperty("username"));
        SOURCE.setPassword(properties.getProperty("password"));
        SOURCE.setMinIdle(Integer.parseInt(properties.getProperty("minIdle")));
        SOURCE.setMaxIdle(Integer.parseInt(properties.getProperty("maxIdle")));
        SOURCE.setMaxOpenPreparedStatements(Integer.parseInt(properties.getProperty("maxOpenPreparedStatements")));
    }

    public static DBStore getInstance() {
        return INSTANCE;
    }

    @Override
    public User add(User user) {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(SQL_ADD_USER)
        ) {
            st.setString(1, user.getName());
            st.setString(2, user.getLogin());
            st.setString(3, user.getEmail());
            st.setString(4, user.getPassword());
            st.setDate(5, java.sql.Date.valueOf(user.getCreateDate()));
            st.setString(6, user.getRole().toString());
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            if (rs != null && rs.next()) {
                int key = rs.getInt(1);
                user.setId(key);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean updatePhoto(User user) {
        boolean updated = false;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(SQL_UPDATE_PHOTO)
        ) {
            st.setInt(1, user.getPhotoId());
            st.setInt(2, user.getId());
            st.executeUpdate();
            updated = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return updated;
    }

    @Override
    public void update(User user) {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(SQL_UPDATE_USER)
        ) {
            st.setString(1, user.getName());
            st.setString(2, user.getLogin());
            st.setString(3, user.getEmail());
            st.setString(4, user.getPassword());
            st.setString(5, user.getRole().toString());
            st.setInt(6, user.getId());
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(User user) {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(SQL_DELETE_USER)
        ) {
            st.setInt(1, user.getId());
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(SQL_FIND_ALL_USERS)
        ) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                users.add(new User.Builder()
                        .withId(rs.getInt("id"))
                        .withName(rs.getString("name"))
                        .withLogin(rs.getString("login"))
                        .withEmail(rs.getString("email"))
                        .withPhotoId(rs.getInt("photoId"))
                        .build());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User findById(int id) {
        User user = null;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(SQL_FIND_USER_BY_ID)
        ) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                user = new User.Builder()
                        .withId(rs.getInt("id"))
                        .withName(rs.getString("name"))
                        .withLogin(rs.getString("login"))
                        .withEmail(rs.getString("email"))
                        .withPassword(rs.getString("password"))
                        .withPhotoId(rs.getInt("photoId"))
                        .withCreateDate(rs.getTimestamp("createDate").toLocalDateTime().toLocalDate())
                        .withRole(Role.valueOf(rs.getString(8)))
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User findByLogin(String login) {
        User user = null;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(SQL_FIND_USER_BY_LOGIN)
        ) {
            st.setString(1, login);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                user = new User.Builder()
                        .withId(rs.getInt("id"))
                        .withName(rs.getString("name"))
                        .withLogin(rs.getString("login"))
                        .withEmail(rs.getString("email"))
                        .withPassword(rs.getString("password"))
                        .withPhotoId(rs.getInt("photoId"))
                        .withCreateDate(rs.getTimestamp("createDate").toLocalDateTime().toLocalDate())
                        .withRole(Role.valueOf(rs.getString(8)))
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}