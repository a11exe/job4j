package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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

    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private Tracker tracker;
    private Item[] items;
    private final int size = 3;
    private final StringBuilder menuOut = new StringBuilder()
            .append("Меню.")
            .append(System.lineSeparator())
            .append("0. Add new Item")
            .append(System.lineSeparator())
            .append("1. Show all items")
            .append(System.lineSeparator())
            .append("2. Edit item")
            .append(System.lineSeparator())
            .append("3. Delete item")
            .append(System.lineSeparator())
            .append("4. Find item by Id")
            .append(System.lineSeparator())
            .append("5. Find items by name")
            .append(System.lineSeparator())
            .append("6. Exit Program");


    @Before
    public void setOutput() {
        System.setOut(new PrintStream(out));
        fillTracker();
    }

    @After
    public void backOutput() {
        System.setOut(stdout);
    }

    @Test
    public void thenShowAllItemThenThreeItems() {
        this.out.reset();
        StartUI startUI = new StartUI(new StubInput(new String[]{"1", "6"}), this.tracker);
        startUI.init();
        String actual = new String(out.toByteArray());

        this.out.reset();
        StringBuilder outTest = new StringBuilder(menuOut)
                .append(System.lineSeparator())
                .append("------------Список всех заявок------------")
                .append(System.lineSeparator());
        Item[] items = this.tracker.findAll();
        for (Item item : items
                ) {
            outTest.append(item.toString());
            outTest.append(System.lineSeparator());
        }
        outTest.append(menuOut);
        System.out.println(outTest);
        String expected = new String(out.toByteArray());
        assertThat(actual, is(expected));
    }

    @Test
    public void thenFindByNameThenFirstItem() {
        String name = "name 0";
        this.out.reset();
        StartUI startUI = new StartUI(new StubInput(new String[]{"5", name, "6"}), this.tracker);
        startUI.init();
        String actual = new String(out.toByteArray());

        this.out.reset();
        StringBuilder outTest = new StringBuilder(menuOut)
                .append(System.lineSeparator())
                .append("------------ Поиск заявок по имени --------------")
                .append(System.lineSeparator());

            outTest.append(this.tracker.findByName(name)[0].toString());
            outTest.append(System.lineSeparator());

        outTest.append(menuOut);
        System.out.println(outTest);
        String expected = new String(out.toByteArray());
        assertThat(actual, is(expected));
    }

    @Test
    public void addItem() {
        System.out.println(tracker.findAll().length);
        assertThat(tracker.findAll()[0].getName(), is(items[0].getName()));
        assertThat(tracker.findAll()[0].getDesc(), is(items[0].getDesc()));
    }

    @Test
    public void deleteItem() {
        StartUI startUI = new StartUI(new StubInput(new String[]{"3", tracker.findAll()[0].getId(), "6"}), tracker);
        startUI.init();
        assertThat(tracker.findAll()[0].getName(), is(items[1].getName()));
        assertThat(tracker.findAll()[1].getDesc(), is(items[2].getDesc()));
        assertThat(tracker.findAll().length, is(size - 1));
    }

    @Test
    public void editItem() {
        StartUI startUI = new StartUI(new StubInput(new String[]{"2", tracker.findAll()[0].getId(), "new name", "new desc", "6"}), tracker);
        startUI.init();
        assertThat(tracker.findAll()[0].getName(), is("new name"));
        assertThat(tracker.findAll()[0].getDesc(), is("new desc"));
    }

    private void fillTracker() {
        this.tracker = new Tracker();
        this.items = new Item[size];
        String[] addActions = new String[3 * size + 1];
        for (int i = 0; i < items.length; i++) {
            items[i] = new Item("name " + i, "desc " + i);
            addActions[i * 3] = "0";
            addActions[i * 3 + 1] = items[i].getName();
            addActions[i * 3 + 2] = items[i].getDesc();
        }
        addActions[addActions.length - 1] = "6";
        StartUI startUI = new StartUI(new StubInput(addActions), this.tracker);
        startUI.init();
    }

}