package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 11.02.2019
 */
public class TrackerEnumTest {

    @Test
    public void whenSingletonEnumThenOneInstance() {
        TrackerEnum trackerEnum1 = TrackerEnum.INSTANCE;
        TrackerEnum trackerEnum2 = TrackerEnum.INSTANCE;
        assertThat(trackerEnum1, is(trackerEnum2));
    }

    @Test
    public void add() {
        TrackerEnum trackerEnum = TrackerEnum.INSTANCE;
        Item item = new Item("test1", "testDescription", 123L);
        trackerEnum.add(item);
        assertThat(trackerEnum.findAll()[0], is(item));
    }
}
