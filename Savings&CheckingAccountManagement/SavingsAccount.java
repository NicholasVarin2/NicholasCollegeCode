/*
 * This class represents a savings account that inherits from the BankAccount class
 * It has a constructor that takes the customer name, accountID, and initial balance.
 * It also has a withdraw method that withdraws and a and deposit method that deposits to the savings account.
 * @return void
 *  @author Nicholas Varin
     * @version 1.0
     * @since 10 Feb 2025
 */
public class SavingsAccount extends BankAccount {

    public SavingsAccount(String customerName, int accountID, double initialBalance) {
        super(customerName, accountID, initialBalance);
    }

    /**
     * This method withdraws from the savings account. If the amount withdrawing
     * is more than the balance it displays an error mssage. Otherwise it
     * subtracts the amount from the balance and adds a transaction of a
     * withdrawal.
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
        if (amount > getBalance()) {
            System.out.println("Insufficient balance");
            return false;
        } else {
            setBalance(getBalance() - amount);
            super.getTransactions().add(new Transaction('W', amount, getBalance(), "Bank Transaction"));
            return true;
        }
    }
}
