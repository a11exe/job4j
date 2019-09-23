package ru.job4j.jmm.race;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 20.09.2019
 */
public class BadBank implements Bank {

    private Integer balance;

    public BadBank(Integer balance) {
        this.balance = balance;
        System.out.println("===============================================");
        System.out.println("Bad bank. Gives out more money than there is");
        System.out.println(String.format("Balance $%s", this.balance));
    }

    @Override
    public boolean getMoney(int money) {
        boolean result = false;
        if (this.balance >= money) {
            String name = Thread.currentThread().getName();
            this.balance = this.balance - money;
            result = true;
            System.out.println(String.format("Bank get $%s from account to %s", money, name));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
