package search;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
        Predicate<Person> name = s -> s.getName().contains(key);
        Predicate<Person> phone = s -> s.getName().contains(key);
        Predicate<Person> surname = s -> s.getName().contains(key);
        return persons.stream().filter(name.or(phone).or(surname)).collect(Collectors.toList());
    }
}
