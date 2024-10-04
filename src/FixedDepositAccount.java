public class FixedDepositAccount extends BankAccount {

    private Float interest;

    private int durationInMonths;

    private boolean interestChanged;

    private boolean monthsChanged;

    private static final Float DEFAULT_INTEREST = 3.0f;

    private static final int DEFAULT_MONTHS = 6;

    public FixedDepositAccount(String name, Float balance) {
        super(name, balance);
        this.interest = DEFAULT_INTEREST;
        this.durationInMonths = DEFAULT_MONTHS;
        this.interestChanged = false;
        this.monthsChanged = false;

    }

    public FixedDepositAccount(String name, Float balance, Float interest) {
        this(name, balance);
        setInterest(interest);
    }

    public FixedDepositAccount(String name, Float balance, Float interest, Integer duration) {
        this(name, balance, interest);
        setDurationInMonths(duration);
    }

    @Override
    public void deposit(float amount) {
        // no operations
    }

    @Override
    public Float getAccountBalance() {
        float accountBal = interest + super.getAccountBalance();
        return accountBal;
    }

    @Override
    public void withdraw(float amount) {
        // no operations
    }

    public Float getInterest() {
        return interest;
    }

    public void setInterest(Float interest) {
        if (interest < 0 || interestChanged) {
            throw new IllegalArgumentException("Interest cannot be a negative value");
        }
        this.interest = interest;
        interestChanged = true;
    }

    public int getDurationInMonths() {
        return durationInMonths;
    }

    public void setDurationInMonths(int durationInMonths) {
        if (durationInMonths < 0 || monthsChanged) {
            throw new IllegalArgumentException("duration cannot be a negative value");
        }
        this.durationInMonths = durationInMonths;
        monthsChanged = true;
    }

    public void displayAccDetails() {
        System.out.println("Account details");
        System.out.println("================");
        System.out.println("FixedD account name: " + this.getAccHolderName());
        System.out.println("FixedD account number: " + generateAccNumber());
        System.out.println("FixedD balance: " + this.getAccountBalance());
        System.out.println("FixedD interest rate: " + this.getInterest());
        System.out.println("FixedD months held: " + this.getDurationInMonths());
        System.out.println("FixedD created on: " + this.getAccCreatedDate());

    }

    public static void main(String[] args) {
        FixedDepositAccount fixedDepositAccount = new FixedDepositAccount("RYAN", 200f, DEFAULT_INTEREST);
        FixedDepositAccount fixedDepositAccountnew = new FixedDepositAccount("SALLY", 200f, 3.6f, 11);

        fixedDepositAccount.displayAccDetails();

        fixedDepositAccountnew.displayAccDetails();
    }

}
