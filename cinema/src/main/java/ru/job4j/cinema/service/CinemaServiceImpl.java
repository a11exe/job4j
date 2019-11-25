package ru.job4j.cinema.service;

import ru.job4j.cinema.persistence.Store;
import ru.job4j.cinema.persistence.StoreImpl;
import ru.job4j.model.Account;
import ru.job4j.model.Hall;
import ru.job4j.model.Seat;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 21.11.2019
 */
public class CinemaServiceImpl implements CinemaService {

  private final Store logic = StoreImpl.getInstance();

  private final static CinemaService INSTANCE = new CinemaServiceImpl();

  private CinemaServiceImpl() {
  }

  public static CinemaService getInstance() {
    return INSTANCE;
  }


  @Override
  public Hall getHall(String sessionId) {
    return logic.getHall(sessionId);
  }

  @Override
  public void bookSeat(Seat seat) {
    logic.bookSeat(seat);
  }

  @Override
  public void confirmBooking(Seat seat) {
    logic.confirmBooking(seat);
  }
}
