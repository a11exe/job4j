package tracker;

import java.util.function.Consumer;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 04.02.2019
 */
public class StartUI {
    /**
     * Получение данных от пользователя.
     */
    private final Input input;

    /*
    * Вывод данных
     */
    private final Consumer<String> output;

    /**
     * Хранилище заявок.
     */
    private final Tracker tracker;

    /**
     * Конструтор инициализирующий поля.
     *
     * @param input   ввод данных.
     * @param tracker хранилище заявок.
     */
    public StartUI(Input input, Tracker tracker, Consumer<String> output) {
        this.input = input;
        this.tracker = tracker;
        this.output = output;
    }

    /**
     * Основой цикл программы.
     */
    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker, this.output);
        menu.fillActions();
        while (!menu.isExit()) {
            menu.show();
            menu.select(input.ask("select:", menu.range()));
        }
    }

    /**
     * Запуск программы.
     *
     * @param args параметры запуска.
     */
    public static void main(String[] args) {
        new StartUI(new ValidateInput(new ConsoleInput()), new Tracker(), System.out::println).init();
    }
}
