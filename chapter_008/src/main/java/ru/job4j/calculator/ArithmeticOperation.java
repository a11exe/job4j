package ru.job4j.calculator;

public interface ArithmeticOperation {

    Double execute(Double hold, Double input);

    CalculatorInputType nextInputType();
}
