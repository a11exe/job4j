package ru.job4j.parser;

import org.apache.logging.log4j.LogManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 31.05.2019
 */
public class DBUtil implements AutoCloseable {

    private Connection connect;
    private final String baseUrl;
    private final String user;
    private final String password;

    private static final org.apache.logging.log4j.Logger LOG = LogManager.getLogger(DBUtil.class);

    public DBUtil(String baseUrl, String user, String password) {
        this.baseUrl = baseUrl;
        this.user = user;
        this.password = password;
        initConnection();
    }

    private void initConnection() {
        try {
            Connection conn =  DriverManager.getConnection(
                    baseUrl,
                    user,
                    password
            );
            if (conn != null) {
                this.connect = conn;
            }
        } catch (SQLException e) {
            LOG.error("Connection failure", e);
        }
    }

    public void save(List<Vacancy> vacancyList) {
        try {
            this.connect.setAutoCommit(false);
            PreparedStatement prep =
                    this.connect.prepareStatement("insert into VACANCIES (NAME, TEXT, LINK) values (?, ?, ?);");
            for (Vacancy aVacancyList : vacancyList) {
                prep.setString(1, aVacancyList.getName());
                prep.setString(2, aVacancyList.getText());
                prep.setString(3, aVacancyList.getUrl());
                // prep.setTimestamp(4, new Timestamp(aVacancyList.getPosted().getTime()));
                prep.addBatch();
            }
            prep.executeBatch();
            try {
                this.connect.commit();
            } catch (SQLException e) {
                this.connect.rollback();
                LOG.error("Error save vacancies rollback", e);
            }
            this.connect.setAutoCommit(true);
        } catch (SQLException e) {
            LOG.error("Error save vacancies ", e);
        }
    }

    public List<Vacancy> getNewVacancies(List<Vacancy> vacancyList) {

        List<Vacancy> newVacancies = new ArrayList<>();

        String namesForQuery = String.join(",", vacancyList.stream().map(vacancy -> "'" + vacancy.getName() + "'").collect(Collectors.toList()));
        String selectNames = "SELECT name FROM VACANCIES WHERE name IN (" + namesForQuery + ")";

        LOG.info(selectNames);

        try (PreparedStatement preparedStatement = this.connect.prepareStatement(selectNames)) {
            ResultSet rs = preparedStatement.executeQuery();
            List<String> namesInBase = new ArrayList<>();
            while (rs.next()) {
                namesInBase.add(rs.getString("name"));
            }
            newVacancies = vacancyList.stream().filter(vacancy -> !namesInBase.contains(vacancy.getName())).collect(Collectors.toList());
        } catch (SQLException e) {
            LOG.error("Error getting new vacancies", e);
        }

        return newVacancies;
    }

    public Date getLastLaunch() {
        Date lastLaunch = new Date();

        String select = "SELECT time FROM LOG WHERE EVENT = 'START' order by time DESC LIMIT 1";

        try (PreparedStatement preparedStatement = this.connect.prepareStatement(select)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                lastLaunch = new Date(rs.getTimestamp("time").getTime());
            }

        } catch (SQLException e) {
            LOG.error("Error get last launch", e);
        }

        return lastLaunch;
    }

    public void addLog(String event, String msg, Date date) {
        try {
            PreparedStatement prep =
                    this.connect.prepareStatement("insert into LOG (EVENT, MSG, TIME) values (?, ?, ?);");

            prep.setString(1, event);
            prep.setString(2, msg);
            prep.setTimestamp(3, new Timestamp(date.getTime()));
            prep.executeUpdate();

        } catch (SQLException e) {
            LOG.error("Error add to log", e);
        }
    }

    @Override
    public void close() throws Exception {
        if (connect != null) {
            connect.close();
        }
    }

}
