package ru.job4j.io;

/**
 * Log all input and output
 *
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 24.04.2019
 */
public interface Logger {

    String readLine();

    void writeLine(String str);

    void writeLog();

}
