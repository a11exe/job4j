package ru.job4j.crud.dao;

import org.apache.commons.dbcp2.BasicDataSource;
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
    private static final String SQL_ADD = "INSERT INTO USERS (name, login, email, createDate) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE USERS SET name = ?, login = ?, email = ?, createDate = ? WHERE id = ?";
    private static final String SQL_UPDATE_PHOTO = "UPDATE USERS SET photoId = ? WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM USERS WHERE id=?";
    private static final String SQL_FIND_ALL = "SELECT id, name, login, email, createdate, photoid FROM USERS";
    private static final String SQL_FIND_BY_ID = "SELECT id, name, login, email, createdate, photoid FROM USERS WHERE id=?";

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
             PreparedStatement st = connection.prepareStatement(SQL_ADD)
        ) {
            st.setString(1, user.getName());
            st.setString(2, user.getLogin());
            st.setString(3, user.getEmail());
            st.setDate(4, java.sql.Date.valueOf(user.getCreateDate()));
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
    public void update(User user) {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(SQL_UPDATE)
        ) {
            st.setString(1, user.getName());
            st.setString(2, user.getLogin());
            st.setString(3, user.getEmail());
            st.setDate(4, java.sql.Date.valueOf(user.getCreateDate()));
            st.setInt(5, user.getId());
            st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(User user) {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(SQL_DELETE)
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
             PreparedStatement st = connection.prepareStatement(SQL_FIND_ALL)
        ) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                users.add(new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDate(5).toLocalDate(),
                        rs.getInt(6)
                        ));
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
             PreparedStatement st = connection.prepareStatement(SQL_FIND_BY_ID)
        ) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                user = new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDate(5).toLocalDate(),
                        rs.getInt(6)
                );
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
}