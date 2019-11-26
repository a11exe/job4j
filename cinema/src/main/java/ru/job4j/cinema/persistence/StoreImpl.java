package ru.job4j.cinema.persistence;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.dbcp2.BasicDataSource;
import ru.job4j.model.Account;
import ru.job4j.model.Hall;
import ru.job4j.model.Seat;
import ru.job4j.model.State;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 21.11.2019
 */
public class StoreImpl implements Store {

  private static final BasicDataSource SOURCE = new BasicDataSource();

  private final static Store INSTANCE = new StoreImpl();
  private static final String SQL_HALL =
          "SELECT id, row, seat_number, price, booked_until, session_id, account_id " +
                  "FROM HALLS ORDER BY row, seat_number";
  private static final String SQL_BOOKING =
          "UPDATE HALLS SET session_id = ?, booked_until = ? " +
          "WHERE row = ? AND seat_number = ? " +
          "AND ((session_id = ? OR session_id is NULL) OR (booked_until <= ? OR booked_until is NULL)) " +
          "AND account_id is NULL";
  private static final String SQL_CANCEL_BOOKING =
          "UPDATE HALLS SET session_id = ?, booked_until = ? " +
          "WHERE session_id = ? AND account_id is NULL";
  private static final String SQL_CONFIRM_BOOKING =
          "UPDATE HALLS SET account_id = ? " +
          "WHERE row = ? AND seat_number = ? " +
          "AND ((session_id = ? OR session_id is NULL) OR booked_until <= ?) " +
          "AND account_id is NULL";
  private static final String SQL_FIND_ACCOUNT =
          "SELECT id, fio, phone " +
                  "FROM ACCOUNTS WHERE fio = ? AND phone = ?";
  private static final String SQL_INSERT_ACCOUNT =
          "INSERT INTO ACCOUNTS (fio, phone) VALUES (?, ?)";

  private StoreImpl() {
    Properties properties = new Properties();
    try (InputStream in = StoreImpl.class.getClassLoader().getResourceAsStream("app.properties")) {
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

  public static Store getInstance() {
    return INSTANCE;
  }

  private State getState(
          Timestamp now, Timestamp booked_until, int account_id, String session_id, String booked_session_id) {

    State state = State.FREE;
    if (account_id > 0) {
      state = State.BOOKED;
    } else {
      if (booked_until != null && booked_until.getTime() > now.getTime()) {
        state = State.PENDING;
      }
    }

    return state;

  }

  @Override
  public Hall getHall(String sessionId) {

    List<Seat> seats = new ArrayList<>();
    try (Connection connection = SOURCE.getConnection();
        PreparedStatement st = connection.prepareStatement(SQL_HALL)
    ) {
      ResultSet rs = st.executeQuery();
      while (rs.next()) {
        seats.add(new Seat(
            rs.getInt("id"),
            rs.getInt("row"),
            rs.getInt("seat_number"),
            rs.getBigDecimal("price"),
            getState(
                    new Timestamp(System.currentTimeMillis()),
                    rs.getTimestamp("booked_until"),
                    rs.getInt("account_id"),
                    sessionId,
                    rs.getString("session_id"))

        ));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return new Hall(seats);
  }

  @Override
  public boolean bookSeat(Seat seat) {

    boolean result = false;
    int delayMinutes = 5;
    Timestamp book_until = new Timestamp(System.currentTimeMillis());
    book_until.setTime(book_until.getTime() + TimeUnit.MINUTES.toMillis(delayMinutes));

    try (Connection connection = SOURCE.getConnection();
        PreparedStatement cancelBookSt = connection.prepareStatement(SQL_CANCEL_BOOKING);
        PreparedStatement bookSt = connection.prepareStatement(SQL_BOOKING)
    ) {

      connection.setAutoCommit(false);

      cancelBookSt.setNull(1, Types.NULL);
      cancelBookSt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
      cancelBookSt.setString(3, seat.getSessionId());
      cancelBookSt.executeUpdate();

      bookSt.setString(1, seat.getSessionId());
      bookSt.setTimestamp(2, book_until);
      bookSt.setInt(3, seat.getRow());
      bookSt.setInt(4, seat.getNumber());
      bookSt.setString(5, seat.getSessionId());
      bookSt.setTimestamp(6, new Timestamp(System.currentTimeMillis()));

      result = (bookSt.executeUpdate() > 0);

      connection.commit();

    } catch (Exception e) {
      e.printStackTrace();
    }

    return result;
  }

  @Override
  public boolean confirmBooking(Seat seat) {

    boolean result = false;

    try (Connection connection = SOURCE.getConnection();
         PreparedStatement findAccSt = connection.prepareStatement(SQL_FIND_ACCOUNT);
         PreparedStatement createAccSt =
             connection.prepareStatement(SQL_INSERT_ACCOUNT, Statement.RETURN_GENERATED_KEYS);
         PreparedStatement confirmSt = connection.prepareStatement(SQL_CONFIRM_BOOKING)
    ) {

      connection.setAutoCommit(false);

      Integer accountId = null;

      findAccSt.setString(1, seat.getAccount().getName());
      findAccSt.setString(2, seat.getAccount().getPhone());

      ResultSet rs = findAccSt.executeQuery();
      while (rs.next()) {
        accountId = rs.getInt("id");
      }
      if (accountId == null) {
        createAccSt.setString(1, seat.getAccount().getName());
        createAccSt.setString(2, seat.getAccount().getPhone());
        createAccSt.executeUpdate();
        rs = createAccSt.getGeneratedKeys();

        if (rs.next()) {
          accountId = rs.getInt(1);
        }
      }

      seat.getAccount().setId(accountId);

      confirmSt.setInt(1, seat.getAccount().getId());
      confirmSt.setInt(2, seat.getRow());
      confirmSt.setInt(3, seat.getNumber());
      confirmSt.setString(4, seat.getSessionId());
      confirmSt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));

      result = (confirmSt.executeUpdate() > 0);

      connection.commit();

    } catch (Exception e) {
      e.printStackTrace();
    }

    return result;
  }
}
