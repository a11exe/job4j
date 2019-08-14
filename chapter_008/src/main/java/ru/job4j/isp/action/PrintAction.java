package ru.job4j.isp.action;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 27.06.2019
 */
public class PrintAction implements MenuAction {

    @Override
    public void doAction() {
        System.out.println("some print action");
    }
}
