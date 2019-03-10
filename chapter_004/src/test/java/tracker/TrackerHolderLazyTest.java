package tracker;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 11.02.2019
 */
public class TrackerHolderLazyTest {

    @Test
    public void whenSingletonHolderLazyThenOneInstance() {
        Tracker trackerEnum1 = TrackerHolderLazy.getInstance();
        Tracker trackerEnum2 = TrackerHolderLazy.getInstance();
        assertThat(trackerEnum1, is(trackerEnum2));
    }

    @Test
    public void add() {
        Tracker tracker = TrackerHolderLazy.getInstance();
        Item item = new Item("test1", "testDescription", 123L);
        tracker.add(item);
        assertThat(tracker.findAll().get(0), is(item));
    }
}
