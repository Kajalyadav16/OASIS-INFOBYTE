abstract class Transaction {
    protected double amount;
    protected String type;

    public Transaction(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return type + " $" + amount;
    }
}
