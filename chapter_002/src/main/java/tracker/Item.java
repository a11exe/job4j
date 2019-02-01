package tracker;

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

    public Item(String name, String desc, long created) {
        this.name = name;
        this.desc = desc;
        this.created = created;
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
}
