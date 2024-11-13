import java.util.Scanner;

public class BankingApp {

    private static User currentUser = null;  // Keeps track of the logged-in user

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n=== Welcome to the Console Banking Application ===");
            System.out.println("1. Register");
            System.out.println("2. Login --> If Registered Please login");
            System.out.println("3. Exit");
            System.out.print("Please choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    loginUser();
                    if (currentUser != null) {
                        userMenu();
                    }
                    break;
                case 3:
                    System.out.println("Thank you for using the Console Banking Application. Goodbye!");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private static void registerUser() {
        User userObj = new User();
        userObj.registerUser();
    }

    private static void loginUser() {
        User userObj = new User();
        if (userObj.login()) {  // Assuming login() returns true on successful login
            currentUser = userObj;  // Save the logged-in user
             BankingApp.userMenu();
        } else {
            System.out.println("Login failed. Please try again.");
            registerUser();
        }
    }

    static void userMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean logout = false;

        while (!logout) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Open New Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Generate Statement");
            System.out.println("5. Calculate Interest");
            System.out.println("6. Check Balance");
            System.out.println("7. Logout");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    openAccount();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    generateStatement();
                    break;
                case 5:
                    calculateInterest();
                    break;
                case 6:
                    checkBalance();
                    break;
                case 7:
                    System.out.println("Logging out...");
                    logout = true;
                    currentUser = null;  // Reset the logged-in user
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void openAccount() {
        if (currentUser == null) {
            System.out.println("No user is logged in.");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Account Holder's Name: ");
        String accountHolderName = scanner.nextLine();

        System.out.print("Enter Account Type (savings/checking): ");
        String accountType = scanner.nextLine();

        System.out.print("Enter Initial Deposit: ");
        double initialDeposit = scanner.nextDouble();

        Account newAccount = new Account(accountHolderName, accountType, initialDeposit);
        currentUser.addAccount(newAccount);

        System.out.println("Account created successfully with Account Number: " + newAccount.getAccountNumber());
        System.out.println("***** NOTE : PLEASE REMEMBER YOUR ACCOUNT NUMBER ***** ");
    }

    private static Account findAccountByNumber(int accountNumber) {
        if (currentUser == null) {
            System.out.println("No user is logged in.");
            return null;
        }

        for (Account account : currentUser.getUserAccounts()) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null;
    }

    private static void deposit() {
        if (currentUser == null) {
            System.out.println("No user is logged in.");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Account Number: ");
        int accountNumber = scanner.nextInt();

        Account account = findAccountByNumber(accountNumber);
        if (account != null) {
            System.out.print("Enter deposit amount: ");
            double amount = scanner.nextDouble();
            account.deposit(amount);
            System.out.println("Deposit successful.");
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void withdraw() {
        if (currentUser == null) {
            System.out.println("No user is logged in.");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Account Number: ");
        int accountNumber = scanner.nextInt();

        Account account = findAccountByNumber(accountNumber);
        if (account != null) {
            System.out.print("Enter withdrawal amount: ");
            double amount = scanner.nextDouble();
            account.withdraw(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void generateStatement() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Account Number: ");
        int accountNumber = scanner.nextInt();

        Account account = findAccountByNumber(accountNumber);
        if (account != null) {
            account.getStatement();  // Calls the getStatement method to display transaction history
        } else {
            System.out.println("Account not found.");
        }


    }

    private static void calculateInterest() {
        if (currentUser == null) {
            System.out.println("No user is logged in.");
            return;
        }
        else // Code to calculate and add interest
        {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter Account Number: ");
            int accountNumber = scanner.nextInt();

            Account account = findAccountByNumber(accountNumber);
            if (account != null) {
                account.calculateInterest();  // Calls the getStatement method to display transaction history
            } else {
                System.out.println("Account not found.");
            }
        }

    }

    private static void checkBalance() {
        // Code to check account balance
        if (currentUser == null) {
            System.out.println("No user is logged in.");
            return;
        }
        else
        {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter Account Number: ");
            int accountNumber = scanner.nextInt();

            Account account = findAccountByNumber(accountNumber);
            if (account != null) {
                account.checkBalance();  // Calls the getStatement method to display transaction history
            } else {
                System.out.println("Account not found.");
            }
        }


    }
}
