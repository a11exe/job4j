package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 25.03.2019
 */
public class SimpleLinkedListTest {

    private SimpleLinkedList<Integer> list;

    @Before
    public void beforeTest() {
        list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    public void whenAddThreeElementsThenUseGetOneResultTwo() {
        assertThat(list.get(1), is(2));
    }

    @Test
    public void whenAddThreeElementsThenUseGetSizeResultThree() {
        assertThat(list.getSize(), is(3));
    }

    @Test
    public void whenAddThreeElementsThenDeleteGetOneResultThree() {
        assertThat(list.delete(), is(3));
        assertThat(list.get(1), is(1));
    }

    @Test
    public void whenAddThreeElementsThenDeleteGetOneSizeTwo() {
        list.delete();
        assertThat(list.getSize(), is(2));
    }

    @Test
    public void whenAddThreeElementsThenDelete4LastDeleteReturnNull() {
        list.delete();
        list.delete();
        list.delete();
        assertNull(list.delete());
    }
}