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

        if (previous.size() == 0 && current.size() > 0) {
            info.added = current.size();
        }
        if (previous.size() > 0 && current.size() == 0) {
            info.deleted = previous.size();
        }
        if (previous.size() > 0 && current.size() > 0) {
            Comparator<User> userComparatorById = Comparator.comparingInt(o -> o.id);
            previous.sort(userComparatorById);
            current.sort(userComparatorById);

            int i = 0;
            int y = 0;
            do {
                User prevUser = previous.get(i);
                User currUser = current.get(y);
                if (isUserEquals(prevUser, currUser)) {
                    i++;
                    y++;
                } else if (isUserChanged(prevUser, currUser)) {
                    info.changed++;
                    i++;
                    y++;
                } else if (isUserDeleted(prevUser, currUser)) {
                    info.deleted++;
                    i++;
                } else if (isUserAdded(prevUser, currUser)) {
                    info.added++;
                    y++;
                }
            } while (i < previous.size() && y < current.size());
            // add tails
            info.added = info.added + current.size() - y;
            info.deleted = info.deleted + previous.size() - i;
        }
        return info;
    }

    private boolean isUserAdded(User prevUser, User currUser) {
        return prevUser.id > currUser.id;
    }

    private boolean isUserDeleted(User prevUser, User currUser) {
        return prevUser.id < currUser.id;
    }

    private boolean isUserChanged(User prevUser, User currUser) {
        return prevUser.id == currUser.id && !prevUser.name.equals(currUser.name);
    }

    private boolean isUserEquals(User prevUser, User currUser) {
        return prevUser.id == currUser.id && prevUser.name.equals(currUser.name);
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
