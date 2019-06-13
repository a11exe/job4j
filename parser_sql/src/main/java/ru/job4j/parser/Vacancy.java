package ru.job4j.parser;

import java.util.Date;
import java.util.Objects;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 31.05.2019
 */
public class Vacancy {

    private int id;
    private String name;
    private String text;
    private String url;
    private Date posted;

    public Vacancy(String name, String url, Date posted) {
        this.name = name;
        this.url = url;
        this.posted = posted;
    }

    public Vacancy(String name, String text, String url, Date posted) {
        this.name = name;
        this.text = text;
        this.url = url;
        this.posted = posted;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public Date getPosted() {
        return posted;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Vacancy)) {
            return false;
        }
        Vacancy vacancy = (Vacancy) o;
        return Objects.equals(name, vacancy.name)
                && Objects.equals(text, vacancy.text)
                && Objects.equals(url, vacancy.url);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, text, url);
    }

    @Override
    public String toString() {
        return "Vacancy{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", text='" + text + '\''
                + ", url='" + url + '\''
                + ", posted=" + posted
                + '}';
    }
}
