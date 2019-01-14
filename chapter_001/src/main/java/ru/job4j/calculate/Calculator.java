package ru.job4j.calculate;

public class Calculator {

    private double result;

    /**
     * Method add save sum first & second in result.
     * @param first first arg
     * @param second second arg
     */
    public void add(double first, double second) {
        this.result = first + second;
    }

    /**
     * Method save subtract first & second in result
     * @param first first arg
     * @param second second arg
     */
    public void subtract(double first, double second) {
        this.result = first - second;
    }

    /**
     * Method save div first & second in result
     * @param first first arg
     * @param second second arg
     */
    public void div(double first, double second) {
        this.result = first / second;
    }

    /**
     * Method save multiple first & second in result
     * @param first first arg
     * @param second second arg
     */
    public void multiple(double first, double second) {
        this.result = first * second;
    }

    /**
     * Method getResult return result value.
     * @return result
     */
    public double getResult() {
        return this.result;
    }

}
