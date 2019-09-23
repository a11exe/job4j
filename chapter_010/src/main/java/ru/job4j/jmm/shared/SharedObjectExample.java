/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 22.09.2019
 */
package ru.job4j.jmm.shared;

public class SharedObjectExample extends Thread {

    boolean keepRunning = true;

    public void run() {
        while (keepRunning) {
        }

        System.out.println("Thread terminated.");
    }

    public static void main(String[] args) throws InterruptedException {
        SharedObjectExample t = new SharedObjectExample();
        t.start();
        Thread.sleep(1000);
        t.keepRunning = false;
        System.out.println("keepRunning set to false.");
    }
}
