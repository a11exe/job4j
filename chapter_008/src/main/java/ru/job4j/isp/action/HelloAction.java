package ru.job4j.isp.action;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 28.06.2019
 */
public class HelloAction implements MenuAction {

    @Override
    public void doAction() {
        System.out.println("hello");
    }
}
