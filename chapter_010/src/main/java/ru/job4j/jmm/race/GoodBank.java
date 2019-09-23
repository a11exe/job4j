package ru.job4j.jmm.race;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 20.09.2019
 */
public class GoodBank implements Bank {

    private Integer balance;

    public GoodBank(Integer balance) {
        this.balance = balance;
        System.out.println("===============================================");
        System.out.println("Good bank. All operations synchronized");
        System.out.println(String.format("Balance $%s", this.balance));
    }

    @Override
    public synchronized boolean getMoney(int money) {
        boolean result = false;
        if (this.balance >= money) {
            String name = Thread.currentThread().getName();
            this.balance = this.balance - money;
            result = true;
            System.out.println(String.format("get $%s from account by %s", money, name));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
