package tracker;

import java.util.Formatter;

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

    public Item(String name, String desc, long created) {
        this.name = name;
        this.desc = desc;
        this.created = created;
    }

    public Item(String name, String desc) {
        this.name = name;
        this.desc = desc;
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

    public long getCreated() {
        return created;
    }

    @Override
    public String toString() {
        Formatter formatter = new Formatter();
        formatter.format(ITEM_FORMAT, this.getName(), this.getDesc(), this.getId());
        return formatter.toString();
    }
}
