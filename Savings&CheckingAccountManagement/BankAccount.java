
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Represents a bank account that lets the user withdraw, deposit, create a saving and checking account.
 * It also lets the user view the cummary of the transactions and save the information to a external file.
 *
 * @author Nicholas Varin
 * @version 1.0
 * @since 10 Feb 2025
 */
public class BankAccount {

    private static char userChoice = 0;
    private static final int EXIT = 0;
    private int accountID;
    private double balance;
    private String customerName;
    private double annualInterestRate;
    private final Date dateCreated;
    private ArrayList<Transaction> transactions;
    private static Scanner scanner = new Scanner(System.in);

    public static BankAccount account = new BankAccount("George", 1122, 20000);

    /**
     * A no-arg constructor that creates a default account. The account ID is
     * set to 0, the balance is set to 0.0, and the annual interest rate is set
     * to 0.0. The date the account was created is set to the current date. It
     * also creates an empty list of transactions for the account and sets the
     * customer name to an empty string.
     *
     * @author Nicholas Varin
     * @version 1.0
     * @since 10 Feb 2025
     */
    public BankAccount() {
        this.accountID = 0;
        this.customerName = "";
        this.balance = 0.0;
        this.annualInterestRate = 0.0;
        dateCreated = new Date();
        this.transactions = new ArrayList<>();
    }

    /**
     * A constructor that creates an account with the specified accountID and
     * initial balance.
     *
     * * @author Nicholas Varin
     * @version 1.0
     * @since 10 Feb 2025
     *
     * @param accountID int: The account ID.
     * @param balance double: The initial balance of the account.
     * @param annualInterestRate double: The annual interest rate of the
     * account.
     * @param dateCreated Date: The date the account was created.
     */
    public BankAccount(String customerName, int accountID, double initialbalance) {
        transactions = new ArrayList<>();
        this.customerName = customerName;
        this.accountID = accountID;
        this.balance = initialbalance;
        this.annualInterestRate = 0.0;
        this.dateCreated = new Date();
        this.transactions = new ArrayList<>();
    }

    /*
   * The accessor and mutator methods for accountID, balance, customerName and
   * annualInterestRate.
   * The accessor method for dateCreated.
     */
    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;

    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    /**
     * The method getMonthlyInterestRate that returns the monthly interest rate
     * by dividing the annual interest rate by 100 to make it a percentage and
     * then divide by 12 to get monthly rate.
     *
     * @return double: The monthly interest rate
     * @author Nicholas Varin
     * @version 1.0
     * @since 10 Feb 2025
     */
    public double getMonthlyInterestRate() {
        return (annualInterestRate / 100.0) / 12.0;
    }

    /**
     * the method getMonthlyInterest that returns the monthly interest by
     * multiplying the balance by the monthly interest rate and then dividing by
     * 100
     *
     * @return double: The monthly interest rate
     *  * @author Nicholas Varin
     * @version 1.0
     * @since 10 Feb 2025
     */
    public double getMonthlyInterest() {
        return balance * getMonthlyInterestRate();
    }

    /**
     * Withdraws a specified amount from the account
     *
     * @param amount double: The amount to withdraw
     * @return boolean: True if the withdraw was successful, false if
     * insufficient balance
     * @author Nicholas Varin
     * @version 1.0
     * @since 10 Feb 2025
     */
    public boolean withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient balance");
            return false;
        } else {
            balance -= amount;
            // add transaction of a withdrawal
            Transaction transaction = new Transaction('W', amount, balance, "Bank Transaction");
            transactions.add(transaction);
            return true;

        }
    }

    /**
     * Deposits a specified amount into the account
     *
     * @param amount double: The amount to deposit
     * @author Nicholas Varin
     * @version 1.0
     * @since 10 Feb 2025
     */
    public void deposit(double amount) {
        balance += amount;
        // add transaction of a deposit
        Transaction transaction = new Transaction('D', amount, balance, "ATM transaction");
        if (amount >= 1) {
            transactions.add(transaction);
        } else {
            System.out.println("Must deposit $1 or more");
        }
    }

    /**
     * Prints a summary of the account, including the customer name, interest
     * rate, monthly interest, and all transactions.
     *
     * @return void
     * @author Nicholas Varin
     * @version 1.0
     * @since 10 Feb 2025
     */
    public void printSummary() {
        System.out.println("SUMMARY");
        System.out.println("Customer Name: " + customerName);
        System.out.println("Interest Rate: " + String.format("%.1f", getAnnualInterestRate()) + "%\n");
        System.out.println("Monthly Interest: " + String.format("%.2f", getMonthlyInterest()));
        System.out.println("Transactions:");
        System.out.printf("%-10s %-15s %-15s %-20s\n", "Type", "Amount", "Balance", "Description");
        for (Transaction transaction : transactions) {
            System.out.printf("%-10c $%-14.2f $%-14.2f %-20s\n", transaction.getTransactionType(), transaction.getAmount(), transaction.getBalance(), transaction.getDescription());
        }
    }

    /**
     * Shows the current balance of the account
     *
     * @return void
     * @author Nicholas Varin
     * @version 1.0
     * @since 10 Feb 2025
     */
    public void showBalance() {
        System.out.println("Balance: $" + balance);
    }

    /**
     * this method displays the date the account was created
     *
     * @return void
     * @author Nicholas Varin
     * @version 1.0
     * @since 10 Feb 2025
     */
    public void showDate() {
        System.out.println("Account created on: " + dateCreated);
    }

    /**
     * Main method to test the BankAccount class and Create a BankAccount object
     * with account name, account ID, and initialbalance. It also sets the
     * annual interest rate to 1.5% and it deposits $30, $40, and $50 into the
     * account. It then withdraws $5, $4, and $2 from the account. Finally, it
     * prints the summary of the account.
     *
     * @author Nicholas Varin
     * @version 1.0
     * @param args the command line arguments
     * @return void
     * @since 31 Jan 2025
     */
    public static void main(String[] args) {
        // Setting a test program with the annual interest rate
        account.setAnnualInterestRate(1.5);

        // Withdrawing $30, $40 and $50
        account.deposit(30.0);
        account.deposit(40.0);
        account.deposit(50.0);

        // Depositing $5,$4,$2
        account.withdraw(5.0);
        account.withdraw(4.0);
        account.withdraw(2.0);

        // Print the summary
        account.printSummary();

        // Run the application
        runApplication();
    }// End of method main

    /**
     * Saves the account to a file by writing the account details to the file
     *
     * @author Nicholas Varin
     * @version 1.0
     * @param filename the name of the file to save to
     * @return void
     * @since 10 Feb 2025
     */
    public void saveToFile(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("Account ID: " + accountID + "\n");
            writer.write("Customer Name: " + customerName + "\n");
            writer.write("Balance:" + balance + "\n");
            writer.write("Interest Rate: " + String.format("%.1f", getAnnualInterestRate()) + "%\n");
            writer.write("Monthly Interest: " + String.format("%.2f", getMonthlyInterest()) + "\n");
            writer.write("Date Created: " + dateCreated + "\n");
            writer.write(String.format("%-10s %-15s %-15s %-20s\n", "Type", "Amount", "Balance", "Description"));
            for (Transaction transaction : transactions) {
                writer.write(transaction.toString() + "\n");

            }

        } catch (IOException e) {
            // prints an error message when there is a problem saving to the file
            System.err.println("Error saving account to file: " + e.getMessage());
        }
    }

    /**
     * runApplication: This method displays the menu, get the user choice and
     * execute a valid operation. If user exits, it clears the console
     *
     * @author Nicholas Varin
     * @version 1.0
     * @param none
     * @return void
     * @since 10 Feb 2025
     */
    public static void runApplication() {
        do {
            clearConsole();
            displayMenu();
            getUserChoice();
            executeOperation();
        } while (userChoice != EXIT);
        account.saveToFile("AccountInfo.txt");
        clearConsole();
    } // End of method runApplication

    /**
     * displayMenu: This method display a simple menu, it gets the imput from
     * the user, and passess the user imput to the method that executes the
     * selected choice.
     *
     * @author Nicholas Varin
     * @version 1.0
     * @param none
     * @return void
     * @since 10 Feb 2025
     */
    public static void displayMenu() {
        System.out.println(" ___________________________________");
        System.out.println(" | Account Information |");
        System.out.println(" |SELECT ONE OF THE FOLLOWING CHOICES|");
        System.out.println(" |___________________________________|");
        System.out.println(" | 1. Create Savings Account |");
        System.out.println(" | 2. Create Checking Account |");
        System.out.println(" | 3. Deposit |");
        System.out.println(" | 4. Withdraw |");
        System.out.println(" | 5. Save account information to file |");
        System.out.println(" | 6. View Summary |");
        System.out.println(" | 0. Exit Program |");
        System.out.println(" |___________________________________|");
        System.out.print(" Enter your choice: ");
    } // End of method displayMenu

    /**
     * getUserChoice: This method gets the choice from the user as an integer
     * and stores it in the userChoice
     *
     * @author Nicholas Varin
     * @version 1.0
     * @param none
     * @return void
     * @since 10 Feb 2025
     */
    public static void getUserChoice() {
        
            userChoice = scanner.next().charAt(0);
            scanner.nextLine();
       
    } // End of method getUserChoice

    /**
     * executeOperation: This method executes the command that matches the case.
     *
     * @author Nicholas Varin
     * @version 1.0
     * @param none
     * @return void
     * @since 10 Feb 2025
     */
    public static void executeOperation() {
        // try (Scanner userInput = new Scanner(System.in)) {
            String customerName;
            int accountID;
            double initialbalance;
            switch (userChoice) {
                case '1' -> {
                    // Create Saving Account

                    while (true) {
                        System.out.println("Enter the customer name: ");
                        customerName = scanner.nextLine();
                        if (customerName.matches("^[a-zA-Z ]+$") && !customerName.trim().isEmpty()) {
                            break;
                        } else {
                            System.out.println("Invalid input. Please enter a valid name with only letters and spaces.");
                        }
                    }
                    try {
                        System.out.println("Enter the account ID: ");
                        accountID = scanner.nextInt();
                        System.out.println("Enter the initial balance: ");
                        initialbalance = scanner.nextDouble();
                        account = new SavingsAccount(customerName, accountID, initialbalance);
                        pressEnterToContinue();
                    }catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid number: ");
                        scanner.next();
                    }
                }
                case '2' -> {
                    // create checking account
                    while (true) {
                        System.out.println("Enter the customer name: ");
                        customerName = scanner.nextLine();
                        if (customerName.matches("^[a-zA-Z ]+$") && !customerName.trim().isEmpty()) {
                            break;
                        } else {
                            System.out.println("Invalid input. Please enter a valid name with only letters and spaces.");
                        }
                    }
                    try {
                        System.out.println("Enter the account ID: ");
                        accountID = scanner.nextInt();
                        System.out.println("Enter the initial balance: ");
                        initialbalance = scanner.nextDouble();
                        account = new SavingsAccount(customerName, accountID, initialbalance);
                        pressEnterToContinue();
                    }catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid number: ");
                        scanner.next();
                    }
                }
                case '3' -> {
                    // deposit
                    while (true) {
                        System.out.println("Enter the amount you want to deposit: ");
                        try {
                            double depositAmount = scanner.nextDouble();
                            account.deposit(depositAmount);
                            System.out.println("After deposit of $" + depositAmount + " your account is now at:");
                            account.showBalance();
                            scanner.nextLine();
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a valid number: ");
                            scanner.next();
                        }
                    }
                    account.showBalance();
                    pressEnterToContinue();
                }
                case '4' -> {
                    while (true) {
                        System.out.println("Enter the amount you want to withdraw: ");
                        try {
                            double withdrawAmount = scanner.nextDouble();
                            if (account.getBalance() - withdrawAmount < 0) {
                                System.out.println("Insufficient balance. You cannot withdraw this amount.");
                            } else {
                                account.withdraw(withdrawAmount);
                                System.out.println("After withdrawal of $" + withdrawAmount + " your account is now at:");
                                account.showBalance();
                                scanner.nextLine();
                                break;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a valid number: ");
                            scanner.next();
                        }
                    }
                    pressEnterToContinue();
                }
                case '5' -> {
                    System.out.println("Saving account to external file");
                    account.saveToFile("SavedAccount.txt");
                    pressEnterToContinue();
                }
                case '6' -> {
                    // View Summary
                    account.printSummary();
                    pressEnterToContinue();
                }
                case '0' -> {
                    System.out.println("Exiting Program and saving info to a file");
                    pressEnterToContinue();
                    System.exit(1);
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
            // withdraw from account
    } // End of method executeCommand



    /**
     * pressEnterToContinue: This method waits for the user to press the "ENTER"
     * key to continue the program execution. It uses the Scanner class to
     * capture the ENTER key from the user.
     *
     * @author Nicholas Varin
     * @version 1.0
     * @param none
     * @return void
     * @since 10 Feb 2025
     */
    public static void pressEnterToContinue() {
        // Use Scanner to get user input
        // Scanner userInput = new Scanner(System.in)) {
            System.out.println();
            System.out.println("Press \"ENTER\" to continue...");
            scanner.nextLine(); // Capture input without using it.
        //}
        clearConsole();
    } // End of method pressEnterToContinue

    /**
     * clearConsoleMac: This method clears the console.
     *
     * This function will work on ANSI terminals, demands POSIX.
     *
     * @author Nicholas Varin
     * @version 1.0
     * @param none
     * @return void
     * @since 10 Feb 2025
     */
    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    } // End of method clearConsoleMac
}// End of class Account
