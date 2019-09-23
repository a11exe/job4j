package ru.job4j.jmm.race;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 20.09.2019
 */
public class Client implements Runnable  {

    private Bank bank;

    public Client(Bank bank) {
        this.bank = bank;
    }

    public void run() {
        getMoney(20);
    }

    public void getMoney(int money) {

        while (bank.getMoney(money)) {
        }
    }
}
