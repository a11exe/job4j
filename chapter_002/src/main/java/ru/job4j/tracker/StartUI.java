package ru.job4j.tracker;

import java.util.Arrays;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 04.02.2019
 */
public class StartUI {
    /**
     * Константа меню для добавления новой заявки.
     */
    private static final String ADD = "0";

    /**
     * Константа меню для вывода всех заявок.
     */
    private static final String SHOW_ALL = "1";

    /**
     * Константа меню для редактирования заявки.
     */
    private static final String EDIT = "2";

    /**
     * Константа меню для удаления заявки.
     */
    private static final String DELETE = "3";

    /**
     * Константа меню для поиска заявки по id.
     */
    private static final String FIND_BY_ID = "4";

    /**
     * Константа меню для поиска заявок по имени.
     */
    private static final String FIND_BY_NAME = "5";

    /**
     * Константа для выхода из цикла.
     */
    private static final String EXIT = "6";

    /**
     * Константы для вывода пунктов меню
     */
    private static final String[] MENU_ITEMS = {ADD, SHOW_ALL, EDIT, DELETE, FIND_BY_ID, FIND_BY_NAME, EXIT};
    private static final String[] MENU_ITEMS_NAMES = {"Add new Item", "Show all items", "Edit item", "Delete item", "Find item by Id", "Find items by name", "Exit Program"};

    /**
     * Получение данных от пользователя.
     */
    private final Input input;

    /**
     * Хранилище заявок.
     */
    private final Tracker tracker;

    /**
     * Конструтор инициализирующий поля.
     * @param input ввод данных.
     * @param tracker хранилище заявок.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Основой цикл программы.
     */
    public void init() {
        boolean exit = false;
        while (!exit) {
            this.showMenu();
            String answer = this.input.ask("Введите пункт меню : ");
            switch (answer) {
                case ADD :
                  this.createItem();
                  break;
                case SHOW_ALL :
                    this.showAllItems();
                    break;
                case EDIT :
                    this.editItem();
                    break;
                case DELETE :
                    this.deleteItem();
                    break;
                case FIND_BY_ID :
                    this.findById();
                    break;
                case FIND_BY_NAME :
                    this.findByName();
                    break;
                case EXIT :
                    exit = true;
                    break;
                    default:
                        break;
            }
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

    /**
     * Редактирование заявки
     */
    private void editItem() {
        System.out.println("------------ Редактирование заявки по номеру --------------");
        String id = this.input.ask("Введите номер заявки :");
        Item item = tracker.findById(id);
        if (item != null) {
            System.out.println(item);
            String name = this.input.ask("Введите новое имя заявки :");
            String desc = this.input.ask("Введите новое описание заявки :");
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

    /**
     * Удаление заявки
     */
    private void deleteItem() {
        System.out.println("------------ Удаление заявки по номеру --------------");
        String id = this.input.ask("Введите номер заявки :");
        if (tracker.delete(id)) {
            System.out.println(actionMsg("Удалена", id));
        } else {
            System.out.println(actionMsg("Ошибка удаления", id));
        }
    }

    /**
     * Поиск заявки по id
     */
    private void findById() {
        System.out.println("------------ Поиск заявки по номеру --------------");
        String id = this.input.ask("Введите номер заявки :");
        Item item = tracker.findById(id);
        System.out.println((item == null ? actionMsg("Не найдена", id) : item));
    }

    /**
     * Поиск заявки по имени
     */
    private void findByName() {
        System.out.println("------------ Поиск заявок по имени --------------");
        String name = this.input.ask("Введите имя заявки :");
        Arrays.stream(tracker.findByName(name)).forEach(System.out::println);
    }

    /**
     * Вывод всех заявок
     */
    private void showAllItems() {
        System.out.println("------------Список всех заявок------------");
        Arrays.stream(tracker.findAll()).forEach(System.out::println);
    }

    /**
     * Метод реализует добавленяи новый заявки в хранилище.
     */
    private void createItem() {
        System.out.println("------------ Добавление новой заявки --------------");
        String name = this.input.ask("Введите имя заявки :");
        String desc = this.input.ask("Введите описание заявки :");
        Item item = new Item(name, desc);
        this.tracker.add(item);
        System.out.println(actionMsg("Добавлена", item.getId()));
    }

    /**
     * Вывод пунктов основного меню
     */
    private void showMenu() {
        System.out.println("Меню.");
        for (int i = 0; i < MENU_ITEMS.length; i++) {
            System.out.println(MENU_ITEMS[i] + ". " + MENU_ITEMS_NAMES[i]);
        }
    }

    /**
     * Запускт программы.
     * @param args параметры запуска.
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}
