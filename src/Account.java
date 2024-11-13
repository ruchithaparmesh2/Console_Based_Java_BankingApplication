import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Account {
    private static final double INTEREST_RATE = 0.02;
    private static int accountCounter = 1000;  // Static counter to generate unique account numbers
    private int accountNumber;
    private String accountHolderName;
    private String accountType;
    private double balance;
    private ArrayList<Transactions> transactions;
    private LocalDate lastInterestDate;

    public Account(String accountHolderName, String accountType, double initialDeposit) {
        this.accountNumber = accountCounter++;
        this.accountHolderName = accountHolderName;
        this.accountType = accountType;
        this.balance = initialDeposit;
        this.transactions = new ArrayList<>();
        this.lastInterestDate = LocalDate.now().minusMonths(1);
        transactions.add(new Transactions("Deposit", initialDeposit));
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public String getAccountType() {
        return accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactions.add(new Transactions("Deposit", amount));
            System.out.println("Deposit successful. New balance: " + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactions.add(new Transactions("Withdrawal", amount));
            System.out.println("Withdrawal successful. New balance: " + balance);
        } else if (amount > balance) {
            System.out.println("Insufficient balance for this withdrawal.");
        } else {
            System.out.println("Invalid withdrawal amount.");
        }
    }
        public void getStatement()
        {
            if (transactions.isEmpty()) {
                System.out.println("No transactions found.");
            } else {
                System.out.println("\n--- Account Statement ---");
                System.out.println("Account Number: " + accountNumber);
                System.out.println("Account Holder: " + accountHolderName);
                System.out.println("Account Type: " + accountType);
                System.out.println("Balance: " + balance);
                System.out.println("\n--- Transaction History ---");

                for (Transactions transaction : transactions) {
                    System.out.println(transaction);  // This calls the toString method of Transactions class
                }
            }
        }
        public void checkBalance()
        {
            System.out.println("Your current Balance is : "+balance);
        }
    public void calculateInterest() {
        if (accountType.equals("savings")) {
            LocalDate currentDate = LocalDate.now();
            long monthsSinceLastInterest = ChronoUnit.MONTHS.between(lastInterestDate, currentDate);

            if (monthsSinceLastInterest >= 1) {
                double interest = balance * INTEREST_RATE * monthsSinceLastInterest;
                balance += interest;
                lastInterestDate = currentDate;
                transactions.add(new Transactions("Interest Added", interest));
                System.out.println("Interest of " + interest + " added to balance. New balance: " + balance);
            } else {
                System.out.println("Interest can only be added once per month.");
            }
        } else {
            System.out.println("Interest calculation is only applicable to savings accounts.");
        }
    }

    }

