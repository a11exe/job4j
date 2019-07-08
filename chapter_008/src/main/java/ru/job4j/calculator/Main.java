package ru.job4j.calculator;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 25.06.2019
 */
public class Main {

    public static void main(String[] args) {
//        InteractCalc calc = new InteractCalc(new ConsoleInput(), System.out::println);
//        while (!calc.isExit()) {
//            calc.next();
//        }

//        InteractCalc calc = new EngineerCalculator(new ConsoleInput(), System.out::println);
//        while (!calc.isExit()) {
//            calc.next();
//        }

        InteractCalc calc = new ThreeParametrCalculator(new ConsoleInput(), System.out::println);
        while (!calc.isExit()) {
            calc.next();
        }

    }

}
