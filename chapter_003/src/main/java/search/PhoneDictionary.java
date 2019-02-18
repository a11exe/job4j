package search;

import java.util.ArrayList;
import java.util.List;

/**
 * Телефонный справочник
 *
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 18.02.2019
 */
public class PhoneDictionary {
    private final List<Person> persons = new ArrayList<>();

    public void add(Person person) {
        this.persons.add(person);
    }

    /**
     * Вернуть список всех пользователей, который содержат key в любых полях.
     * @param key Ключ поиска.
     * @return Список подощедщих пользователей.
     */
    public List<Person> find(String key) {
        List<Person> result = new ArrayList<>();
        if (key != null) {
            for (Person person : persons) {
                if (person.getAddress().contains(key)
                        || person.getName().contains(key)
                        || person.getPhone().contains(key)
                        || person.getSurname().contains(key)) {
                    result.add(person);
                }
            }
        }
        return result;
    }
}
