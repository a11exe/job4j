package ru.job4j.scripts;

import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 14.06.2019
 */
public class ScriptsTest {

    @Test
    public void whenScriptsOrderedShouldGetAll() {

        Map<Integer, List<Integer>> ds = Map.of(
                1, List.of(2, 3),
                2, List.of(4),
                3, List.of(4, 5),
                4, List.of(),
                5, List.of()
                );

        List<Integer> expected = List.of(2, 3, 4, 5);
        Scripts scripts = new Scripts();
        List<Integer> actual = scripts.load(ds, 1);

        assertThat(actual, containsInAnyOrder(expected.toArray()));
    }

    @Test
    public void whenScriptsMixedShouldGetAll() {

        Map<Integer, List<Integer>> ds = Map.of(
                4, List.of(),
                5, List.of(),
                1, List.of(2, 3),
                2, List.of(4),
                3, List.of(4, 5)
        );

        List<Integer> expected = List.of(2, 3, 4, 5);
        Scripts scripts = new Scripts();
        List<Integer> actual = scripts.load(ds, 1);

        assertThat(actual, containsInAnyOrder(expected.toArray()));
    }

    @Test
    public void whenScriptsCrossLinkedShouldSkipCross() {

        Map<Integer, List<Integer>> ds = Map.of(
                1, List.of(2, 3),
                2, List.of(4),
                3, List.of(4, 5),
                4, List.of(),
                5, List.of(6),
                6, List.of(1)
        );

        List<Integer> expected = List.of(2, 3, 4, 5, 6);
        Scripts scripts = new Scripts();
        List<Integer> actual = scripts.load(ds, 1);

        assertThat(actual, containsInAnyOrder(expected.toArray()));
    }

    @Test
    public void whenScriptsCrossLinkedInsideShouldSkipCross() {

        Map<Integer, List<Integer>> ds = Map.of(
                1, List.of(2, 3),
                2, List.of(4),
                3, List.of(4, 5),
                4, List.of(),
                5, List.of(6),
                6, List.of(4)
        );

        List<Integer> expected = List.of(2, 3, 4, 5, 6);
        Scripts scripts = new Scripts();
        List<Integer> actual = scripts.load(ds, 1);

        assertThat(actual, containsInAnyOrder(expected.toArray()));
    }


    @Test
    public void whenScriptsCrossLinkedBySelfShouldSkipCross() {

        Map<Integer, List<Integer>> ds = Map.of(
                1, List.of(1, 2, 3),
                2, List.of(4),
                3, List.of(4, 5),
                4, List.of(),
                5, List.of(2)
        );

        List<Integer> expected = List.of(2, 3, 4, 5);
        Scripts scripts = new Scripts();
        List<Integer> actual = scripts.load(ds, 1);

        assertThat(actual, containsInAnyOrder(expected.toArray()));
    }

    @Test
    public void whenScriptsLinkedToKeysNotPresentShouldAvoidNPE() {

        Map<Integer, List<Integer>> ds = Map.of(
                1, List.of(2, 3),
                2, List.of(4),
                3, List.of(4, 5),
                4, List.of(),
                5, List.of(6)
        );

        List<Integer> expected = List.of(2, 3, 4, 5, 6);
        Scripts scripts = new Scripts();
        List<Integer> actual = scripts.load(ds, 1);

        assertThat(actual, containsInAnyOrder(expected.toArray()));
    }

}