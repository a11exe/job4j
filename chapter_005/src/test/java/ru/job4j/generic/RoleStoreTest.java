package ru.job4j.generic;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class RoleStoreTest {

    @Test
    public void thenAdd1Get1ShouldBeSame() {
        RoleStore roleStore = new RoleStore(5);
        Role role = new Role("dd");
        roleStore.add(role);
        assertThat(roleStore.findById("dd"), is(role));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenAdd3ThenSizeStore2ShouldException() {
        RoleStore roleStore = new RoleStore(2);
        Role role1 = new Role("dd1");
        Role role2 = new Role("dd2");
        Role role3 = new Role("dd3");
        roleStore.add(role1);
        roleStore.add(role2);
        roleStore.add(role3);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenAdd3DeleteIndex2GetSecondShouldException() {
        RoleStore roleStore = new RoleStore(2);
        Role role1 = new Role("dd1");
        Role role2 = new Role("dd2");
        Role role3 = new Role("dd3");
        roleStore.add(role1);
        roleStore.add(role2);
        roleStore.add(role3);
        assertTrue(roleStore.delete("dd2"));
        roleStore.findById("dd2");
    }

    @Test
    public void whenDeleteNotAddedShouldFalse() {
        RoleStore roleStore = new RoleStore(2);
        Role role1 = new Role("dd1");
        Role role3 = new Role("dd3");
        roleStore.add(role1);
        roleStore.add(role3);
        assertFalse(roleStore.delete("dd2"));
    }

    @Test
    public void whenDeleteEmptyArrayShouldFalse() {
        RoleStore roleStore = new RoleStore(0);
        assertFalse(roleStore.delete("dd2"));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenFindByIdNotPresentShouldException() {
        RoleStore roleStore = new RoleStore(2);
        Role role2 = new Role("dd2");
        Role role3 = new Role("dd3");
        roleStore.add(role2);
        roleStore.add(role3);
        roleStore.findById("dd4");
    }

    @Test(expected = NoSuchElementException.class)
    public void whenReplaceAndGetReplacedShouldException() {
        RoleStore roleStore = new RoleStore(3);
        Role role1 = new Role("dd1");
        Role role2 = new Role("dd2");
        Role role3 = new Role("dd3");
        Role role4 = new Role("dd4");
        roleStore.add(role1);
        roleStore.add(role2);
        roleStore.add(role3);
        assertTrue(roleStore.replace("dd3", role4));
        assertThat(roleStore.findById("dd4"), is(role4));
        roleStore.findById("dd3");
    }

    @Test
    public void whenReplaceIdNotPresentShouldFalse() {
        RoleStore roleStore = new RoleStore(3);
        Role role1 = new Role("dd1");
        Role role4 = new Role("dd4");
        roleStore.add(role1);
        assertFalse(roleStore.replace("dd3", role4));
    }

}