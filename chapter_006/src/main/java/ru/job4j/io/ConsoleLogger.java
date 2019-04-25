package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * Log all console input and output
 *
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 24.04.2019
 */
public class ConsoleLogger implements Logger {

    private final Scanner scanner = new Scanner(System.in, System.getProperty("console.encoding", "utf-8"));
    private StringBuilder stringBuilder = new StringBuilder();
    private final String lp = System.lineSeparator();
    private final Path logFileName;
    private int gap = 0;
    private int count = 0;

    public ConsoleLogger(String logFileName) {
        this.logFileName = Paths.get(logFileName);
        this.stringBuilder.append(LocalDateTime.now()).append(lp);
    }

    public ConsoleLogger(String logFileName, int gap) {
        this(logFileName);
        this.gap = gap;
    }


    @Override
    public String readLine() {
        String str = scanner.nextLine();
        stringBuilder.append(str).append(lp);
        flush();
        return str;
    }

    @Override
    public void writeLine(String str) {
        stringBuilder.append(str).append(lp);
        flush();
        System.out.println(str);
    }

    @Override
    public void writeLog() {
        try {
            Files.write(logFileName, stringBuilder.toString().getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * write temp data to file
     * reset count
     */
    private void flush() {
        count++;
        if (gap != 0 && count >= gap) {
            writeLog();
            stringBuilder = new StringBuilder();
            count = 0;
        }
    }

}
