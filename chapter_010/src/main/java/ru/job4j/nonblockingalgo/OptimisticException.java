package ru.job4j.nonblockingalgo;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 24.09.2019
 */
public class OptimisticException extends RuntimeException {
    public OptimisticException(String message) {
        super(message);
    }
}
