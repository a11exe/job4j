package ru.job4j.tracker;

/**
 * private static final class. Lazy loading.
 *
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 11.02.2019
 */
public class TrackerHolderLazy {
    private TrackerHolderLazy() { }

    private static final class Holder {
        private static final Tracker INSTANCE = new Tracker();
    }

    public static Tracker getInstance() {
        return Holder.INSTANCE;
    }

}
