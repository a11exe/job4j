package ru.job4j.trackersql;

import org.junit.Test;
import ru.job4j.tracker.Item;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 23.05.2019
 */
public class TrackerSQLTest {

    //@BeforeClass
    public static void initDB() {

//        final class SqlExecuter extends SQLExec {
//            public SqlExecuter() {
//                Project project = new Project();
//                project.init();
//                setProject(project);
//                setTaskType("sql");
//                setTaskName("sql");
//            }
//        }
//
//        try (InputStream in = TrackerSQLTest.class.getClassLoader().getResourceAsStream("app.properties")) {
//            Properties config = new Properties();
//            config.load(in);
//
//            SqlExecuter executer = new SqlExecuter();
//            executer.setSrc(new File(TrackerSQLTest.class.getClassLoader().getResource("create.sql").getPath()));
//            executer.setDriver(config.getProperty("driver-class-name"));
//            executer.setPassword(config.getProperty("password"));
//            executer.setUserid(config.getProperty("username"));
//            executer.setUrl(config.getProperty("url"));
//            executer.execute();
//
//        } catch (Exception e) {
//            throw new IllegalStateException(e);
//        }
    }

    public Connection init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")

            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void checkConnection() throws SQLException {
        TrackerSQL sql = new TrackerSQL(ConnectionRollback.create(this.init()));
        assertThat(sql.init(), is(true));
    }

    @Test
    public void whenFindAllShouldFoundAllItems() throws SQLException {
        TrackerSQL sql = new TrackerSQL(ConnectionRollback.create(this.init()));
        Item item1 = new Item("item 1", "res1", 0L);
        Item item2 = new Item("item 2", "res2", 0L);
        Item item3 = new Item("item 3", "res3", 0L);
        sql.add(item1);
        sql.add(item2);
        sql.add(item3);
        List<Item> expected = List.of(item1, item2, item3);
        assertThat(sql.findAll(), is(expected));
    }

    @Test
    public void whenCreateItemAndGetByIdShouldBeSame() throws SQLException {
        TrackerSQL sql = new TrackerSQL(ConnectionRollback.create(this.init()));
        Item item = new Item("test item", "test item desc", 0L);
        item.setComments(new String[] {"com1", "com2"});
        Item expected = sql.add(item);
        Item actual = sql.findById(expected.getId());
        assertThat(actual, is(expected));
    }

    @Test
    public void whenDeleteItemByIdShouldBeDeleted() throws SQLException {
        TrackerSQL sql = new TrackerSQL(ConnectionRollback.create(this.init()));
        Item item = new Item("delete item", "", 0L);
        sql.add(item);
        assertTrue(sql.delete(item.getId()));
        assertNull(sql.findById(item.getId()));
        assertFalse(sql.delete(item.getId()));
    }

    @Test
    public void whenReplaceItemAndGetByIdShouldBeReplaced() throws SQLException {
        TrackerSQL sql = new TrackerSQL(ConnectionRollback.create(this.init()));
        Item item = new Item("replace item", "res", 0L);
        sql.add(item);
        item.setName("replaced");
        assertTrue(sql.replace(item.getId(), item));
        assertThat(item, is(sql.findById(item.getId())));
    }

    @Test
    public void whenFindItemByNameShouldFoundAllItems() throws SQLException {
        TrackerSQL sql = new TrackerSQL(ConnectionRollback.create(this.init()));
        Item item1 = new Item("same item", "res1", 0L);
        Item item2 = new Item("same item", "res2", 0L);
        Item item3 = new Item("same item", "res3", 0L);
        sql.add(item1);
        sql.add(item2);
        sql.add(item3);
        List<Item> expected = List.of(item1, item2, item3);
        assertTrue(sql.findByName("same item").containsAll(expected));
    }


}