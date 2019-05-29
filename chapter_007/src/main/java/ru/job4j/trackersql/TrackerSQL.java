package ru.job4j.trackersql;

import org.apache.logging.log4j.LogManager;
import ru.job4j.tracker.ITracker;
import ru.job4j.tracker.Item;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 23.05.2019
 */
public class TrackerSQL implements ITracker, AutoCloseable {

    private Connection connection;
    private final String selectCommentById = "SELECT COMMENT_NAME FROM comments "
            + "WHERE ITEM_ID = ?";

    private static final org.apache.logging.log4j.Logger LOG = LogManager.getLogger(TrackerSQL.class);

    public boolean init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            this.connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return this.connection != null;
    }

    @Override
    public Item add(Item item) {
        String insertItem = "INSERT INTO item"
                + "(ITEM_NAME, ITEM_DESC, ITEM_CREATED) VALUES"
                + "(?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertItem, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, item.getName());
            preparedStatement.setString(2, item.getDesc());
            preparedStatement.setTimestamp(3, new Timestamp(item.getCreated()));

            LOG.info(preparedStatement.toString());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                LOG.error("Creating item failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int itemId = generatedKeys.getInt(1);
                    item.setId(String.valueOf(itemId));
                    // add comments
                    String insertComment = "INSERT INTO comments"
                            + "(ITEM_ID, COMMENT_NAME) VALUES"
                            + "(?,?)";
                    try (PreparedStatement psComment = connection.prepareStatement(insertComment)) {
                        int count = 0;
                        String[] comments = item.getComments();
                        if (comments != null) {
                            for (String comment : comments
                                    ) {
                                psComment.setInt(1, itemId);
                                psComment.setString(2, comment);
                                psComment.addBatch();
                                count++;
                                // execute every 100 rows or less
                                if (count % 100 == 0 || count == comments.length) {
                                    psComment.executeBatch();
                                }
                            }
                        }
                    }

                } else {
                    LOG.error("Creating item failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            LOG.error("Connection failure", e);
        }

        return item;
    }

    @Override
    public boolean replace(String id, Item item) {

        boolean replaced = false;

        String updateItem = "UPDATE item SET "
                + "ITEM_NAME = ?, ITEM_DESC = ?, ITEM_CREATED = ? "
                + "WHERE ITEM_ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateItem)) {
            preparedStatement.setString(1, item.getName());
            preparedStatement.setString(2, item.getDesc());
            preparedStatement.setTimestamp(3, new Timestamp(item.getCreated()));
            preparedStatement.setInt(4, Integer.parseInt(item.getId()));

            LOG.info(preparedStatement.toString());

            int rows = preparedStatement.executeUpdate();

            replaced = (rows > 0);

        } catch (SQLException e) {
            LOG.error("Connection failure", e);
        }

        return replaced;
    }

    @Override
    public boolean delete(String id) {
        boolean deleted = false;
        String deleteById = "DELETE FROM item "
                + "WHERE ITEM_ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteById)) {
            preparedStatement.setInt(1, Integer.parseInt(id));

            LOG.info(preparedStatement.toString());

            int rez = preparedStatement.executeUpdate();
            deleted = (rez > 0);


        } catch (SQLException e) {
            LOG.error("Connection failure", e);
        }

        return deleted;
    }

    @Override
    public List<Item> findAll() {
        List<Item> items = new ArrayList<>();

        String selectAll = "SELECT ITEM_ID, ITEM_NAME, ITEM_DESC, ITEM_CREATED FROM item";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectAll)) {

            LOG.info(preparedStatement.toString());

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Item item = new Item();
                item.setId(String.valueOf(rs.getInt("item_id")));
                item.setName(rs.getString("item_name"));
                item.setDesc(rs.getString("item_desc"));
                item.setCreated(rs.getTimestamp("item_created").getTime());

                try (PreparedStatement psComment = connection.prepareStatement(this.selectCommentById)) {
                    psComment.setInt(1, Integer.parseInt(item.getId()));
                    List<String> comments = new ArrayList<>();
                    ResultSet rsComment = psComment.executeQuery();
                    while (rsComment.next()) {
                        comments.add(rsComment.getString("comment_name"));
                    }
                    item.setComments(comments.toArray(new String[comments.size()]));
                }

                items.add(item);
            }

        } catch (SQLException e) {
            LOG.error("Connection failure", e);
        }

        return items;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> items = new ArrayList<>();

        String selectByName = "SELECT ITEM_ID, ITEM_NAME, ITEM_DESC, ITEM_CREATED FROM item "
                + "WHERE ITEM_NAME = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectByName)) {
            preparedStatement.setString(1, key);

            LOG.info(preparedStatement.toString());

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Item item = new Item();
                item.setId(String.valueOf(rs.getInt("item_id")));
                item.setName(rs.getString("item_name"));
                item.setDesc(rs.getString("item_desc"));
                item.setCreated(rs.getTimestamp("item_created").getTime());

                try (PreparedStatement psComment = connection.prepareStatement(this.selectCommentById)) {
                    psComment.setInt(1, Integer.parseInt(item.getId()));
                    List<String> comments = new ArrayList<>();
                    ResultSet rsComment = psComment.executeQuery();
                    while (rsComment.next()) {
                        comments.add(rsComment.getString("comment_name"));
                    }
                    item.setComments(comments.toArray(new String[comments.size()]));
                }

                items.add(item);
            }

        } catch (SQLException e) {
            LOG.error("Connection failure", e);
        }

        return items;
    }

    @Override
    public Item findById(String id) {

        Item item = null;

        String selectById = "SELECT ITEM_ID, ITEM_NAME, ITEM_DESC, ITEM_CREATED FROM item "
                + "WHERE ITEM_ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectById)) {
            preparedStatement.setInt(1, Integer.parseInt(id));

            LOG.info(preparedStatement.toString());

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                item = new Item();
                item.setId(String.valueOf(rs.getInt("item_id")));
                item.setName(rs.getString("item_name"));
                item.setDesc(rs.getString("item_desc"));
                item.setCreated(rs.getTimestamp("item_created").getTime());

                try (PreparedStatement psComment = connection.prepareStatement(this.selectCommentById)) {
                    psComment.setInt(1, Integer.parseInt(item.getId()));
                    List<String> comments = new ArrayList<>();
                    ResultSet rsComment = psComment.executeQuery();
                    while (rsComment.next()) {
                        comments.add(rsComment.getString("comment_name"));
                    }
                    item.setComments(comments.toArray(new String[comments.size()]));
                }
            }

        } catch (SQLException e) {
            LOG.error("Connection failure", e);
        }

        return item;
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
