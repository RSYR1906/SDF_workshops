import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class BankAccount2 {

    private final String accHolderName;

    private Float accountBalance;

    private final String accNumber;

    private final List<String> transactionHistory;

    private boolean accClosed;

    private final Date accCreatedDate;

    private Date accDeletedDate;

    public BankAccount2(String accHolderName) {
        this.accHolderName = accHolderName;
        this.accountBalance = 0.0f;
        this.accNumber = generateAccNumber();
        this.transactionHistory = new ArrayList<>();
        this.accClosed = false;
        this.accCreatedDate = new Date();
        this.accDeletedDate = null;
    }

    public BankAccount2(String accHolderName, float initialAccBalance) {
        this(accHolderName);
        if (initialAccBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be a negative value");
        }

        this.accountBalance = initialAccBalance;

    }

    public String getAccHolderName() {
        return accHolderName;
    }

    public Float getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Float accountBalance) {
        this.accountBalance = accountBalance;
    }

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }

    public boolean isAccClosed() {
        return accClosed;
    }

    public void setAccClosed(boolean accClosed) {
        this.accClosed = accClosed;
    }

    public Date getAccCreatedDate() {
        return accCreatedDate;
    }

    public Date getAccDeletedDate() {
        return accDeletedDate;
    }

    public String getCurrentDate() {
        // Get the current date and time
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Define a custom format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm:ss");

        // Format the date and time
        String formattedDateTime = currentDateTime.format(formatter);

        return formattedDateTime;
    }

    public void setAccDeletedDate(Date accDeletedDate) {
        this.accDeletedDate = accDeletedDate;
    }

    public void deposit(float amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Initial balance cannot be a negative value");
        }

        accountBalance += amount;
        transactionHistory.add("Deposited " + amount + " at " + getCurrentDate());
    }

    public void withdraw(float amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Initial balance cannot be a negative value");
        }

        accountBalance -= amount;
        transactionHistory.add("Withdrawn " + amount + " at " + getCurrentDate());
    }

    private String generateAccNumber() {
        Random random = new Random();
        String randAccNumber = String.format("%09d", random.nextInt(1000000000));
        return randAccNumber;
    }

    public void closeAcc() {
        if (accClosed = false) {
            this.accClosed = true;
            this.accDeletedDate = new Date();
            transactionHistory.add("Account closed at " + getCurrentDate());
        }
    }

}
