package ru.job4j.map;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 08.04.2019
 */
public class SimpleMapTest {

    @Test
    public void whenInsertValueByKeyShouldGetByKeySameValue() {
        SimpleMap<String, Integer> simpleMap = new SimpleMap<>();
        simpleMap.insert("ss", 5);
        assertThat(simpleMap.get("ss"), is(5));
    }

    @Test
    public void whenInsertOneKey2TimesShouldGetByKeyFirstValue() {
        SimpleMap<String, Integer> simpleMap = new SimpleMap<>();
        simpleMap.insert("ss", 5);
        assertFalse(simpleMap.insert("ss", 8));
        assertThat(simpleMap.get("ss"), is(5));
    }

    @Test
    public void whenGetByKeyDoesNotExistShouldNull() {
        SimpleMap<String, Integer> simpleMap = new SimpleMap<>();
        assertNull(simpleMap.get("ss"));
    }

    @Test
    public void whenAddMoreThan16ValuesShouldIncreaseSize() {
        SimpleMap<Integer, Integer> simpleMap = new SimpleMap<>();
        for (int i = 0; i < 16; i++) {
            simpleMap.insert(i, i);
        }
        assertTrue(simpleMap.insert(25, 25));
        assertThat(simpleMap.get(25), is(25));
    }

    @Test
    public void whenDeleteValueShouldGetNullByKey() {
        SimpleMap<String, Integer> simpleMap = new SimpleMap<>();
        assertTrue(simpleMap.insert("ss", 5));
        assertThat(simpleMap.get("ss"), is(5));
        assertTrue(simpleMap.delete("ss"));
        assertNull(simpleMap.get("ss"));
    }

    @Test
    public void whenDeleteValueNotPresentShouldFalse() {
        SimpleMap<String, Integer> simpleMap = new SimpleMap<>();
        assertFalse(simpleMap.delete("ss"));
    }

    @Test
    public void whenInsertKeyNullShouldGetValueByKey() {
        SimpleMap<Integer, Integer> simpleMap = new SimpleMap<>();
        assertTrue(simpleMap.insert(5, 5));
        assertTrue(simpleMap.insert(null, 7));
        assertTrue(simpleMap.insert(15, 15));
        assertThat(simpleMap.get(null), is(7));
    }


}