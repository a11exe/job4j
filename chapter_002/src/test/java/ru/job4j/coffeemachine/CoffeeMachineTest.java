package ru.job4j.coffeemachine;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 25.02.2019
 */
public class CoffeeMachineTest {

    @Test
    public void when50and35then10and15() throws NoCoinsForChange, NotEnoughMoney {
        CoffeeMachine coffeeMachine = new CoffeeMachine(1, 5, 2, 10);
        int[] expected = {10, 5};
        assertThat(coffeeMachine.changes(50, 35), is(expected));
    }

    @Test
    public void when50and35ThenOnly2and1() throws NoCoinsForChange, NotEnoughMoney {
        CoffeeMachine coffeeMachine = new CoffeeMachine(1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2);
        int[] expected = {2, 2, 2, 2, 2, 2, 2, 1};
        assertThat(coffeeMachine.changes(50, 35), is(expected));
    }

    @Test
    public void when50and35Then6for2and3for1() throws NoCoinsForChange, NotEnoughMoney {
        CoffeeMachine coffeeMachine = new CoffeeMachine(1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2);
        int[] expected = {2, 2, 2, 2, 2, 2, 1, 1, 1};
        assertThat(coffeeMachine.changes(50, 35), is(expected));
    }

    @Test
    public void when50and35Then3for5() throws NoCoinsForChange, NotEnoughMoney {
        CoffeeMachine coffeeMachine = new CoffeeMachine(1, 1, 1, 1, 1, 2, 2, 2, 5, 5, 5);
        int[] expected = {5, 5, 5};
        assertThat(coffeeMachine.changes(50, 35), is(expected));
    }

    @Test
    public void when35and35ThenNoChange() throws NoCoinsForChange, NotEnoughMoney {
        CoffeeMachine coffeeMachine = new CoffeeMachine(1, 1, 1, 1, 1, 2, 2, 2, 5, 5, 5);
        int[] expected = {};
        assertThat(coffeeMachine.changes(35, 35), is(expected));
    }

    @Test(expected = NoCoinsForChange.class)
    public void whenEmptyThenNoCoinsForChange() throws NoCoinsForChange, NotEnoughMoney {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        int[] expected = {5, 5, 5};
        assertThat(coffeeMachine.changes(50, 35), is(expected));
    }

    @Test(expected = NotEnoughMoney.class)
    public void when50and65ThenNotEnoughMoney() throws NoCoinsForChange, NotEnoughMoney {
        CoffeeMachine coffeeMachine = new CoffeeMachine(1, 1, 10, 10, 1, 10);
        int[] expected = {};
        assertThat(coffeeMachine.changes(50, 65), is(expected));
    }

    @Test(expected = NoCoinsForChange.class)
    public void when50and35ThenNoCoinsForChange() throws NoCoinsForChange, NotEnoughMoney {
        CoffeeMachine coffeeMachine = new CoffeeMachine(1, 1, 10, 10, 1, 10);
        int[] expected = {};
        assertThat(coffeeMachine.changes(50, 35), is(expected));
    }
}