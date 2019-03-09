package ru.job4j.tracker;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.function.Consumer;

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

    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final Consumer<String> output = new Consumer<String>() {
        private final PrintStream stdout = new PrintStream(out);
        @Override
        public void accept(String s) {
            stdout.println(s);
        }
    };
    private Tracker tracker;
    private Item[] items;
    private final int size = 3;
    private final StringBuilder menuOut = new StringBuilder()
            .append("0. Add new item")
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
        fillTracker();
    }

    @Test
    public void thenShowAllItemThenThreeItems() {
        StartUI startUI = new StartUI(new StubInput(new String[]{"1", "6"}), this.tracker, this.output);
        startUI.init();

        StringBuilder outTest = new StringBuilder(menuOut)
                .append(System.lineSeparator())
                .append("------------Список всех заявок------------")
                .append(System.lineSeparator());
        List<Item> items = this.tracker.findAll();
        for (Item item : items
                ) {
            outTest.append(item.toString());
            outTest.append(System.lineSeparator());
        }
        outTest.append(menuOut);
        System.out.println(outTest);
        String expected = new String(out.toByteArray());
        assertThat(this.out.toString(), is(expected));
    }

    @Test
    public void thenFindByNameThenFirstItem() {
        String name = "name 0";
        StartUI startUI = new StartUI(new StubInput(new String[]{"5", name, "6"}), this.tracker, this.output);
        startUI.init();

        StringBuilder outTest = new StringBuilder(menuOut)
                .append(System.lineSeparator())
                .append("------------ Поиск заявок по имени --------------")
                .append(System.lineSeparator());

            outTest.append(this.tracker.findByName(name).get(0).toString());
            outTest.append(System.lineSeparator());

        outTest.append(menuOut);
        System.out.println(outTest);
        String expected = new String(out.toByteArray());
        assertThat(this.out.toString(), is(expected));
    }

    @Test
    public void addItem() {
        System.out.println(tracker.findAll().size());
        assertThat(tracker.findAll().get(0).getName(), is(items[0].getName()));
        assertThat(tracker.findAll().get(0).getDesc(), is(items[0].getDesc()));
    }

    @Test
    public void deleteItem() {
        StartUI startUI = new StartUI(
                new StubInput(new String[]{"3", tracker.findAll().get(0).getId(), "6"}), this.tracker, this.output
        );
        startUI.init();
        assertThat(tracker.findAll().get(0).getName(), is(items[1].getName()));
        assertThat(tracker.findAll().get(1).getDesc(), is(items[2].getDesc()));
        assertThat(tracker.findAll().size(), is(size - 1));
    }

    @Test
    public void editItem() {
        StartUI startUI = new StartUI(
                new StubInput(
                        new String[]{"2", tracker.findAll().get(0).getId(), "new name", "new desc", "6"}),
                this.tracker,
                this.output
        );
        startUI.init();
        assertThat(tracker.findAll().get(0).getName(), is("new name"));
        assertThat(tracker.findAll().get(0).getDesc(), is("new desc"));
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
        StartUI startUI = new StartUI(new StubInput(addActions), this.tracker, this.output);
        startUI.init();
    }

}