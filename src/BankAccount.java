import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Random;

public class BankAccount {
    private final String accountHolderName; // Read-only property
    private final String accountNumber; // Randomly generated account number
    private float accountBalance; // Account balance
    private final List<String> transactions; // List of transactions
    private boolean closed; // Indicates if the account is closed
    private final Date accountCreationDate; // Account creation date
    private Date accountClosingDate; // Account closing date (nullable)

    // Constructor for setting account holder's name only
    public BankAccount(String accountHolderName) {
        this.accountHolderName = accountHolderName;
        this.accountNumber = generateAccountNumber();
        this.accountBalance = 0.0f;
        this.transactions = new ArrayList<>();
        this.closed = false;
        this.accountCreationDate = new Date(); // Set to current date
        this.accountClosingDate = null; // Not set initially
    }

    // Constructor for setting account holder's name and initial balance
    public BankAccount(String accountHolderName, float initialBalance) {
        this(accountHolderName); // Call the first constructor
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative.");
        }
        this.accountBalance = initialBalance;
        transactions.add("Initial deposit of $" + initialBalance + " at " + getCurrentDateTime());
    }

    // Method to generate a random account number
    private String generateAccountNumber() {
        // Generate a random 10-digit account number
        Random random = new Random();
        return String.format("%010d", random.nextInt(1000000000));
    }

    // Method to deposit an amount
    public void deposit(float amount) {
        if (amount <= 0 || closed) {
            throw new IllegalArgumentException("Deposit amount must be positive and account must be open.");
        }
        accountBalance += amount;
        transactions.add("Deposit $" + amount + " at " + getCurrentDateTime());
    }

    // Method to withdraw an amount
    public void withdraw(float amount) {
        if (amount <= 0 || amount > accountBalance || closed) {
            throw new IllegalArgumentException("Invalid withdrawal amount or account is closed.");
        }
        accountBalance -= amount;
        transactions.add("Withdraw $" + amount + " at " + getCurrentDateTime());
    }

    // Method to close the account
    public void closeAccount() {
        if (!closed) {
            this.closed = true;
            this.accountClosingDate = new Date();
            transactions.add("Account closed at " + getCurrentDateTime());
        }
    }

    // Getters for read-only properties
    public String getAccountHolderName() {
        return accountHolderName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public float getAccountBalance() {
        return accountBalance;
    }

    public List<String> getTransactions() {
        return transactions;
    }

    public boolean isClosed() {
        return closed;
    }

    public Date getAccountCreationDate() {
        return accountCreationDate;
    }

    public Date getAccountClosingDate() {
        return accountClosingDate;
    }

    // Helper method to get the current date and time in a readable format
    private String getCurrentDateTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(new Date());
    }

    
}
