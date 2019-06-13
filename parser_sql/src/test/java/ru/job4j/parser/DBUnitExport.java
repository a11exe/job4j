package ru.job4j.parser;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 04.06.2019
 */
public class DBUnitExport {

    public static void main(String[] args) throws DatabaseUnitException, SQLException, IOException {

        Connection conn = null;

        Properties properties = new Properties();
        try (InputStream in = Parser.class.getClassLoader().getResourceAsStream("app.properties")) {
            properties.load(in);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }

        try {
            conn =  DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("username"),
                    properties.getProperty("password")
            );
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        IDatabaseConnection iConnection = new DatabaseConnection(conn);

        // экспорт всей базы данных полностью
        IDataSet fullDataSet = iConnection.createDataSet();
        FlatXmlDataSet.write(fullDataSet, new FileOutputStream("all-tables-dataset.xml"));
    }

}
