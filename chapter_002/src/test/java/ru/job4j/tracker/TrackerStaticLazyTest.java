package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 11.02.2019
 */
public class TrackerStaticLazyTest {

    @Test
    public void whenSingletonStaticLazyThenOneInstance() {
        Tracker trackerEnum1 = TrackerStaticLazy.getInstance();
        Tracker trackerEnum2 = TrackerStaticLazy.getInstance();
        assertThat(trackerEnum1, is(trackerEnum2));
    }

    @Test
    public void add() {
        Tracker tracker = TrackerStaticLazy.getInstance();
        Item item = new Item("test1", "testDescription", 123L);
        tracker.add(item);
        assertThat(tracker.findAll()[0], is(item));
    }
}
