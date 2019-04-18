package ru.job4j.codeforces;

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
class Fafa {

    private int startManagers = 2;
    private int startWorkers = 0;

    /**
     * Return number variants
     * distribution of employees to managers and subordinates
     * @param workersCount count employees for test
     * @return number of variants
     */
    public int numberOptions(int workersCount) {
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

    private void log(int managers, int workers) {
        System.out.println("managers: " + managers + " workers: " + workers);
    }



}
