package ru.job4j.isp;

import ru.job4j.isp.action.MenuAction;

/**
 * Menu with actions
 *
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 27.06.2019
 */
public class ActionMenu extends Menu {

    private final MenuAction action;

    public ActionMenu(String name, MenuAction action) {
        super(name);
        this.action = action;
    }

    /**
     * do action
     */
    public void doAction() {
        if (action != null) {
            action.action();
        } else {
            System.out.println("no action");
        }
    }
}
