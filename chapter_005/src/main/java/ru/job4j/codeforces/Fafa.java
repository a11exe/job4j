package ru.job4j.codeforces;

import java.util.Scanner;

/**
 * Количество вариантов разделения n сотрудников
 * на руководителей и подчиненных
 *
 * http://codeforces.com/contest/935/problem/A
 *
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 18.04.2019
 */
public class Fafa {

    static int startManagers;
    static int startWorkers;

    /**
     * Return number variants
     * distribution of employees to managers and subordinates
     * @param workersCount count employees for test
     * @return number of variants
     */
    public static int numberOptions(int workersCount) {

        startManagers = 2;
        startWorkers = 0;

        int numberOptions = 0;
        if (workersCount > 1) {
            log(1, workersCount - 1);
            numberOptions++;
        }

        startWorkers = workersCount / 2;

        for (int managers = startManagers; managers <= workersCount / 2; managers++) {
            for (int workers = startWorkers; workers > 1; workers--) {
                if (workers * managers + managers == workersCount) {
                    numberOptions++;
                    log(managers, workers);
                    startManagers = managers;
                    startWorkers = workers;
                }
            }
        }

        if (workersCount > 2 && workersCount % 2 == 0) {
            numberOptions++;
            log(workersCount / 2, 1);
        }

        return numberOptions;
    }

    private static void log(int managers, int workers) {
        //System.out.println("managers: " + managers + " workers: " + workers);
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        int workersCount = Integer.parseInt(str);
        System.out.println(numberOptions(workersCount));

    }



}
