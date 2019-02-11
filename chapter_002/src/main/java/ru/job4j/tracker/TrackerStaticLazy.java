package ru.job4j.tracker;

/**
 * static field. Lazy loading.
 *
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 11.02.2019
 */
public class TrackerStaticLazy {
    private static Tracker instance = null;

    public static Tracker getInstance() {
        if (instance == null) {
            instance = new Tracker();
        }
        return instance;
    }

    private TrackerStaticLazy() { }
}
