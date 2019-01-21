package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 21.01.2019
 */
public class ArrayDuplicateTest {

    @Test
    public void remove() {
        ArrayDuplicate arrayDuplicate = new ArrayDuplicate();
        assertThat(arrayDuplicate.remove(new String[]{"Привет", "Мир", "Привет", "Супер", "Мир"}), is(new String[]{"Привет", "Мир", "Супер"}));
    }

    @Test
    public void removeNoDuplicates() {
        ArrayDuplicate arrayDuplicate = new ArrayDuplicate();
        assertThat(arrayDuplicate.remove(new String[]{"Привет", "Мир"}), is(new String[]{"Привет", "Мир"}));
    }

    @Test
    public void remove3Duplicates() {
        ArrayDuplicate arrayDuplicate = new ArrayDuplicate();
        assertThat(arrayDuplicate.remove(new String[]{"Привет", "Мир", "Мир", "Мир"}), is(new String[]{"Привет", "Мир"}));
    }

    @Test
    public void removeOnlyDuplicates() {
        ArrayDuplicate arrayDuplicate = new ArrayDuplicate();
        assertThat(arrayDuplicate.remove(new String[]{"Мир", "Мир", "Мир"}), is(new String[]{"Мир"}));
    }

}