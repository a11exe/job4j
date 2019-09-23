package ru.job4j.jmm.race;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 20.09.2019
 */
public class RaceConditionExample {

    public static void main(String[] args) {

        RaceConditionExample raceConditionExample = new RaceConditionExample();
        raceConditionExample.process();

    }

    public void process() {

        Bank bank = new BadBank(100);

        Client client = new Client(bank);

        new Thread(client, "client in first filial").start();
        new Thread(client, "client in second filial").start();

    }

}
