package ru.job4j.cinema.service;

import ru.job4j.model.Account;
import ru.job4j.model.Hall;
import ru.job4j.model.Seat;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 21.11.2019
 */
public interface CinemaService {

  Hall getHall(String sessionId);

  void bookSeat(Seat seat, String sessionId);

  void cancelBooking(Seat seat, String sessionId);

  void confirmBooking(Seat seat, String sessionId, Account account);

}
