package bank;

/**
 * Банковский счет.
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 27.02.2019
 */
public class Account {

    private double value;
    private final String requisites;

    public Account(double value, String requisites) {
        this.value = value;
        this.requisites = requisites;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getRequisites() {
        return requisites;
    }

    @Override
    public String toString() {
        return "Account{" + "value=" + value + ", requisites='" + requisites + '\'' + '}';
    }
}
