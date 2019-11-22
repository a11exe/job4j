package ru.job4j.cinema.persistence;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.apache.commons.dbcp2.BasicDataSource;
import ru.job4j.model.Account;
import ru.job4j.model.Hall;
import ru.job4j.model.Seat;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 21.11.2019
 */
public class StoreImpl implements Store {

  private static final BasicDataSource SOURCE = new BasicDataSource();

  private final static Store INSTANCE = new StoreImpl();
  private static final String SQL_HALL = "SELECT id, row, seat, price FROM HALLS ORDER BY row, seat";
  private static final String SQL_ADD_BOOK = "UPDATE HALLS SET session_id = ?, booking_time = ? WHERE row = ? AND seat = ?";
  private static final String SQL_REMOVE_BOOK = "";

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
            rs.getInt("seat"),
            rs.getBigDecimal("price")
        ));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return new Hall(seats);
  }

  @Override
  public void bookSeat(Seat seat, String sessionId) {

    try (Connection connection = SOURCE.getConnection();
        PreparedStatement st = connection.prepareStatement(SQL_ADD_BOOK)
    ) {
      st.setString(1, sessionId);
      st.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
      st.setInt(3, seat.getRow());
      st.setInt(4, seat.getNumber());
      st.executeUpdate();

    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  @Override
  public void buyTicket(Seat seat, String sessionId, Account account) {

  }
}
