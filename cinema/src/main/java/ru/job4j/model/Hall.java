package ru.job4j.model;

import java.util.List;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 21.11.2019
 */
public class Hall {

  private final List<Seat> hall;

  public Hall(List<Seat> hall) {
    this.hall = hall;
  }

  public List<Seat> getHall() {
    return hall;
  }
}
