package ru.job4j.bomberman;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 30.09.2019
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {

        Board board = new Board(2);

        Hero hero1 = new Hero(board, new Cell(0, 0), "hero1");
        Hero hero2 = new Hero(board, new Cell(1, 0), "hero2");

        Thread thread1 = new Thread(hero1, hero1.getName());
        Thread thread2 = new Thread(hero2, hero2.getName());

        thread1.start();
        thread2.start();


        Thread.sleep(15000);
        thread1.interrupt();
        thread2.interrupt();

    }
}
