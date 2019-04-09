package ru.job4j.analyze;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 09.04.2019
 */
public class AnalizeTest {

    private final Analize.User user1 = new Analize.User();
    private final Analize.User user2 = new Analize.User();
    private final Analize.User user3 = new Analize.User();
    private final Analize.User user4 = new Analize.User();
    private final Analize.User user5 = new Analize.User();

    @Before
    public void before() {
        user1.id = 1;
        user1.name = "Alex";
        user2.id = 2;
        user2.name = "Bob";
        user3.id = 3;
        user3.name = "Jhon";
        user4.id = 4;
        user4.name = "Felix";
        user5.id = 4;
        user5.name = "Chuck";
    }

    @Test
    public void whenBothEmptyShouldAllZero() {
        Analize analize = new Analize();
        Analize.Info info = analize.diff(List.of(), List.of());
        assertThat(info.added, is(0));
        assertThat(info.deleted, is(0));
        assertThat(info.changed, is(0));
    }

    @Test
    public void whenPrevEmptyShouldAllAdded() {
        Analize analize = new Analize();
        Analize.Info info = analize.diff(List.of(), List.of(user1, user2, user3, user4));
        assertThat(info.added, is(4));
        assertThat(info.deleted, is(0));
        assertThat(info.changed, is(0));
    }

    @Test
    public void whenCurrEmptyShouldAllDeleted() {
        Analize analize = new Analize();
        Analize.Info info = analize.diff(List.of(user1, user2, user3, user4), List.of());
        assertThat(info.added, is(0));
        assertThat(info.deleted, is(4));
        assertThat(info.changed, is(0));
    }

    @Test
    public void whenTwoChangedEmptyShould2Changed() {
        Analize analize = new Analize();
        Analize.Info info = analize.diff(new ArrayList<>(List.of(user1, user2, user3, user4)), new ArrayList<>(List.of(user1, user2, user3, user5)));
        assertThat(info.added, is(0));
        assertThat(info.deleted, is(0));
        assertThat(info.changed, is(1));
    }

    @Test
    public void whenTwoChangedOneAddedOneDeletedMessShouldChanged1Deleted1Added1() {
        Analize analize = new Analize();
        Analize.Info info = analize.diff(new ArrayList<>(List.of(user2, user3, user4)), new ArrayList<>(List.of(user5, user1, user3)));
        assertThat(info.added, is(1));
        assertThat(info.deleted, is(1));
        assertThat(info.changed, is(1));
    }

    @Test
    public void whenPrevLongerShouldDeleted() {
        Analize analize = new Analize();
        Analize.Info info = analize.diff(new ArrayList<>(List.of(user1, user2, user3, user4)), new ArrayList<>(List.of(user5)));
        assertThat(info.added, is(0));
        assertThat(info.deleted, is(3));
        assertThat(info.changed, is(1));
    }

    @Test
    public void whenCurrLongerShouldAdded() {
        Analize analize = new Analize();
        Analize.Info info = analize.diff(new ArrayList<>(List.of(user5)), new ArrayList<>(List.of(user1, user2, user3, user4)));
        assertThat(info.added, is(3));
        assertThat(info.deleted, is(0));
        assertThat(info.changed, is(1));
    }

}