package peer;
// TODO убрать пустую строку

public class Account {

    double values; // TODO не понятно почему во множественном числе, хранится одно значение, возможно лучше value
    String reqs; // TODO не понятное название переменной, возможно лучше requisites

    public Account(double values, String requisites) {
        this.values = values;
        this.reqs = requisites;
    }

    public double getValues() {
        return this.values;
    }
	// TODO убрать пустую строку

    public String getReqs () {
        return this.reqs;
    }

    boolean transfer(Account destination, double amount) {
        boolean success = false;
        if (amount > 0 && amount < this.values && destination != null) {
            success = true;
            this.values -= amount;
            destination.values += amount;
        }
        return success;
    }

    public String toString() {
        String otvet;
        otvet = "Account{" + "values=" + values + ", reqs='" + reqs + "\\" + "}"; 
        return otvet; // TODO лучше заменить все одной строкой return "Account{" + "values=" + values + ", reqs='" + reqs + "\\" + "}"; 
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
// TODO убрать пустую строку
        Account account = (Account) o;
// TODO убрать пустую строку
        return this.reqs.equals(account.reqs);
    }

    public int hashCode() {
        return this.reqs.hashCode(); // TODO возможно лучше return Objects.hash(this.reqs);
    }
}