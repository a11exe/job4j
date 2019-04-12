package ru.job4j.analyze;

import java.util.*;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 09.04.2019
 */
public class Analize {

    public Info diff(List<User> previous, List<User> current) {

        Info info = new Info();

        Map<Integer, User> map = new TreeMap<>();

        if (previous.size() == 0 && current.size() > 0) {
            info.added = current.size();
        }
        if (previous.size() > 0 && current.size() == 0) {
            info.deleted = previous.size();
        }
        if (previous.size() > 0 && current.size() > 0) {

            int maxSize = Math.max(previous.size(), current.size());
            for (int i = 0; i < maxSize; i++) {
                // prev
                if (i < previous.size()) {
                    User prevUser = previous.get(i);
                    if (map.containsKey(prevUser.id)) {
                        if (!map.get(prevUser.id).name.equals(prevUser.name)) {
                            info.changed++;
                            info.added--;
                        } else {
                            info.added--;
                        }
                    } else {
                        map.put(prevUser.id, prevUser);
                        info.deleted++;
                    }
                }
                // current
                if (i < current.size()) {
                    User currUser = current.get(i);
                    if (map.containsKey(currUser.id)) {
                        if (!map.get(currUser.id).name.equals(currUser.name)) {
                            info.changed++;
                            info.deleted--;
                        } else {
                            info.deleted--;
                        }
                    } else {
                        map.put(currUser.id, currUser);
                        info.added++;
                    }
                }
            }
        }
        return info;
    }

    public static class User {
        int id;
        String name;
    }

    public static class Info {
        int added;
        int changed;
        int deleted;
    }

}
