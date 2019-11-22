package ru.job4j.cinema.persistence;

import ru.job4j.model.Account;
import ru.job4j.model.Hall;
import ru.job4j.model.Seat;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 21.11.2019
 */
public interface Store {

  Hall getHall(String sessionId);

  void bookSeat(Seat seat, String sessionId);

  void buyTicket(Seat seat, String sessionId, Account account);

}
