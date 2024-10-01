
public class FixedDepositAccount extends BankAccount {
    private float interest; // Interest rate
    private int duration; // Duration in months
    private boolean interestChanged; // Flag to track if interest has been changed
    private boolean durationChanged; // Flag to track if duration has been changed

    // Default values for interest and duration
    private static final float DEFAULT_INTEREST = 3.0f;
    private static final int DEFAULT_DURATION = 6;

    // Constructor with account holder's name and initial balance
    public FixedDepositAccount(String name, Float balance) {
        super(name, balance); // Call to the BankAccount constructor
        this.interest = DEFAULT_INTEREST;
        this.duration = DEFAULT_DURATION;
        this.interestChanged = false;
        this.durationChanged = false;
    }

    // Constructor with account holder's name, initial balance, and interest
    public FixedDepositAccount(String name, Float balance, Float interest) {
        this(name, balance); // Call to the first constructor
        setInterest(interest);
    }

    // Constructor with account holder's name, initial balance, interest, and
    // duration
    public FixedDepositAccount(String name, Float balance, Float interest, Integer duration) {
        this(name, balance, interest); // Call to the second constructor
        setDuration(duration);
    }

    // Method to set interest rate, allowing it to be set only once
    public void setInterest(float interest) {
        if (interestChanged) {
            throw new IllegalArgumentException("Interest can only be set once.");
        }
        this.interest = interest;
        this.interestChanged = true;
    }

    // Method to set duration, allowing it to be set only once
    public void setDuration(int duration) {
        if (durationChanged) {
            throw new IllegalArgumentException("Duration can only be set once.");
        }
        this.duration = duration;
        this.durationChanged = true;
    }

    // Override the deposit method (no operation)
    @Override
    public void deposit(float amount) {
        // No operation for Fixed Deposit
    }

    // Override the withdraw method (no operation)
    @Override
    public void withdraw(float amount) {
        // No operation for Fixed Deposit
    }

    // Override getAccountBalance to return balance plus interest
    @Override
    public float getAccountBalance() {
        return super.getAccountBalance() + interest;
    }

    // Getters for interest and duration
    public float getInterest() {
        return interest;
    }

    public int getDuration() {
        return duration;
    }
}
