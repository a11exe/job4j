package ru.job4j.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 21.11.2019
 */
public class Seat {

  private int id;
  private int row;
  private int number;
  private BigDecimal price;
  private String sessionId;
  private Account account;
  private State state;

  public Seat() {
  }

  public Seat(int id, int row, int number, BigDecimal price, State state) {
    this.id = id;
    this.row = row;
    this.number = number;
    this.price = price;
    this.state = state;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public int getRow() {
    return row;
  }

  public int getNumber() {
    return number;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  public String getSessionId() {
    return sessionId;
  }

  public void setSessionId(String sessionId) {
    this.sessionId = sessionId;
  }

  public State getState() {
    return state;
  }

  public void setState(State state) {
    this.state = state;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Seat)) {
      return false;
    }
    Seat seat = (Seat) o;
    return id == seat.id;
  }

  @Override
  public int hashCode() {

    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "Row=" + row
        + ", Seat=" + number;
  }
}
