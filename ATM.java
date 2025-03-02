
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ATM {

    private static final Map<String, Account> accounts = new HashMap<>();
    private static Account currentAccount;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeAccounts();
        start();
    }

    private static void initializeAccounts() {
        accounts.put("1234", new Account("1234", "1234", 1000.0));
        accounts.put("5678", new Account("5678", "5678", 500.0));
        accounts.put("9012", new Account("9012", "9012", 2000.0));

    }

    private static void start() {
        System.out.println("Welcome to the ATM!");
        System.out.print("Enter your account number: ");
        String accountNumber = scanner.nextLine();

        currentAccount = accounts.get(accountNumber);

        if (currentAccount == null) {
            System.out.println("Invalid account number.");
            return;
        }

        System.out.print("Enter your PIN: ");
        String pin = scanner.nextLine();

        if (!currentAccount.validatePin(pin)) {
            System.out.println("Invalid PIN.");
            return;
        }

        showMenu();
    }

    private static void showMenu() {
        while (true) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM!");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void checkBalance() {
        System.out.println("Your balance is: $" + currentAccount.getBalance());
    }

    private static void deposit() {
        System.out.print("Enter the amount to deposit: $");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        if (amount > 0) {
            currentAccount.deposit(amount);
            System.out.println("Deposit successful. Your new balance is: $" + currentAccount.getBalance());
        } else {
            System.out.println("Invalid amount.");
        }
    }

    private static void withdraw() {
        System.out.print("Enter the amount to withdraw: $");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        if (amount > 0 && currentAccount.getBalance() >= amount) {
            currentAccount.withdraw(amount);
            System.out.println("Withdrawal successful. Your new balance is: $" + currentAccount.getBalance());
        } else {
            System.out.println("Insufficient funds or invalid amount.");
        }
    }

    static class Account {
        private String accountNumber;
        private String pin;
        private double balance;

        public Account(String accountNumber, String pin, double balance) {
            this.setAccountNumber(accountNumber);
            this.pin = pin;
            this.balance = balance;
        }

        public boolean validatePin(String pin) {
            return this.pin.equals(pin);
        }

        public double getBalance() {
            return balance;
        }

        public void deposit(double amount) {
            balance += amount;
        }

        public void withdraw(double amount) {
            balance -= amount;
        }

		public String getAccountNumber() {
			return accountNumber;
		}

		public void setAccountNumber(String accountNumber) {
			this.accountNumber = accountNumber;
		}
    }
}



