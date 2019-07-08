package ru.job4j.calculator;

import java.util.function.Consumer;

/**
 * Calculator with function using 3 parametr
 *
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 02.07.2019
 */
public class ThreeParametrCalculator extends InteractCalc {

    private Double holdFirst;
    private Double holdSecond;
    private Double holdThird;
    private Consumer<String> output;
    private Input input;

    public ThreeParametrCalculator(Input input, Consumer<String> output) {
        super(input, output);
        this.output = output;
        this.input = input;
    }

    public class SumThree implements ArithmeticOperation {

        @Override
        public Double execute(Double hold, Double input) {
            holdFirst = Double.parseDouble(ThreeParametrCalculator.this.input.ask("insert 1 parametr").toLowerCase().trim());
            holdSecond = Double.parseDouble(ThreeParametrCalculator.this.input.ask("insert 2 parametr").toLowerCase().trim());
            holdThird = Double.parseDouble(ThreeParametrCalculator.this.input.ask("insert 3 parametr").toLowerCase().trim());
            return (holdFirst + holdSecond + holdThird);
        }

        @Override
        public CalculatorInputType nextInputType() {
            return CalculatorInputType.OPERATION;
        }
    }


    @Override
    protected void loadOperations() {
        super.loadOperations();
        addOperation("sum3", new ThreeParametrCalculator.SumThree());
    }


}
