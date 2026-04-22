/*
 * This class represents a checking account that inherits from the BankAccount class
 * It has a constructor that takes the customer name, accountID, and initial balance
 * It also has a withdraw method that withdraws and a and deposit method that deposits to the savings account.
 * @return void
 *  @author Nicholas Varin
     * @version 1.0
     * @since 10 Feb 2025
 */
public class CheckingAccount extends BankAccount {

    private final double overdraftLimit;

    /*
 * The constructor for the CheckingAccount class
 * 
 * @param customerName String: The name of the customer
 * @param accountID int: The account ID
 * @param initialBalance double: The initial balance    
    * @author Nicholas Varin
    * @version 1.0
    * @since 10 Feb 2025
    * 
     */
    public CheckingAccount(String customerName, int accountID, double initialBalance) {
        super(customerName, accountID, initialBalance);
        this.overdraftLimit = 200.0;
    }

    /**
     * This method withdraws a epecified amount from the checking account. If
     * the amount exceds the total of the balance and withdrawl limit it then
     * prints an error message, otherwise it withdraws the specified amount the
     * user selects.
     *
     * @param amount double: The amount to withdraw
     * @return boolean: True if the withdraw was successful, false if
     * insufficient balance
     * @author Nicholas Varin
     * @version 1.0
     * @since 10 Feb 2025
     */
    @Override
    public boolean withdraw(double amount) {
        if (amount > getBalance() + overdraftLimit) {
            System.out.println("You went over your balance and overdraft amount");
            return false;
        } else {
            setBalance(getBalance() - amount);
            super.getTransactions().add(new Transaction('W', amount, super.getBalance(), "Bank Transaction"));
            return true;
        }
    }

}
