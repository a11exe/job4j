package ru.job4j.crud.model;

import java.util.Objects;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 20.11.2019
 */
public class City {

  private Integer id;
  private String name;
  private Country country;

  public City() {
  }

  public City(Integer id, String name, Country country) {
    this.id = id;
    this.name = name;
    this.country = country;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Country getCountry() {
    return country;
  }

  public void setCountry(Country country) {
    this.country = country;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof City)) {
      return false;
    }
    City city = (City) o;
    return Objects.equals(id, city.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "City{"
        + "id=" + id
        + ", name='" + name + '\''
        + ", country=" + country
        + '}';
  }
}
