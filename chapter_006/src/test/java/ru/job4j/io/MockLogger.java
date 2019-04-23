package ru.job4j.io;

import java.util.Iterator;
import java.util.List;

/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 24.04.2019
 */
public class MockLogger implements Logger {

    private final Iterator<String> questions;

    private final StringBuilder stringBuilder = new StringBuilder();
    private final String lp = System.lineSeparator();

    public MockLogger(List<String> questions) {
        this.questions = questions.iterator();
    }

    @Override
    public String readLine() {
        String str = null;
        if (questions.hasNext()) {
            str = questions.next();
            stringBuilder.append(str).append(lp);
        }
        return str;
    }

    @Override
    public void writeLine(String str) {
        stringBuilder.append(str).append(lp);
    }

    @Override
    public void writeLog() {

    }

    public String getLog() {
        System.out.println(stringBuilder);
        return stringBuilder.toString();
    }
}
