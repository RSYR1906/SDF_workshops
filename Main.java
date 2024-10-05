public class Main {

    public static void main(String[] args) {

        BankAccount fredAccount = new BankAccount("Fred");

        fredAccount.deposit(1000.0f);
        fredAccount.withdraw(100.0f);

        FixedDepositAccount juneAccount = new FixedDepositAccount("June", 100.0f, 5.0f);

        juneAccount.displayAccDetails();

        

    }
}
