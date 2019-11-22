package ru.job4j.model;

import java.util.Objects;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 21.11.2019
 */
public class Account {

  private final int id;
  private final String name;
  private final String phone;

  public Account(int id, String name, String phone) {
    this.id = id;
    this.name = name;
    this.phone = phone;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getPhone() {
    return phone;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Account)) {
      return false;
    }
    Account account = (Account) o;
    return id == account.id;
  }

  @Override
  public int hashCode() {

    return Objects.hash(id);
  }
}
