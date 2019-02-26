package peer;
// TODO убрать пустую строку

import java.util.*;

public class Sorter {

    public Sorter(){ // TODO пустой конструктор можно не создавать, если нет других конструкторов, то создается автоматически
// TODO убрать пустую строку
    }

    Set<User> sort (List<User> list) {
        TreeSet<User> sortedList = new TreeSet<>();
        sortedList.addAll(list);
        return sortedList; // TODO заменить на одну строку return new TreeSet<>(list)
    }

    List<User> sortnamelength (List<User> list) { // TODO изменить название в camel case sortNameLength
        Comparator<User> compar = new Comparator<User>() {
            @Override
            public int compare (User o1, User o2) {
                return o1.getName().length() - o2.getName().length();
            }
        };
        list.sort(compar); // TODO убрать компаратор, заменить на list.sort((o1, o2) -> o1.getName().length() - o2.getName().length());
        return list;
    }

    List<User> sortboth (List<User> list) { // TODO изменить название в camel case sortBoth, или возможно переименовать sortNameAge
        Comparator<User> compar1 = new Comparator<User>() {
            @Override
            public int compare (User o1, User o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };
        Comparator<User> compar2 = new Comparator<User>() {
            @Override
            public int compare (User o1, User o2) {
                return o1.getAge() - o2.getAge();
            }
        };
        list.sort(compar1.thenComparing(compar2)); // TODO убрать компарторы заменить на list.sort(Comparator.comparing(User::getName).thenComparing(User::getAge));
        return list;
    }
}