package ru.job4j.jmm.race;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 20.09.2019
 */
public class RaceConditionSolution {

    public static void main(String[] args) {

        RaceConditionSolution raceConditionExample = new RaceConditionSolution();
        raceConditionExample.process();

    }

    public void process() {

        Bank bank = new GoodBank(100);

        Client client = new Client(bank);

        new Thread(client, "client in first filial").start();
        new Thread(client, "client in second filial").start();

    }

}
