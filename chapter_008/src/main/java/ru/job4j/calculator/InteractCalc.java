package ru.job4j.calculator;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Interactive calculator
 *
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 18.06.2019
 */
public class InteractCalc {

    private Double hold;

    private boolean exit = false;

    private final Input input;

    private final Consumer<String> output;

    private final String exitCommand = "exit";

    class Return implements ArithmeticOperation {

        @Override
        public Double execute(Double hold, Double input) {
            return hold;
        }

        @Override
        public CalculatorInputType nextInputType() {
            return CalculatorInputType.OPERATION;
        }
    }

    class Reset implements ArithmeticOperation {

        @Override
        public Double execute(Double hold, Double input) {
            return 0D;
        }

        @Override
        public CalculatorInputType nextInputType() {
            return CalculatorInputType.OPERATION;
        }
    }

    class Add implements ArithmeticOperation {
        private final Calculator cal = new Calculator();

        @Override
        public Double execute(Double hold, Double input) {
            cal.add(hold, input);
            return cal.getResult();
        }

        @Override
        public CalculatorInputType nextInputType() {
            return CalculatorInputType.NUMBER;
        }
    }

    class Multiple implements ArithmeticOperation {
        private final Calculator cal = new Calculator();

        @Override
        public Double execute(Double hold, Double input) {
            cal.multiple(hold, input);
            return cal.getResult();
        }

        @Override
        public CalculatorInputType nextInputType() {
            return CalculatorInputType.NUMBER;
        }
    }

    class Subtract implements ArithmeticOperation {
        private final Calculator cal = new Calculator();

        @Override
        public Double execute(Double hold, Double input) {
            cal.subtract(hold, input);
            return cal.getResult();
        }

        @Override
        public CalculatorInputType nextInputType() {
            return CalculatorInputType.NUMBER;
        }
    }

    class Div implements ArithmeticOperation {
        private final Calculator cal = new Calculator();

        @Override
        public Double execute(Double hold, Double input) {
            cal.div(hold, input);
            return cal.getResult();
        }

        @Override
        public CalculatorInputType nextInputType() {
            return CalculatorInputType.NUMBER;
        }
    }

    /**
     * mapping calculator commands
     */
    private final Map<String, ArithmeticOperation> arithmeticOperations = new HashMap<>();

    private ArithmeticOperation actualOperation;

    private CalculatorInputType nextInputType;

    /**
     * Default constructor
     *
     * @param input  - input user data.
     * @param output - output data.
     */
    public InteractCalc(Input input, Consumer<String> output) {
        this.input = input;
        this.output = output;
        this.nextInputType = CalculatorInputType.NUMBER;
        loadOperations();
    }

    /**
     * Check if user enter exit command
     *
     * @return exit command
     */
    public boolean isExit() {
        return exit;
    }

    /**
     * Get next step of operation
     * write info to console5
     * read user input
     * validate input
     * execute operation
     */
    public void next() {
        boolean valid = false;
        while (!valid) {
            String answer = this.input.ask(nextInputType.getQuestion()).toLowerCase().trim();
            if (exitCommand.equals(answer)) {
                valid = exit();
            } else if (nextInputType == CalculatorInputType.NUMBER) {
                valid = getNumber(answer);
            } else {
                valid = getOperation(answer);
            }
        }
    }

    /**
     * Exit command.
     *
     * @return input valid
     */
    private boolean exit() {
        this.exit = true;
        return true;
    }

    /**
     * Get number from user input.
     * if input valid make operation.
     *
     * @param answer user input.
     * @return input valid.
     */
    private boolean getNumber(String answer) {
        boolean valid = true;
        try {
            Double number = Double.parseDouble(answer);
            if (this.actualOperation == null) {
                hold = number;
            } else {
                hold = actualOperation.execute(hold, number);
            }
            this.nextInputType = CalculatorInputType.OPERATION;
        } catch (NumberFormatException e) {
            this.output.accept(nextInputType.getErrorMsg());
            valid = false;
        }
        return valid;
    }

    /**
     * Get command from user input.
     * if command valid save operation to actualOperation.
     *
     * @param answer user input.
     * @return input valid.
     */
    private boolean getOperation(String answer) {

        boolean valid = arithmeticOperations.keySet().contains(answer);
        if (valid) {
            this.nextInputType = arithmeticOperations.get(answer).nextInputType();
            if (this.nextInputType == CalculatorInputType.OPERATION) {
                this.actualOperation = arithmeticOperations.get(answer);
                hold = actualOperation.execute(hold, 0D);
                this.output.accept(hold.toString());
                this.actualOperation = null;
            } else {
                this.actualOperation = arithmeticOperations.get(answer);
            }
        } else {
            this.output.accept(nextInputType.getErrorMsg());
            arithmeticOperations.keySet().forEach(this.output);
        }
        return valid;
    }

    protected void loadOperations() {
        this.arithmeticOperations.put("+", new Add());
        this.arithmeticOperations.put("-", new Subtract());
        this.arithmeticOperations.put("*", new Multiple());
        this.arithmeticOperations.put("/", new Div());
        this.arithmeticOperations.put("=", new Return());
        this.arithmeticOperations.put("reset", new Reset());
    }

    public void addOperation(String name, ArithmeticOperation operation) {
        this.arithmeticOperations.put(name, operation);
    }

}

