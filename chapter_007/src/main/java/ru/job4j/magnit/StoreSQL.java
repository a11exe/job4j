package ru.job4j.magnit;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 29.05.2019
 */
public class StoreSQL implements AutoCloseable {

    private final Config config;
    private Connection connect;

    public StoreSQL(Config config) {
        this.config = config;
    }

    public void generate(int size) {

        initConnection();
        createDatabaseIfNotExist();
        execSQL("DROP TABLE IF EXISTS account");
        execSQL("CREATE TABLE IF NOT EXISTS account"
                + "  (value           INTEGER)");

        try {
            this.connect.setAutoCommit(false);
            PreparedStatement prep =
                    this.connect.prepareStatement("insert into account values (?);");
            for (int i = 0; i < size; i++) {
                prep.setInt(1, i);
                prep.addBatch();
            }
            prep.executeBatch();
            try {
                this.connect.commit();
            } catch (SQLException e) {
                this.connect.rollback();
            }
            this.connect.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Entry> load() {

        List<Entry> list = new ArrayList<>();

        ResultSet rs = execSQL("SELECT value FROM account");
        try {
            while (rs.next()) {
                list.add(new Entry(rs.getInt(1)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private void createDatabaseIfNotExist() {
        String url = config.get("url");
        String fileName = url.replace(url, "jdbc:sqlite:");

        File file = new File(fileName);

        if (file.exists()) {
            System.out.print(String.format("This database %s already exists", fileName));
        } else {
            if (this.connect != null) {
                try {
                    DatabaseMetaData meta = this.connect.getMetaData();
                    System.out.println(String.format("The driver name is %s", meta.getDriverName()));
                    System.out.println(String.format("A new database %s has been created.", fileName));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private ResultSet execSQL(String sql) {
        ResultSet resultSet = null;
        try {
            Statement stmt = this.connect.createStatement();
            stmt.execute(sql);
            resultSet = stmt.getResultSet();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    private void initConnection() {
        try {
            Connection conn = DriverManager.getConnection(config.get("url"));
            if (conn != null) {
               this.connect = conn;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void close() throws Exception {
        if (connect != null) {
            connect.close();
        }
    }

}
