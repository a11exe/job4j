package ru.job4j.calculator;

/**
 * Accepted type operations
 * used for validation and info messages
 *
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 18.06.2019
 */
public enum CalculatorInputType {
    NUMBER("enter the number or 'exit' or 'reset'", "wrong data. enter the number"),
    OPERATION("enter calc operation or 'exit' or 'reset'", "wrong data. enter calc operation one from: ");

    private final String question;

    private final String errorMsg;

    CalculatorInputType(String question, String errorMsg) {
        this.question = question;
        this.errorMsg = errorMsg;
    }

    public String getQuestion() {
        return question;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
