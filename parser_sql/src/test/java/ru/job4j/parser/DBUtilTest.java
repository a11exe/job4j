package ru.job4j.parser;

import org.dbunit.Assertion;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.h2.tools.RunScript;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 05.06.2019
 */
public class DBUtilTest {

    private static final String JDBC_DRIVER = org.h2.Driver.class.getName();
    private static final String JDBC_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASSWORD = "";
    private IDatabaseTester databaseTester;

    @BeforeClass
    public static void createSchema() throws Exception {
        String schema = Parser.class.getClassLoader().getResource("schema.sql").getFile();
        RunScript.execute(JDBC_URL, USER, PASSWORD, schema, Charset.forName("UTF-8"), false);
    }

    @Before
    public void importDataSet() throws Exception {
        IDataSet dataSet = readDataSet();
        cleanlyInsert(dataSet);
    }

    private IDataSet readDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(DBUtilTest.class.getClassLoader().getResourceAsStream("all-tables-dataset.xml"));
    }

    private void cleanlyInsert(IDataSet dataSet) throws Exception {
        databaseTester = new JdbcDatabaseTester(JDBC_DRIVER, JDBC_URL, USER, PASSWORD);
        databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();
    }

    @Test
    public void whenSaveVacanciesShouldBeSaved() throws Exception {
        DBUtil dbUtil = new DBUtil(JDBC_URL, USER, PASSWORD);
        List<Vacancy> vacancies = List.of(
                new Vacancy("vac11", "vac text 11", "https://www.sql.ru/forum/1313253/nuzhen-programmist-na-super-interesnuu-rabotu-tolko-g-krasnodar", new Date()),
                new Vacancy("vac33", "vac text 22", "https://www.sql.ru/forum/1313179/razrabotchik-python-moskva", new Date()),
                new Vacancy("vac44", "vac text 33", "https://www.sql.ru/forum/1313162/be-razrabotchik-java", new Date())
        );
        dbUtil.save(vacancies);

        IDataSet databaseDataSet = databaseTester.getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("vacancies");
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(DBUtilTest.class.getClassLoader().getResourceAsStream("expected-dataset.xml"));
        ITable expectedTable = expectedDataSet.getTable("vacancies");

        Assertion.assertEqualsIgnoreCols(expectedTable, actualTable, new String[]{"posted"});
    }

    @Test
    public void whenGetOnlyNewVacanciesShouldBeOnlyNew() {
        DBUtil dbUtil = new DBUtil(JDBC_URL, USER, PASSWORD);
        List<Vacancy> vacancies = List.of(
                new Vacancy("vac2", "vac text 1", "https://www.sql.ru/forum/1313253/nuzhen-programmist-na-super-interesnuu-rabotu-tolko-g-krasnodar", null),
                new Vacancy("vac3", "vac text 2", "https://www.sql.ru/forum/1313179/razrabotchik-python-moskva", null),
                new Vacancy("vac40", "vac text 3", "https://www.sql.ru/forum/1313162/be-razrabotchik-java", null)
        );
        List<Vacancy> expected = List.of(
                new Vacancy("vac2", "vac text 1", "https://www.sql.ru/forum/1313253/nuzhen-programmist-na-super-interesnuu-rabotu-tolko-g-krasnodar", null),
                new Vacancy("vac40", "vac text 3", "https://www.sql.ru/forum/1313162/be-razrabotchik-java", null)
        );

        List<Vacancy> actual = dbUtil.getNewVacancies(vacancies);
        assertThat(actual, is(expected));
    }

    @Test
    public void whenAddLogShouldAdded() throws Exception {
        DBUtil dbUtil = new DBUtil(JDBC_URL, USER, PASSWORD);
        dbUtil.addLog("START", "start parsing", new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").parse("05/06/2019 15:01:04.104"));
        IDataSet databaseDataSet = databaseTester.getConnection().createDataSet();
        ITable actualTable = databaseDataSet.getTable("log");
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(DBUtilTest.class.getClassLoader().getResourceAsStream("expected-dataset.xml"));
        ITable expectedTable = expectedDataSet.getTable("log");
        Assertion.assertEquals(expectedTable, actualTable);
    }

    @Test
    public void whenGetLastLaunchShouldGetMaxDate() throws ParseException {
        DBUtil dbUtil = new DBUtil(JDBC_URL, USER, PASSWORD);
        Date actual = dbUtil.getLastLaunch();
        Date expected = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS").parse("05/06/2019 08:59:41.038");
        assertThat(actual, is(expected));
    }

}