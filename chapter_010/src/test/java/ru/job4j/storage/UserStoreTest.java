package ru.job4j.storage;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 23.09.2019
 */
public class UserStoreTest {

    @Test
    public void add() {
        UserStore userStore = new UserStore();
        User user1 = new User(1, 100);
        User user2 = new User(2, 200);
        User user3 = new User(1, 300);
        assertTrue(userStore.add(user1));
        assertTrue(userStore.add(user2));
        assertFalse(userStore.add(user3));
    }

    @Test
    public void update() {
        UserStore userStore = new UserStore();
        User user1 = new User(1, 100);
        User user2 = new User(2, 200);
        User user3 = new User(1, 300);
        assertTrue(userStore.add(user1));
        assertFalse(userStore.update(user2));
        assertTrue(userStore.update(user3));
    }

    @Test
    public void delete() {
        UserStore userStore = new UserStore();
        User user1 = new User(1, 100);
        User user2 = new User(2, 200);
        User user3 = new User(1, 300);
        assertTrue(userStore.add(user1));
        assertFalse(userStore.delete(user2));
        assertTrue(userStore.delete(user3));
    }

    @Test
    public void transfer() {
        UserStore userStore = new UserStore();
        User user1 = new User(1, 100);
        User user2 = new User(2, 200);
        assertFalse(userStore.transfer(4, 5, 300));
        assertTrue(userStore.add(user1));
        assertTrue(userStore.add(user2));
        assertFalse(userStore.transfer(1, 2, 500));
        assertTrue(userStore.transfer(1, 2, 100));
        assertFalse(userStore.transfer(1, 2, 100));
    }

    @Test
    public void transferConcurrency() throws InterruptedException {
        UserStore userStore = new UserStore();
        User user1 = new User(1, 1000);
        User user2 = new User(2, 1000);
        userStore.add(user1);
        userStore.add(user2);
        
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                userStore.transfer(1, 2, 1);
//                    System.out.println(String.format("transfer $%s from %s to %s", 1, 1, 2));
            }
        });
        thread1.join();


        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                userStore.transfer(2, 1, 1);
//                    System.out.println(String.format("transfer $%s from %s to %s", 1, 2, 1));
            }
        });

        thread2.join();

        thread1.start();
        thread2.start();

        assertTrue(userStore.transfer(1, 2, 100));
        assertTrue(userStore.transfer(2, 1, 200));
    }
}