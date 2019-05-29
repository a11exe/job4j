package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

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

    /*
    * вывод данных
     */
    private final Consumer<String> output;

    /**
     * хранит ссылку на объект .
     */
    private final ITracker tracker;
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
    public MenuTracker(Input input, ITracker tracker, Consumer<String> output) {
        this.input = input;
        this.tracker = tracker;
        this.output = output;
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

    public List<Integer> range() {
        return this.actions.stream().mapToInt(UserAction::key).boxed().collect(Collectors.toList());
    }


    /**
     * Метод выводит на экран меню.
     */
    public void show() {
        for (UserAction action : this.actions) {
            if (action != null) {
                this.output.accept(action.info());
            }
        }
    }

    public class AddItem extends BaseAction {

        public AddItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, ITracker tracker) {
            output.accept("------------ Adding new item --------------");
            String name = input.ask("Please, provide item name:");
            String desc = input.ask("Please, provide item description:");
            Item item = new Item(name, desc);
            tracker.add(item);
            output.accept("------------ New Item with Id : " + item.getId());
            output.accept("------------ New Item with Name : " + item.getName());
            output.accept("------------ New Item with Description : " + item.getDesc());
        }
    }

    private class ShowItems extends BaseAction {
        public ShowItems(int key, String name) {
            super(key, name);
        }
        @Override
        public void execute(Input input, ITracker tracker) {
            output.accept("------------Список всех заявок------------");
            tracker.findAll().forEach(System.out::println);
        }
    }

    private class UpdateItem extends BaseAction {

        public UpdateItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, ITracker tracker) {
            output.accept("------------ Редактирование заявки по номеру --------------");
            String id = input.ask("Введите номер заявки :");
            Item item = tracker.findById(id);
            if (item != null) {
                output.accept(item.toString());
                String name = input.ask("Введите новое имя заявки :");
                String desc = input.ask("Введите новое описание заявки :");
                Item itemNew = new Item(name, desc);
                itemNew.setId(item.getId());
                if (tracker.replace(id, itemNew)) {
                    output.accept(actionMsg("Изменена", id));
                } else {
                    output.accept(actionMsg("Не удалось изменить", id));
                }
            } else {
                output.accept(actionMsg("Не найдена", id));
            }
        }
    }

    private class DeleteItem extends BaseAction {
        public DeleteItem(int key, String name) {
            super(key, name);
        }
        @Override
        public void execute(Input input, ITracker tracker) {
            output.accept("------------ Удаление заявки по номеру --------------");
            String id = input.ask("Введите номер заявки :");
            if (tracker.delete(id)) {
                output.accept(actionMsg("Удалена", id));
            } else {
                output.accept(actionMsg("Ошибка удаления", id));
            }
        }
    }

    private class FindItemById extends BaseAction {
        public FindItemById(int key, String name) {
            super(key, name);
        }
        @Override
        public void execute(Input input, ITracker tracker) {
            output.accept("------------ Поиск заявки по номеру --------------");
            String id = input.ask("Введите номер заявки :");
            Item item = tracker.findById(id);
            output.accept((item == null ? actionMsg("Не найдена", id) : item.toString()));
        }
    }

    private class FindItemsByName extends BaseAction {
        public FindItemsByName(int key, String name) {
            super(key, name);
        }
        @Override
        public void execute(Input input, ITracker tracker) {
            output.accept("------------ Поиск заявок по имени --------------");
            String name = input.ask("Введите имя заявки :");
            tracker.findByName(name).forEach(s->output.accept(s.toString()));
        }
    }

    private class ExitProgram extends BaseAction {
        public ExitProgram(int key, String name) {
            super(key, name);
        }
        @Override
        public void execute(Input input, ITracker tracker) {
            setExit(true);
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
