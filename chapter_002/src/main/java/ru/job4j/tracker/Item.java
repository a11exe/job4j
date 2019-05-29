package ru.job4j.tracker;

import java.util.Arrays;
import java.util.Objects;

/**
 * Задача
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 01.02.2019
 */
public class Item {

    private String id;
    private String name;
    private String desc;
    private long created;
    private String[] comments;
    private static final String ITEM_FORMAT = "%-20.20s  %-25.25s %-50.50s";

    public Item() {
    }

    public Item(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public Item(String id, String name, String desc, long created) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.created = created;
        this.comments = new String[0];
    }

    public Item(String name, String desc, long created) {
        this.name = name;
        this.desc = desc;
        this.created = created;
        this.comments = new String[0];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public long getCreated() {
        return created;
    }


    public String[] getComments() {
        return comments;
    }

    public void setComments(String[] comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Item)) {
            return false;
        }

        Item item = (Item) o;
        return created == item.created
                && Objects.equals(id, item.id)
                && Objects.equals(name, item.name)
                && Objects.equals(desc, item.desc)
                && Arrays.equals(comments, item.comments);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(id, name, desc, created);
        result = 31 * result + Arrays.hashCode(comments);
        return result;
    }

    @Override
    public String toString() {
        return "Item{"
                + "id='" + id + '\''
                + ", name='" + name + '\''
                + ", desc='" + desc + '\''
                + ", created=" + created
                + ", comments=" + (comments == null ? null : Arrays.asList(comments))
                + '}';
    }
}
