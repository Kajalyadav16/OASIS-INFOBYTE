import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ATM {
    private List<User> users;
    private User currentUser;
    private Scanner scanner;

    public ATM() {
        users = new ArrayList<>();
        // Dummy users for demonstration purposes
        users.add(new User("123456", "7890", 1000.0)); 
        users.add(new User("987654", "4321", 500.0));
        scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Welcome to the ATM!");


        System.out.print("Enter User ID: ");
        String userID = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

      
        for (User user : users) {
            if (user.authenticate(userID, pin)) {
                currentUser = user;
                System.out.println("Authentication successful!");
                mainMenu();
                return;
            }
        }
        System.out.println("Invalid User ID or PIN. Exiting...");
    }

    private void mainMenu() {
        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Transaction History");
            System.out.println("6. Quit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    System.out.println("Your current balance is: $" + currentUser.getBalance());
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    // Implement transfer functionality
                    System.out.println("Transfer functionality is not implemented yet.");
                    break;
                case 5:
                    currentUser.printTransactionHistory();
                    break;
                case 6:
                    System.out.println("Thank you for using our ATM. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void deposit() {
        System.out.print("Enter the amount to deposit: $");
        double amount = scanner.nextDouble();
        currentUser.deposit(amount);
    }

    private void withdraw() {
        System.out.print("Enter the amount to withdraw: $");
        double amount = scanner.nextDouble();
        currentUser.withdraw(amount);
    }

    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.start();
    }
}
class User {
    private String userID;
    private String pin;
    private double balance;
    private List<Transaction> transactionHistory;

    public User(String userID, String pin, double balance) {
        this.userID = userID;
        this.pin = pin;
        this.balance = balance;
        transactionHistory = new ArrayList<>();
    }

    public boolean authenticate(String userID, String pin) {
        return this.userID.equals(userID) && this.pin.equals(pin);
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        Transaction transaction = new Deposit(amount);
        transactionHistory.add(transaction);
        System.out.println("Deposit successful. Your new balance is: $" + balance);
    }

    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient funds. Your current balance is: $" + balance);
            return;
        }
        balance -= amount;
        Transaction transaction = new Withdrawal(amount);
        transactionHistory.add(transaction);
        System.out.println("Withdrawal successful. Your new balance is: $" + balance);
    }

    public void printTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions yet.");
            return;
        }
        System.out.println("Transaction History:");
        for (Transaction transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }
}
