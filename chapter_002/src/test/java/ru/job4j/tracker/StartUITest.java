package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Тест методов tracker
 *
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 05.02.2019
 */
public class StartUITest {

    @Test
    public void addItem() {
        int size = 3;
        Tracker tracker = new Tracker();
        Item[] items = new Item[size];
        String[] addActions = new String[3 * size + 1];
        for (int i = 0; i < items.length; i++) {
            items[i] = new Item("name " + i, "desc " + i);
            addActions[i * 3] = "0";
            addActions[i * 3 + 1] = items[i].getName();
            addActions[i * 3 + 2] = items[i].getDesc();
        }
        addActions[addActions.length - 1] = "6";
        StartUI startUI = new StartUI(new StubInput(addActions), tracker);
        startUI.init();
        System.out.println(tracker.findAll().length);
        assertThat(tracker.findAll()[0].getName(), is(items[0].getName()));
        assertThat(tracker.findAll()[0].getDesc(), is(items[0].getDesc()));
    }


    @Test
    public void deleteItem() {
        int size = 3;
        Tracker tracker = new Tracker();
        Item[] items = new Item[size];
        String[] addActions = new String[3 * size + 1];
        for (int i = 0; i < items.length; i++) {
            items[i] = new Item("name " + i, "desc " + i);
            addActions[i * 3] = "0";
            addActions[i * 3 + 1] = items[i].getName();
            addActions[i * 3 + 2] = items[i].getDesc();
        }
        addActions[addActions.length - 1] = "6";
        StartUI startUI = new StartUI(new StubInput(addActions), tracker);
        startUI.init();

        startUI = new StartUI(new StubInput(new String[]{"3", tracker.findAll()[0].getId(), "6"}), tracker);
        startUI.init();

        assertThat(tracker.findAll()[0].getName(), is(items[1].getName()));
        assertThat(tracker.findAll()[1].getDesc(), is(items[2].getDesc()));
        assertThat(tracker.findAll().length, is(size - 1));
    }

    @Test
    public void editItem() {
        int size = 3;
        Tracker tracker = new Tracker();
        Item[] items = new Item[size];
        String[] addActions = new String[3 * size + 1];
        for (int i = 0; i < items.length; i++) {
            items[i] = new Item("name " + i, "desc " + i);
            addActions[i * 3] = "0";
            addActions[i * 3 + 1] = items[i].getName();
            addActions[i * 3 + 2] = items[i].getDesc();
        }
        addActions[addActions.length - 1] = "6";
        StartUI startUI = new StartUI(new StubInput(addActions), tracker);
        startUI.init();

        startUI = new StartUI(new StubInput(new String[]{"2", tracker.findAll()[0].getId(),"new name", "new desc", "6"}), tracker);
        startUI.init();

        assertThat(tracker.findAll()[0].getName(), is("new name"));
        assertThat(tracker.findAll()[0].getDesc(), is("new desc"));
    }
}