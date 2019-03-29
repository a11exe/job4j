package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 26.03.2019
 */
public class NodeTest {

    private Node<Integer> first;
    private Node<Integer> two;
    private Node<Integer> third;
    private Node<Integer> four;

    @Before
    public void before() {
        first = new Node<>(1);
        two = new Node<>(2);
        third = new Node<>(3);
        four = new Node<>(4);
    }


    @Test
    public void whenHasLoopShouldTrue() {
        first.next = two;
        two.next = third;
        third.next = four;
        four.next = first;
        assertTrue(new Node<>(6).hasLoop(first));
    }

    @Test
    public void whenHasLoopInsideShouldTrue() {
        first.next = two;
        two.next = third;
        third.next = two;
        four.next = first;
        assertTrue(new Node<>(6).hasLoop(first));
    }

    @Test
    public void whenHasNoCycleShouldFalse() {
        first.next = two;
        two.next = third;
        third.next = four;
        four.next = null;
        assertFalse(first.hasLoop(first));
    }

    @Test
    public void whenHasBigListCycleShouldTrue() {

        Node<Integer> first = new Node<>(1);
        Node<Integer> node = first;

        for (int i = 2; i < 100000; i++) {
            Node<Integer> next = new Node<>(i);
            node.next = next;
            node = next;
        }

        node.next = first;
        assertTrue(first.hasLoop(first));
    }

    @Test
    public void whenHasBigListNoCycleShouldFalse() {

        Node<Integer> first = new Node<>(1);
        Node<Integer> node = first;

        for (int i = 2; i < 100000; i++) {
            Node<Integer> next = new Node<>(i);
            node.next = next;
            node = next;
        }

        assertFalse(first.hasLoop(first));
    }


}