package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 11.02.2019
 */
public class TrackerStaticEagerTest {

    @Test
    public void whenSingletonStaticEagerThenOneInstance() {
        Tracker trackerEnum1 = TrackerStaticEager.INSTANCE;
        Tracker trackerEnum2 = TrackerStaticEager.INSTANCE;
        assertThat(trackerEnum1, is(trackerEnum2));
    }

    @Test
    public void add() {
        Tracker tracker = TrackerStaticEager.INSTANCE;
        Item item = new Item("test1", "testDescription", 123L);
        tracker.add(item);
        assertThat(tracker.findAll()[0], is(item));
    }
}
