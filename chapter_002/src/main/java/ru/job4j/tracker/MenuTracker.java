package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Основное меню трекера.
 *
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 06.02.2019
 */
public class MenuTracker {

    private static final int ADD = 0;
    private static final int SHOW_ALL = 1;
    private static final int EDIT = 2;
    private static final int DELETE = 3;
    private static final int FIND_BY_ID = 4;
    private static final int FIND_BY_NAME = 5;
    private static final int EXIT = 6;

    /**
     * Признак выхода из меню
     */
    private boolean exit = false;

    /**
     * хранит ссылку на объект .
     */
    private final Input input;
    /**
     * хранит ссылку на объект .
     */
    private final Tracker tracker;
    /**
     * хранит ссылку на массив типа UserAction.
     */
    private final List<UserAction> actions = new ArrayList<>();

    /**
     * Конструктор.
     *
     * @param input   объект типа Input
     * @param tracker объект типа Tracker
     */
    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public boolean isExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    /**
     * Метод заполняет массив.
     */
    public void fillActions() {
        this.actions.add(new AddItem(ADD, "Add new item"));
        this.actions.add(new ShowItems(SHOW_ALL, "Show all items"));
        this.actions.add(new UpdateItem(EDIT, "Edit item"));
        this.actions.add(new DeleteItem(DELETE, "Delete item"));
        this.actions.add(new FindItemById(FIND_BY_ID, "Find item by Id"));
        this.actions.add(new FindItemsByName(FIND_BY_NAME, "Find items by name"));
        this.actions.add(new ExitProgram(EXIT, "Exit Program"));
    }

    /**
     * Метод в зависимости от указанного ключа, выполняет соотвествующие действие.
     *
     * @param key ключ операции
     */
    public void select(int key) {
        this.actions.get(key).execute(this.input, this.tracker);
    }

    /**
     * Метод выводит на экран меню.
     */
    public void show() {
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    public class AddItem implements UserAction {

        private final int key;
        private final String name;

        public AddItem(int key, String name) {
            this.key = key;
            this.name = name;
        }

        @Override
        public int key() {
            return key;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Adding new item --------------");
            String name = input.ask("Please, provide item name:");
            String desc = input.ask("Please, provide item description:");
            Item item = new Item(name, desc);
            tracker.add(item);
            System.out.println("------------ New Item with Id : " + item.getId());
            System.out.println("------------ New Item with Name : " + item.getName());
            System.out.println("------------ New Item with Description : " + item.getDesc());
        }

        @Override
        public String info() {
            return key() + ". " + this.name;
        }
    }

    private class ShowItems implements UserAction {

        private final int key;
        private final String name;

        public ShowItems(int key, String name) {
            this.key = key;
            this.name = name;
        }

        @Override
        public int key() {
            return this.key;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------Список всех заявок------------");
            Arrays.stream(tracker.findAll()).forEach(System.out::println);
        }

        @Override
        public String info() {
            return key() + ". " + this.name;
        }
    }

    private class UpdateItem implements UserAction {

        private final int key;
        private final String name;

        public UpdateItem(int key, String name) {
            this.key = key;
            this.name = name;
        }

        @Override
        public int key() {
            return this.key;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Редактирование заявки по номеру --------------");
            String id = input.ask("Введите номер заявки :");
            Item item = tracker.findById(id);
            if (item != null) {
                System.out.println(item);
                String name = input.ask("Введите новое имя заявки :");
                String desc = input.ask("Введите новое описание заявки :");
                Item itemNew = new Item(name, desc);
                itemNew.setId(item.getId());
                if (tracker.replace(id, itemNew)) {
                    System.out.println(actionMsg("Изменена", id));
                } else {
                    System.out.println(actionMsg("Не удалось изменить", id));
                }
            } else {
                System.out.println(actionMsg("Не найдена", id));
            }
        }

        @Override
        public String info() {
            return key() + ". " + this.name;
        }
    }

    private class DeleteItem implements UserAction {

        private final int key;
        private final String name;

        public DeleteItem(int key, String name) {
            this.key = key;
            this.name = name;
        }

        @Override
        public int key() {
            return this.key;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Удаление заявки по номеру --------------");
            String id = input.ask("Введите номер заявки :");
            if (tracker.delete(id)) {
                System.out.println(actionMsg("Удалена", id));
            } else {
                System.out.println(actionMsg("Ошибка удаления", id));
            }
        }

        @Override
        public String info() {
            return key() + ". " + this.name;
        }
    }

    private class FindItemById implements UserAction {

        private final int key;
        private final String name;

        public FindItemById(int key, String name) {
            this.key = key;
            this.name = name;
        }

        @Override
        public int key() {
            return this.key;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Поиск заявки по номеру --------------");
            String id = input.ask("Введите номер заявки :");
            Item item = tracker.findById(id);
            System.out.println((item == null ? actionMsg("Не найдена", id) : item));
        }

        @Override
        public String info() {
            return key() + ". " + this.name;
        }
    }

    private class FindItemsByName implements UserAction {

        private final int key;
        private final String name;

        public FindItemsByName(int key, String name) {
            this.key = key;
            this.name = name;
        }

        @Override
        public int key() {
            return this.key;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Поиск заявок по имени --------------");
            String name = input.ask("Введите имя заявки :");
            Arrays.stream(tracker.findByName(name)).forEach(System.out::println);
        }

        @Override
        public String info() {
            return key() + ". " + this.name;
        }
    }

    private class ExitProgram implements UserAction {

        private final int key;
        private final String name;

        public ExitProgram(int key, String name) {
            this.key = key;
            this.name = name;
        }

        @Override
        public int key() {
            return this.key;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            setExit(true);
        }

        @Override
        public String info() {
            return key() + ". " + this.name;
        }
    }

    /**
     * Конструктор ссобщений о результаты операции с заявкой.
     * @param action результат операции с заявкой.
     * @param id номер заявки.
     * @return сообщение о результате операции с заявкой.
     */
    private String actionMsg(String action, String id) {
        return action + " заявка с id: " + id;
    }
}
