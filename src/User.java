import java.util.ArrayList;
import java.util.Scanner;

public  class User {
    String username;
    String password;
    static ArrayList<String> accounts = new ArrayList<>();  // Static to retain data across instances
    ArrayList<Account> userAccounts = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public  void registerUser() {
        System.out.print("Please enter the username you want to register: ");
        username = sc.next();

        if (accounts.contains(username)) {
            System.out.println("Sorry, this username already exists.");
            System.out.println("Try again with another username.");
            registerUser();  // Recursively prompts again if username exists
        } else {
            System.out.print("Please enter the password: ");
            password = sc.next();
            accounts.add(username);
            System.out.println(" **** User registered successfully.*** ");
            System.out.println("**** Now please login with register Username *****");

        }
    }

    public boolean login()
    {
        System.out.println("** Please login **");
        System.out.println("Please enter the registered Username:");
        String user=sc.next();

        if(accounts.contains(user))
        {
            System.out.println("login successful");
            return true;
        }
        else {
            System.out.println("Login failed due to Unregistered Username please Register again");
            return false;
        }
    }
    public void addAccount(Account account) {
        userAccounts.add(account);
    }

    public ArrayList<Account> getUserAccounts() {
        return userAccounts;
    }

}
