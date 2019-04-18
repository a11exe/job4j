package ru.job4j.codeforces;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 18.04.2019
 */
public class FafaTest {

    @Test
    public void when10EmployeesShould3Variants() {
        Fafa fafa = new Fafa();
        assertThat(fafa.numberOptions(10), is(3));
    }

    @Test
    public void when2EmployeesShould1Variants() {
        Fafa fafa = new Fafa();
        assertThat(fafa.numberOptions(2), is(1));
    }

    @Test
    public void when1EmployeesShould0Variants() {
        Fafa fafa = new Fafa();
        assertThat(fafa.numberOptions(1), is(0));
    }

    @Test
    public void when0EmployeesShould0Variants() {
        Fafa fafa = new Fafa();
        assertThat(fafa.numberOptions(0), is(0));
    }

    @Test
    public void when20EmployeesShould4Variants() {
        Fafa fafa = new Fafa();
        assertThat(fafa.numberOptions(20), is(5));
    }

    @Test
    public void when15EmployeesShould3Variants() {
        Fafa fafa = new Fafa();
        assertThat(fafa.numberOptions(15), is(3));
    }

    @Test
    public void when100000EmployeesShould35Variants() {
        Fafa fafa = new Fafa();
        assertThat(fafa.numberOptions(100000), is(35));
    }

}