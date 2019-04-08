package ru.job4j.tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.*;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 08.04.2019
 */
public class TreeTest {

    @Test
    public void when6ElFindLastThen6() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertTrue(
                tree.findBy(6).isPresent()
        );
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        assertFalse(
                tree.findBy(7).isPresent()
        );
    }

    @Test
    public void whenDuplicateChildAddShouldFalse() {
        Tree<Integer> tree = new Tree<>(1);
        assertTrue(tree.add(1, 2));
        assertFalse(tree.add(2, 2));
    }

    @Test
    public void whenAdd6ShouldGet6ByIterator() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        tree.add(5, 2);
        List<Integer> actual = new ArrayList<>();
        for (Integer i: tree
             ) {
            actual.add(i);
        }
        assertThat(List.of(1, 2, 3, 4, 5, 6), containsInAnyOrder(actual.toArray()));
    }


}