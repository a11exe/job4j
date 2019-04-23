package ru.job4j.io;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;
import java.util.stream.Stream;


/**
 * Console chat
 *
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 24.04.2019
 */
public class ConsoleChat {

    private static final String STOP_WORD = "стоп";
    private static final String EXIT_WORD = "закончить";
    private static final String CONTINUE_WORD = "продолжить";
    private static final String ANSWERS_FILE_NAME = "consoleChatAnswers.txt";

    private final Logger logger;

    public ConsoleChat(Logger logger) {
        this.logger = logger;
    }

    public static void main(String[] args) {

        ConsoleChat consoleChat = new ConsoleChat(new ConsoleLogger("consoleChat.log", 5));
        consoleChat.init();
    }

    public void init() {
        boolean stop = false;
        Supplier<Stream<String>> streamSupplierLines = getStreamSupplierOfLines();
        long numOfLinesAnswers = streamSupplierLines.get().count();
        String str = logger.readLine();

        while (!EXIT_WORD.equals(str)) {

            stop = stop ? !CONTINUE_WORD.equals(str) : STOP_WORD.equals(str);
            if (!stop) {
                logger.writeLine(getAnswer(streamSupplierLines.get(), numOfLinesAnswers));
            }
            str = logger.readLine();
        }
        logger.writeLog();
    }


    /**
     * Cause IllegalStateException: stream has already been operated upon or closed.
     * @return stream of strings
     */
    private Supplier<Stream<String>> getStreamSupplierOfLines() {
        return () -> new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(ANSWERS_FILE_NAME), Charset.forName("UTF-8"))).lines();
    }

    /**
     * Return random line from file
     * @param lines stream of lines
     * @param numOfLines num of lines in file
     * @return random string
     */
    private String getAnswer(Stream<String> lines, long numOfLines) {
        long skip = ThreadLocalRandom.current().nextLong(0, numOfLines - 1);
        return lines.skip(skip).findFirst().orElse("Not found line");
    }

}
