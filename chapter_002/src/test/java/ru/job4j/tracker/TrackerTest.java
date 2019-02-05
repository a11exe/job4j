package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 01.02.2019
 */
public class TrackerTest {

    @Test
    public void add() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        tracker.add(item);
        assertThat(tracker.findAll()[0], is(item));
    }

    @Test
    public void replace() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("test1", "testDescription", 121L);
        Item item2 = new Item("test2", "testDescription", 122L);
        Item item3 = new Item("test3", "testDescription", 123L);
        Item item4 = new Item("test4", "testDescription", 124L);
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        tracker.replace(item2.getId(), item4);
        Tracker trackerReplaced = new Tracker();
        trackerReplaced.add(item1);
        trackerReplaced.add(item4);
        trackerReplaced.add(item3);
        assertThat(tracker.findAll(), is(trackerReplaced.findAll()));
    }

    @Test
    public void delete() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("test1", "testDescription", 121L);
        Item item2 = new Item("test2", "testDescription", 122L);
        Item item3 = new Item("test3", "testDescription", 123L);
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        tracker.delete(item2.getId());
        assertThat(tracker.findAll(), is(new Item[]{item1, item3}));
    }

    @Test
    public void deleteWhenFull() {
        Tracker tracker = new Tracker();
        Tracker trackerDeleted = new Tracker();
        int deletePosition = 2;
        Item deletedItem = new Item("", "", 1L);
        for (int i = 0; i < 100; i++) {
            Item item = new Item("test" + i, "testDescription", 121L);
            tracker.add(item);
            if (i != deletePosition) {
                trackerDeleted.add(item);
            } else {
                deletedItem = item;
            }
        }
        tracker.delete(deletedItem.getId());
        assertThat(tracker.findAll(), is(trackerDeleted.findAll()));
    }

    @Test
    public void deleteWhenLast() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("test1", "testDescription", 121L);
        Item item2 = new Item("test2", "testDescription", 122L);
        Item item3 = new Item("test3", "testDescription", 123L);
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        tracker.delete(item3.getId());
        assertThat(tracker.findAll(), is(new Item[]{item1, item2}));
    }

    @Test
    public void findAll() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("test1", "testDescription", 121L);
        Item item2 = new Item("test2", "testDescription", 122L);
        Item item3 = new Item("test3", "testDescription", 123L);
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        assertThat(tracker.findAll(), is(new Item[]{item1, item2, item3}));
    }

    @Test
    public void findByName() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("test1", "testDescription", 121L);
        Item item2 = new Item("test2", "testDescription", 122L);
        Item item3 = new Item("test2", "testDescription", 123L);
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        assertThat(tracker.findByName("test2"), is(new Item[]{item2, item3}));
    }

    @Test
    public void findById() {
        Tracker tracker = new Tracker();
        Item item1 = new Item("test1", "testDescription", 121L);
        Item item2 = new Item("test2", "testDescription", 122L);
        Item item3 = new Item("test2", "testDescription", 123L);
        tracker.add(item1);
        tracker.add(item2);
        tracker.add(item3);
        assertThat(tracker.findById(item2.getId()), is(item2));
    }

    @Test
    public void whenReplaceNameThenReturnNewName() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1", "testDescription", 123L);
        // Добавляем заявку в трекер. Теперь в объект проинициализирован id.
        tracker.add(previous);
        // Создаем новую заявку.
        Item next = new Item("test2", "testDescription2", 1234L);
        // Проставляем старый id из previous, который был сгенерирован выше.
        next.setId(previous.getId());
        // Обновляем заявку в трекере.
        tracker.replace(previous.getId(), next);
        // Проверяем, что заявка с таким id имеет новые имя test2.
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }
}