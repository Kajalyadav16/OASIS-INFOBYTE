class Deposit extends Transaction {
    public Deposit(double amount) {
        super(amount);
        type = "Deposit";
    }
}
